package com.library.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nama;
    private String email;
    private String password;
    private String noAnggota;
    private Boolean statusAktif;
}