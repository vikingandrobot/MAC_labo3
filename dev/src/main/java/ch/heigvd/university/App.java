package ch.heigvd.university;

import ch.heigvd.university.models.ChargeDeCours;
import ch.heigvd.university.models.Cours;
import ch.heigvd.university.models.CoursExterieur;
import ch.heigvd.university.entity.Enseignant;
import ch.heigvd.university.models.Etudiant;
import ch.heigvd.university.models.Inscription;
import ch.heigvd.university.models.Professeur;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class App {
    
    public static void displayStudent(List<Etudiant> etudiants) {
        for (Etudiant e : etudiants) {
            System.out.println("*** " + e);
        }
    }
    public static void displayStudent(Set<Etudiant> etudiants) {
        for (Etudiant e : etudiants) {
            System.out.println("*** " + e);
        }
    }
    
    public static void displayStudentAndCours(List<Etudiant> etudiants) {
        for (Etudiant e : etudiants) {
            System.out.println("*** Etudiant - " + e + "\nCours : ");
            displayCours(e.getCours());
            System.out.println("");
        }
    }
    
    public static void displayCours(List<Cours> cours) {
        for (Cours c : cours) {
            System.out.println("- " + c);
        }
    }
    
    public static void displayCours(Set<Cours> cours) {
        for (Cours c : cours) {
            System.out.println("- " + c);
        }
    }
    
    public static void displayInscriptions(List<Inscription> inscriptions) {
        for (Inscription i : inscriptions) {
            System.out.println(
                    "id: " + i.getId()
                    + " cours: <" + i.getCours()+ ">"
                    + " etudiant: <" + i.getEtudiant() + ">"
                    + " grade: <" + i.getGrade() + ">"
            );
        }
    }
        
    private static final String[] NOMS = {"De la Vega", "Carlson", "PantHurth"};
    private static final String[] PRENOMS = {"Marie", "Carl", "John"};
    private static final String[] COURS = {"Bio Alchemy", "Star Flying", "Magic"};
    private static final String[] COURS_EXTERIEUR = {"Botanic", "Chirurgie","Theatre"};
    private static final String[] ECOLE = {"Ecole de la fleurs", "Ecole du bistouri"};
    
    private static SessionFactory sessionFactory;

    /**
     * Creates data in the database. Creates three students and three courses.
     */
    private static void createData() {
        // Open a new session and begin a new transaction
        List<Etudiant> etudiants = new LinkedList<>();
        List<Cours> cours = new LinkedList<>();
        List<CoursExterieur> coursExterieur = new LinkedList<>();
        List<Enseignant> enseignants = new LinkedList<>();
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Create a bunch of students and courses
        for (int i = 0; i < 3; ++i) {
            etudiants.add(new Etudiant(PRENOMS[i], NOMS[i], new Date()));
            session.save(etudiants.get(i));
            
            cours.add(new Cours(COURS[i], (i + 1) * 2));
            session.save(cours.get(i));
        }
        
        enseignants.add(new Professeur("Pr. Dupont"));
        enseignants.add(new ChargeDeCours("Pierre", "Jack", "PJ"));
        session.save(enseignants.get(0));
        session.save(enseignants.get(1));
        
        coursExterieur.add(new CoursExterieur(COURS_EXTERIEUR[0], 2, enseignants.get(0), ECOLE[0]));
        coursExterieur.add(new CoursExterieur(COURS_EXTERIEUR[1], 2, enseignants.get(0), ECOLE[1]));
        coursExterieur.add(new CoursExterieur(COURS_EXTERIEUR[2], 2, enseignants.get(1), ECOLE[1]));
        coursExterieur.add(new CoursExterieur(COURS_EXTERIEUR[2], 3, enseignants.get(0), ECOLE[0]));
        session.save(coursExterieur.get(0));
        session.save(coursExterieur.get(1));
        session.save(coursExterieur.get(2));
        session.save(coursExterieur.get(3));
        
        etudiants.get(0).ajouterCours(cours.get(0));
        etudiants.get(0).ajouterCours(cours.get(1));
        etudiants.get(0).ajouterCours(cours.get(2));
        etudiants.get(1).ajouterCours(cours.get(0));
        etudiants.get(1).ajouterCours(cours.get(1));
        etudiants.get(2).ajouterCours(cours.get(2));
        etudiants.get(2).ajouterCours(cours.get(1));
        
        etudiants.get(1).ajouterCours(coursExterieur.get(0));
        etudiants.get(1).ajouterCours(coursExterieur.get(1));
        etudiants.get(1).ajouterCours(coursExterieur.get(2));
        etudiants.get(1).ajouterCours(coursExterieur.get(3));
        etudiants.get(2).ajouterCours(coursExterieur.get(1));
         
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

       public static void testEtape4() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        System.out.append("\n********** ETAPE 4 **********\n");
        List etudiants = session.createQuery("FROM Etudiant").list();
         
        System.out.println("\nListe des étudiants : ");
        displayStudent(etudiants);
        
        System.out.println("\nListe des cours : ");
        List cours = session.createQuery("FROM Cours").list();
        displayCours((List<Cours>) cours);
        
        System.out.println("\nListe des étudiants et leurs cours");
        List<Etudiant> listEtudiants = session.createQuery("FROM Etudiant").list();
        displayStudentAndCours(etudiants);
        
        System.out.println("*** Liste des enseignants de chaque étudiant\n");
        for(Etudiant e : listEtudiants) {
           System.out.println("L'étudiant " + e.getNom() + " a comme professeur : ");
       
           for(Enseignant ens : e.getEnseignants(session)){
               System.out.println(ens);
           }
           System.out.println("");
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
        testEtape4();
        
        sessionFactory.close();
    }
}
