package ua.nure.gudkov.ST4.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Holder for errors.
 * 
 * @author A.Gudkov
 *
 */
public class ErrorHolder {
	private Map<String, String> errors = new HashMap<>();

	public void add(String errorField, String errorMsg) {
		errors.put(errorField, errorMsg);
	}

	public void add(String key) {
		errors.put(key, key);
	}

	public void addAll(ErrorHolder errorHolder) {
		errors.putAll(errorHolder.getErrors());
	}

	public boolean isEmpty() {
		return errors.isEmpty();
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void clear() {
		errors.clear();
	}

}
