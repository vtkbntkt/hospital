package ua.nure.gudkov.ST4.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.constant.Constants;

/**
 * File uploading filter.
 * 
 * @author A.Gudkov
 *
 */
public class UploadFileFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(UploadFileFilter.class);
	private long maxFileSize;
	private List<String> extensions = new ArrayList<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.debug("Filter initialization starts");

		String maxFileSize = filterConfig.getInitParameter(Constants.FilterConstants.MAX_FILE_SIZE);
		this.maxFileSize = Long.parseLong(maxFileSize);
		LOG.trace("Max file size from web.xml --> " + maxFileSize);

		extensions = asList(filterConfig.getInitParameter(Constants.FilterConstants.FILE_EXTENSION));
		LOG.trace("File extensions --> " + extensions);

		LOG.debug("Filter initialization finished");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOG.debug("Filter starts");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		LOG.trace("Request uri --> " + httpRequest.getRequestURI());

		HttpServletRequest parsedRequest = parseRequest(httpRequest);
		chain.doFilter(parsedRequest, response);

	}

	@Override
	public void destroy() {
		LOG.debug("Filter destruction starts");
		LOG.debug("Filter destruction finished");

	}

	/**
	 * Parse the given HttpServletRequest. If the request is a multipart
	 * request, then all multipart request items will be processed, else the
	 * request will be returned unchanged. During the processing of all
	 * multipart request items, the name and value of each regular form field
	 * will be added to the parameterMap of the HttpServletRequest. The name and
	 * File object of each form file field will be added as attribute of the
	 * given HttpServletRequest. If a FileUploadException has occurred when the
	 * file size has exceeded the maximum file size, then the
	 * FileUploadException will be added as attribute value instead of the
	 * FileItem object.
	 * 
	 * @param request
	 *            The HttpServletRequest to be checked and parsed as multipart
	 *            request.
	 * @return The parsed HttpServletRequest.
	 * @throws ServletException
	 *             If parsing of the given HttpServletRequest fails.
	 */
	private HttpServletRequest parseRequest(HttpServletRequest request) throws ServletException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			LOG.trace("Is not multipart content");
			return request;
		}
		List<FileItem> multipartItems = null;
		try {
			multipartItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			LOG.trace("Content was obtained ---> " + multipartItems);

		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request: " + e.getMessage());
		}

		Map<String, String[]> parameterMap = new HashMap<String, String[]>();

		for (FileItem multipartItem : multipartItems) {
			if (multipartItem.isFormField()) {
				processFormField(multipartItem, parameterMap);
			} else {
				processFileField(multipartItem, request);
			}
		}
		return wrapRequest(request, parameterMap);
	}

	/**
	 * Process multipart request item as regular form field. The name and value
	 * of each regular form field will be added to the given parameterMap.
	 * 
	 * @param formField
	 *            The form field to be processed.
	 * @param parameterMap
	 *            The parameterMap to be used for the HttpServletRequest.
	 */
	private void processFormField(FileItem formField, Map<String, String[]> parameterMap) {
		String name = formField.getFieldName();
		LOG.trace("field name ---> " + name);

		String value = formField.getString();
		LOG.trace("value ---> " + value);

		String[] values = parameterMap.get(name);
		LOG.trace("values ---> " + values);

		if (values == null) {
			parameterMap.put(name, new String[] { value });
		} else {
			int length = values.length;
			String[] newValues = new String[length + 1];
			System.arraycopy(values, 0, newValues, 0, length);
			newValues[length] = value;
			parameterMap.put(name, newValues);
		}
	}

	/**
	 * Process multipart request item as file field. The name and FileItem
	 * object of each file field will be added as attribute of the given
	 * HttpServletRequest. If a FileUploadException has occurred when the file
	 * size has exceeded the maximum file size, then the FileUploadException
	 * will be added as attribute value instead of the FileItem object.
	 * 
	 * @param fileField
	 *            The file field to be processed.
	 * @param request
	 *            The involved HttpServletRequest.
	 */
	private void processFileField(FileItem fileField, HttpServletRequest request) {
		if (fileField.getName().length() <= 0) {
			request.setAttribute(fileField.getFieldName(), null);
			LOG.trace("no file uploaded ---> ");

		} else if (maxFileSize > 0 && fileField.getSize() > maxFileSize) {
			request.setAttribute(fileField.getFieldName(),
					new FileUploadException(Constants.Messages.ERR_FILE_SIZE_TOO_BIG));
			fileField.delete();
			LOG.trace("File size exceeds maximum file size --->" + fileField.getSize());

		} else if (!isValidExtension(fileField)) {
			request.setAttribute(fileField.getFieldName(), null);
			LOG.trace("Invalid extension ---> ");

		} else {
			LOG.trace("File uploaded with good size and extension");
			request.setAttribute(fileField.getFieldName(), fileField);
		}
	}

	private boolean isValidExtension(FileItem fileField) {
		String filename = FilenameUtils.getName(fileField.getName());
		String fileExtension = FilenameUtils.getExtension(filename);
		LOG.trace("File extension ---> " + fileExtension);

		if (extensions.contains(fileExtension)) {
			return true;
		}
		return false;
	}

	/**
	 * Wrap the given HttpServletRequest with the given parameterMap.
	 * 
	 * @param request
	 *            The HttpServletRequest of which the given parameterMap have to
	 *            be wrapped in.
	 * @param parameterMap
	 *            The parameterMap to be wrapped in the given
	 *            HttpServletRequest.
	 * @return The HttpServletRequest with the parameterMap wrapped in.
	 */
	private HttpServletRequest wrapRequest(HttpServletRequest request, final Map<String, String[]> parameterMap) {
		return new HttpServletRequestWrapper(request) {
			public Map<String, String[]> getParameterMap() {
				return parameterMap;
			}

			public String[] getParameterValues(String name) {
				return parameterMap.get(name);
			}

			public String getParameter(String name) {
				String[] params = getParameterValues(name);
				return params != null && params.length > 0 ? params[0] : null;
			}

			public Enumeration<String> getParameterNames() {
				return Collections.enumeration(parameterMap.keySet());
			}
		};
	}

	/**
	 * Extracts parameter values from string.
	 * 
	 * @param str
	 *            parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}

}
