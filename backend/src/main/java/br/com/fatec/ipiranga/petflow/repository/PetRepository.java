package br.com.fatec.ipiranga.petflow.repository;

import br.com.fatec.ipiranga.petflow.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {}