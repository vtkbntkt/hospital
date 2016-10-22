package ua.nure.gudkov.ST4.db;

import java.sql.Connection;

/**
 * Thread local connection holder implementation.
 * 
 * @author A.Gudkov
 *
 */
public class ConnectionHolder {
	private static final ThreadLocal<Connection> threadLocalScope = new ThreadLocal<>();

	public static void setConnection(Connection connection) {
		threadLocalScope.set(connection);
	}

	public static Connection getConnection() {
		return threadLocalScope.get();
	}

	public static void removeConnection() {
		threadLocalScope.remove();
	}

}
