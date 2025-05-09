package it.epicode.epic_energy_services.auth;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    String username;
    String email;
    String rawPassword;
    String nome;
    String cognome;
    String avatar;
    Set<Role> roles;
}
