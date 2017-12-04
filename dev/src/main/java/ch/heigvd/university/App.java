package ch.heigvd.university;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class App {

   public static void main(String... args) {
      System.out.println("At this moment he knew he f'd up");

      SessionFactory sessionFactory = new Configuration()
              .configure()
              .buildSessionFactory();

      //Session session = HibernateUtil.getSessionFactory().openSession();
   }
}
