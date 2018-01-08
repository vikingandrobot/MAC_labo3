
package ch.heigvd.university.entity;

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
     
    String ecole;
    
    public CoursExterieur(){
        super();
    }
    
   public CoursExterieur(String titre, int credits, String ecole){
       super(titre, credits);
       this.ecole = ecole;
   }
   
   @Override
   public String toString(){
       return super.toString() + " ecole: " + ecole;
   }
    
}
