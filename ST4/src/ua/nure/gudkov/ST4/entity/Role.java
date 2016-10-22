package ua.nure.gudkov.ST4.entity;


/**
 * Role entity.
 * 
 * @author A.Gudkov
 *
 */
public enum Role {
	ADMIN("admin"), PATIENT("patient"), DOCTOR("doctor"), NURSE("nurse");

	private String value;

	Role(String value) {
		this.value = value;
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}

}
