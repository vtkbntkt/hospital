package ua.nure.gudkov.ST4.constant;


/**
 * All constants.
 * 
 * @author A.Gudkov
 *
 */
public class Constants {

	/**
	 * Contains command name and paths to perform defined commands.
	 */
	public static final class Commands {
		public static final String COMMAND = "command";
		public static final String CHANGE_LANG_COMMAND = "changelang";
		public static final String SIGN_IN_COMMAND = "signin";
		public static final String REG_USER_COMMAND = "reguser";
		public static final String GET_ACCOUNT_COMMAND = "getaccount";
		public static final String GET_LOGIN_COMMAND = "getlogin";
		public static final String GET_ALL_DOCTORS = "getAllDoctors";
		public static final String GET_ALL_PATIENTS = "getAllPatients";
		public static final String GET_ASSIGNDOCTOR_FORM = "getAssignDoctorForm";
		public static final String ASSIGN_DOCTOR_COMMAND = "assigndoctor";
		public static final String LOGOUT_COMMAND = "logout";
		public static final String GET_MY_PATIENTS = "getMyPatients";
		public static final String GET_PATIENT_CARD = "getPatientCard";
		public static final String ADD_SERVICE = "addService";
		public static final String ADD_DIAGNOSIS = "addDiagnosis";
		public static final String GET_DOCTOR_TASKS = "getDoctorTasks";
		public static final String PERFORM_TASK = "performTask";
		public static final String UPLOAD_PHOTO = "uploadPhoto";
		public static final String GET_PHOTO = "getPhoto";
		public static final String DISCHARGE_PATIENT = "dischargePatient";
		public static final String GET_MYCARDS = "getMyCards";
		public static final String PRINT_MYCARD = "printMyCard";
		public static final String GET_NEW_PATIENTS = "getNewPatients";
		public static final String SERVLET_GET_ACCOUNT_COMMAND = "?command=getaccount";
		public static final String SERVLET_GET_LOGIN_COMMAND = "?command=getlogin";
		public static final String SERVLET_GET_ASSIGN_DOC_FORM_COMMAND = "?command=getAssignDoctorForm";
		public static final String SERVLET_GET_PATIENT_CARD_COMMAND = "?command=getPatientCard&idPatient=";
		public static final String SERVLET_GET_TASKS_COMMAND = "?command=getDoctorTasks";
		public static final String SERVLET_GET_ALL_PATIENTS_COMMAND = "?command=getAllPatients";
		public static final String SERVLET_GET_ALL_MYPATIENTS_COMMAND = "?command=getMyPatients";

		private Commands() {
		}
	}

	/**
	 * Registration form parameters
	 */
	public static final class RegFormParameters {
		public static final String FIRST_NAME_FIELD = "firstname";
		public static final String LAST_NAME_FIELD = "lastname";
		public static final String EMAIL_FIELD = "email";
		public static final String PHONE_NUMBER_FIELD = "telephone";
		public static final String DATE_OF_BIRTH_FIELD = "bday";
		public static final String ROLE_FIELD = "role";
		public static final String CATEGORY_FIELD = "category";
		public static final String MSG_NOTICE_ERROR = "noticeErr";
		public static final String MSG_NOTICE_SUCCESS = "noticeSuccess";
		public static final String MSG_MAIL_ERROR = "mailErr";
		public static final String MSG_MAIL_SUCCESS = "mailSuccess";

		private RegFormParameters() {
		}
	}

	/**
	 * Context attributes.
	 */
	public static final class ContextAttr {
		public static final String COMMAND_CONTAINER = "commandContainer";

		private ContextAttr() {
		}
	}

	/**
	 * Patient card parameters
	 */
	public static final class PatientCardParameters {
		public static final String ID_ANAMNESIS = "idAnamnesis";
		public static final String TYPE_SERVICE = "serviceType";
		public static final String ID_PATIENT = "idPatient";
		public static final String SERV_VALUE_FIELD = "servValue";
		public static final String DIAGNOSIS_VALUE_FIELD = "diagnosValue";

		private PatientCardParameters() {
		}
	}

	/**
	 * Sign in form parameters
	 */
	public static final class SignInFormParameters {
		public static final String EMAIL_FIELD = "email";
		public static final String PASSWORD_FIELD = "password";

		private SignInFormParameters() {
		}
	}

	/**
	 * List of task parameters.
	 */
	public static final class TaskListParameters {
		public static final String MSG_ERROR_TASKS = "taskListError";
		public static final String ID_TASK = "idTask";

