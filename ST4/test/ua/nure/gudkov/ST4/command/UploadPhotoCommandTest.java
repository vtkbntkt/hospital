package ua.nure.gudkov.ST4.command;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.constant.Constants;

public class UploadPhotoCommandTest extends Mockito {
	private Map<String, String> params = new HashMap<String, String>();

	@Test
	public void testExecute() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		new UploadPhotoCommand().execute(request, response);

		Map<String, String> errors = new HashMap<String, String>();
		errors.put(Constants.PhotoUploadingParameters.PHOTO_FILE_FIELD,
				Constants.ErrorConstants.UPLOAD_PHOTO_EXTENSION_KEY);
		params.put(Constants.GenericParameters.ID_PATIENT, "2016");
		when(request.getSession()).thenReturn(session);
		request = initRequest(request);
		new UploadPhotoCommand().execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.UPLOADING_PHOTO_ERRORS, errors);

		when(request.getAttribute(Constants.PhotoUploadingParameters.PHOTO_FILE_FIELD))
				.thenReturn(new FileUploadException());
		errors.clear();
		errors.put(Constants.PhotoUploadingParameters.PHOTO_FILE_FIELD, Constants.ErrorConstants.UPLOAD_PHOTO_SIZE_KEY);
		new UploadPhotoCommand().execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.UPLOADING_PHOTO_ERRORS, errors);

		FileItem fileItem = mock(FileItem.class);
		when(request.getAttribute(Constants.PhotoUploadingParameters.PHOTO_FILE_FIELD)).thenReturn(fileItem);
		when(fileItem.getName()).thenReturn("test.jpg");
		new UploadPhotoCommand().execute(request, response);
		
		errors.clear();
		errors.put(Constants.PhotoUploadingParameters.PHOTO_FILE_FIELD,
				Constants.ErrorConstants.UPLOAD_PHOTO_IMPOSSIBLE_KEY);
		doThrow(new IOException()).when(fileItem).write(any());
		new UploadPhotoCommand().execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.UPLOADING_PHOTO_ERRORS, errors);

	}

	public HttpServletRequest initRequest(HttpServletRequest request) {

		return new HttpServletRequestWrapper(request) {
			public String getParameter(String name) {
				return params.get(name);
			}
		};
	}

}
