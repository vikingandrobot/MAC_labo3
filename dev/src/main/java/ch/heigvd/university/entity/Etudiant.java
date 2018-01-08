package ch.heigvd.university.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    @Temporal(TemporalType.DATE)
    private Date dateInscription;

    public Etudiant() {
    }

    public Etudiant(String prenom, String nom, Date dateInscription) {
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

    public void setDateInscription(Date dateInscription) {
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

    public Date getDateInscription() {
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
    
    @Override
    public String toString(){
        return "nom: " + nom + " prénom: " + prenom;
    }
}
