package com.aula.pos.msproduto.repository;

import com.aula.pos.msproduto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Produto, Long> {
}
