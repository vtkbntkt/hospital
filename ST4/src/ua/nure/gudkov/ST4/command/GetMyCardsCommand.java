package ua.nure.gudkov.ST4.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.aggregation.UsersAnamnesisesBean;
import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.bean.dto.CardTitleBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.converter.UsersAnamnesisesBeanToCardTitleBeanConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.UserService;

/**
 * Get patient`s card command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class GetMyCardsCommand extends AbstractCommand {

	private static final Logger LOG = Logger.getLogger(GetMyCardsCommand.class);

	private AnamnesisService anamnesisService;
	private UserService userService;
	private Converter<UsersAnamnesisesBean, List<CardTitleBean>> converter;

	public GetMyCardsCommand(UserService userService, AnamnesisService anamnesisService) {
		this.userService = userService;
		this.anamnesisService = anamnesisService;
		converter = new UsersAnamnesisesBeanToCardTitleBeanConverter();

	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		List<User> doctors = new ArrayList<User>();
		List<CardTitleBean> cardTitles = new ArrayList<CardTitleBean>();
		List<Anamnesis> anamnesList = new ArrayList<Anamnesis>();
		ErrorHolder errorHolder = new ErrorHolder();
		HttpSession session = request.getSession();
		Long idPatient = extractIdUser(session);

		try {

			if (idPatient != null) {
				anamnesList = anamnesisService.findAllDischargedByIdPatient(idPatient);

				if (!anamnesList.isEmpty()) {
					LOG.trace("list of anamnesis got ----> " + ", " + anamnesList);

					doctors = userService.findAllDoctors();
					if (!doctors.isEmpty()) {
						LOG.trace("list of doctors got ----> " + ", " + doctors);

						cardTitles = converter.convert(new UsersAnamnesisesBean(anamnesList, doctors));
					}
				}

			}
		} catch (DBException e) {
			errorHolder.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, errorHolder.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			return pb;
		}

		if (!cardTitles.isEmpty()) {
			session.setAttribute(Constants.SessionAttributes.LIST_OF_MY_CARDS, cardTitles);
			LOG.trace("list of mycards ----> "+cardTitles);
		} else {
			errorHolder.add(Constants.MyCardsParameters.MSG_ERROR_MYCARDS, Constants.ErrorConstants.FIND_MYCARDS_KEY);
			session.setAttribute(Constants.ErrorConstants.MYCARDS_LIST_ERRORS, errorHolder.getErrors());
			//session.setAttribute(Constants.SessionAttributes.LIST_OF_MY_CARDS, cardTitles);
			LOG.trace("list of mycards is empty ----> ");
		}

		return pb;
	}

}
