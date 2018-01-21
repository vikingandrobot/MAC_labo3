package students_MVC;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import models.*;
import controllers.StudentsController;

public class StudentShowView extends JPanel{

	private StudentsController studentsController;

	private JLabel espacement = new JLabel("       ");
	
	private JLabel labelPrenom = 	new JLabel("Prenom");
	private JLabel labelNom = 		new JLabel("Nom");
	private JLabel labelDate = 		new JLabel("Inscription");
	
	private JLabel valeurPrenom = 	new JLabel();
	private JLabel valeurNom = 		new JLabel();
	private JLabel valeurDate = 	new JLabel();


	public StudentShowView(StudentsController ce, Etudiant etudiant) {
		studentsController= ce;

		valeurPrenom.setForeground(new Color(0x00008B));
		valeurNom.setForeground(new Color(0x00008B));

		this.setLayout(new BorderLayout());
		JLabel vueTitre = new JLabel("<html><body><br/><h2 style=\"font-style:normal; color:black\">"+"Valeur etudiant " + etudiant.getId() +"</h2></body></html>");
		this.add(vueTitre, BorderLayout.NORTH);

		JPanel vueContenu = new JPanel();
		this.add (vueContenu, BorderLayout.CENTER);

		GroupLayout layout = new GroupLayout(vueContenu);
		vueContenu.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(  // Définition des colonnes selon l'axe horizontal
		   	layout.createSequentialGroup()
		      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		           	.addComponent(labelPrenom)
		           	.addComponent(labelNom)
		           	.addComponent(labelDate))
		      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		      		.addComponent(espacement))
			  	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		           	.addComponent(valeurPrenom)
		           	.addComponent(valeurNom)
		           	.addComponent(valeurDate))
				
		);
		layout.setVerticalGroup( // Définition des lignes selon l'axe vertical
		   	layout.createSequentialGroup()
		   	  	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            	.addComponent(labelPrenom)
	            	.addComponent(espacement)
	            	.addComponent(valeurPrenom))
		      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            	.addComponent(labelNom)
	            	.addComponent(espacement)
	            	.addComponent(valeurNom))	
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            	.addComponent(labelDate)
	            	.addComponent(espacement)
	            	.addComponent(valeurDate))		
		);

		setInfo(etudiant);

	}

	public void setInfo(Etudiant etudiant){
		valeurPrenom.setText( etudiant.getPrenom());
		valeurNom.setText( etudiant.getNom());
		LocalDate date = etudiant.getDateInscription();
		DateTimeFormatter myFormater = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		valeurDate.setText(date.format(myFormater));
	}

}