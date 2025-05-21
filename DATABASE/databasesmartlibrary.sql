CREATE DATABASE library_db;
USE library_db;

-- User table (abstract, implemented as Admin and Anggota)
CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    nama VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'ANGGOTA') NOT NULL
);

-- Admin table
CREATE TABLE admins (
    id VARCHAR(36) PRIMARY KEY,
    id_pegawai VARCHAR(50) UNIQUE NOT NULL,
    jabatan VARCHAR(100),
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Anggota table
CREATE TABLE anggota (
    id VARCHAR(36) PRIMARY KEY,
    no_anggota VARCHAR(50) UNIQUE NOT NULL,
    status_aktif BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Kategori table
CREATE TABLE kategori (
    id_kategori VARCHAR(36) PRIMARY KEY,
    nama_kategori VARCHAR(100) NOT NULL
);

-- Buku table
CREATE TABLE buku (
    id_buku VARCHAR(36) PRIMARY KEY,
    judul VARCHAR(255) NOT NULL,
    penulis VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) UNIQUE,
    tahun_terbit INT,
    sinopsis TEXT,
    status ENUM('TERSEDIA', 'DIPINJAM') DEFAULT 'TERSEDIA',
    cover_path VARCHAR(255),
    id_kategori VARCHAR(36),
    FOREIGN KEY (id_kategori) REFERENCES kategori(id_kategori)
);

-- Peminjaman table
CREATE TABLE peminjaman (
    id_peminjaman VARCHAR(36) PRIMARY KEY,
    id_buku VARCHAR(36),
    id_anggota VARCHAR(36),
    tanggal_pinjam DATE NOT NULL,
    batas_kembali DATE NOT NULL,
    status ENUM('MENUNGGU', 'DISETUJUI', 'DITOLAK') DEFAULT 'MENUNGGU',
    FOREIGN KEY (id_buku) REFERENCES buku(id_buku),
    FOREIGN KEY (id_anggota) REFERENCES anggota(id)
);

-- Pengembalian table
CREATE TABLE pengembalian (
    id_pengembalian VARCHAR(36) PRIMARY KEY,
    id_peminjaman VARCHAR(36),
    tanggal_kembali DATE NOT NULL,
    denda DOUBLE DEFAULT 0.0,
    FOREIGN KEY (id_peminjaman) REFERENCES peminjaman(id_peminjaman)
);

-- Bukti Peminjaman table
CREATE TABLE bukti_peminjaman (
    id_bukti VARCHAR(36) PRIMARY KEY,
    id_peminjaman VARCHAR(36),
    kode_peminjaman VARCHAR(50) UNIQUE NOT NULL,
    tanggal_dibuat DATE NOT NULL,
    status_validasi BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_peminjaman) REFERENCES peminjaman(id_peminjaman)
);

-- Ulasan table
CREATE TABLE ulasan (
    id_ulasan VARCHAR(36) PRIMARY KEY,
    id_buku VARCHAR(36),
    id_anggota VARCHAR(36),
    rating INT CHECK (rating BETWEEN 1 AND 5),
    komentar TEXT,
    FOREIGN KEY (id_buku) REFERENCES buku(id_buku),
    FOREIGN KEY (id_anggota) REFERENCES anggota(id)
);

-- Notifikasi table
CREATE TABLE notifikasi (
    id_notifikasi VARCHAR(36) PRIMARY KEY,
    id_user VARCHAR(36),
    pesan TEXT NOT NULL,
    tanggal DATE NOT NULL,
    status_baca BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_user) REFERENCES users(id)
);

-- Laporan table
CREATE TABLE laporan (
    id_laporan VARCHAR(36) PRIMARY KEY,
    jenis ENUM('HARIAN', 'BULANAN') NOT NULL,
    tanggal_dibuat DATE NOT NULL,
    content TEXT
);