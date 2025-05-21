// src/pages/AdminPage.jsx
import React from 'react';
import AdminDashboard from '../components/AdminDashboard'; // Pastikan jalur ini benar

const AdminPage = () => {
    return (
        <div className="container mx-auto py-8">
            <h2 className="text-3xl font-extrabold text-gray-900 mb-6 text-center">
                Dashboard Admin
            </h2>
            <p className="text-center text-gray-600 mb-8">
                Kelola data buku, pengguna, dan transaksi perpustakaan.
            </p>
            <AdminDashboard />
            {/* Di sini Anda bisa menambahkan komponen atau bagian lain untuk fitur admin,
                seperti: BookManagement, UserManagement, BorrowingReports, dll. */}
            <div className="mt-10 p-6 bg-white rounded-lg shadow-md">
                <h3 className="text-xl font-semibold mb-4">Fitur Admin Lainnya</h3>
                <ul className="list-disc list-inside text-gray-700">
                    <li>Manajemen Buku (Tambah, Edit, Hapus)</li>
                    <li>Manajemen Pengguna (Lihat, Edit Peran, Hapus)</li>
                    <li>Laporan Peminjaman dan Pengembalian</li>
                    <li>Notifikasi Sistem</li>
                </ul>
            </div>
        </div>
    );
};

export default AdminPage;