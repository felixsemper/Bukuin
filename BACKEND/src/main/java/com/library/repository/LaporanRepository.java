package com.library.repository;

import com.library.entity.Laporan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaporanRepository extends JpaRepository<Laporan, String> {
}