package com.example.lembretesfinal.repository;

import com.example.lembretesfinal.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select exists (select p from Pessoa p where p.id = :id)")
    boolean doesExist(@Param("id") final Long id);

    @Query("select exists (select l from Lembrete l where l.pessoa.id = :id)")
    boolean isInLembrete(@Param("id") final Long id);



}
