package ch.heigvd.university.students_MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ch.heigvd.university.controllers.StudentsController;

public class StudentsGUIMainView extends JPanel {
	
	private JLabel warningLabel = new JLabel();
	private JLabel errorMessage = new JLabel();

    private StudentsController studentsController; 
 
	private StudentsListView studentsListView;

	private JPanel secondaryPanel;

	public StudentsGUIMainView(StudentsController controller) {
		this.studentsController=controller; 
        setLayout (new BorderLayout());


		JPanel tools_warnings = new JPanel();	// Barre d'outils & warnings
		tools_warnings.setLayout(new GridLayout(1, 2));   // 1 Ligne - 2 col
		add(tools_warnings, BorderLayout.NORTH);


		JPanel tools = new JPanel();			// Outils à gauche
		tools.setLayout(new FlowLayout(FlowLayout.LEFT));
		tools_warnings.add(tools);

		JPanel warnings = new JPanel(); 		// Warnings à droite
		warnings.setLayout(new FlowLayout(FlowLayout.RIGHT));
        warnings.add(warningLabel);
		tools_warnings.add(warnings);

		JPanel error_messages = new JPanel();	// Messages d'erreur (textes d'exception)
		error_messages.setLayout(new FlowLayout(FlowLayout.LEFT));
		error_messages.add(errorMessage);
		add(error_messages, BorderLayout.SOUTH);

		JPanel gui = new JPanel();			// Panneau pour les données et les dialogues
		gui.setLayout(new GridLayout(1, 2));
		add(gui, BorderLayout.CENTER);

		// Définition de la barre d'outils
		Icon refreshIcon = new ImageIcon(StudentsGUIMainView.class.getResource("/images/Reload-icon.png"));
		JButton firstButton = new JButton(refreshIcon);
		firstButton.addActionListener (new ActionListener(){
			public void actionPerformed(ActionEvent e){
				studentsController.refreshStudentsList();
			}
		});
		tools.add(firstButton);

		Icon addIcon = new ImageIcon(StudentsGUIMainView.class.getResource("/images/Add-icon.png"));
		JButton secondButton = new JButton(addIcon);
		secondButton.addActionListener (new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//JOptionPane.showMessageDialog(secondButton, "Ajouter un etudiant " );
  		    	studentsController.addStudent();
  		    }
		});
		tools.add(secondButton);

		
		// Définition du panneau pour les données et les dialogues
		studentsListView = new StudentsListView(studentsController);
		gui.add(studentsListView);

		secondaryPanel = new JPanel();
		secondaryPanel.setLayout(new BorderLayout(10, 0));
		gui.add(secondaryPanel);

	}
	 
    public Dimension getPreferredSize() {
		return new Dimension (900, 320);
	}  

	public StudentsListView getListView(){
		return studentsListView;
	}

	public void setSecondaryPanel(JPanel contenu){
		secondaryPanel.removeAll();
		if (contenu != null){
			//secondaryPanel.add(contenu, BorderLayout.NORTH)
			secondaryPanel.add(contenu, BorderLayout.CENTER);
		}
		else{
			secondaryPanel.add(new JPanel(), BorderLayout.CENTER);
		}
		secondaryPanel.validate();
	}

	public void setErrorMessage(String text, String exception_text) {
		Icon warningIcon = new ImageIcon(StudentsGUIMainView.class.getResource("/images/Error-icon.png"));
		warningLabel.setIcon (warningIcon);
		warningLabel.setText ("<html><body><br/><h3 style=\"font-style:normal; color:#DC143C\">"+text+"</h3></body></html>");
		errorMessage.setText ("<html><body><br/><h4 style=\"font-style:normal; color:#DC143C\">"+exception_text+"</h4></body></html>");
	}
	
	public void setWarningMessage(String text) {
		Icon warningIcon = new ImageIcon(StudentsGUIMainView.class.getResource("/images/Warning-icon.png"));
		warningLabel.setIcon (warningIcon);
		warningLabel.setText ("<html><body><br/><h3 style=\"font-style:normal; color:#FF8C00\">"+text+"</h3></body></html>");
	}
	
	public void setAcknoledgeMessage(String text) {
		Icon okIcon = new ImageIcon(StudentsGUIMainView.class.getResource("/images/Success-icon.png"));
		warningLabel.setText ("<html><body><br/><h3 style=\"font-style:normal; color:green\">"+text+"</h3></body></html>");
		warningLabel.setIcon (okIcon);
	}
	
	public void resetMessage() {
		Icon okIcon = new ImageIcon(StudentsGUIMainView.class.getResource("/images/Success-icon.png"));
		warningLabel.setText ("<html><body><br/><h3 style=\"font-style:normal; color:green\">"+"Tout Ok"+"</h3></body></html>");
		warningLabel.setIcon (okIcon);
		errorMessage.setText ("<html><body><br/><h4 style=\"font-style:normal; color:#DC143C\">"+""+"</h4></body></html>");
	}
}