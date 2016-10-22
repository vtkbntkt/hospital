package ua.nure.gudkov.ST4.extractor.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Bean extractor interface.
 * 
 * @author A.Gudkov
 *
 */
public interface BeanExtractor<T> {
	/**
	 * Extracts bean from request.
	 * 
	 * @param request
	 *            the http servlet request
	 * @return the extracted bean
	 * @throws IOException
	 * @throws ServletException
	 */
	T extract(HttpServletRequest request) throws IOException, ServletException;

}