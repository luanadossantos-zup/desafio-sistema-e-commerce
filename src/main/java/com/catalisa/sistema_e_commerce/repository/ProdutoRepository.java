package com.catalisa.sistema_e_commerce.repository;

import com.catalisa.sistema_e_commerce.models.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {
}
