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
import ua.nure.gudkov.ST4.dao.api.UserDAO;
import ua.nure.gudkov.ST4.db.ConnectionHolder;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DAOException;

public class MySqlUserDAO implements UserDAO {

	private static final Logger LOG = Logger.getLogger(MySqlUserDAO.class);

	@Override
	public User findByEmail(String email) throws DAOException {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_USER_BY_EMAIL_QUERY);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL);
		} finally {
			close(pstmt);
			close(rs);
		}
		return user;
		// throw new
		// DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL);
	}

	@Override
	public User findById(int idUser) throws DAOException {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_USER_BY_ID_QUERY);
			pstmt.setInt(1, idUser);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_USER_BY_ID);
		} finally {
			close(pstmt);
			close(rs);
		}
		return user;
		// throw new
		// DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL);
	}

	@Override
	public List<User> findByIdRole(int idRole) throws DAOException {
		List<User> userList = new ArrayList<User>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_USERS_BY_IDROLE_QUERY);
			pstmt.setInt(1, idRole);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userList.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_USERS_BY_IDROLE);
		} finally {
			close(pstmt);
			close(rs);
		}
		return userList;

	}

	@Override
	public boolean insertUser(User user) throws DAOException {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.ADD_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
			fillUserStatement(pstmt, user);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getLong(1));
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

	@Override
	public List<User> findAllUsers() throws DAOException {
		List<User> userList = new ArrayList<User>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_ALL_USERS_QUERY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userList.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_ALL_USERS, ex);

		} finally {
			close(rs);
			close(pstmt);
		}
		return userList;
	}

	/**
	 * Puts user entity into the prepared statement.
	 * 
	 * @param preparedStatement
	 *            PreparedStatement
	 * @param user
	 *            user entity
	 */
	private void fillUserStatement(PreparedStatement preparedStatement, User user) throws SQLException {
		int index = 1;
		preparedStatement.setString(index++, user.getEmail());
		preparedStatement.setString(index++, user.getPassword());
		preparedStatement.setInt(index++, user.getRoleId());
		preparedStatement.setString(index++, user.getLastName());
		preparedStatement.setString(index++, user.getFirstName());
		preparedStatement.setString(index++, user.getPhone());
		preparedStatement.setDate(index++, user.getDateOfBirth());
		preparedStatement.setInt(index++, user.getCategoryId());
	}

	/**
	 * Extracts user entity from resultset.
	 * 
	 * @param resultSet
	 * @return user
	 */
	private User extractUser(ResultSet resultSet) throws SQLException {
		int index = 1;
		User user = new User();
		user.setId(resultSet.getLong(index++));
		user.setEmail(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
		user.setRoleId(resultSet.getInt(index++));
		user.setLastName(resultSet.getString(index++));
		user.setFirstName(resultSet.getString(index++));
		user.setPhone(resultSet.getString(index++));
		user.setDateOfBirth(resultSet.getDate(index++));
		user.setCategoryId(resultSet.getInt(index++));
		return user;
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
