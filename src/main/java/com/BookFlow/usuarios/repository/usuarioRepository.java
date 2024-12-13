package com.BookFlow.usuarios.repository;

import com.BookFlow.usuarios.domain.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface usuarioRepository extends JpaRepository<usuario, Long> {
    Optional<usuario>findByEmailAndPassword(String email, String password);
    Optional<usuario> findByEmail(String email);
}
