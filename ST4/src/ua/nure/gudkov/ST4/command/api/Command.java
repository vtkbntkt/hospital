package ua.nure.gudkov.ST4.command.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.gudkov.ST4.bean.app.PageBean;

/**
 * Main interface for the Command pattern implementation.
 * 
 * @author A.Gudkov
 * 
 */
public interface Command {
	/**
	 * Execution method for command.
	 * 
	 * @return Address and action to go once the command is executed.
	 */
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
