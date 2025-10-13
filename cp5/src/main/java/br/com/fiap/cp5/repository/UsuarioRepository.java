package br.com.fiap.cp5.repository;

import br.com.fiap.cp5.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    public Optional<UsuarioEntity> findByLogin(String login);
}
