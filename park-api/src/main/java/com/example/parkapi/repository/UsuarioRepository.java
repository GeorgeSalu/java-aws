package com.example.parkapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parkapi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
