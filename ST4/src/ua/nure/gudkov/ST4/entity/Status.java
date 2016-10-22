package ua.nure.gudkov.ST4.entity;


/**
 * Status entity.
 * 
 * @author A.Gudkov
 *
 */
public enum Status {
	TAKEN("taken"), DISCHARGED("discharged"), NEW("new"), PERFORMED("performed");

	private String value;

	Status(String value) {
		this.value = value;
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}

}
