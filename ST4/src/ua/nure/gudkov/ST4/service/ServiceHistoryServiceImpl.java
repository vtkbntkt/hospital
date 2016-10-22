package ua.nure.gudkov.ST4.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.dao.api.ServiceHistoryDAO;
import ua.nure.gudkov.ST4.db.DBManager;
import ua.nure.gudkov.ST4.db.Operation;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.Status;
import ua.nure.gudkov.ST4.exception.DAOException;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;

/**
 * Service history service implementation.
 * 
 * @author A.Gudkov
 */
public class ServiceHistoryServiceImpl implements ServiceHistoryService {
	private ServiceHistoryDAO serviceHistoryDAO;
	private DBManager dbManager;
	private static final Logger LOG = Logger.getLogger(ServiceHistoryServiceImpl.class);

	public ServiceHistoryServiceImpl(ServiceHistoryDAO serviceHistoryDAO, DBManager dbManager) {
		this.serviceHistoryDAO = serviceHistoryDAO;
		this.dbManager = dbManager;
		LOG.debug("Service history service was successfully initialized");

	}

	@Override
	public List<ServiceHistory> findAllServiceHistoryByIdAnamnesis(int idAnamnesis) throws DBException {
		return dbManager.execute(new Operation<List<ServiceHistory>>() {
			@Override
			public List<ServiceHistory> execute() throws DAOException {
				return serviceHistoryDAO.findAllByIdAnamnesis(idAnamnesis);
			}

		});
	}

	@Override
	public boolean addServiceHistory(ServiceHistory serviceHistory) throws DBException {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() throws DAOException {
				return serviceHistoryDAO.insertServHistory(serviceHistory);
			}

		});
	}

	@Override
	public List<ServiceHistory> findAllTakenServiceHistory() throws DBException {
		return dbManager.execute(new Operation<List<ServiceHistory>>() {
			@Override
			public List<ServiceHistory> execute() throws DAOException {
				return serviceHistoryDAO.findAllByIdStatus(Status.TAKEN.ordinal());
			}

		});
	}

	@Override
	public ServiceHistory findById(int idServHistory) throws DBException {
		return dbManager.execute(new Operation<ServiceHistory>() {
			@Override
			public ServiceHistory execute() throws DAOException {
				return serviceHistoryDAO.findByIdServiceHistory(idServHistory);
			}

		});
	}

	@Override
	public boolean changeServiceStatusToPerformed(int idServHistory) throws DBException {
		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() throws DAOException {
				return serviceHistoryDAO.updateServiceStatus(Status.PERFORMED.ordinal(), idServHistory);
			}

		});
	}
}
