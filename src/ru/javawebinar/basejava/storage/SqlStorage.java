package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;
import util.JsonParser;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteContacts(resume, conn);
            deleteSection(resume, conn);
            saveContacts(resume, conn);
            saveSection(resume, conn);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            saveContacts(resume, conn);
            saveSection(resume, conn);
            return null;
        });
    }
    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume  WHERE uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet resultSet = ps.executeQuery();
                if (!resultSet.next()) {
                    throw new NotExistStorageException(uuid);
                } else {
                    resume = new Resume(uuid, resultSet.getString("full_name"));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact  WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    addContact(resultSet, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section  WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    addSection(resultSet, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume  WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name,uuid")) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    String full_name = resultSet.getString("full_name");
                    map.put(uuid, new Resume(uuid, full_name));
                }
            }
                doGet("SELECT * FROM contact",map, conn, this::addContact);
                doGet("SELECT * FROM section",map, conn, this::addSection);
            return new ArrayList<>(map.values());
        });
    }

    private interface BlocOfCode {
        void execute(ResultSet rs, Resume r) throws SQLException;
    }

    private void doGet(String sql, Map<String, Resume> map, Connection conn , BlocOfCode blocOfCode) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String resume_uuid = resultSet.getString("resume_uuid");
                Resume resume = map.get(resume_uuid);
                blocOfCode.execute(resultSet, resume);
            }
        }
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*)  FROM resume", ps -> {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        });
    }

    private void addContact(ResultSet resultSet, Resume resume) throws SQLException {
        String value = resultSet.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(resultSet.getString("type"));
            resume.addContact(type, value);
        }
    }

    private void addSection(ResultSet resultSet, Resume resume) throws SQLException {
        String description = resultSet.getString("description");
        SectionType type = SectionType.valueOf(resultSet.getString("type"));
        resume.addSection(type, JsonParser.read(description, Section.class));
    }

    private void  saveContacts(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO  contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void saveSection(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO  section (resume_uuid,type, description) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : resume.getSections().entrySet()) {
                Section section = e.getValue();
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, JsonParser.write(section,Section.class));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Resume resume, Connection conn) throws SQLException {
        doDelete(resume, conn, "DELETE FROM contact  WHERE resume_uuid = ?");
    }

    private void deleteSection(Resume resume, Connection conn) throws SQLException {
        doDelete(resume, conn, "DELETE FROM section  WHERE resume_uuid = ?");

    }

    private void doDelete(Resume resume, Connection conn, String s) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(s)) {
            ps.setString(1, resume.getUuid());
            ps.executeUpdate();
        }
    }
}

