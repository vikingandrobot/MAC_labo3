package ch.heigvd.university.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inscriptions")
public class Inscription implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(name = "cours_id", nullable = false)
   private int coursId;

   @Column(name = "etudiant_id", nullable = false)
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
