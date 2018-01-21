package students_MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;


import models.*;
import controllers.StudentsController;

public class StudentNewView extends JPanel{

	private StudentsController studentsController;

	private JLabel espacement = new JLabel("      ");
	
	private JLabel labelPrenom = new JLabel("Prenom");
	private JLabel labelNom = new JLabel("Nom");

	
	private JTextField inputPrenom = new JTextField();
	private JTextField inputNom = new JTextField();

	
	private JButton acceptButton = new JButton("Appliquer");
	private JButton clearButton = new JButton("Annuler");


	public StudentNewView(StudentsController ce) {
		studentsController= ce;

		inputPrenom.setForeground(new Color(0x00008B));
		inputNom.setForeground(new Color(0x00008B));

		this.setLayout(new BorderLayout());
		JLabel vueTitre = new JLabel("<html><body><br/><h2 style=\"font-style:normal; color:black\">"+"Nouvel etudiant " +"</h2></body></html>");
		this.add(vueTitre, BorderLayout.NORTH);

		JPanel vueContenu = new JPanel();
		this.add (vueContenu, BorderLayout.CENTER);

		GroupLayout layout = new GroupLayout(vueContenu);
		vueContenu.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		           .addComponent(labelPrenom)
		           .addComponent(labelNom)
				   .addComponent(clearButton))
			  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			       .addComponent(inputPrenom)
			       .addComponent(inputNom)
			       .addComponent(acceptButton))
			  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
			       .addComponent(espacement))
				
		);
		layout.setVerticalGroup(
		   layout.createSequentialGroup()
		   	  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	               .addComponent(labelPrenom)
	               .addComponent(inputPrenom))
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		           .addComponent(labelNom)
		           .addComponent(inputNom))
		      .addComponent(espacement)
			  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			       .addComponent(clearButton)
			       .addComponent(acceptButton))			
		);

		acceptButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					String prenom= inputPrenom.getText().trim();
					String nom= inputNom.getText().trim();
					studentsController.newStudent
						(new Etudiant(inputPrenom.getText(),
									  inputNom.getText(), 
									  LocalDate.now()));
				}
				catch(Exception ex) {System.out.println (ex.toString());}
			}
		});

		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				inputPrenom.setText("");
				inputNom.setText("");
				studentsController.cancelCreation();
			}
		});

	}


}