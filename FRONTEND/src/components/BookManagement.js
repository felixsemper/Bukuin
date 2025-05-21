import React, { useState, useEffect } from 'react';
import axios from 'axios';

const BookManagement = () => {
    const [books, setBooks] = useState([]);
    const [formData, setFormData] = useState({
        judul: '', penulis: '', isbn: '', tahunTerbit: '', sinopsis: '', status: 'TERSEDIA', coverPath: '', idKategori: ''
    });
    const [error, setError] = useState('');

    useEffect(() => {
        fetchBooks();
    }, []);

    const fetchBooks = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/books', {
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
            });
            setBooks(response.data);
        } catch (err) {
            setError('Failed to fetch books');
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8080/api/books', formData, {
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
            });
            fetchBooks();
            setFormData({ judul: '', penulis: '', isbn: '', tahunTerbit: '', sinopsis: '', status: 'TERSEDIA', coverPath: '', idKategori: '' });
        } catch (err) {
            setError('Failed to add book');
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/api/books/${id}`, {
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
            });
            fetchBooks();
        } catch (err) {
            setError('Failed to delete book');
        }
    };

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Book Management</h2>
            {error && <p className="text-red-500 mb-4">{error}</p>}
            <form onSubmit={handleSubmit} className="mb-8">
                <div className="grid grid-cols-2 gap-4">
                    <input
                        type="text"
                        placeholder="Title"
                        value={formData.judul}
                        onChange={(e) => setFormData({ ...formData, judul: e.target.value })}
                        className="border p-2 rounded"
                        required
                    />
                    <input
                        type="text"
                        placeholder="Author"
                        value={formData.penulis}
                        onChange={(e) => setFormData({ ...formData, penulis: e.target.value })}
                        className="border p-2 rounded"
                        required
                    />
                    <input
                        type="text"
                        placeholder="ISBN"
                        value={formData.isbn}
                        onChange={(e) => setFormData({ ...formData, isbn: e.target.value })}
                        className="border p-2 rounded"
                        required
                    />
                    <input
                        type="number"
                        placeholder="Year"
                        value={formData.tahunTerbit}
                        onChange={(e) => setFormData({ ...formData, tahunTerbit: e.target.value })}
                        className="border p-2 rounded"
                    />
                    <textarea
                        placeholder="Synopsis"
                        value={formData.sinopsis}
                        onChange={(e) => setFormData({ ...formData, sinopsis: e.target.value })}
                        className="border p-2 rounded col-span-2"
                    />
                    <input
                        type="text"
                        placeholder="Category ID"
                        value={formData.idKategori}
                        onChange={(e) => setFormData({ ...formData, idKategori: e.target.value })}
                        className="border p-2 rounded"
                    />
                    <input
                        type="text"
                        placeholder="Cover Path"
                        value={formData.coverPath}
                        onChange={(e) => setFormData({ ...formData, coverPath: e.target.value })}
                        className="border p-2 rounded"
                    />
                </div>
                <button type="submit" className="mt-4 bg-blue-500 text-white p-2 rounded">
                    Add Book
                </button>
            </form>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                {books.map((book) => (
                    <div key={book.idBuku} className="border p-4 rounded">
                        <h3 className="font-bold">{book.judul}</h3>
                        <p>Author: {book.penulis}</p>
                        <p>ISBN: {book.isbn}</p>
                        <p>Status: {book.status}</p>
                        <button
                            onClick={() => handleDelete(book.idBuku)}
                            className="mt-2 bg-red-500 text-white p-2 rounded"
                        >
                            Delete
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default BookManagement;