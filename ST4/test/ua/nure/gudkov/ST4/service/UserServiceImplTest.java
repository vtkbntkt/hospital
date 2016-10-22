package ua.nure.gudkov.ST4.service;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.dao.api.UserDAO;
import ua.nure.gudkov.ST4.db.DBManager;
import ua.nure.gudkov.ST4.db.Operation;
import ua.nure.gudkov.ST4.entity.Role;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DAOException;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.util.Password;

public class UserServiceImplTest extends Mockito {
	ExpectedException exc = ExpectedException.none();
	private UserDAO userDAO = mock(UserDAO.class);
	private DBManager dbManager = mock(DBManager.class);
	private UserServiceImpl serv;

	@SuppressWarnings("rawtypes")
	@Before
	public void setUp() throws Exception {
		when(dbManager.execute(any()))
				.thenAnswer(invocationOnMock -> ((Operation) invocationOnMock.getArguments()[0]).execute());
		serv = new UserServiceImpl(userDAO, dbManager);
	}

	@Test
	public void testLoginWrongPass() throws DAOException, DBException {
		User user = new User();
		user.setPassword("pp");
		when(userDAO.findByEmail("mail@mail.com")).thenReturn(user);
		serv.login("mail@mail.com", "pass");
		verify(userDAO).findByEmail("mail@mail.com");

	}

	@Test
	public void testLoginUserNull() throws DAOException, DBException, NoSuchAlgorithmException {
		when(userDAO.findByEmail(anyString())).thenReturn(null);
		serv.login("mail@mail.com", "pass");
		verify(userDAO).findByEmail("mail@mail.com");

	}

	@Test
	public void testLogin() throws DAOException, DBException, NoSuchAlgorithmException, UnsupportedEncodingException {
		User user = new User();
		user.setPassword(Password.hash("123"));
		when(userDAO.findByEmail("mail@mail.com")).thenReturn(user);
		serv.login("mail@mail.com", "123");
		verify(userDAO).findByEmail("mail@mail.com");

	}

	@Test
	public void testRegisterUser() throws DAOException, DBException {
		User user = new User();
		user.setPassword("pass");
		when(userDAO.findByEmail("mail@mail.com")).thenReturn(null);
		serv.registerUser(user);
		verify(userDAO).insertUser(user);
	}

	@Test
	public void testRegisterUserErrorUserExists() throws DAOException, DBException {
		User user = new User();
		user.setPassword("pass");
		user.setEmail("mail@mail.com");
		when(userDAO.findByEmail(user.getEmail())).thenReturn(user);
		assertEquals(false, serv.registerUser(user));
	}

	@Test
	public void testFindAllUsers() throws DBException, DAOException {
		when(userDAO.findAllUsers()).thenReturn(any());
		serv.findAllUsers();
		verify(userDAO).findAllUsers();

	}

	@Test
	public void testFindById() throws DAOException, DBException {
		when(userDAO.findById(2016)).thenReturn(any());
		serv.findById(2016);
		verify(userDAO).findById(2016);
	}

	@Test
	public void testVerifyDoctor() throws DAOException, DBException {
		User user = new User();
		user.setRoleId(Role.DOCTOR.ordinal());
		when(userDAO.findById(2016)).thenReturn(user);
		assertEquals(true, serv.verifyDoctor(2016));
	}

	@Test
	public void testVerifyDoctorErrorWrongRole() throws DAOException, DBException {
		User user = new User();
		user.setRoleId(Role.ADMIN.ordinal());
		when(userDAO.findById(2016)).thenReturn(user);
		assertEquals(false, serv.verifyDoctor(2016));
	}

	@Test
	public void testVerifyDoctorErrorUserIsNull() throws DAOException, DBException {
		when(userDAO.findById(2016)).thenReturn(null);
		assertEquals(false, serv.verifyDoctor(2016));
	}

	@Test
	public void testVerifyPatient() throws DAOException, DBException {
		User user = new User();
		user.setRoleId(Role.PATIENT.ordinal());
		when(userDAO.findById(2016)).thenReturn(user);
		assertEquals(true, serv.verifyPatient(2016));
	}

	@Test
	public void testVerifyPatientErrorWrongRole() throws DAOException, DBException {
		User user = new User();
		user.setRoleId(Role.DOCTOR.ordinal());
		when(userDAO.findById(2016)).thenReturn(user);
		assertEquals(false, serv.verifyPatient(2016));
	}

	@Test
	public void testVerifyPatientErrorUserIsNull() throws DAOException, DBException {
		when(userDAO.findById(2016)).thenReturn(null);
		assertEquals(false, serv.verifyPatient(2016));
	}

	@Test
	public void testFindAllDoctors() throws DAOException, DBException {
		when(userDAO.findByIdRole(Role.DOCTOR.ordinal())).thenReturn(any());
		serv.findAllDoctors();
		verify(userDAO).findByIdRole(Role.DOCTOR.ordinal());

	}

	@Test
	public void testFindAllPatients() throws DAOException, DBException {
		when(userDAO.findByIdRole(Role.PATIENT.ordinal())).thenReturn(any());
		serv.findAllPatients();
		verify(userDAO).findByIdRole(Role.PATIENT.ordinal());
	}

}
