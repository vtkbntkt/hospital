package ua.nure.gudkov.ST4.converter;

import java.sql.Date;
import java.util.List;

import ua.nure.gudkov.ST4.bean.aggregation.ServValueStatusBean;
import ua.nure.gudkov.ST4.bean.dto.PatientCardBean;
import ua.nure.gudkov.ST4.converter.api.Converter;

/**
 * Patient card bean to note converter.
 * 
 * @author A.Gudkov
 *
 */
public class PatientCardBeanToNote implements Converter<PatientCardBean, String> {
	public static final String SEPARATOR = System.lineSeparator();

	@Override
	public String convert(PatientCardBean pcb) {
		StringBuilder sb = new StringBuilder();
		int idCard = pcb.getIdCard();
		Date creationDate = pcb.getCreationDate();
		String lastName = pcb.getLastName();
		String firstName = pcb.getFirstName();
		Date dateOfBirth = pcb.getDateOfBirth();
		String initialDiagnosis = pcb.getInitialDiagnosis();
		String finalDiagnosis = pcb.getFinalDiagnosis();
		List<ServValueStatusBean> manipulations = pcb.getManipulations();
		List<ServValueStatusBean> drugs = pcb.getDrugs();
		List<ServValueStatusBean> surgeries = pcb.getSurgeries();

		sb.append("Patient card #").append(idCard).append(SEPARATOR);
		sb.append("Creation date: ").append(creationDate).append(SEPARATOR);
		sb.append("Patient: ").append(firstName).append(" ").append(lastName).append(SEPARATOR);
		sb.append("Date of birth: ").append(dateOfBirth).append(SEPARATOR).append(SEPARATOR);
		sb.append("INITIAL DIAGNOSIS: ").append(SEPARATOR);
		sb.append(initialDiagnosis).append(SEPARATOR);
		sb.append("FINAL DIAGNOSIS: ").append(SEPARATOR);
		sb.append(finalDiagnosis).append(SEPARATOR);

		if (!manipulations.isEmpty()) {
			addManipulations(sb, manipulations);
		}
		if (!drugs.isEmpty()) {
			addDrugs(sb, drugs);
		}
		if (!surgeries.isEmpty()) {
			addSurgeries(sb, surgeries);
		}

		return sb.toString();
	}

	/**
	 * Add list of manipulations into the note.
	 * 
	 * @param sb
	 *            the note
	 * @param manipulations
	 *            list of manipulation
	 */
	private void addManipulations(StringBuilder sb, List<ServValueStatusBean> manipulations) {
		sb.append("MANIPULATIONS: ").append(SEPARATOR);
		for (ServValueStatusBean svsb : manipulations) {
			sb.append(svsb.getServDate()).append(" ").append(svsb.getServStatus()).append(" ")
					.append(svsb.getServValue()).append(SEPARATOR);
		}

	}

	/**
	 * Add list of drugs into the note.
	 * 
	 * @param sb
	 *            the note
	 * @param drugs
	 *            list of drugs
	 */
	private void addDrugs(StringBuilder sb, List<ServValueStatusBean> drugs) {
		sb.append("DRUGS: ").append(SEPARATOR);
		for (ServValueStatusBean svsb : drugs) {
			sb.append(svsb.getServDate()).append(" ").append(svsb.getServStatus()).append(" ")
					.append(svsb.getServValue()).append(SEPARATOR);
		}
	}

	/**
	 * Add list of surgeries into the note.
	 * 
	 * @param sb
	 *            the note
	 * @param surgeries
	 *            list of surgeries
	 */
	private void addSurgeries(StringBuilder sb, List<ServValueStatusBean> surgeries) {
		sb.append("SURGERIES: ").append(SEPARATOR);
		for (ServValueStatusBean svsb : surgeries) {
			sb.append(svsb.getServDate()).append(" ").append(svsb.getServStatus()).append(" ")
					.append(svsb.getServValue()).append(SEPARATOR);
		}
	}

}
