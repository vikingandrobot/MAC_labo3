package ch.heigvd.university;

import ch.heigvd.university.entity.Cours;
import ch.heigvd.university.entity.Etudiant;
import ch.heigvd.university.entity.Inscription;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class App {
    
    public static void displayStudent(List<Etudiant> etudiants) {
        for (Etudiant e : etudiants) {
            System.out.println("*** " + e.getNom()
                    + " " + e.getPrenom());
        }
    }
    public static void displayStudent(Set<Etudiant> etudiants) {
        for (Etudiant e : etudiants) {
            System.out.println("*** " + e.getNom()
                    + " " + e.getPrenom());
        }
    }
    
    public static void displayStudentAndCours(List<Etudiant> etudiants) {
        for (Etudiant e : etudiants) {
            System.out.println("*** " + e.getNom()
                    + " " + e.getPrenom() + "\nCours : ");
            displayCours(e.getCours());
        }
    }
    
    public static void displayCours(List<Cours> cours) {
        for (Cours c : cours) {
            System.out.println("- id:" + c.getId() + " name:" + c.getTitre() + " credits:" + c.getCredits());
        }
    }
    
    public static void displayCours(Set<Cours> cours) {
        for (Cours c : cours) {
            System.out.println("- id:" + c.getId() + " name:" + c.getTitre() + " credits:" + c.getCredits());
        }
    }
    
    public static void displayInscriptions(List<Inscription> inscriptions) {
        for (Inscription i : inscriptions) {
            System.out.println(
                    "id: " + i.getId()
                    + " cours: <" + i.getCours().getId() + " " + i.getCours().getTitre() + ">"
                    + " etudiant: <" + i.getEtudiant().getId() + " " + i.getEtudiant().getNom() + ">"
                    + " grade: <" + i.getGrade() + ">"
            );
        }
    }
    
    private static final String[] NOMS = {"De la Vega", "Carlson", "PantHurth"};
    private static final String[] PRENOMS = {"Marie", "Carl", "John"};
    private static final String[] COURS = {"Bio Alchemy", "Star Flying", "Magic"};
    
    private static SessionFactory sessionFactory;

    /**
     * Creates data in the database. Creates three students and three courses.
     */
    private static void createData() {
        // Open a new session and begin a new transaction
        List<Etudiant> etudiants = new LinkedList<>();
        List<Cours> cours = new LinkedList<>();
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Create a bunch of students and courses
        for (int i = 0; i < 3; ++i) {
            etudiants.add(new Etudiant(PRENOMS[i], NOMS[i], new Date()));
            session.save(etudiants.get(i));
            
            cours.add(new Cours(COURS[i], (i + 1) * 2));
            session.save(cours.get(i));
        }
        
        etudiants.get(0).ajouterCours(cours.get(0));
        etudiants.get(0).ajouterCours(cours.get(1));
        etudiants.get(0).ajouterCours(cours.get(2));
        etudiants.get(1).ajouterCours(cours.get(0));
        etudiants.get(1).ajouterCours(cours.get(1));
        etudiants.get(2).ajouterCours(cours.get(2));
        etudiants.get(2).ajouterCours(cours.get(1));

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
        List etudiants = session.createQuery("FROM Etudiant").list();
        
        System.out.append("\n********** READ **********\n");
        System.out.println("\nListe des étudiants : ");
        displayStudent(etudiants);
        
        System.out.println("\nListe des cours : ");
        List cours = session.createQuery("FROM Cours").list();
        displayCours((List<Cours>) cours);
        
        System.out.println("\nListe des étudiants et leurs cours");
        List listEtudiants = session.createQuery("FROM Etudiant").list();
        displayStudentAndCours(etudiants);

        // Commit the transaction and close the session
        session.getTransaction().commit();
        session.close();
    }
    
    private static void deleteData() {
        // Open a new session and begin a new transaction
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.append("\n********** DELETE **********\n");

        // Query the name of the students and display them
        List etudiants = session.createQuery("FROM Etudiant").list();
        
        System.out.println("Etudiants avant suppression : \n");
        displayStudent(etudiants);
        
        System.out.println("\nInscriptions avant suppression : ");
        List inscriptions = session.createQuery("FROM Inscription").list();
        displayInscriptions(inscriptions);
        
        Etudiant etudiant = (Etudiant) etudiants.get(0);
        System.out.println("\nSuppression de l'étudiant : " + etudiant.getId() + ". " + etudiant.getNom());
        session.delete(etudiant);
        
        etudiants = session.createQuery("FROM Etudiant").list();
        System.out.println("\nEtudiants après suppression : ");
        displayStudent(etudiants);
        
        System.out.println("\nInscriptions après suppression: ");
        inscriptions = session.createQuery("FROM Inscription").list();
        displayInscriptions(inscriptions);

        // Commit the transaction and close the session
        session.getTransaction().commit();
        session.close();
    }
    
    public static void testEtape3() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        System.out.append("\n********** ETAPE 3 **********\n");
        
        List<Etudiant> etudiants = session.createQuery("FROM Etudiant").list();
        List<Cours> cours = session.createQuery("FROM Cours").list();
        Etudiant etudiant = etudiants.get(0);
        
        System.out.println("Liste des inscriptions :");
        displayInscriptions(session.createQuery("FROM Inscription").list());
        
        session.getTransaction().commit();
                
        System.out.println("\nListe des cours sans note pour " + etudiant.getNom());
        displayCours(etudiant.coursNonCredites(session));
               
        System.out.println("\nAttribution de la note 5 pour le cours" + cours.get(0).getTitre());
        etudiant.attribuerGrade(cours.get(0), '5', session);
        System.out.println("\nListe des inscriptions de " + etudiant.getNom());
        displayInscriptions(session.createQuery("FROM Inscription WHERE etudiant_id = " + etudiant.getId()).list());
        
        try {
            System.out.println("\nAttribution de la note 3 pour le cours " + cours.get(0).getTitre());
            etudiant.attribuerGrade(cours.get(2), '3', session);
        } catch (IllegalAccessError e) {
            System.out.println("L'étudiant " + etudiant.getNom()
                    + " n'est pas inscrit au cours <"
                    + +cours.get(2).getId() + " " + cours.get(2).getTitre() + ">");
        }
        
        for (Cours c : cours) {
            System.out.println("\nListe des étuidants du cours " + c.getTitre());
            Set<Etudiant> listAll = c.getEtudiants();
            displayStudent(listAll);
            System.out.println("Etudiants sans note");
            List<Etudiant> list = c.etudiantsEnAttente(session);
            if(list.isEmpty()){
                System.out.println(" - ");
            }else{
                displayStudent(list);
            }
        }
        
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
        } catch (HibernateException ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        // Test program that creates students and courses and then displays them
        createData();
        readData();
        deleteData();
        testEtape3();
        
        System.out.println("\n");
        sessionFactory.close();
    }
}
