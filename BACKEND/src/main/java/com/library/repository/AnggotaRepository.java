package com.library.repository;

import com.library.entity.Anggota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnggotaRepository extends JpaRepository<Anggota, String> {
    Anggota findByNoAnggota(String noAnggota);
}