package com.catalisa.sistema_e_commerce.repository;

import com.catalisa.sistema_e_commerce.models.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

    boolean existsByEmail(String email);
}
