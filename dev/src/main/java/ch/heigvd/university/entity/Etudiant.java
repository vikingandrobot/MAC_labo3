package ch.heigvd.university.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "etudiants")
public class Etudiant implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(name = "prenom", nullable = false, length = 45)
   private String prenom;

   @Column(name = "nom", nullable = false, length = 45)
   private String nom;

   @Column(name = "date_inscription", nullable = false)
   @Temporal(TemporalType.DATE)
   private Date dateInscription;

   public Etudiant() {
   }

   public Etudiant(String prenom, String nom, Date dateInscription) {
      this.prenom = prenom;
      this.nom = nom;
      this.dateInscription = dateInscription;
   }

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
