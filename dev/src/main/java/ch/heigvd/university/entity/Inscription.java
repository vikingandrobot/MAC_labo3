package ch.heigvd.university.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inscription implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   private int coursId;

   private int etudiantId;

   public Inscription() {
   }

   public Inscription(int coursId, int etudiantId) {
      this.coursId = coursId;
      this.etudiantId = etudiantId;
   }
   
   public int getCoursId() {
      return coursId;
   }

   public int getEtudiantId() {
      return etudiantId;
   }

   public long getId() {
      return id;
   }

   public void setCoursId(int coursId) {
      this.coursId = coursId;
   }

   public void setEtudiantId(int etudiantId) {
      this.etudiantId = etudiantId;
   }

   public void setId(long id) {
      this.id = id;
   }

}
