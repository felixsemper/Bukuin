import React, { useState, useEffect } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Home from './pages/Home';
import MemberDashboard from './pages/MemberDashboard';
import AdminPage from './pages/AdminPage';
import Login from './components/Login';
import Register from './components/Register';
import axios from 'axios';

const App = () => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            axios.get('http://localhost:8080/api/auth/me', {
                headers: { Authorization: `Bearer ${token}` }
            }).then(response => {
                setUser(response.data);
            }).catch(() => {
                localStorage.removeItem('token');
                setUser(null);
            });
        }
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('token');
        setUser(null);
    };

    return (
        <div className="min-h-screen bg-gray-100">
            <header className="bg-blue-600 text-white p-4">
                <div className="container flex justify-between items-center">
                    <h1 className="text-2xl font-bold">Digital Library</h1>
                    {user && (
                        <div>
                            <span className="mr-4">Welcome, {user.nama} ({user.role})</span>
                            <button onClick={handleLogout} className="bg-red-500 px-4 py-2 rounded">
                                Logout
                            </button>
                        </div>
                    )}
                </div>
            </header>
            <main className="container mx-auto p-4">
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login setUser={setUser} />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/member" element={user && user.role === 'ANGGOTA' ? <MemberDashboard user={user} /> : <Navigate to="/login" />} />
                    <Route path="/admin" element={user && user.role === 'ADMIN' ? <AdminPage /> : <Navigate to="/login" />} />
                </Routes>
            </main>
        </div>
    );
};

export default App;