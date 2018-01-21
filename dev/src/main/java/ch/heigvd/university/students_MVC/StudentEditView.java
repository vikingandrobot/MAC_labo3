package ch.heigvd.university.students_MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import ch.heigvd.university.models.*;
import ch.heigvd.university.controllers.StudentsController;

public class StudentEditView extends JPanel{

	private StudentsController studentsController;
	private Etudiant etudiant;

	private JLabel espacement = new JLabel("      ");
	
	private JLabel labelPrenom = new JLabel("Prenom");
	private JLabel labelNom = new JLabel("Nom");
	private JLabel labelDateInscription = new JLabel("Date inscription");
	
	private JTextField inputPrenom = new JTextField();
	private JTextField inputNom = new JTextField();
	private JLabel valeurDateInscription = new JLabel();

	
	private JButton acceptButton = new JButton("Appliquer");
	private JButton clearButton = new JButton("Annuler");


	public StudentEditView(StudentsController ce, final Etudiant etudiant) {
		studentsController= ce;
		this.etudiant = etudiant;

		inputPrenom.setForeground(new Color(0x00008B));
		inputNom.setForeground(new Color(0x00008B));

		this.setLayout(new BorderLayout());
		JLabel vueTitre = new JLabel("<html><body><br/><h2 style=\"font-style:normal; color:black\">"+"Edition etudiant " + etudiant.getId() +"</h2></body></html>");
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
		           .addComponent(labelDateInscription)
				   .addComponent(clearButton))
			  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			       .addComponent(inputPrenom)
			       .addComponent(inputNom)
			       .addComponent(valeurDateInscription)
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
		      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		           .addComponent(labelDateInscription)
		           .addComponent(valeurDateInscription))
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
					etudiant.setPrenom(prenom);
					etudiant.setNom(nom);
					studentsController.updateStudent(etudiant);
				}
				catch(Exception ex) {System.out.println (ex.toString());}
			}
		});

		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				inputPrenom.setText("");
				inputNom.setText("");
				studentsController.cancelEdition();
			}
		});

		setInfo(etudiant);
	}

	public void setInfo(Etudiant etudiant){
		inputPrenom.setText( etudiant.getPrenom());
		inputNom.setText( etudiant.getNom());
		LocalDate date = etudiant.getDateInscription();
		DateTimeFormatter myFormater = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		valeurDateInscription.setText(date.format(myFormater));
	}

}