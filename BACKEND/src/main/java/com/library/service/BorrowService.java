package com.library.service;

import com.library.entity.Buku;
import com.library.entity.Peminjaman;
import com.library.entity.BuktiPeminjaman;
import com.library.repository.BukuRepository;
import com.library.repository.PeminjamanRepository;
import com.library.repository.BuktiPeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class BorrowService {
    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private BukuRepository bukuRepository;

    @Autowired
    private BuktiPeminjamanRepository buktiPeminjamanRepository;

    public Peminjaman requestBorrow(Peminjaman peminjaman) {
        peminjaman.setIdPeminjaman(UUID.randomUUID().toString());
        peminjaman.setTanggalPinjam(new Date());
        peminjaman.setBatasKembali(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)); // 7 days
        peminjaman.setStatus(Peminjaman.Status.MENUNGGU);
        return peminjamanRepository.save(peminjaman);
    }

    public Peminjaman approveBorrow(String id) {
        Peminjaman peminjaman = peminjamanRepository.findById(id).orElseThrow();
        peminjaman.setStatus(Peminjaman.Status.DISETUJUI);
        Buku buku = peminjaman.getBuku();
        buku.setStatus(Buku.Status.DIPINJAM);
        bukuRepository.save(buku);

        BuktiPeminjaman bukti = new BuktiPeminjaman();
        bukti.setIdBukti(UUID.randomUUID().toString());
        bukti.setPeminjaman(peminjaman);
        bukti.setKodePeminjaman(UUID.randomUUID().toString());
        bukti.setTanggalDibuat(new Date());
        bukti.setStatusValidasi(false);
        buktiPeminjamanRepository.save(bukti);

        return peminjamanRepository.save(peminjaman);
    }

    public Peminjaman rejectBorrow(String id) {
        Peminjaman peminjaman = peminjamanRepository.findById(id).orElseThrow();
        peminjaman.setStatus(Peminjaman.Status.DITOLAK);
        return peminjamanRepository.save(peminjaman);
    }
}