package com.library.service;

import com.library.entity.Buku;
import com.library.entity.Pengembalian;
import com.library.entity.Peminjaman;
import com.library.repository.BukuRepository;
import com.library.repository.PengembalianRepository;
import com.library.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ReturnService {
    @Autowired
    private PengembalianRepository pengembalianRepository;

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private BukuRepository bukuRepository;

    public Pengembalian returnBook(String peminjamanId) {
        Peminjaman peminjaman = peminjamanRepository.findById(peminjamanId).orElseThrow();
        Pengembalian pengembalian = new Pengembalian();
        pengembalian.setIdPengembalian(UUID.randomUUID().toString());
        pengembalian.setPeminjaman(peminjaman);
        pengembalian.setTanggalKembali(new Date());

        long diff = new Date().getTime() - peminjaman.getBatasKembali().getTime();
        if (diff > 0) {
            pengembalian.setDenda(diff / (1000.0 * 60 * 60 * 24) * 1000); // 1000 per day late
        } else {
            pengembalian.setDenda(0.0);
        }

        Buku buku = peminjaman.getBuku();
        buku.setStatus(Buku.Status.TERSEDIA);
        bukuRepository.save(buku);

        return pengembalianRepository.save(pengembalian);
    }
}