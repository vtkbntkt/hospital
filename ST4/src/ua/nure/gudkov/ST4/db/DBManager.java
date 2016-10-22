package ua.nure.gudkov.ST4.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.exception.DAOException;
import ua.nure.gudkov.ST4.exception.DBException;

/**
 * Data base manager implementation.
 * 
 * @author A.Gudkov
 *
 */

public class DBManager {
	private static final Logger LOG = Logger.getLogger(DBManager.class);
	private DataSource dataSource;

	public DBManager(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public <T> T execute(Operation<T> transaction) throws DBException {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			ConnectionHolder.setConnection(connection);
			T value = transaction.execute();
			connection.commit();
			return value;
			// throw new
			// DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);

		} catch (SQLException | DAOException ex) {
			LOG.error(Constants.Messages.ERR_CANNOT_EXECUTE_OPERATION, ex);
			rollback(connection);
			throw new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		} finally {
			close(connection);
			ConnectionHolder.removeConnection();
		}
		// return null;
	}

	private void rollback(Connection connection) {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				LOG.error(Constants.Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex);
			}
		}
	}

	private void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				LOG.error(Constants.Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}
}
