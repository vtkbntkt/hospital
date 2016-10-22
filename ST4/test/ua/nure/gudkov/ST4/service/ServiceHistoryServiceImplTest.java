package ua.nure.gudkov.ST4.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.dao.api.ServiceHistoryDAO;
import ua.nure.gudkov.ST4.db.DBManager;
import ua.nure.gudkov.ST4.db.Operation;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.exception.DAOException;
import ua.nure.gudkov.ST4.exception.DBException;

public class ServiceHistoryServiceImplTest extends Mockito {
	
	private ServiceHistoryDAO serviceHistoryDAO = mock(ServiceHistoryDAO.class);
	private DBManager dbManager = mock(DBManager.class);
	private ServiceHistoryServiceImpl serv;
	
	@SuppressWarnings("rawtypes")
	@Before
	public void setUp() throws Exception {
		when(dbManager.execute(any()))
				.thenAnswer(invocationOnMock -> ((Operation) invocationOnMock.getArguments()[0]).execute());
		serv = new ServiceHistoryServiceImpl(serviceHistoryDAO, dbManager);
	}


	@Test
	public void testFindAllServiceHistoryByIdAnamnesis() throws DAOException, DBException {
		List<ServiceHistory> sh = new ArrayList<ServiceHistory>();
		when(serviceHistoryDAO.findAllByIdAnamnesis(2016)).thenReturn(sh);
		assertEquals(sh, serv.findAllServiceHistoryByIdAnamnesis(2016));
	}

	@Test
	public void testAddServiceHistory() throws DAOException, DBException {
		ServiceHistory sh = new ServiceHistory();
		when(serviceHistoryDAO.insertServHistory(sh)).thenReturn(true);
		assertEquals(true, serv.addServiceHistory(sh));
	}

	@Test
	public void testFindAllTakenServiceHistory() throws DAOException, DBException {
		List<ServiceHistory> sh = new ArrayList<ServiceHistory>();
		when(serviceHistoryDAO.findAllByIdStatus(0)).thenReturn(sh);
		assertEquals(sh, serv.findAllTakenServiceHistory());

	}

	@Test
	public void testFindById() throws DAOException, DBException {
		ServiceHistory sh = new ServiceHistory();
		sh.setIdRecord(2016);
		when(serviceHistoryDAO.findByIdServiceHistory(sh.getIdRecord())).thenReturn(sh);
		assertEquals(sh, serv.findById(sh.getIdRecord()));
	}

	@Test
	public void testChangeServiceStatusToPerformed() throws DAOException, DBException {
		when(serviceHistoryDAO.updateServiceStatus(3,2016)).thenReturn(true);
		assertEquals(true, serv.changeServiceStatusToPerformed(2016));
	}

}
