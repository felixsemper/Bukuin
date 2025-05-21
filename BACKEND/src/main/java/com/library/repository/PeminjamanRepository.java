package com.library.repository;

import com.library.entity.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, String> {
    List<Peminjaman> findByAnggotaId(String anggotaId);
}