package ua.nure.gudkov.ST4.entity;


/**
 * Category entity.
 * 
 * @author A.Gudkov
 *
 */
public enum Category {
	PEDIATRICIAN("pediatrician"), THERAPIST("therapist"), OTOLARYNGOLOGIST("otolaryngologist"), OPHTHALMOLOGIST("ophthalmologist"), 
	NEUROLOGIST("neurologist"), SURGEON("surgeon"), TRAUMATOLOGIST("traumatologist");
	
	private String value;

	Category(String value) {
			this.value = value;
		}

	
	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
