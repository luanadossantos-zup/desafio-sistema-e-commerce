package com.catalisa.sistema_e_commerce.repository;

import com.catalisa.sistema_e_commerce.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {
}
