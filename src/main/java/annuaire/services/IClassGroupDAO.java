package annuaire.services;


import annuaire.model.ClassGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IClassGroupDAO extends CrudRepository<ClassGroup, Long> {
    // Récupérer depuis l'id
    ClassGroup findById(long id);

    // Récupérer depuis le nom
    List<ClassGroup> findByNameContains(String name);

    // récupérer les groupes
    List<ClassGroup> findAll();

    // ajoute le groupe en base de données
    ClassGroup save(ClassGroup c);

    // vide la table
    void deleteAll();

    /*
    // modification d'un groupe
    void updateClassGroup(ClassGroup g);
    */
}