		private TaskListParameters() {
		}
	}

	/**
	 * Constants to be used by filters.
	 */
	public static final class FilterConstants {
		public static final String MAX_FILE_SIZE = "maxFileSize";
		public static final String FILE_EXTENSION = "fileExtension";
		public static final String ENCODING = "encoding";
		public static final String ADMIN_ROLE = "admin";
		public static final String DOCTOR_ROLE = "doctor";
		public static final String NURSE_ROLE = "nurse";
		public static final String PATIENT_ROLE = "patient";
		public static final String COMMON_ACCESS = "common";
		public static final String NOCONTROL_ACCESS = "out-of-control";

		private FilterConstants() {
		}
	}

	/**
	 * Generic parameters for all the commands
	 */
	public static final class GenericParameters {
		public static final String ID_ANAMNESIS = "idAnamnesis";
		public static final String ID_PATIENT = "idPatient";
		public static final String ID_SERV_HISTORY = "idTask";
		/*
		 * backup params
		 */
		public static final String BACKUP_PARAMS = "backupParams";
		public static final String START_INDEX = "startIndex";

		private GenericParameters() {
		}
	}

	/**
	 * Uploading photo parameters.
	 */
	public static final class PhotoUploadingParameters {
		public static final String PHOTO_FILE_FIELD = "photoFile";
		public static final String UPLOAD_DIRECTORY = "C:/uploads";
		public static final String SRC_DIRECTORY = "C:/src";
		public static final String MED_FILE = "blank.jpg";
		public static final String PHOTO_EXTENSION = ".jpg";
		public static final String DEF_PHOTO = "0.jpg";

		private PhotoUploadingParameters() {
		}
	}

	/**
	 * Parameters for not creation.
	 */
	public static final class NoteParameters {
		public static final String CARD_BLANK_PATH = "C:/src/blank.jpg";
		public static final String TIMES_ROMAN_FONT = "TimesRoman";

		private NoteParameters() {
		}
	}

	/**
	 * List of doctors parameters
	 */
	public static final class DoctorListParameters {
		public static final String MSG_ERROR_DOCTORS = "doctorListError";
		public static final String SORTING_PARAMETER = "sorting";

		private DoctorListParameters() {
		}
	}

	/**
	 * List of patients parameters
	 */
	public static final class PatientListParameters {
		public static final String MSG_ERROR_PATIENTS = "patientListError";
		public static final String SORTING_PARAMETER = "sorting";

		private PatientListParameters() {
		}
	}

	/**
	 * List of my doctors parameters
	 */
	public static final class MyPatientsParameters {
		public static final String MSG_ERROR_PATIENTS = "myPatientsError";
		public static final String ID_PATIENT = "idPatient";

		private MyPatientsParameters() {
		}
	}

	/**
	 * List of my cards parameters
	 */
	public static final class MyCardsParameters {
		public static final String MSG_ERROR_MYCARDS = "myCardsError";
		public static final String ID_ANAMNESIS = "idAnamnesis";

		private MyCardsParameters() {
		}
	}

	/**
	 * Assign doctor parameters
	 */
	public static final class AssignDoctorParameters {
		public static final String MSG_ERROR_DOC_PAT = "docPatListError";
		public static final String ID_PATIENT_FIELD = "idPatient";
		public static final String ID_DOCTOR_FIELD = "idDoctor";
		public static final String MSG_FOUND_DOCTOR_ERROR = "foundDocErr";
		public static final String MSG_FOUND_PATIENT_ERROR = "foundPatErr";
		public static final String MSG_ANAMNES_EXIST_ERROR = "anamnesExistErr";
		public static final String MSG_ANAMNES_ADD_ERROR = "anamnesAddErr";
		public static final String MSG_ASSIGN_DOCTOR_SUCCESS = "assignDoctorSuccess";

		private AssignDoctorParameters() {
		}
	}

	/**
	 * Change language form parameters
	 */
	public static final class LangFormParameters {
		public static final String SELECTED_LANG = "language";
		public static final String REFERRER_PATH = "path";

		private LangFormParameters() {
		}
	}

	/**
	 * Regular expression to validate input fields
	 */
	public static final class RegexConstants {
		public static final String EMAIL_REG_EXP = "^([a-zA-Z0-9_-]+\\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\."
				+ "[a-z]{2,4}$";
		public static final String NAME_REG_EXP = "^[A-ZÀ-ß][a-zà-ÿ]{1,19}$";
		public static final String PHONE_REG_EXP = "^(\\d{3}) (\\d{3}) (\\d{4})$";
		public static final String DATE_REG_EXP = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";

