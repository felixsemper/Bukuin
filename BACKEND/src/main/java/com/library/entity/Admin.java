package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admins")
@Data
public class Admin extends User {
    private String idPegawai;
    private String jabatan;
}