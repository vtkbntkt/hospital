package ua.nure.gudkov.ST4.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Note creator.
 * 
 * @author A.Gudkov
 *
 */
public class NoteCreator {
	public static final String SEPARATOR = System.lineSeparator();

	/**
	 * Returns note as image. Allocate given content on prepared blank.
	 * 
	 * @param blankPath
	 *            blank path
	 * @param content
	 *            note content
	 * @param leftMargin
	 *            left margin
	 * @param topMargin
	 *            top margin
	 * @param color
	 *            color of the content
	 * @param font
	 *            font of the content
	 * @return note as image
	 * @throws IOException
	 *             if note can not be created
	 */
	public static BufferedImage createNote(String blankPath, String content, int leftMargin, int topMargin, Color color,
			Font font) throws IOException {
		BufferedImage image = ImageIO.read(new File(blankPath));
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(color);
		g2d.setFont(font);
		for (String line : content.split(SEPARATOR))
			g2d.drawString(line, leftMargin, topMargin += g2d.getFontMetrics().getHeight());
		g2d.dispose();
		return image;
	}

}