		private RegexConstants() {
		}
	}

	/**
	 * Represents errors keys should be translated into defined language
	 *
	 */
	public final class ErrorConstants {
		public static final String REGISTRATION_ERRORS = "regErrors";
		public static final String LOGIN_ERRORS = "loginErrors";
		public static final String DB_ERRORS = "dbErrors";
		public static final String DOCTOR_LIST_ERRORS = "doctorErrors";
		public static final String PATIENT_LIST_ERRORS = "patientErrors";
		public static final String MY_PATIENTS_ERRORS = "myPatientsListErrors";
		public static final String DOCTOR_PATIENT_LIST_ERRORS = "docPatErrors";
		public static final String ASSIGN_DOCTOR_ERRORS = "assignDoctorErrors";
		public static final String CREATE_PATIENT_CARD_ERRORS = "createPatCardErrors";
		public static final String ADD_SERVICE_ERRORS = "addServErrors";
		public static final String ADD_DIAGNOSIS_ERRORS = "addDiagnosisErrors";
		public static final String TASK_LIST_ERRORS = "taskListErrors";
		public static final String UPLOADING_PHOTO_ERRORS = "uploadingPhotoErrors";
		public static final String MYCARDS_LIST_ERRORS = "myCardsListErrors";
		public static final String LOGIN_PASSWORD_EMAIL_KEY = "login.error.password.email";
		public static final String LOGIN_PASSWORD_KEY = "login.error.password";
		public static final String LOGIN_EMAIL_KEY = "login.error.email";
		public static final String ERR_CANNOT_EXECUTE_OPERATION = "application.error.dbexception";
		public static final String REGISTER_FIRSTNAME_KEY = "register.error.firstName";
		public static final String REGISTER_LASTNAME_KEY = "register.error.lastName";
		public static final String REGISTER_EMAIL_KEY = "register.error.email";
		public static final String REGISTER_PHONE_KEY = "register.error.phone";
		public static final String REGISTER_DATE_OF_BIRTH__KEY = "register.error.dateOfBirth";
		public static final String REGISTER_ROLE = "register.error.role";
		public static final String REGISTER_CATEGORY = "register.error.category";
		public static final String REGISTER_MAIL_KEY = "register.error.mail";
		public static final String REGISTER_NOTICE_KEY = "register.error.notice";
		public static final String FIND_DOCTORS_KEY = "find.error.doctors";
		public static final String FIND_PATIENTS_KEY = "find.error.patients";
		public static final String FIND_DOCTORS_PATIENTS_KEY = "find.error.doctors.patients";
		public static final String CHOOSE_DOCTOR_KEY = "choose.error.doctor";
		public static final String CHOOSE_PATIENT_KEY = "choose.error.patient";
		public static final String FOUND_DOCTOR_KEY = "found.error.doctor";
		public static final String FOUND_PATIENT_KEY = "found.error.patient";
		public static final String ADD_ANAMNESIS_KEY = "add.error.anamnesis";
		public static final String EXISTS_ANAMNESIS_KEY = "exist.error.anamnesis";
		public static final String ADD_SERVICE_KEY = "add.error.service";
		public static final String ADD_DIAGNOSIS_KEY = "add.error.diagnosis";
		public static final String ADD_SERVICE_HIDDEN_PARAMS_KEY = "param.error.service";
		public static final String ADD_DIAGNOSIS_HIDDEN_PARAMS_KEY = "param.error.diagnosis";
		public static final String UPLOAD_PHOTO_SIZE_KEY = "upload.error.size";
		public static final String UPLOAD_PHOTO_EXTENSION_KEY = "upload.error.extension";
		public static final String UPLOAD_PHOTO_IMPOSSIBLE_KEY = "upload.error.impossible";
		public static final String FIND_TASKS_KEY = "find.error.tasks";
		public static final String FIND_MYCARDS_KEY = "find.error.mycards";

		private ErrorConstants() {
		}
	}

	/**
	 * Success message keys.
	 */
	public final class SuccessConstants {
		public static final String REGISTER_MAIL_KEY = "register.success.mail";
		public static final String REGISTER_NOTICE_KEY = "register.success.notice";
		public static final String ASSIGN_NOTICE_KEY = "assign.success.notice";

