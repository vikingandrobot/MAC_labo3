/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.university.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class ChargeDeCours implements Enseignant {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String prenom;
   private String nom;
   private String sigle;

   public ChargeDeCours() {
   }

   public ChargeDeCours(String prenom, String nom, String sigle) {
      this.prenom = prenom;
      this.nom = nom;
      this.sigle = sigle;
   }

   @Override
   public String toString() {
      return "Charger de cours: " + nom + " " + prenom + " sigle: " + sigle;
   }
}
