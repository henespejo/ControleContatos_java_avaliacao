package com.projeto.controleContatos.repository;

import com.projeto.controleContatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query(value = "SELECT c FROM Contato c WHERE c.pessoa.id = :pPessoa", nativeQuery = false)
    List<Contato> buscaContatoPorPessoa(@Param(value = "pPessoa") Long idPessoa);
}
