package com.galli.loja.repository;

import com.galli.loja.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(
            value = "select * from produto where categoria = :categoria",
            nativeQuery = true
    )
    List<Produto> findAllByCategoria(@Param("categoria") String categoria);

    @Query(
//            value = "select distinct categoria from produto order by categoria",
            value = "select distinct categoria from produto order by categoria",
            nativeQuery = true
    )
    List<String> findAllCategorias();
}
