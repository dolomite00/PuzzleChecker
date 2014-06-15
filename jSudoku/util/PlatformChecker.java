package jSudoku.util;

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
 * File: PlatformChecker.java
 * Project: JSudoku
 * Date: 06/2008
 * Synopsis: Platform Checking Utility Class
 *  
 */

public class PlatformChecker {

	/* platform related constants */
	public static final int MAC = 1;
	public static final int WIN = 2;
	
	/**
	 * checks the current platform OS.
	 * 
	 * @param  none
	 * @return int
	 */			
	public static int checkPlatform() {
		String platform = System.getProperty("os.name").toString();
//		if (platform.contains("Mac"))
			return MAC;
//		else
//			return WIN;
	}
}
