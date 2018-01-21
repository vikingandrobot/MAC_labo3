package ch.heigvd.university.models;

import ch.heigvd.university.entity.Enseignant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.*;
import org.hibernate.Session;

@Entity
public class Etudiant implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String prenom;

    private String nom;

    @OneToMany(targetEntity = Inscription.class, fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}, mappedBy = "etudiant")
    private Set<Inscription> inscriptions = new HashSet();

    private LocalDate dateInscription;

    public Etudiant() {
    }

    public Etudiant(String prenom, String nom, LocalDate dateInscription) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateInscription = dateInscription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void ajouterCours(Cours cours) {
        inscriptions.add(new Inscription(cours, this));
    }

    public Set<Cours> getCours() {
        Set<Cours> cours = new HashSet();
        for (Inscription ins : inscriptions) {
            cours.add(ins.getCours());
        }
        return cours;
    }

    public void attribuerGrade(Cours cours, char grade, Session session) {
        session.beginTransaction();
        
        if (getCours().contains(cours)) {
            for(Inscription i : inscriptions){
                if(cours.getId() == i.getCours().getId()){
                    i.setGrade(grade);
                }
            }
            session.getTransaction().commit();
        } else {
            session.getTransaction().commit();
            throw new IllegalAccessError();
        }       
    }

    public List<Cours> coursNonCredites(Session session) {
        session.beginTransaction();
        List<Cours> listCours = new LinkedList<>();
        for (Inscription i : inscriptions) {
            if (i.getGrade() == '-') {
                listCours.add(i.getCours());
            }
        }
        session.getTransaction().commit();
        return listCours;
    }
  
     public List<Enseignant> getEnseignants(Session session) {
       List<Enseignant> enseignants = new LinkedList<>();
       /*une fonction getEnseignants qui retournera la liste des enseignants 
       subis par l’étudiant qui reçoit le message*/
       List<Cours> cours = 
              session.createQuery("Select c"
              + " from Etudiant as e "
              + " join e.inscriptions as i with e.id = "+ getId()
              + " join i.cours as c with c.enseignant != null "
              + " group by c.enseignant"
              ).list();
       
        for(Cours c : cours){
           enseignants.add(c.getEnseignant());
        }
       return enseignants;
    }
    
    @Override
    public String toString(){
        return "nom: " + nom + " prénom: " + prenom;
    }
}
