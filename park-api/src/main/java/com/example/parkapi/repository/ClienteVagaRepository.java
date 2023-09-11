package com.example.parkapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parkapi.entity.ClienteVaga;
import com.example.parkapi.repository.projection.ClienteVagaProjection;

public interface ClienteVagaRepository extends JpaRepository<ClienteVaga, Long>{

	Optional<ClienteVaga> findByReciboAndDataSaidaIsNull(String recibo);

	long countByClienteCpfAndDataSaidaIsNotNull(String cpf);

	Page<ClienteVagaProjection> findAllByClienteCpf(String cpf, Pageable pageable);

}
