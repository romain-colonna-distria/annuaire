package annuaire.services;


import annuaire.model.ClassGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface IClassGroupDAO extends CrudRepository<ClassGroup, Long> {
    // lire un groupe
    ClassGroup findById(long id);

    // récupérer les groupes
    Collection<ClassGroup> findAll();

    // ajoute le groupe en base de données
    ClassGroup save(ClassGroup c);

    // vide la table
    void deleteAll();

    /*
    // modification d'un groupe
    void updateClassGroup(ClassGroup g);
    */
}