		private SuccessConstants() {
		}
	}

	/**
	 * Session attributes
	 */
	public final class SessionAttributes {
		public static final String LANG_ATTR = "language";
		public static final String USER_ATTR = "userInfo";
		public static final String REGISTRATION_USER_BEAN = "regUserBean";
		public static final String LOGIN_USER_BEAN = "loginUserBean";
		public static final String LIST_OF_DOCTORS = "doctorList";
		public static final String LIST_OF_PATIENTS = "patientList";
		public static final String LIST_OF_NEW_PATIENTS = "newPatientList";
		public static final String DATALIST_OF_DOCTORS = "doctorDataList";
		public static final String DATALIST_OF_PATIENTS = "patientDataList";
		public static final String LIST_OF_MYPATIENTS = "myPatientList";
		public static final String CARD_OF_MYPATIENT = "myPatientCard";
		public static final String LIST_OF_TASKS = "taskList";
		public static final String LIST_OF_MY_CARDS = "myCardsList";

		private SessionAttributes() {
		}
	}

	/**
	 * Paths of pages
	 *
	 */
	public static final class PagePath {
		public static final String DEFAULT_PATH = "/ST4";
		public static final String BASE_PATH = "/WEB-INF/jsp";
		public static final String SIG_IN_PAGE = "/index.jsp";
		public static final String ACCOUNT_PAGE = "/account.jsp";

		private PagePath() {
		}
	}

	/**
	 * Language values.
	 */
	public static final class LangValues {
		public static final String RU_VALUE = "ru";
		public static final String EN_VALUE = "en";
		public static final String DEFAULT_VALUE = "ru";

		private LangValues() {
		}
	}

	/**
	 * The connection values.
	 */
	public static final class Connection {
		public static final String OBJECT_TO_LOCK_UP_NAME = "java:comp/env/jdbc/hospital";

		private Connection() {
		}
	}

	/**
	 * SQL queries
	 */
	public static final class Query {
		public static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM `users` WHERE `email` like ? ";
		public static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM `users` WHERE `iduser` = ? ";
		public static final String FIND_USERS_BY_IDROLE_QUERY = "SELECT * FROM `users` WHERE `idrole` = ? ";
		public static final String ADD_USER_QUERY = "INSERT INTO `users`(`email`,`password`, `idrole`, `lastname`, `firstname`, `phone`, `dateofbirth`,`idcategory`) VALUES (?,?,?,?,?,?,?,?)";
		public static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
		public static final String FIND_ALL_ANAMNESIS = "SELECT * FROM anamnesis";
		public static final String FIND_ALL_ANAMNESIS_BY_IDSTATUS = "SELECT * FROM anamnesis WHERE idstatus=?";
		public static final String FIND_ALL_BY_IDSTATUS_AND_IDPATIENT = "SELECT * FROM anamnesis WHERE idstatus=? AND idpatient=?";
		public static final String FIND_ALL_BY_IDSTATUS_AND_IDDOCTOR = "SELECT * FROM anamnesis WHERE idstatus=? AND iddoctor=?";
		public static final String FIND_ANAMNESIS_BY_IDANAMNESIS = "SELECT * FROM anamnesis WHERE idanamnesis=?";
		public static final String ADD_ANAMNESIS_QUERY = "INSERT INTO `anamnesis` VALUES (DEFAULT, ?, ?,?,?,?,DEFAULT)";
		public static final String FIND_ALLSERVHISTORY_BY_IDANAMNESIS = "SELECT * FROM historyservices WHERE idanamnesis=?";
		public static final String ADD_SERVICE_HISTORY_QUERY = "INSERT INTO `historyservices` VALUES (DEFAULT,?,?,?,?,?)";
		public static final String UPDATE_INITIAL_DIAGNOSIS_QUERY = "UPDATE anamnesis SET initialdiagnosis =? WHERE IDanamnesis = ?";
		public static final String UPDATE_FINAL_DIAGNOSIS_QUERY = "UPDATE anamnesis SET finaldiagnosis =? WHERE IDanamnesis = ?";
		public static final String UPDATE_ANAMNESIS_STATUS_QUERY = "UPDATE anamnesis SET idstatus =? WHERE IDanamnesis = ?";
		public static final String FIND_ALLSERVHISTORY_BY_IDSTATUS = "SELECT * FROM historyservices WHERE idstatus=?";
		public static final String FIND_SERVHISTORY_BY_ID = "SELECT * FROM historyservices WHERE idrecord=?";
		public static final String UPDATE_SERVICE_STATUS_QUERY = "UPDATE historyservices SET idstatus =? WHERE idrecord = ?";

