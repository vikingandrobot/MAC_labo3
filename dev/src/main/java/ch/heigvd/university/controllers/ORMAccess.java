// Auteur: Eric Lefrançois - Janvier. 2015
package ch.heigvd.university.controllers;

import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

// Du jar hibernate-core-4.3.5.Final
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import ch.heigvd.university.models.*;

//------------------------------------------------------------------------------
public class ORMAccess  {


   @SuppressWarnings("all") 

   private static SessionFactory sessionFactory;

   public ORMAccess() {
      sessionFactory = new Configuration()
            .configure()
            .buildSessionFactory();
   }

   public void peuplerLaBase() throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      List<Etudiant> etudiants = null;
      try {

         tx= session.beginTransaction();

         // COMPLETER A CET ENDROIT

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }
   }

   public List<Etudiant> GET_ETUDIANTS() throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      List<Etudiant> etudiants = null;
      try {

         tx= session.beginTransaction();

         // COMPLETER A CET ENDROIT

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }

      return etudiants;
   }

   public Etudiant GET_ETUDIANT(int studentId) throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      Etudiant etudiant = null;
      try {

         tx= session.beginTransaction();

         // COMPLETER A CET ENDROIT

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }
      return etudiant;
   }

   public void SAVE_ETUDIANT(Etudiant etudiant) throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      try {

         tx= session.beginTransaction();

         // COMPLETER A CET ENDROIT

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }
   }

   public void UPDATE_ETUDIANT(Etudiant etudiant) throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      try {

         tx= session.beginTransaction();

         // COMPLETER A CET ENDROIT

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }
   }

  public void DELETE_ETUDIANT(int studentId) throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      try {

         tx= session.beginTransaction();

         // COMPLETER A CET ENDROIT

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }
   }

   public static void terminate() {
        try {
            if ( sessionFactory != null ) {
               sessionFactory.close();
            }
        }
        catch(Exception e) {
            System.out.println (e.toString());
        }      
   }

}