package ch.heigvd.university.entity;

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
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cours implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String titre;

   private int credits;

   @Any(metaColumn = @Column(name = "enseignant_type"))
   @AnyMetaDef(idType = "int", metaType = "string",
           metaValues = {
               @MetaValue(targetEntity = Professeur.class, value = "PROFESSEUR"),
               @MetaValue(targetEntity = ChargeDeCours.class, value = "CHARGEDECOURS"),
           })
   @JoinColumn(name = "enseignant_id")
   private Enseignant enseignant;

   @OneToMany(targetEntity = Inscription.class, fetch = FetchType.LAZY,
           cascade = {CascadeType.ALL}, mappedBy = "cours")
   private Set<Inscription> inscriptions = new HashSet();

   public Cours() {
   }

   public Cours(String titre, int credits) {
      this.titre = titre;
      this.credits = credits;
   }

   public Cours(String titre, int credits, Enseignant enseignant) {
      this.titre = titre;
      this.credits = credits;
      this.enseignant = enseignant;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setTitre(String titre) {
      this.titre = titre;
   }

   public void setCredits(int credits) {
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

   public Set<Etudiant> getEtudiants() {
      Set<Etudiant> etudiants = new HashSet();
      for (Inscription ins : inscriptions) {
         etudiants.add(ins.getEtudiant());
      }
      return etudiants;
   }

   public List<Etudiant> etudiantsEnAttente(Session session) {
      List<Etudiant> list = new LinkedList<>();
      session.beginTransaction();
      for (Inscription i : inscriptions) {
         if (i.getGrade() == '-') {
            list.add(i.getEtudiant());
         }
      }
      session.getTransaction().commit();
      return list;
   }

   @Override
   public String toString() {
      return "Id: " + id + " nom: " + titre + " credits: " + credits + (enseignant != null ? " enseignant:" + enseignant : "");
   }
}
