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
public class Professeur implements Enseignant {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String nomComplet;

   public Professeur() {
   }

   public Professeur(String nomComplet) {
      this.nomComplet = nomComplet;
   }

   @Override
   public String toString() {
      return "Professeur : " + nomComplet;
   }
}
