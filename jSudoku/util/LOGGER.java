package jSudoku.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Copyright 2008, POET Lab (Dr. Deborah Tatar) @ Virginia Tech, Blacksburg, VA
 *
 * Permission to use and modify this software and its documentation for
 * any purpose other than its incorporation into a commercial product is
 * hereby granted without fee.  Permission to copy and distribute this
 * software and its documentation only for non-commercial use is also
 * granted without fee, provided, however, that the above copyright notice
 * appear in all copies, that both that copyright notice and this permission
 * notice appear in supporting documentation, that the name of POET Lab 
 * (Dr. Deborah Tatar) not be used in advertising or publicity pertaining to
 * distribution of the software without specific, written prior permission,
 * and that the person doing the distribution notify POET Lab (Dr. Deborah Tatar) of
 * such distributions outside of his or her organization. POET Lab (Dr. Deborah Tatar)
 * makes no representations about the suitability of this software for
 * any purpose.  It is provided "as is" without express or implied warranty.
 * POET Lab (Dr. Deborah Tatar) requests notification of any modifications to this
 * software or its documentation.
 *
 * Send the following redistribution information:
 *
 *      Name:
 *      Organization:
 *      Address (postal and/or electronic):
 *
 * To:
 *      Dr. Deborah Tatar
 *      Computer Science Department
 *      Virginia Polytechnic Institute and State University
 *      Blacksburg, VA 24061
 *
 *      Joon Suk Lee
 *      Computer Science Department
 *      Virginia Polytechnic Institute and State University
 *      Blacksburg, VA 24061
 *
 *              or
 *
 *		tatar@cs.vt.edu
 *      joonlee@vt.edu
 *
 * We will acknowledge all electronic notifications.
 */

/**
 * 
 * @author: dolomite
 * File: LOGGER.java
 * Project: JSudoku
 * Date: 07/2008
 * Synopsis: LOG Utility Class
 *  
 */

public class LOGGER {

	private static LOGGER logger = null;
	private static BufferedWriter out = null;
	
	public static final String YYYY_FILTER = "yyyy";
	public static final String MONTH_FILTER = "MM";
	public static final String DATE_FILTER = "dd";
	public static final String TIME_FILTER = "HH:mm:ss";
	
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH-mm-ss";


// ******************************************************************************
//
// Constructor
//
// ******************************************************************************
	/**
	 * private default constructor - prevent others to create instances of this class.
	 * No instance should be created.
	 * 
	 * @param  none
	 * @return N/A
	 */	
	private LOGGER() {
	}
	
// ******************************************************************************
//
// Calendar Operations
//
// ******************************************************************************
	private static String getYYYY() {
		Calendar cal = Calendar.getInstance();		  

		SimpleDateFormat year = new SimpleDateFormat(YYYY_FILTER);
		return year.format(cal.getTime()).toString();
	}
	
	private static String getMM() {
		Calendar cal = Calendar.getInstance();		  

		SimpleDateFormat month = new SimpleDateFormat(MONTH_FILTER);		
		return month.format(cal.getTime()).toString();
	}
	
	private static String getDD() {
		Calendar cal = Calendar.getInstance();		  
		
		SimpleDateFormat dateOfMonth = new SimpleDateFormat(DATE_FILTER);		
		return dateOfMonth.format(cal.getTime()).toString();
	}

	private static String getTime() {
		Calendar cal = Calendar.getInstance();		  

		SimpleDateFormat timeOfDay = new SimpleDateFormat(TIME_FILTER);		
		return timeOfDay.format(cal.getTime()).toString();
	}

	public static String getDATE() {
		Calendar cal = Calendar.getInstance();		  

		SimpleDateFormat date = new SimpleDateFormat(DATE_FORMAT_NOW);
		return date.format(cal.getTime()).toString();
	}
	
	
// ******************************************************************************
//
// File Operations
//
// ******************************************************************************
	
	public static boolean open(String filename) {
		if (filename == null) return false;
		if (logger == null) {
			logger = new LOGGER();
		}
		
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			System.out.println(filename);
			out = new BufferedWriter(new FileWriter(filename));
			out.write("SUDOKU LOG file\n\n");
			out.write("Year: " + getYYYY() +"\n");
			out.write("Month: " + getMM() +"\n");
			out.write("Date: " + getDD() +"\n");
			out.write("******************************************\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return false;
	}
	
	public static void log(String str) {
		if (logger == null) return;
		
		try {
			if (out != null) {
				out.write("[" + getTime() + "]     ");
				out.write(str);
				out.write("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close() {
		if (logger == null) return;
		
		try {
			out.write("******************************************\n");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		out = null;
		logger = null;
	}
	
	public static void main(String[] args) {
		LOGGER.open("test");
		LOGGER.log("lllll");
		LOGGER.close();
		LOGGER.log("lllll");
	}
}
