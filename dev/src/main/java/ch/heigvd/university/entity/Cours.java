package ch.heigvd.university.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cours")
public class Cours implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(name = "titre", nullable = false, length = 45)
   private String titre;

   @Column(name = "credits", nullable = false)
   private int credits;

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
}
