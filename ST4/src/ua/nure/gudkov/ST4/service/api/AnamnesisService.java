package ua.nure.gudkov.ST4.service.api;

import java.util.List;

import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.exception.DBException;

/**
 * Anamnesis service interface.
 * 
 * @author A.Gudkov
 *
 */
public interface AnamnesisService {

	/**
	 * Returns list of all anamnesis contained on the base.
	 * 
	 * @return list of anamnesis
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	List<Anamnesis> findAllAnamnesis() throws DBException;

	/**
	 * Check whether an anamnesis with given patient id exists and has new
	 * status.
	 * 
	 * @param idPatient
	 *            the patient id
	 * @return true if anamnesis with given patient id exists and has new
	 *         status, false if not.
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	boolean verifyByIdPatient(int idPatient) throws DBException;

	/**
	 * Adds an anamnesis into the data base.
	 * 
	 * @param anamnes
	 *            the anamnesis
	 * @return true if the anamnesis was added into the data base, false if was
	 *         not
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	boolean addAnamnesis(Anamnesis anamnes) throws DBException;

	/**
	 * Returns new anamnesis found by patient id.
	 * 
	 * @param idPatient
	 *            the patient id
	 * @return anamnesis
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	Anamnesis findNewByIdPatient(int idPatient) throws DBException;

	/**
	 * Returns an anamnesis found by its id.
	 * 
	 * @param idAnamnesis
	 *            the anamnesis id
	 * @return anamnesis
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	Anamnesis findByIdAnamnesis(int idAnamnesis) throws DBException;

	/**
	 * Updates the initial diagnosis.
	 * 
	 * @param diagnosis
	 *            the initial diagnosis
	 * @param idAnamnesis
	 *            anamnesis id
	 * @return true if the initial diagnosis was updated, false if was not
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	boolean updateInitialDiagnosis(String diagnosis, int idAnamnesis) throws DBException;

	/**
	 * Updates the final diagnosis and anamnesis status found by the anamnesis
	 * id.
	 * 
	 * @param finalDiagnos
	 * @param idAnamnesis
	 * @return true if final diagnosis was updated and anamnesis status was
	 *         changed, false if the diagnosis or anamnesis status was not
	 *         updated
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	boolean dischargePatient(String finalDiagnos, int idAnamnesis) throws DBException;

	/**
	 * Returns all anamnesis with new status.
	 * 
	 * @return anamnesis list
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	List<Anamnesis> findAllNewAnamnesis() throws DBException;

	/**
	 * Returns all anamnesis with discharged status found by the patient id.
	 * 
	 * @param idPatient
	 *            patient id
	 * @return anamnesis list
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	List<Anamnesis> findAllDischargedByIdPatient(long idPatient) throws DBException;

	/**
	 * Returns all anamnesis with new status found by the doctor id.
	 * 
	 * @param idDoctor
	 *            doctor id
	 * @return anamnesis list
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	List<Anamnesis> findAllNewAnamnesisByIdDoctor(long idDoctor) throws DBException;

}
