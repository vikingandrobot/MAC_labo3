package ch.heigvd.university.entity;

public class Cours {

   private int id;
   private String titre;
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
