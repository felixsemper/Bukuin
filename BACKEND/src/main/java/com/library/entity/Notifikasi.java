package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "notifikasi")
@Data
public class Notifikasi {
    @Id
    private String idNotifikasi;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private String pesan;
    private Date tanggal;
    private Boolean statusBaca;
}