package com.senai.apigastsix.repositories;

import com.senai.apigastsix.models.ServicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServicoRepository extends JpaRepository<ServicoModel, UUID> {
}
