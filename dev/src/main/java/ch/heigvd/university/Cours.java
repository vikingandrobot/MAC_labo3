package ch.heigvd.university;

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
