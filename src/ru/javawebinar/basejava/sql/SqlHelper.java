package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public interface BlocOfCode<T> {
        T executes(PreparedStatement ps) throws SQLException;
    }

    public interface SqlTransaction<T> {
        T execute(Connection conn) throws SQLException;
    }

    public <T> T execute(String sql, BlocOfCode<T> blocOfCode) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            return blocOfCode.executes(preparedStatement);
        } catch (SQLException e) {
            throw convertException(e);
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.execute(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                throw convertException(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    private StorageException convertException(SQLException e) {
        if (e.getSQLState().equals("23505")) {
            return new ExistStorageException(null);
        } else {
            return new StorageException(e);
        }
    }
}
