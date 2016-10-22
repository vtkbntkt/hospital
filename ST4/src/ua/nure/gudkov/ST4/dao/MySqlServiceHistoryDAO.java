package ua.nure.gudkov.ST4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.dao.api.ServiceHistoryDAO;
import ua.nure.gudkov.ST4.db.ConnectionHolder;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.exception.DAOException;

/**
 * MySQL serviceHistory DAO implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MySqlServiceHistoryDAO implements ServiceHistoryDAO {
	private static final Logger LOG = Logger.getLogger(MySqlServiceHistoryDAO.class);

	@Override
	public List<ServiceHistory> findAllByIdAnamnesis(int idAnamnesis) throws DAOException {
		List<ServiceHistory> userList = new ArrayList<ServiceHistory>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_ALLSERVHISTORY_BY_IDANAMNESIS);
			pstmt.setInt(1, idAnamnesis);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userList.add(extractServiceHistory(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_ALL_SERVHISTORY_BY_IDANAMNESIS, ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return userList;
	}

	@Override
	public List<ServiceHistory> findAllByIdStatus(int idStatus) throws DAOException {
		List<ServiceHistory> userList = new ArrayList<ServiceHistory>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_ALLSERVHISTORY_BY_IDSTATUS);
			pstmt.setInt(1, idStatus);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userList.add(extractServiceHistory(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_ALL_SERVHISTORY_BY_IDSTATUS, ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return userList;
	}

	@Override
	public ServiceHistory findByIdServiceHistory(int idServHistory) throws DAOException {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ServiceHistory servHistory = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_SERVHISTORY_BY_ID);
			pstmt.setInt(1, idServHistory);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				servHistory = extractServiceHistory(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_SERVHISTORY_BY_ID);
		} finally {
			close(pstmt);
			close(rs);
		}
		return servHistory;
		// throw new
		// DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL);
	}

	@Override
	public boolean updateServiceStatus(int idStatus, int idServHistory) throws DAOException {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.UPDATE_SERVICE_STATUS_QUERY);
			pstmt.setInt(1, idStatus);
			pstmt.setInt(2, idServHistory);
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_UPDATE_SERVICE_STATUS);
		} finally {
			close(pstmt);
		}
		return res;
	}

	@Override
	public boolean insertServHistory(ServiceHistory servHistory) throws DAOException {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.ADD_SERVICE_HISTORY_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			fillServHistoryStatement(pstmt, servHistory);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					servHistory.setIdRecord(rs.getInt(1));
					res = true;
				}
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_INSERT_USER);
		} finally {
			close(rs);
			close(pstmt);
		}
		return res;
		// throw new DAOException(Constants.Messages.ERR_CANNOT_INSERT_USER);
	}

	/**
	 * Puts srviceHistory entity into the prepared statement.
	 * 
	 * @param preparedStatement
	 *            PreparedStatement
	 * @param servHistory
	 *            the srviceHistory
	 */
	private void fillServHistoryStatement(PreparedStatement preparedStatement, ServiceHistory servHistory)
			throws SQLException {
		int index = 1;
		preparedStatement.setDate(index++, servHistory.getDateRecord());
		preparedStatement.setInt(index++, servHistory.getIdAnamnesis());
		preparedStatement.setInt(index++, servHistory.getIdService());
		preparedStatement.setString(index++, servHistory.getServiceValue());
		preparedStatement.setInt(index++, servHistory.getIdStatus());
	}

	/**
	 * Extracts srviceHistory from resultset.
	 * 
	 * @param resultSet
	 *            ResultSet
	 * @return srviceHistory
	 */
	private ServiceHistory extractServiceHistory(ResultSet resultSet) throws SQLException {
		int index = 1;
		ServiceHistory serviceHistory = new ServiceHistory();
		serviceHistory.setIdRecord(resultSet.getInt(index++));
		serviceHistory.setDateRecord(resultSet.getDate(index++));
		serviceHistory.setIdAnamnesis(resultSet.getInt(index++));
		serviceHistory.setIdService(resultSet.getInt(index++));
		serviceHistory.setServiceValue(resultSet.getString(index++));
		serviceHistory.setIdStatus(resultSet.getInt(index++));
		return serviceHistory;
	}

	/**
	 * Close resultset.
	 * 
	 * @param rs
	 *            ResultSet
	 */
	private void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			LOG.error(Constants.Messages.ERR_CANNOT_CLOSE_RESULTSET, e);
		}

	}

	/**
	 * Close statement.
	 * 
	 * @param stmt
	 *            Statement
	 */
	private void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			LOG.error(Constants.Messages.ERR_CANNOT_CLOSE_STATEMENT, e);
		}

	}

}
