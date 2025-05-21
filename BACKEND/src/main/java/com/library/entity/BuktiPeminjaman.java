package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "bukti_peminjaman")
@Data
public class BuktiPeminjaman {
    @Id
    private String idBukti;

    @OneToOne
    @JoinColumn(name = "id_peminjaman")
    private Peminjaman peminjaman;

    private String kodePeminjaman;
    private Date tanggalDibuat;
    private Boolean statusValidasi;
}