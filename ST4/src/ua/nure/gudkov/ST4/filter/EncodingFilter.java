package ua.nure.gudkov.ST4.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.constant.Constants;

/**
 * Encoding filter.
 * 
 * @author A.Gudkov
 *
 */
public class EncodingFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

	private String encoding;

	public void destroy() {
		LOG.debug("Filter destruction starts");
		LOG.debug("Filter destruction finished");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LOG.debug("Filter starts");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		LOG.trace("Request uri --> " + httpRequest.getRequestURI());

		String requestEncoding = request.getCharacterEncoding();
		if (requestEncoding == null) {
			LOG.trace("Request encoding = null, set encoding --> " + encoding);
			request.setCharacterEncoding(encoding);
		}

		LOG.debug("Filter finished");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("Filter initialization starts");
		encoding = fConfig.getInitParameter(Constants.FilterConstants.ENCODING);
		LOG.trace("Encoding from web.xml --> " + encoding);
		LOG.debug("Filter initialization finished");
	}

}
