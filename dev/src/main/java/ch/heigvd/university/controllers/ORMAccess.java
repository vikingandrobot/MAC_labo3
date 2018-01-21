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
import java.time.LocalDate;
import java.util.LinkedList;

//------------------------------------------------------------------------------
public class ORMAccess {

    @SuppressWarnings("all")

    private static SessionFactory sessionFactory;
    
    private List<Etudiant> etudiants;

    private static final String[] NOMS = {"De la Vega", "Carlson", "PantHurth"};
    private static final String[] PRENOMS = {"Marie", "Carl", "John"};
    private static final String[] COURS = {"Bio Alchemy", "Star Flying", "Magic"};
    private static final String[] COURS_EXTERIEUR = {"Botanic", "Chirurgie", "Theatre"};
    private static final String[] ECOLE = {"Ecole de la fleurs", "Ecole du bistouri"};

    public ORMAccess() {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public void peuplerLaBase() throws Exception {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();

            for (int i = 0; i < 3; ++i) {
                Etudiant e = new Etudiant(PRENOMS[i], NOMS[i], LocalDate.now());
                session.save(e);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                    System.out.println(e.toString());
                    throw e;
                } catch (HibernateException he) {
                    System.out.println(he.toString());
                    throw he;
                }
            }
        } finally {
            try {
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
    }

    public List<Etudiant> GET_ETUDIANTS() throws Exception {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Etudiant> etudiants = null;
        try {

            tx = session.beginTransaction();
            
            Query query = session.createQuery("select e from Etudiant as e"); 
            etudiants = query.list();

            // COMPLETER A CET ENDROIT
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                    System.out.println(e.toString());
                    throw e;
                } catch (HibernateException he) {
                    System.out.println(he.toString());
                    throw he;
                }
            }
        } finally {
            try {
                session.close();
            } catch (HibernateException he) {
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

            tx = session.beginTransaction();
            
            etudiant = (Etudiant) session.get(Etudiant.class, studentId);

            // COMPLETER A CET ENDROIT
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                    System.out.println(e.toString());
                    throw e;
                } catch (HibernateException he) {
                    System.out.println(he.toString());
                    throw he;
                }
            }
        } finally {
            try {
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
        return etudiant;
    }

    public void SAVE_ETUDIANT(Etudiant etudiant) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            
            session.save(etudiant);
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                    System.out.println(e.toString());
                    throw e;
                } catch (HibernateException he) {
                    System.out.println(he.toString());
                    throw he;
                }
            }
        } finally {
            try {
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
    }

    public void UPDATE_ETUDIANT(Etudiant etudiant) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            
            session.update(etudiant);
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                    System.out.println(e.toString());
                    throw e;
                } catch (HibernateException he) {
                    System.out.println(he.toString());
                    throw he;
                }
            }
        } finally {
            try {
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
    }

    public void DELETE_ETUDIANT(int studentId) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            
            Query q = session.createQuery("delete Etudiant where id = :id")
                    .setParameter("id", studentId);
            q.executeUpdate();
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                    System.out.println(e.toString());
                    throw e;
                } catch (HibernateException he) {
                    System.out.println(he.toString());
                    throw he;
                }
            }
        } finally {
            try {
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
    }

    public static void terminate() {
        try {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
