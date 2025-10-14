package br.com.fiap.cp5.entity;

public enum UsuarioRoles {
    ADMIN("ADMIN"),
    USUARIO("USUARIO");

    private String role;

    UsuarioRoles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }


}
