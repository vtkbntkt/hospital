package ua.nure.gudkov.ST4.entity;


/**
 * Service entity.
 * 
 * @author A.Gudkov
 *
 */
public enum Service {
	MANIPULATION("manipulation"), DRUG("drug"), SURGERY("surgery");

	private String value;

	Service(String value) {
		this.value = value;
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
