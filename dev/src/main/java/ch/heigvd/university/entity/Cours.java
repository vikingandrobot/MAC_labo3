package ch.heigvd.university.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.*;

@Entity
public class Cours implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String titre;

   private int credits;
   
   @OneToMany(targetEntity=Inscription.class, fetch= FetchType.LAZY,
           cascade={CascadeType.ALL},mappedBy="etudiant")
   private Set<Inscription> inscriptions = new HashSet();

   public Cours() {
   }

   public Cours(String titre, int credits) {
      this.titre = titre;
      this.credits = credits;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setTitre(String titre) {
      this.titre = titre;
   }

   public void setCredits(int credits) {
      this.credits = credits;
   }

   public int getCredits() {
      return credits;
   }

   public String getTitre() {
      return titre;
   }

   public int getId() {
      return id;
   }
   
   public Set<Etudiant> getEtudiants(){
       Set<Etudiant> etudiants = new HashSet();
       for(Inscription ins : inscriptions){
           etudiants.add(ins.getEtudiant());
       }
       return etudiants;
   }
}
