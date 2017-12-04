package ch.heigvd.university.entity;
import java.util.Date;

public class Etudiant {
 	private int id;
	private String prenom;
	private String nom;
	private Date dateInscription;
   
   public Etudiant(){}

   public Etudiant(String prenom, String nom, Date dateInscription) {
      this.prenom = prenom;
      this.nom = nom;
      this.dateInscription = dateInscription;
   }

   public int getId() {
      return id;
   }

   public String getNom() {
      return nom;
   }

   public String getPrenom() {
      return prenom;
   }

   public Date getDateInscription() {
      return dateInscription;
   }   
}
