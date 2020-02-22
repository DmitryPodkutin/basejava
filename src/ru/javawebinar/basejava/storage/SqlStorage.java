package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
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
            try (PreparedStatement ps = conn.prepareStatement("UPDATE contact SET (resume_uuid, type, value) VALUES (?,?,?)")) {
                deleteContacts(resume);
                saveContacts(resume, ps);
            }
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
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                        saveContacts(resume, ps);
                    }
                    return null;
                }
        );
    }

    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        " SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "     ON r.uuid = c.resume_uuid " +
                        "  WHERE r.uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet resultSet = ps.executeQuery();
                    if (!resultSet.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, resultSet.getString("full_name"));
                    do {
                        addContact(resultSet, resume);
                    } while (resultSet.next());
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
        return sqlHelper.execute(
                "SELECT * FROM resume r " +
                        "LEFT JOIN contact c " +
                        "ON r.uuid = c.resume_uuid " +
                        "ORDER BY full_name,uuid",
                ps -> {
                    ResultSet resultSet = ps.executeQuery();
                    Map<String, Resume> map = new LinkedHashMap<>();

                    while (resultSet.next()) {
                        String uuid = resultSet.getString("uuid");
                        String full_name = resultSet.getString("full_name");
//                        Resume resume = map.computeIfAbsent(uuid,u-> new Resume(u, resultSet.getString(("full_name"))));
                        Resume resume = map.computeIfAbsent(uuid, u -> new Resume(u, full_name));
                       // https://ru.stackoverflow.com/questions/916032/Рефакторинг-кода-используя-computeifabsent
                        addContact(resultSet, resume);
                    }
                    return new ArrayList<>(map.values());
                    //https://overcoder.net/q/1963/как-преобразовать-карту-в-список-в-java
                });
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

    private void saveContacts(Resume resume, PreparedStatement ps) throws SQLException {
        for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, e.getKey().name());
            ps.setString(3, e.getValue());
            ps.addBatch();
        }
        ps.executeBatch();
    }
    private void deleteContacts(Resume resume){
        sqlHelper.execute("DELETE FROM contact  WHERE resume_uuid = ?", ps -> {
                ps.setString(1, resume.getFullName());
                ps.execute();
            return null;
        });
    }
}

