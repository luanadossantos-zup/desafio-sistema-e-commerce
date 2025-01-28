package com.catalisa.sistema_e_commerce.repository;

import com.catalisa.sistema_e_commerce.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
