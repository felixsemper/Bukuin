package com.library.repository;

import com.library.entity.Ulasan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UlasanRepository extends JpaRepository<Ulasan, String> {
    List<Ulasan> findByBukuIdBuku(String idBuku);
}