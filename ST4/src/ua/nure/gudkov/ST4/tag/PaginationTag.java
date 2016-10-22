package ua.nure.gudkov.ST4.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.gudkov.ST4.constant.Constants;

/**
 * Pagination tag implementation.
 * 
 * @author A.Gudkov
 *
 */
public class PaginationTag extends SimpleTagSupport {

	private int listSize;
	private int resultNum;
	private int startIndex;
	private String command;

	public void setCommand(String command) {
		this.command = command;
	}

	public void doTag() throws JspException, IOException {
		int linkNum = (int) Math.ceil((double) listSize / (double) resultNum);
		int clickedIndex = (startIndex / resultNum + 1);
		getWriter().write(createPaginator(linkNum, clickedIndex));
	}

	/**
	 * Creates pagination links according number of links and index clicked
	 * link.
	 * 
	 * @param linkNum
	 *            number of links
	 * @param clickedIndex
	 *            index clicked link
	 * @return
	 */
	public String createPaginator(int linkNum, int clickedIndex) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= linkNum; i++) {

			int startPos = (i - 1) * resultNum;
			boolean isClicked = false;
			Link link = new Link(command, i);
			link.getParams().put(Constants.GenericParameters.START_INDEX, Integer.toString(startPos));
			if (i == clickedIndex) {
				isClicked = true;
			}
			sb.append(link.createLink(isClicked));
			sb.append(" ");
		}

		return sb.toString();
	}

	private Writer getWriter() {
		JspWriter out = getJspContext().getOut();
		return out;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public void setResultNum(int resultNum) {
		this.resultNum = resultNum;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * Represents link that allows send get request to the controller.
	 * 
	 * @author A.Gudkov
	 *
	 */
	public static class Link {
		/*
		 * Constants representing pieces of link
		 */
		private final static String SERVLET = "Controller?";
		private final static String COMMAND = "command=";
		private final static String DEF_CLASS = "pList";
		private final static String HREF_TAG = "href=";
		private final static String DEF_CLICKED_COLOR = "green";
		private final static String CLASS_TAG = "class=";
		private final static String A_START_TAG = "<a";
		private final static String A_END_TAG = "</a>";
		private final static String CLICKED_STYLE_START = "style='background-color:";
		private final static String CLICKED_STYLE_END = ";'";

		private String linkClass;
		private String commandValue;
		private Map<String, String> params = new HashMap<String, String>();
		private String linkText;
		private String clickedColor;

		/**
		 * Constructs link with given arguments
		 * 
		 * @param commandValue
		 *            command value
		 * @param linkText
		 *            link text
		 */
		public Link(String commandValue, String linkText) {
			this.commandValue = commandValue;
			this.linkText = linkText;
		}

		/**
		 * Constructs link with given arguments
		 * 
		 * @param commandValue
		 *            command value
		 * @param linkText
		 *            link text
		 */
		public Link(String commandValue, int linkText) {
			this.commandValue = commandValue;
			this.linkText = Integer.toString(linkText);
		}

		/**
		 * Creates link to send get request to the controller.
		 * 
		 * @param isClicked
		 *            parameter to determine is the link clicked
		 * @return link
		 */
		public String createLink(boolean isClicked) {
			StringBuilder sb = new StringBuilder();
			sb.append(A_START_TAG).append(" ").append(HREF_TAG).append(SERVLET).append(COMMAND).append(commandValue);
			if (!params.isEmpty()) {
				sb.append("&");
				addParams(sb);
			}
			sb.append(" ");
			addClass(sb);
			if (isClicked) {
				addClickedStyle(sb);
			}
			sb.append(">").append(linkText).append(A_END_TAG);

			return sb.toString();
		}

		/**
		 * Adds style class in the link.
		 * 
		 * @param sb
		 *            link
		 */
		private void addClass(StringBuilder sb) {
			String linkcl = linkClass;
			if (linkcl == null) {
				linkcl = DEF_CLASS;
			}
			sb.append(CLASS_TAG).append("'").append(linkcl).append("'").append(" ");
		}

		/**
		 * Adds style attributes to make link as clicked.
		 * 
		 * @param sb
		 *            link
		 */
		private void addClickedStyle(StringBuilder sb) {
			String color = clickedColor;
			if (color == null) {
				color = DEF_CLICKED_COLOR;
			}
			sb.append(CLICKED_STYLE_START).append(color).append(CLICKED_STYLE_END).append(" ");

		}

		/**
		 * Adds request parameters in the link.
		 * 
		 * @param sb
		 *            link
		 */
		private void addParams(StringBuilder sb) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
			sb.deleteCharAt(sb.length() - 1);

		}

		public void setLinkClass(String linkClass) {
			this.linkClass = linkClass;
		}

		public void setCommandValue(String commandValue) {
			this.commandValue = commandValue;
		}

		public void setParams(Map<String, String> params) {
			this.params = params;
		}

		public void setLinkText(String linkText) {
			this.linkText = linkText;
		}

		public void setClickedColor(String clickedColor) {
			this.clickedColor = clickedColor;
		}

		public Map<String, String> getParams() {
			return params;
		}

	}

}
