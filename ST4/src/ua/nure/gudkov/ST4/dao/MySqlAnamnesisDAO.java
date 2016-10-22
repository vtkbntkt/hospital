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
import ua.nure.gudkov.ST4.dao.api.AnamnesisDAO;
import ua.nure.gudkov.ST4.db.ConnectionHolder;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.exception.DAOException;

/**
 * MySQL anamnesis DAO implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MySqlAnamnesisDAO implements AnamnesisDAO {

	private static final Logger LOG = Logger.getLogger(MySqlAnamnesisDAO.class);

	@Override
	public List<Anamnesis> findAll() throws DAOException {
		List<Anamnesis> anamnesisList = new ArrayList<Anamnesis>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_ALL_ANAMNESIS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				anamnesisList.add(extractAnamnesis(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_ALL_ANAMNESIS, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return anamnesisList;
	}

	@Override
	public List<Anamnesis> findAllByIdStatus(int idStatus) throws DAOException {
		List<Anamnesis> anamnesisList = new ArrayList<Anamnesis>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_ALL_ANAMNESIS_BY_IDSTATUS);
			pstmt.setInt(1, idStatus);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				anamnesisList.add(extractAnamnesis(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_ALL_ANAMNESIS_BY_IDSTATUS, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return anamnesisList;
	}

	@Override
	public List<Anamnesis> findAllByIdStatusAndIdPatient(int idStatus, long idPatient) throws DAOException {
		List<Anamnesis> anamnesisList = new ArrayList<Anamnesis>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_ALL_BY_IDSTATUS_AND_IDPATIENT);
			pstmt.setInt(1, idStatus);
			pstmt.setLong(2, idPatient);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				anamnesisList.add(extractAnamnesis(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_ALL_ANAMNESIS_BY_IDSTATUS_AND_IDPATIENT, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return anamnesisList;
	}
	
	@Override
	public List<Anamnesis> findAllByIdStatusAndIdDoctor(int idStatus, long idDoctor) throws DAOException {
		List<Anamnesis> anamnesisList = new ArrayList<Anamnesis>();
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_ALL_BY_IDSTATUS_AND_IDDOCTOR);
			pstmt.setInt(1, idStatus);
			pstmt.setLong(2, idDoctor);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				anamnesisList.add(extractAnamnesis(rs));
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_ALL_ANAMNESIS_BY_IDSTATUS_AND_IDDOCTOR, ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return anamnesisList;
	}

	@Override
	public Anamnesis findByIdAnamnesis(int idAnamnesis) throws DAOException {
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Anamnesis anamnes = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.FIND_ANAMNESIS_BY_IDANAMNESIS);
			pstmt.setInt(1, idAnamnesis);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				anamnes = extractAnamnesis(rs);
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_ANAMNESIS_BY_IDANAMNESIS);
		} finally {
			close(pstmt);
			close(rs);
		}
		return anamnes;
		// throw new
		// DAOException(Constants.Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL);
	}

	@Override
	public boolean insertAnamnesis(Anamnesis anamnesis) throws DAOException {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.ADD_ANAMNESIS_QUERY, Statement.RETURN_GENERATED_KEYS);
			fillAnamnesisStatement(pstmt, anamnesis);
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					anamnesis.setIdanamnesis((rs.getInt(1)));
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
	public boolean updateInitialDiagnosis(String diagnosis, int idAnamnesis) throws DAOException {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.UPDATE_INITIAL_DIAGNOSIS_QUERY);
			pstmt.setString(1, diagnosis);
			pstmt.setInt(2, idAnamnesis);
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_UPDATE_INITIAL_DIAGNOSIS);
		} finally {
			close(pstmt);
		}
		return res;
	}

	@Override
	public boolean updateFinalDiagnosis(String diagnosis, int idAnamnesis) throws DAOException {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.UPDATE_FINAL_DIAGNOSIS_QUERY);
			pstmt.setString(1, diagnosis);
			pstmt.setInt(2, idAnamnesis);
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_UPDATE_FINAL_DIAGNOSIS);
		} finally {
			close(pstmt);
		}
		return res;
	}

	@Override
	public boolean updateAnamnesisStatus(int idStatus, int idAnamnesis) throws DAOException {
		boolean res = false;
		Connection connection = ConnectionHolder.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(Constants.Query.UPDATE_ANAMNESIS_STATUS_QUERY);
			pstmt.setInt(1, idStatus);
			pstmt.setInt(2, idAnamnesis);
			if (pstmt.executeUpdate() > 0) {
				res = true;
			}
		} catch (SQLException ex) {
			LOG.warn(ex.getMessage());
			throw new DAOException(Constants.Messages.ERR_CANNOT_UPDATE_ANAMNESIS_STATUS);
		} finally {
			close(pstmt);
		}
		return res;
	}

	/**
	 * Puts anamnesis entity into the prepared statement.
	 * 
	 * @param preparedStatement PreparedStatement
	 * @param anamnesis anamnesis
	 */
	private void fillAnamnesisStatement(PreparedStatement preparedStatement, Anamnesis anamnesis) throws SQLException {
		int index = 1;
		preparedStatement.setDate(index++, anamnesis.getCreationDate());
		preparedStatement.setInt(index++, anamnesis.getIdPatient());
		preparedStatement.setString(index++, anamnesis.getInitialDiagnosis());
		preparedStatement.setInt(index++, anamnesis.getIdDoctor());
		preparedStatement.setString(index++, anamnesis.getFinalDiagnosis());

	}

	/**
	 * Extracts anamnesis from resultset.
	 * 
	 * @param resultSet ResultSet
	 * @return anamnesis
	 */
	private Anamnesis extractAnamnesis(ResultSet resultSet) throws SQLException {
		int index = 1;
		Anamnesis anamnesis = new Anamnesis();
		anamnesis.setIdanamnesis(resultSet.getInt(index++));
		anamnesis.setCreationDate(resultSet.getDate(index++));
		anamnesis.setIdPatient(resultSet.getInt(index++));
		anamnesis.setInitialDiagnosis(resultSet.getString(index++));
		anamnesis.setIdDoctor(resultSet.getInt(index++));
		anamnesis.setFinalDiagnosis(resultSet.getString(index++));
		anamnesis.setIdStatus(resultSet.getInt(index++));
		return anamnesis;
	}

	/**
	 * Close resultset.
	 * 
	 * @param rs ResultSet
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
