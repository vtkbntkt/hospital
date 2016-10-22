package ua.nure.gudkov.ST4.converter.api;

/**
 * Converter interface.
 * 
 * @author A.Gudkov
 *
 */
public interface Converter<T, E> {
	/**
	 * Converts one object to another.
	 *
	 * @param t
	 *            the given object
	 * @return the converted object
	 */
	E convert(T t);
}
