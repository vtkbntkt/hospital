package ua.nure.gudkov.ST4.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.dao.api.AnamnesisDAO;
import ua.nure.gudkov.ST4.db.DBManager;
import ua.nure.gudkov.ST4.db.Operation;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.Status;
import ua.nure.gudkov.ST4.exception.DAOException;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;

/**
 * Anamnesis service implementation.
 * 
 * @author A.Gudkov
 *
 */
public class AnamnesisServiceImpl implements AnamnesisService {

	private AnamnesisDAO anamnesisDAO;
	private DBManager dbManager;
	private static final Logger LOG = Logger.getLogger(AnamnesisServiceImpl.class);

	public AnamnesisServiceImpl(AnamnesisDAO anamnesisDAO, DBManager dbManager) {
		this.anamnesisDAO = anamnesisDAO;
		this.dbManager = dbManager;
		LOG.debug("Anamnesis service was successfully initialized");
	}

	@Override
	public List<Anamnesis> findAllAnamnesis() throws DBException {
		return dbManager.execute(new Operation<List<Anamnesis>>() {
			@Override
			public List<Anamnesis> execute() throws DAOException {
				return anamnesisDAO.findAll();
			}

		});
	}

	@Override
	public boolean verifyByIdPatient(int idPatient) throws DBException {
		return findNewByIdPatient(idPatient) != null;
	}

	@Override
	public boolean addAnamnesis(Anamnesis anamnesis) throws DBException {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() throws DAOException {
				return anamnesisDAO.insertAnamnesis(anamnesis);
			}
		});
	}

	@Override
	public Anamnesis findNewByIdPatient(int idPatient) throws DBException {
		List<Anamnesis> anamnesisList = dbManager.execute(new Operation<List<Anamnesis>>() {
			@Override
			public List<Anamnesis> execute() throws DAOException {
				return anamnesisDAO.findAllByIdStatusAndIdPatient(Status.NEW.ordinal(), idPatient);
			}

		});
		if (anamnesisList.isEmpty()) {
			return null;
		}
		return anamnesisList.get(0);
	}

	@Override
	public Anamnesis findByIdAnamnesis(int idAnamnesis) throws DBException {
		return dbManager.execute(new Operation<Anamnesis>() {
			@Override
			public Anamnesis execute() throws DAOException {
				return anamnesisDAO.findByIdAnamnesis(idAnamnesis);
			}

		});
	}

	@Override
	public boolean updateInitialDiagnosis(String diagnosis, int idAnamnesis) throws DBException {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() throws DAOException {
				return anamnesisDAO.updateInitialDiagnosis(diagnosis, idAnamnesis);
			}

		});
	}

	@Override
	public boolean dischargePatient(String finalDiagnos, int idAnamnesis) throws DBException {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() throws DAOException {
				return anamnesisDAO.updateFinalDiagnosis(finalDiagnos, idAnamnesis)
						&& anamnesisDAO.updateAnamnesisStatus(Status.DISCHARGED.ordinal(), idAnamnesis);
			}

		});
	}

	@Override
	public List<Anamnesis> findAllNewAnamnesis() throws DBException {
		return dbManager.execute(new Operation<List<Anamnesis>>() {
			@Override
			public List<Anamnesis> execute() throws DAOException {
				return anamnesisDAO.findAllByIdStatus(Status.NEW.ordinal());
			}

		});
	}

	@Override
	public List<Anamnesis> findAllNewAnamnesisByIdDoctor(long idDoctor) throws DBException {
		return dbManager.execute(new Operation<List<Anamnesis>>() {
			@Override
			public List<Anamnesis> execute() throws DAOException {
				return anamnesisDAO.findAllByIdStatusAndIdDoctor(Status.NEW.ordinal(), idDoctor);
			}
		});
	}

	@Override
	public List<Anamnesis> findAllDischargedByIdPatient(long idPatient) throws DBException {
		return dbManager.execute(new Operation<List<Anamnesis>>() {
			@Override
			public List<Anamnesis> execute() throws DAOException {
				return anamnesisDAO.findAllByIdStatusAndIdPatient(Status.DISCHARGED.ordinal(), idPatient);
			}

		});
	}

}
