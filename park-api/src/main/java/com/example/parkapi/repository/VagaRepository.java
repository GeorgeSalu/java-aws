package com.example.parkapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parkapi.entity.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long>{

	Optional<Vaga> findByCodigo(String codigo);

	Optional<Vaga> findFirstByStatus(Vaga.StatusVaga statusVaga);

}
