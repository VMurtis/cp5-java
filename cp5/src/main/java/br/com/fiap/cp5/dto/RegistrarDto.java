package br.com.fiap.cp5.dto;

import br.com.fiap.cp5.entity.UsuarioRoles;

public record RegistrarDto(String login, String password, UsuarioRoles role) {
}
