package com.library.repository;

import com.library.entity.Notifikasi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotifikasiRepository extends JpaRepository<Notifikasi, String> {
    List<Notifikasi> findByUserId(String userId);
}