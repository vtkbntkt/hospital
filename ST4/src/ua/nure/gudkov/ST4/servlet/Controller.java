package ua.nure.gudkov.ST4.servlet;

import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.command.CommandContainer;

import ua.nure.gudkov.ST4.command.api.Command;
import ua.nure.gudkov.ST4.constant.Constants;

/**
 * Main servlet controller.
 * 
 * @author A.Gudkov
 * 
 */

@WebServlet("/Controller")
@MultipartConfig

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 8424014944730124930L;
	private CommandContainer commandContainer;

	public void init(ServletConfig config) throws ServletException {
		super.init();
		commandContainer = (CommandContainer) config.getServletContext()
				.getAttribute(Constants.ContextAttr.COMMAND_CONTAINER);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Main method of this controller.
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean page = null;
		Command command = commandContainer.getCommand(request);
		page = command.execute(request, response);
		dispatch(request, response, page);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, PageBean page)
			throws javax.servlet.ServletException, java.io.IOException {
		switch (page.getAction()) {
		case FORWARD:
			request.getRequestDispatcher(page.getPath()).forward(request, response);
			break;
		case REDIRECT:
			response.sendRedirect(page.getPath());
			break;
		case DRAW:
			OutputStream out = response.getOutputStream();
			ImageIO.write(page.getBufferedImage(), "jpg", out);
			out.close();
			break;
		}

	}

}
