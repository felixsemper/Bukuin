package com.library.repository;

import com.library.entity.Pengembalian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PengembalianRepository extends JpaRepository<Pengembalian, String> {
}