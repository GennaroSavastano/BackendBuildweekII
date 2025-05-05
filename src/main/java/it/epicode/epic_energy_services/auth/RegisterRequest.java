package it.epicode.epic_energy_services.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
