package students_MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentsMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	public StudentsMenuBar() {
		JMenu fileMenu = new JMenu ("File");
		JMenu helpMenu = new JMenu ("Help");
		JMenuItem quitOption = new JMenuItem ("Quit");
		
		// Construction du menu "File"
		add (fileMenu);
		fileMenu.add (quitOption);
		quitOption.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Construction du menu "Help"
		add (helpMenu);
	}
}
