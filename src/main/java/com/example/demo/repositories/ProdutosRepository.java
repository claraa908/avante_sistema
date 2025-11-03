package com.example.demo.repositories;

import com.example.demo.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos,Long> {
    List<Produtos> findByCategoriaId(Long categoriaId);
}
