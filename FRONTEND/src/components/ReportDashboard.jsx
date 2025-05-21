import React, { useState } from 'react';
import axios from 'axios';

const ReportDashboard = () => {
    const [reportType, setReportType] = useState('HARIAN');
    const [report, setReport] = useState(null);
    const [error, setError] = useState('');

    const handleGenerateReport = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/reports/${reportType}`, {}, {
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
            });
            setReport(response.data);
            setError('');
        } catch (err) {
            setError('Failed to generate report');
        }
    };

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Generate Report</h2>
            {error && <p className="text-red-500 mb-4">{error}</p>}
            <div className="mb-4">
                <label className="block text-gray-700">Report Type</label>
                <select
                    value={reportType}
                    onChange={(e) => setReportType(e.target.value)}
                    className="border p-2 rounded"
                >
                    <option value="HARIAN">Daily</option>
                    <option value="BULANAN">Monthly</option>
                </select>
                <button
                    onClick={handleGenerateReport}
                    className="ml-2 bg-blue-500 text-white p-2 rounded"
                >
                    Generate
                </button>
            </div>
            {report && (
                <div className="border p-4 rounded">
                    <h3 className="font-bold">{report.jenis} Report</h3>
                    <p>Generated on: {new Date(report.tanggalDibuat).toLocaleDateString()}</p>
                    <p>{report.content}</p>
                </div>
            )}
        </div>
    );
};

export default ReportDashboard;