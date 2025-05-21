package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "laporan")
@Data
public class Laporan {
    @Id
    private String idLaporan;

    @Enumerated(EnumType.STRING)
    private Jenis jenis;

    private Date tanggalDibuat;
    private String content;

    public enum Jenis {
        HARIAN, BULANAN
    }
}