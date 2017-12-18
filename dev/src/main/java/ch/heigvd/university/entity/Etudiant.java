package ch.heigvd.university.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.*;

@Entity
public class Etudiant implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String prenom;

   private String nom;
 
   @OneToMany(targetEntity = Inscription.class, fetch= FetchType.LAZY,
           cascade={CascadeType.ALL},mappedBy="etudiant")
   private Set<Inscription> inscriptions = new HashSet();

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
   
   public void ajouterCours(Cours cours){
       inscriptions.add(new Inscription(cours, this));
   }
   
   public Set<Cours> getCours(){
      Set<Cours> cours = new HashSet();
       for(Inscription ins : inscriptions){
            cours.add(ins.getCours());
       }
       return cours;
   }
}
