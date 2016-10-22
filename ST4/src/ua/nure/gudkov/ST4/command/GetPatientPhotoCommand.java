package ua.nure.gudkov.ST4.command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.constant.Constants;

/**
 * Get patient`s photo command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class GetPatientPhotoCommand extends AbstractCommand {

	private static final Logger LOG = Logger.getLogger(GetPatientPhotoCommand.class);

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.DRAW);
		Integer idPatient = extractIdPatient(request);
		File file = new File(Constants.PhotoUploadingParameters.UPLOAD_DIRECTORY + File.separator + idPatient
				+ Constants.PhotoUploadingParameters.PHOTO_EXTENSION);
		LOG.trace("file ---> " + file);
		if (!file.exists()) {
			file = new File(Constants.PhotoUploadingParameters.UPLOAD_DIRECTORY + File.separator
					+ Constants.PhotoUploadingParameters.DEF_PHOTO);
			LOG.trace("read default photo  ---> ");
		}
		BufferedImage image = ImageIO.read(file);

		LOG.trace("image ---> " + image);
		pb.setBufferedImage(image);
		return pb;
	}

}
