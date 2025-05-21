import React, { useState } from 'react';
import axios from 'axios';

const BookSearch = ({ onBorrow }) => {
    const [query, setQuery] = useState('');
    const [books, setBooks] = useState([]);
    const [error, setError] = useState('');

    const handleSearch = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/books/search?query=${query}`);
            setBooks(response.data);
            setError('');
        } catch (err) {
            setError('Failed to fetch books');
        }
    };

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Search Books</h2>
            {error && <p className="text-red-500 mb-4">{error}</p>}
            <div className="flex mb-4">
                <input
                    type="text"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    className="border p-2 rounded w-full"
                    placeholder="Search by title, author, or ISBN"
                />
                <button
                    onClick={handleSearch}
                    className="ml-2 bg-blue-500 text-white p-2 rounded"
                >
                    Search
                </button>
            </div>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                {books.map((book) => (
                    <div key={book.idBuku} className="border p-4 rounded">
                        <h3 className="font-bold">{book.judul}</h3>
                        <p>Author: {book.penulis}</p>
                        <p>ISBN: {book.isbn}</p>
                        <p>Status: {book.status}</p>
                        {book.status === 'TERSEDIA' && (
                            <button
                                onClick={() => onBorrow(book.idBuku)}
                                className="mt-2 bg-green-500 text-white p-2 rounded"
                            >
                                Borrow
                            </button>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default BookSearch;