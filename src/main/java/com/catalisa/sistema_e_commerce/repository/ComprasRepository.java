package com.catalisa.sistema_e_commerce.repository;

import com.catalisa.sistema_e_commerce.models.ComprasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprasRepository extends JpaRepository<ComprasEntity, String> {
}
