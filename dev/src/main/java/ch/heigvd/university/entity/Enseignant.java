package ch.heigvd.university.entity;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public interface Enseignant {
   @Override
   public String toString();
}
