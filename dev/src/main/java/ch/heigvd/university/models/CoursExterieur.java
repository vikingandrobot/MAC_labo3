
package ch.heigvd.university.models;

import ch.heigvd.university.entity.Enseignant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CoursExterieur extends Cours{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     
    private String ecole;
    
    public CoursExterieur(){
        super();
    }
    
   public CoursExterieur(String titre, int credits, String ecole){
       super(titre, credits);
       this.ecole = ecole;
   }
   
   public CoursExterieur(String titre, int credits, Enseignant enseignant, String ecole){
       super(titre, credits, enseignant);
       this.ecole = ecole;
   }
   
   @Override
   public int getId(){
    return id;
   }
   
   @Override
   public String toString(){
       return super.toString() + " ecole: " + ecole;
   }
    
}
