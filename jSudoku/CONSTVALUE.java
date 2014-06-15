package jSudoku;

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
 * File: CONSTVALUE.java
 * Project: JSudoku
 * Date: 5/2008
 * Synopsis: This class holds constant variables for JSudoku Game and Game maker applications.
 * No instance of this class should be made.
 * 
 */

public class CONSTVALUE {

	/* event/error handling related constants */
	public static final int ABNORMAL_TERMINATION = 1;
	public static final int NORMAL_TERMINATION = 0;
	
	/* file names and path related constants */
	public static final String RESOURCES_PATH = "Resources/";
	public static final String JAR_RESOURCES_PATH = "/Resources/";
	public static final String PROGRAM_ICON = "logo.png";
	public static final String FILEOPEN_ICON = "menu_icon/fldr_obj.gif";
	public static final String FILENEW_ICON = "menu_icon/new_wiz.gif";
	public static final String FILESAVE_ICON = "menu_icon/save_edit.gif";
	public static final String FILESAVEAS_ICON = "menu_icon/saveas_edit.gif";
	public static final String FILECLOSE_ICON = "menu_icon/clear_co.gif";
	public static final String PUZZLELOAD_ICON = "menu_icon/keygroups_obj.gif";
	public static final String GAMENEW_ICON = "menu_icon/new_wiz.gif";
	public static final String GAMEJOIN_ICON = "menu_icon/import_wiz.gif";
	public static final String GAMELEAVE_ICON = "menu_icon/export_wiz.gif";
	public static final String TSCONNECT_ICON = "menu_icon/link_obj.gif";
	public static final String TSDISCONNECT_ICON = "menu_icon/disconnect_co.gif";
	public static final String HELPUSERID_ICON = "menu_icon/help_view.gif";
	public static final String HELPCHECK_ICON = "menu_icon/selected_mode.gif";
	public static final String HELPABOUT_ICON = "menu_icon/linkto_help.gif";
	public static final String COLOR_ON_ICON = "menu_icon/color.gif";
	public static final String COLOR_OFF_ICON = "menu_icon/color_off.gif";
	public static final String LOG_ON_ICON = "menu_icon/log.gif";
	public static final String LOG_OFF_ICON = "menu_icon/log_off.gif";
	public static final String PEN_BTN_ICON = "button_icon/pen.png";
	public static final String ERASER_BTN_ICON = "button_icon/eraser.png";
	public static final String PENCIL_BTN_ICON = "button_icon/pencil.png";
	public static final String POINTER_BTN_ICON = "button_icon/pointer.png";
	public static final String HIGHLIGHTER_BTN_ICON = "button_icon/highlighter.png";
	public static final String MOUSE_BTN_ICON = "button_icon/mouse.png";
	public static final String MOUSE_BLACK_ICON = "mouse_pointer/BLACK.png";
	public static final String MOUSE_RED_ICON = "mouse_pointer/RED.png";
	public static final String MOUSE_BLUE_ICON = "mouse_pointer/BLUE.png";
	public static final String MOUSE_GREEN_ICON = "mouse_pointer/GREEN.png";
	public static final String MOUSE_PINK_ICON = "mouse_pointer/PINK.png";
	public static final String MOUSE_ORANGE_ICON = "mouse_pointer/ORANGE.png";
	public static final String MOUSE_MAGENTA_ICON = "mouse_pointer/MAGENTA.png";
	public static final String MOUSE_YELLOW_ICON = "mouse_pointer/YELLOW.png";
	public static final String NO_TOOL_ICON = "button_icon/no.png";
	public static final String B0_BTN_ICON = "button_icon/0.png";
	public static final String B1_BTN_ICON = "button_icon/1.png";
	public static final String B2_BTN_ICON = "button_icon/2.png";
	public static final String B3_BTN_ICON = "button_icon/3.png";
	public static final String B4_BTN_ICON = "button_icon/4.png";
	public static final String B5_BTN_ICON = "button_icon/5.png";
	public static final String B6_BTN_ICON = "button_icon/6.png";
	public static final String B7_BTN_ICON = "button_icon/7.png";
	public static final String B8_BTN_ICON = "button_icon/8.png";
	public static final String B9_BTN_ICON = "button_icon/9.png";
	public static final String B0H_BTN_ICON = "button_icon/0h.png";
	public static final String B1H_BTN_ICON = "button_icon/1h.png";
	public static final String B2H_BTN_ICON = "button_icon/2h.png";
	public static final String B3H_BTN_ICON = "button_icon/3h.png";
	public static final String B4H_BTN_ICON = "button_icon/4h.png";
	public static final String B5H_BTN_ICON = "button_icon/5h.png";
	public static final String B6H_BTN_ICON = "button_icon/6h.png";
	public static final String B7H_BTN_ICON = "button_icon/7h.png";
	public static final String B8H_BTN_ICON = "button_icon/8h.png";
	public static final String B9H_BTN_ICON = "button_icon/9h.png";
	public static final String USER_BW_BTN_ICON = "button_icon/BW.png";
	public static final String USER_RED_BTN_ICON = "button_icon/RED.png";
	public static final String USER_BLUE_BTN_ICON = "button_icon/BLUE.png";
	public static final String USER_GREEN_BTN_ICON = "button_icon/GREEN.png";
	public static final String USER_ORANGE_BTN_ICON = "button_icon/ORANGE.png";
	public static final String USER_PINK_BTN_ICON = "button_icon/PINK.png";
	public static final String USER_MAGENTA_BTN_ICON = "button_icon/MAGENTA.png";
	public static final String USER_YELLOW_BTN_ICON = "button_icon/YELLOW.png";
	public static final String UNDO_ICON = "button_icon/undo.png";
	public static final String REDO_ICON = "button_icon/redo.png";
	public static final String FEATURE_ICON = "button_icon/stop.png";
	
	/* UI related constants */
	public static final int PUZZLE_GRID_OFFSET = 50;
	public static final int PUZZLE_GRID_WIDTH = 500;
	public static final int PUZZLE_GRID_HEIGHT = 500;
	public static final int BOTTOM_COMPONENT_PANEL_WIDTH = 470;
	public static final int BOTTOM_COMPONENT_PANEL_HEIGHT = 190;
	public static final int MSG_LABEL_HEIGHT = 30;
	public static final int BUTTON_ICON_SIZE = 48;
	public static final int BUTTON_PANEL_OFFSET = 10;
	
	
	/* program logic related constants */
	
	/* debug flag */
	public static final boolean _DEBUG_FLAG_LEVEL_1_ = true;
	public static final boolean _DEBUG_FLAG_LEVEL_2_ = false;
	public static final boolean _DEBUG_FLAG_LEVEL_3_ = false;
	public static final boolean _DEBUG_FLAG_LEVEL_4_ = false;
	public static final boolean _DEBUG_FLAG_LEVEL_5_ = false;
	
	/* platform independent values */
	public static final String NEWLINE = System.getProperty("line.separator");
	public static final String FILESEP = System.getProperty("file.separator");
	
	/* This class is a repository of const values used through out the program */
	/* No instance should be created. */

	private CONSTVALUE() {
		return;
	}
}