package ch.heigvd.university.entity;
import java.util.Date;

public class Etudiant {
 	private int id;
	private String prenom;
	private String nom;
	private Date dateInscription;
   
   public Etudiant(){}

   public void setId(int id) {
      this.id = id;
   }

   public void setPrenom(String prenom) {
      this.prenom = prenom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public void setDateInscription(Date dateInscription) {
      this.dateInscription = dateInscription;
   }

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
