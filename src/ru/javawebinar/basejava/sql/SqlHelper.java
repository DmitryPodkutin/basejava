package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;

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

    public <T> T execute(String sql, BlocOfCode<T> blocOfCode) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            return blocOfCode.executes(preparedStatement);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(null);
            } else try {
                throw new SQLException(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
