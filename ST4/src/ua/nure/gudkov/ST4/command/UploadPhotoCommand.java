package ua.nure.gudkov.ST4.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.ErrorHolder;

/**
 * Upload photo command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class UploadPhotoCommand extends AbstractCommand {

	private static final Logger LOG = Logger.getLogger(UploadPhotoCommand.class);

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(Constants.Commands.SERVLET_GET_ALL_PATIENTS_COMMAND);
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		Integer idPatient = extractIdPatient(request);
		if (idPatient == null) {
			LOG.trace("invalid idPatient ----> " + idPatient);
			return pb;
		}

		ErrorHolder errorHolder = new ErrorHolder();
		HttpSession session = request.getSession();
		Object fileObject = request.getAttribute(Constants.PhotoUploadingParameters.PHOTO_FILE_FIELD);
		if (fileObject == null) {
			errorHolder.add(Constants.PhotoUploadingParameters.PHOTO_FILE_FIELD,
					Constants.ErrorConstants.UPLOAD_PHOTO_EXTENSION_KEY);
			LOG.trace("Invalid extension photo or empty ----> ");

		} else if (fileObject instanceof FileUploadException) {
			errorHolder.add(Constants.PhotoUploadingParameters.PHOTO_FILE_FIELD,
					Constants.ErrorConstants.UPLOAD_PHOTO_SIZE_KEY);
			LOG.trace("File size exceeds maximum file size ----> ");
		}

		if (errorHolder.isEmpty()) {
			FileItem fileItem = (FileItem) fileObject;
			String fileName = FilenameUtils.getName(fileItem.getName());
			LOG.error("fileName "+fileName);
			String prefix = idPatient + "";
			String suffix = "." + FilenameUtils.getExtension(fileName);
			try {
				File file = new File(
						Constants.PhotoUploadingParameters.UPLOAD_DIRECTORY + File.separator + prefix + suffix);
				fileItem.write(file);
				LOG.trace("file " + file + " was written");
			} catch (Exception e) {
				errorHolder.add(Constants.PhotoUploadingParameters.PHOTO_FILE_FIELD,
						Constants.ErrorConstants.UPLOAD_PHOTO_IMPOSSIBLE_KEY);
				LOG.error(e);
			}
		}
		if (!errorHolder.isEmpty()) {
			session.setAttribute(Constants.ErrorConstants.UPLOADING_PHOTO_ERRORS, errorHolder.getErrors());
		}
		return pb;
	}

}
