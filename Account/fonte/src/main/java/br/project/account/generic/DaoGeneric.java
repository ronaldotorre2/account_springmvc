package br.project.account.generic;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Project Account
 * @author Ronaldo Torre
 * @param <E>
 */
public abstract class DaoGeneric<E extends Serializable> {

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/accountdb?useUnicode=yes&characterEncoding=utf-8";
    private static String USER = "esys";
    private static String PASSWD = "gerente2016";
    
    @SuppressWarnings("unused")
	private Connection con;
    
    public final Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWD);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
}