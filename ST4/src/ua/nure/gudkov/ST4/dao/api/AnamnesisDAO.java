package ua.nure.gudkov.ST4.dao.api;

import java.util.List;

import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.exception.DAOException;

/**
 * Anamnesis DAO interface.
 * 
 * @author A.Gudkov
 *
 */
public interface AnamnesisDAO {

	/**
	 * Returns list of all anamnesis contained on the base.
	 * 
	 * @return list of anamnesis
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	List<Anamnesis> findAll() throws DAOException;

	/**
	 * Adds anamnesis into data base.
	 * 
	 * @param anamnesis
	 *            the anamnesis
	 * @return true if the anamnesis was added into database, false if was not.
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	boolean insertAnamnesis(Anamnesis anamnesis) throws DAOException;

	/**
	 * Return anamnesis found by its id.
	 * 
	 * @param idAnamnesis
	 *            the anamnesis id
	 * @return anamnesis
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	Anamnesis findByIdAnamnesis(int idAnamnesis) throws DAOException;

	/**
	 * Updates initial diagnosis/
	 * 
	 * @param diagnosis
	 *            the initial diagnosis
	 * @param idAnamnesis
	 *            the anamnesis id
	 * @return true if the initial diagnosis was updated, false if was not.
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	boolean updateInitialDiagnosis(String diagnosis, int idAnamnesis) throws DAOException;

	/**
	 * Updates final diagnosis/
	 * 
	 * @param diagnosis
	 *            the final diagnosis
	 * @param idAnamnesis
	 *            the anamnesis id
	 * @return true if the final diagnosis was updated, false if was not.
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	boolean updateFinalDiagnosis(String diagnosis, int idAnamnesis) throws DAOException;

	/**
	 * Updates anamnesis status.
	 * 
	 * @param idStatus
	 *            the status id
	 * @param idAnamnesis
	 *            the anamnesis ad
	 * @return true if anamnesis status was updated, false if was not
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	boolean updateAnamnesisStatus(int idStatus, int idAnamnesis) throws DAOException;

	/**
	 * Returns all anamnesis found by status id.
	 * 
	 * @param idStatus
	 *            the status id
	 * @return anamnesis list
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	List<Anamnesis> findAllByIdStatus(int idStatus) throws DAOException;

	/**
	 * returns all anamnesis found by status and patient id.
	 * 
	 * @param idStatus
	 *            the status id
	 * @param idPatient
	 *            the patient id
	 * @return anamnesis list
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	List<Anamnesis> findAllByIdStatusAndIdPatient(int idStatus, long idPatient) throws DAOException;

	/**
	 * Returns all anamnesis found by status id and doctor id.
	 * 
	 * @param idStatus
	 *            the status id
	 * @param idDoctor
	 *            the doctor id
	 * @return anamnesis list
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	List<Anamnesis> findAllByIdStatusAndIdDoctor(int idStatus, long idDoctor) throws DAOException;
}
