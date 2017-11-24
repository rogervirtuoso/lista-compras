package io.github.rogervirtuoso.listacompras.java.backend.core;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Roger
 */
public interface ConexaoJDBC {

    public Connection getConnection();

    public void close();

    public void commit() throws SQLException;

    public void rollback();
}
