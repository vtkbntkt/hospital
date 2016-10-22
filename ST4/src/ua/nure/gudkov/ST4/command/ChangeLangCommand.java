package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;

/**
 * Change GUI language command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class ChangeLangCommand extends AbstractCommand {

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		HttpSession session = request.getSession(true);
		String lang = (String) request.getParameter(Constants.LangFormParameters.SELECTED_LANG);

		if (lang == null) {
			lang = Constants.LangValues.DEFAULT_VALUE;
		} else if (!lang.equals(Constants.LangValues.EN_VALUE) && !lang.equals(Constants.LangValues.RU_VALUE)) {
			lang = Constants.LangValues.DEFAULT_VALUE;

		}
		session.setAttribute(Constants.SessionAttributes.LANG_ATTR, lang);
		String path = request.getParameter(Constants.LangFormParameters.REFERRER_PATH);
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(path));
		return pb;
	}

}
