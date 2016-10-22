package ua.nure.gudkov.ST4.converter;

import java.util.HashMap;

import ua.nure.gudkov.ST4.constant.Constants;

/**
 * Path converter implementation.
 * 
 * @author A.Gudkov
 *
 */
public class PathConverter {
	private static PathConverter instance = null;
	private static final HashMap<String, String> pathCommands = new HashMap<String, String>();

	private PathConverter() {
		pathCommands.put(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE,
				Constants.Commands.SERVLET_GET_ACCOUNT_COMMAND);
	}

	/**
	 * Converts page path to command.
	 * 
	 * @param path
	 *            path of page
	 * @return command to perform defined task
	 */
	public String getPath(String path) {
		String command = pathCommands.get(path);
		if (command == null) {
			command = Constants.Commands.SERVLET_GET_LOGIN_COMMAND;
		}
		return command;
	}

	public static PathConverter getInstance() {
		if (instance == null) {
			instance = new PathConverter();
		}
		return instance;
	}

}
