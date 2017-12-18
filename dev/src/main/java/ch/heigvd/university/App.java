package ch.heigvd.university;

import ch.heigvd.university.entity.Cours;
import ch.heigvd.university.entity.Etudiant;
import ch.heigvd.university.entity.Inscription;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class App {

   private static final String[] NOMS = {"De la Vega", "Carlson", "PantHurth"};
   private static final String[] PRENOMS = {"Marie", "Carl", "John"};
   private static final String[] COURS = {"Bio Alchemy", "Star Flying", "Magic"};

   private static SessionFactory sessionFactory;

   /**
    * Creates data in the database. Creates three students and three courses.
    */
   private static void createData() {
      // Open a new session and begin a new transaction
      
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      // Create a bunch of students and courses
      for (int i = 0; i < 3; ++i) {
         Etudiant etudiant = new Etudiant(PRENOMS[i], NOMS[i], new Date());
         session.save(etudiant);

         Cours cours = new Cours(COURS[i], (i + 1) * 2);
         session.save(cours);
      }

      // Commit the transaction and close the session
      session.getTransaction().commit();
      session.close();
   }

   /**
    * Read data from the database and display it in the console. Reads all
    * students and courses and displays their last name or title.
    */
   private static void readData() {
      // Open a new session and begin a new transaction
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      // Query the name of the students and display them
      Query query = session.createQuery("select p.nom from Etudiant as p");
      List<String> list = query.list();

      System.out.println("Affichage des étudiants: ");
      for (String nom : list) {
         System.out.println(nom);
      }

      System.out.println("");

      // Query the title of the courses and display them
      query = session.createQuery("select c.titre from Cours as c");
      list = query.list();

      System.out.println("Affichage des cours: ");
      for (String titre : list) {
         System.out.println(titre);
      }

      // Commit the transaction and close the session
      session.getTransaction().commit();
      session.close();
   }

   /**
    * Main program. Creates a bunch of students and courses in the DB, and then
    * display them back in the console after reading them from the DB.
    *
    * @param args
    */
   public static void main(String... args) {
       try {
        sessionFactory = new Configuration()
                   .configure()
                   .buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      // Test program that creates students and courses and then displays them
      createData();
      readData();
      
      sessionFactory.close();
   }
}