		private Query() {
		}
	}

	/**
	 * Application messages
	 *
	 */
	public static final class Messages {
		public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";
		public static final String ERR_USER_ALREADY_EXISTS = "User alredy exits";
		public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
		public static final String ERR_CANNOT_OBTAIN_USER_BY_EMAIL = "Cannot obtain a user by its email";
		public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";
		public static final String ERR_CANNOT_OBTAIN_USERS_BY_IDROLE = "Cannot obtain users by role id";
		public static final String ERR_CANNOT_OBTAIN_ALL_USERS = "Cannot obtain all users";
		public static final String ERR_CANNOT_INSERT_USER = "Cannot insert a user";
		public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";
		public static final String ERR_CANNOT_ROLLBACK_TRANSACTION = "Cannot rollback transaction";
		public static final String ERR_SHA_PASSWORD = "shaNoSuchAlgorithm";
		public static final String ERR_CANNOT_EXECUTE_OPERATION = "Can not execute operation";
		public static final String ERR_CANNOT_OBTAIN_ALL_ANAMNESIS = "Cannot obtain all anamnesis";
		public static final String ERR_CANNOT_OBTAIN_ALL_ANAMNESIS_BY_IDSTATUS = "Cannot obtain all anamnesis by status id";
		public static final String ERR_CANNOT_OBTAIN_ALL_ANAMNESIS_BY_IDSTATUS_AND_IDPATIENT = "Cannot obtain anamnesises by status and patient id";
		public static final String ERR_CANNOT_OBTAIN_ALL_ANAMNESIS_BY_IDSTATUS_AND_IDDOCTOR = "Cannot obtain anamnesises by status and doctor id";
		public static final String ERR_CANNOT_OBTAIN_ANAMNESIS_BY_IDANAMNESIS = "Cannot obtain anamnesis by its id";
		public static final String ERR_CANNOT_OBTAIN_ALL_SERVHISTORY_BY_IDANAMNESIS = "Cannot obtain all servhistories by anamnesis id";
		public static final String ERR_CANNOT_INSERT_SERVICEHISTORY = "Cannot insert a service history";
		public static final String ERR_CANNOT_UPDATE_INITIAL_DIAGNOSIS = "Cannot update initial diagnosis";
		public static final String ERR_CANNOT_UPDATE_FINAL_DIAGNOSIS = "Cannot update final diagnosis";
		public static final String ERR_CANNOT_UPDATE_ANAMNESIS_STATUS = "Cannot update anamnesis status";
		public static final String ERR_CANNOT_OBTAIN_ALL_SERVHISTORY_BY_IDSTATUS = "Cannot obtain all servhistories by status id";
		public static final String ERR_CANNOT_OBTAIN_SERVHISTORY_BY_ID = "Cannot obtain servhistory by its id";
		public static final String ERR_CANNOT_UPDATE_SERVICE_STATUS = "Cannot update service status";
		public static final String ERR_FILE_SIZE_TOO_BIG = "File size exceeds maximum file size of ";

		private Messages() {
		}
	}

	/**
	 * Mailer parameters
	 */
	public static final class Mailer {
		public static final String USERNAME = "tasksummary4@gmail.com";
		public static final String PASSWORD = "ha160886gai";
		public static final String FROM_ADDRESS = "tasksummary4@gmail.com";
		public static final String FROM_NAME = "Hospital";
		public static final String TITLE_ACC_INFO = "You login and password";

		private Mailer() {
		}

	}

	/**
	 * Sorting constants.
	 */
	public static final class SortConstants {
		public static final String SORT_DOCTORS_BY_PATIENT_NUMBER = "sortDoctorsByPatientNumber";
		public static final String SORT_DOCTORS_BY_ALPHABET = "sortDoctorsByAlphabet";
		public static final String SORT_DOCTORS_BY_CATEGORY = "sortDoctorsByCategory";
		public static final String SORT_PATIENTS_BY_ALPHABET = "sortPatientsByAlphabet";
		public static final String SORT_PATIENTS_BY_DATE_OF_BIRTH = "sortPatientsByDateOfBirth";
		public static final String SORT_PATIENTS_BY_DOCTOR = "sortPatientsByDoctor";

		private SortConstants() {
		}
	}

}
