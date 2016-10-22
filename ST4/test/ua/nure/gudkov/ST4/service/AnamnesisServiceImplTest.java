package ua.nure.gudkov.ST4.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.dao.MySqlAnamnesisDAO;
import ua.nure.gudkov.ST4.dao.api.AnamnesisDAO;
import ua.nure.gudkov.ST4.db.DBManager;
import ua.nure.gudkov.ST4.db.Operation;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.exception.DAOException;
import ua.nure.gudkov.ST4.exception.DBException;

public class AnamnesisServiceImplTest extends Mockito {

	private AnamnesisDAO anamnesisDAO = mock(MySqlAnamnesisDAO.class);
	private DBManager dbManager = mock(DBManager.class);
	private AnamnesisServiceImpl serv;

	@SuppressWarnings("rawtypes")
	@Before
	public void setUp() throws Exception {
		when(dbManager.execute(any()))
				.thenAnswer(invocationOnMock -> ((Operation) invocationOnMock.getArguments()[0]).execute());
		serv = new AnamnesisServiceImpl(anamnesisDAO, dbManager);
	}

	@Test
	public void testFindAllAnamnesis() throws DBException, DAOException {
		Anamnesis anamnes = new Anamnesis();
		List<Anamnesis> anamnessis = new ArrayList<Anamnesis>();
		anamnessis.add(anamnes);
		when(anamnesisDAO.findAll()).thenReturn(anamnessis);
		assertEquals(anamnessis, serv.findAllAnamnesis());
	}

	@Test
	public void testVerifyByIdPatient() throws DBException, DAOException {
		assertEquals(false, serv.verifyByIdPatient(2016));
		
		List<Anamnesis> anamnessis = new ArrayList<Anamnesis>();
		Anamnesis anamnes = new Anamnesis();
		anamnessis.add(anamnes);
		when(anamnesisDAO.findAllByIdStatusAndIdPatient(2, 2016)).thenReturn(anamnessis);
		assertEquals(true, serv.verifyByIdPatient(2016));

	}

	@Test
	public void testAddAnamnesis() throws DAOException, DBException {
		Anamnesis anamnes = new Anamnesis();
		when(anamnesisDAO.insertAnamnesis(anamnes)).thenReturn(true);
		assertEquals(true, serv.addAnamnesis(anamnes));
	}

	@Test
	public void testFindNewByIdPatient() throws DAOException, DBException {
		List<Anamnesis> anamnessis = new ArrayList<Anamnesis>();
		when(anamnesisDAO.findAllByIdStatusAndIdPatient(2, 2016)).thenReturn(anamnessis);
		assertEquals(null, serv.findNewByIdPatient(2016));

		Anamnesis anamnes = new Anamnesis();
		anamnessis.add(anamnes);
		assertEquals(anamnes, serv.findNewByIdPatient(2016));

	}

	@Test
	public void testFindByIdAnamnesis() throws DAOException, DBException {
		Anamnesis anamnes = new Anamnesis();
		anamnes.setIdanamnesis(2016);
		when(anamnesisDAO.findByIdAnamnesis(anamnes.getIdanamnesis())).thenReturn(anamnes);
		assertEquals(anamnes, serv.findByIdAnamnesis(anamnes.getIdanamnesis()));

	}

	@Test
	public void testUpdateInitialDiagnosis() throws DAOException, DBException {
		when(anamnesisDAO.updateInitialDiagnosis("test",2016)).thenReturn(true);
		assertEquals(true, serv.updateInitialDiagnosis("test",2016));

	}

	@Test
	public void testDischargePatient() throws DAOException, DBException {
		when(anamnesisDAO.updateFinalDiagnosis("test",2016)).thenReturn(true);
		when(anamnesisDAO.updateAnamnesisStatus(1,2016)).thenReturn(true);
		assertEquals(true, serv.dischargePatient("test",2016));
		
		when(anamnesisDAO.updateFinalDiagnosis("test",2016)).thenReturn(true);
		when(anamnesisDAO.updateAnamnesisStatus(1,2016)).thenReturn(false);
		assertEquals(false, serv.dischargePatient("test",2016));
		
		when(anamnesisDAO.updateFinalDiagnosis("test",2016)).thenReturn(false);
		when(anamnesisDAO.updateAnamnesisStatus(1,2016)).thenReturn(true);
		assertEquals(false, serv.dischargePatient("test",2016));


	}

	@Test
	public void testFindAllNewAnamnesis() throws DAOException, DBException {
		List<Anamnesis> anamnesis = new ArrayList<Anamnesis>();
		when(anamnesisDAO.findAllByIdStatus(2)).thenReturn(anamnesis);
		assertEquals(anamnesis, serv.findAllNewAnamnesis());



	}

	@Test
	public void testFindAllNewAnamnesisByIdDoctor() throws DAOException, DBException {
		List<Anamnesis> anamnesis = new ArrayList<Anamnesis>();
		when(anamnesisDAO.findAllByIdStatusAndIdDoctor(2,2016)).thenReturn(anamnesis);
		assertEquals(anamnesis, serv.findAllNewAnamnesisByIdDoctor(2016));

	}

	@Test
	public void testFindAllDischargedByIdPatient() throws DAOException, DBException {
		List<Anamnesis> anamnesis = new ArrayList<Anamnesis>();
		when(anamnesisDAO.findAllByIdStatusAndIdPatient(1,2016)).thenReturn(anamnesis);
		assertEquals(anamnesis, serv.findAllDischargedByIdPatient(2016));
	}

}
