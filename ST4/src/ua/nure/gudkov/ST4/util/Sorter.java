package ua.nure.gudkov.ST4.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.nure.gudkov.ST4.bean.dto.DoctorBean;
import ua.nure.gudkov.ST4.bean.dto.NewPatientBean;
import ua.nure.gudkov.ST4.bean.dto.PatientBean;

/**
 * Contains static methods for sorting.
 * 
 * @author A.Fudkov
 * 
 */
public final class Sorter {
	
	/**
	 * Sorts doctors by number of patients
	 */
	public static final Comparator<DoctorBean> SORT_DOCTORS_BY_PATIENT_NUMBER = new Comparator<DoctorBean>() {
		@Override
		public int compare(DoctorBean o1, DoctorBean o2) {
			return (int)o1.getPatientNum() - (o2.getPatientNum());
		}
	};

	/**
	 * Sorts doctors by alphabet.
	 */
	public static final Comparator<DoctorBean> SORT_DOCTORS_BY_ALPHABET = new Comparator<DoctorBean>() {
		@Override
		public int compare(DoctorBean o1, DoctorBean o2) {
			int res = o1.getLastName().compareToIgnoreCase(o2.getLastName());
			if (res != 0)
				return res;
			return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
		}
	};

	/**
	 * Sorts doctors by category.
	 */
	public static final Comparator<DoctorBean> SORT_DOCTORS_BY_CATEGORY = new Comparator<DoctorBean>() {
		@Override
		public int compare(DoctorBean o1, DoctorBean o2) {
			return o1.getCategory().compareTo(o2.getCategory());
		}
	};

	/**
	 * Sorts patients by alphabet.
	 */
	public static final Comparator<PatientBean> SORT_PATIENTS_BY_ALPHABET = new Comparator<PatientBean>() {
		@Override
		public int compare(PatientBean o1, PatientBean o2) {
			int res = o1.getLastName().compareToIgnoreCase(o2.getLastName());
			if (res != 0)
				return res;
			return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
		}
	};

	/**
	 * Sorts patients by date of birth.
	 */
	public static final Comparator<PatientBean> SORT_PATIENTS_BY_DATE_OF_BIRTH = new Comparator<PatientBean>() {
		@Override
		public int compare(PatientBean o1, PatientBean o2) {
			long t1 = o1.getDateOfBirth().getTime();
			long t2 = o2.getDateOfBirth().getTime();
			if (t2 > t1)
				return 1;
			else if (t1 > t2)
				return -1;
			else
				return 0;
		}
	};
	
	/**
	 * Sorts patients by doctor.
	 */
	public static final Comparator<NewPatientBean> SORT_PATIENTS_BY_DOCTOR = new Comparator<NewPatientBean>() {
		@Override
		public int compare(NewPatientBean o1, NewPatientBean o2) {
			return o1.getDoctor().getLastName().compareTo(o2.getDoctor().getLastName());
		}
	};
	
	public static List<NewPatientBean> sortPatientsByDoctor(List<NewPatientBean> patientList) {
		Collections.sort(patientList, SORT_PATIENTS_BY_DOCTOR);
		return patientList;
	}

	public static List<DoctorBean> sortDoctorsByPatientNum(List<DoctorBean> doctorList) {
		Collections.sort(doctorList, SORT_DOCTORS_BY_PATIENT_NUMBER);
		return doctorList;
	}

	public static List<DoctorBean> sortDoctorsByAlphabet(List<DoctorBean> doctorList) {
		Collections.sort(doctorList, SORT_DOCTORS_BY_ALPHABET);
		return doctorList;
	}
	
	public static List<DoctorBean> sortDoctorsByCategory(List<DoctorBean> doctorList) {
		Collections.sort(doctorList, SORT_DOCTORS_BY_CATEGORY);
		return doctorList;
	}
	
	public static List<PatientBean> sortPatientsByAlphabet(List<PatientBean> patientList) {
		Collections.sort(patientList, SORT_PATIENTS_BY_ALPHABET);
		return patientList;
	}
	
	public static List<PatientBean> sortPatientsByDateOfBirth(List<PatientBean> patientList) {
		Collections.sort(patientList, SORT_PATIENTS_BY_DATE_OF_BIRTH);
		return patientList;
	}

}
