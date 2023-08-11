package com.example.lembretesfinal.repository;

import com.example.lembretesfinal.entity.Lembrete;
import com.example.lembretesfinal.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long> {

    @Query("select exists (select l from Lembrete l where l.id = :id)")
    boolean doesExist(@Param("id") final Long id);

    @Query("select l from Lembrete l where l.pessoa.nome = :nome")
    List<Lembrete> findByNome(@Param("nome") final String nome);

    @Query("select p from Pessoa p where p.id = :id")
    Pessoa getPessoaById(@Param("id") final Long id);
}
