import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import BookManagement from './BookManagement';
import ReportDashboard from './ReportDashboard';

const AdminDashboard = () => {
    const [pendingBorrows, setPendingBorrows] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchPendingBorrows = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/borrow', {
                    headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
                });
                setPendingBorrows(response.data.filter(b => b.status === 'MENUNGGU'));
            } catch (err) {
                setError('Failed to fetch pending borrows');
            }
        };
        fetchPendingBorrows();
    }, []);

    const handleApprove = async (id) => {
        try {
            await axios.put(`http://localhost:8080/api/borrow/approve/${id}`, {}, {
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
            });
            setPendingBorrows(pendingBorrows.filter(b => b.idPeminjaman !== id));
        } catch (err) {
            setError('Failed to approve borrow request');
        }
    };

    const handleReject = async (id) => {
        try {
            await axios.put(`http://localhost:8080/api/borrow/reject/${id}`, {}, {
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
            });
            setPendingBorrows(pendingBorrows.filter(b => b.idPeminjaman !== id));
        } catch (err) {
            setError('Failed to reject borrow request');
        }
    };

    return (
        <div className="p-4">
            <h2 className="text-2xl font-bold mb-4">Admin Dashboard</h2>
            {error && <p className="text-red-500 mb-4">{error}</p>}
            <div className="mb-8">
                <Link to="/register" className="bg-blue-500 text-white p-2 rounded mr-2">
                    Register New Member
                </Link>
            </div>
            <BookManagement />
            <h3 className="text-xl font-bold mt-8 mb-4">Pending Borrow Requests</h3>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {pendingBorrows.map((borrow) => (
                    <div key={borrow.idPeminjaman} className="border p-4 rounded">
                        <p>Book: {borrow.buku.judul}</p>
                        <p>Member: {borrow.anggota.nama}</p>
                        <p>Request Date: {new Date(borrow.tanggalPinjam).toLocaleDateString()}</p>
                        <button
                            onClick={() => handleApprove(borrow.idPeminjaman)}
                            className="bg-green-500 text-white p-2 rounded mr-2"
                        >
                            Approve
                        </button>
                        <button
                            onClick={() => handleReject(borrow.idPeminjaman)}
                            className="bg-red-500 text-white p-2 rounded"
                        >
                            Reject
                        </button>
                    </div>
                ))}
            </div>
            <ReportDashboard />
        </div>
    );
};

export default AdminDashboard;