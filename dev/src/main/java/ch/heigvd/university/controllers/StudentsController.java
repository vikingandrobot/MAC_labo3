package ch.heigvd.university.controllers;


import java.util.*;
import javax.swing.*;
import org.hibernate.SessionFactory;

import ch.heigvd.university.models.*;
import ch.heigvd.university.students_MVC.StudentEditView;
import ch.heigvd.university.students_MVC.StudentNewView;
import ch.heigvd.university.students_MVC.StudentShowView;
import ch.heigvd.university.students_MVC.StudentsGUI;
import ch.heigvd.university.students_MVC.StudentsGUIMainView;
import ch.heigvd.university.students_MVC.StudentsListView;

public class StudentsController {

	private StudentsGUIMainView studentsMainView;	// Référence à la vue principale
	private StudentsGUI studentsGui;				// Référence à la fenêtre principale
	private StudentsListView listView;				// Référence sur la vue "Liste des étudiants"
	private JPanel secondaryView;					// Référence sur la vue secondaire - Show - Edit -..

	private ORMAccess ormAccess;						// Tampon avec l'ORM Hibernate
	private SessionFactory sessionFactory;

	public StudentsController(ORMAccess ormAccess){
		this.ormAccess=ormAccess;
		studentsMainView = new StudentsGUIMainView(this);
		studentsGui = new StudentsGUI(studentsMainView, this);
		listView = studentsMainView.getListView();
		internalStudentsListRefresh();
	}

	public void setSecondaryView(JPanel view) {
		secondaryView = view;
		studentsMainView.setSecondaryPanel(view); 	// Afficher la vue secondaire - Show - Edit -..
	}
	
	// REQUETES EN PROVENANCE DES VUES
 	// ------------------------------

 	public void cancelEdition(){
 		studentsMainView.resetMessage();
 		setSecondaryView(null);
 	}

 	public void cancelCreation(){
 		studentsMainView.resetMessage();
 		setSecondaryView(null);
 	}

	public void refreshStudentsList() {
		studentsMainView.resetMessage();
		setSecondaryView(null);
		try {
			List<Etudiant> students = ormAccess.GET_ETUDIANTS();	
			listView.resetWith(students);
			studentsMainView.setAcknoledgeMessage("Rafraichissement OK" );
		}
		catch (Exception e){
			studentsMainView.setErrorMessage("Liste des etudiants non accessible", e.toString());
		}	
	}

	public void internalStudentsListRefresh() {
		// Méthode appelée depuis le contrôleur lui-même - N'aura pas de message de succès
		studentsMainView.resetMessage();
		setSecondaryView(null);
		try {
			List<Etudiant> students = ormAccess.GET_ETUDIANTS();	
			listView.resetWith(students);
		}
		catch (Exception e){
			studentsMainView.setErrorMessage("Liste des etudiants non accessible", e.toString());
		}	
	}

 	public void addStudent() {
 		setSecondaryView(new StudentNewView(this));
 	}

 	public void editStudent(int studentId) {
		studentsMainView.resetMessage();
		setSecondaryView(null);
		try {
			Etudiant student = ormAccess.GET_ETUDIANT(studentId); 
			StudentEditView studentEditView= new StudentEditView(this, student);
			setSecondaryView(studentEditView);
		}
		catch (Exception e){
			studentsMainView.setErrorMessage("Etudiant "+studentId+ " non accessible", e.toString());
		}		
 	}



	public void showStudent(int studentId) {
		studentsMainView.resetMessage();
		setSecondaryView(null);
		try {
			Etudiant student = ormAccess.GET_ETUDIANT(studentId); 
			StudentShowView studentShowView= new StudentShowView(this, student);
			setSecondaryView(studentShowView);
		}
		catch (Exception e){
			studentsMainView.setErrorMessage("Etudiant "+studentId+ " non accessible", e.toString());
		}	
	}

	public void newStudent (Etudiant student){
		studentsMainView.resetMessage();
		setSecondaryView(null);
		try {
			ormAccess.SAVE_ETUDIANT(student);
			internalStudentsListRefresh();
		}
		catch (Exception e){
			studentsMainView.setErrorMessage("Enregistrement impossible", e.toString());
		}	
	}
	
	public void updateStudent (Etudiant student){
		studentsMainView.resetMessage();
		try {
			ormAccess.UPDATE_ETUDIANT(student);
			internalStudentsListRefresh();
		}
		catch (Exception e){
			studentsMainView.setErrorMessage("Mise a jour impossible", e.toString());
		}
	}

	public void deleteStudent(int studentId) {
		studentsMainView.resetMessage();
		try {
			ormAccess.DELETE_ETUDIANT(studentId); 
			internalStudentsListRefresh();
		}
		catch (Exception e){
			studentsMainView.setErrorMessage("Etudiant "+studentId+ " non accessible", e.toString());
		}	
	}

	public boolean isAlive() {return studentsGui != null;}

}