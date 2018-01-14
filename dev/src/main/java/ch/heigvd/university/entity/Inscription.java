package ch.heigvd.university.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.*;

@Entity
public class Inscription implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_id", nullable = false)
    private Cours cours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;

    private char grade;

    public Inscription() {
    }

    public Inscription(Cours cours, Etudiant etudiant) {
        this.cours = cours;
        this.etudiant = etudiant;
        this.grade = '-';
    }

    public Cours getCours() {
        return cours;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public long getId() {
        return id;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

}
