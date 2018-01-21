package ch.heigvd.university.students_MVC;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import controllers.StudentsController;


public class StudentsGUI {  
	
	private StudentsGUIMainView sgmp;
    private StudentsController studentsController;

    public StudentsGUI(StudentsGUIMainView mainView, StudentsController sctr) {
        sgmp = mainView;
        this.studentsController = sctr;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Etudiants");
        StudentsMenuBar menuBar = new StudentsMenuBar();

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        
        //Add content to the window.
        frame.add(sgmp, BorderLayout.CENTER);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
}