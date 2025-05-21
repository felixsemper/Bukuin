package com.library.service;

import com.library.entity.Laporan;
import com.library.repository.LaporanRepository;
import com.library.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ReportService {
    @Autowired
    private LaporanRepository laporanRepository;

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    public Laporan generateReport(Laporan.Jenis jenis) {
        Laporan laporan = new Laporan();
        laporan.setIdLaporan(UUID.randomUUID().toString());
        laporan.setJenis(jenis);
        laporan.setTanggalDibuat(new Date());
        laporan.setContent("Report for " + jenis + ": " + peminjamanRepository.count() + " borrowings recorded.");
        return laporanRepository.save(laporan);
    }
}