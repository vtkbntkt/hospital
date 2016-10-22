package ua.nure.gudkov.ST4.bean.app;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Page bean implementation. Contains path of a page to be shown and appropriate
 * action. Also contains BufferedImage object to show image.
 * 
 * @author A.Gudkov
 *
 */
public class PageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String path;
	private Action action;
	private BufferedImage bufferedImage;

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param path
	 *            page path
	 * @param action
	 *            to be performed
	 */
	public PageBean(String path, Action action) {
		this.path = path;
		this.action = action;
	}

	/**
	 * Constructs an empty bean.
	 */
	public PageBean() {
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	/*
	 * action to be performed by application
	 */
	public enum Action {
		REDIRECT, FORWARD, DRAW;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "PageBean [path=" + path + ", action=" + action + ", bufferedImage=" + bufferedImage + "]";
	}

}
