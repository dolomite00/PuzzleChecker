package jSudoku.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.MouseInfo;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class screen2image {

	public static boolean capture(JFrame window) {
		Robot robot;
		
		//Get the screen size
		Rectangle rectangle = new Rectangle(window.getBounds());
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		BufferedImage screenShot = robot.createScreenCapture(rectangle);					
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh.mm.ss a");
   	 	String filename = dateFormat.format( new Date() ) + " at " + timeFormat.format( new Date() );;
        try {
			ImageIO.write(screenShot, "png", new File("Screen Shot " + filename + ".png"));			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
        
        return true;
	}
}
