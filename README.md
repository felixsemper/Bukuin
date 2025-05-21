# Aplikasi Perpustakaan Digital

**Aplikasi Perpustakaan Digital** adalah sistem manajemen perpustakaan berbasis web yang dirancang untuk mengotomatisasi operasional perpustakaan, seperti pencarian buku, peminjaman, pengembalian, ulasan, notifikasi, dan pelaporan. Aplikasi ini dibangun dengan **Spring Boot** untuk backend dan **React** untuk frontend, menggunakan autentikasi berbasis JWT dan akses berbasis peran (Admin dan Anggota). Proyek ini bertujuan untuk memberikan solusi modern, efisien, dan ramah pengguna bagi perpustakaan.

---

## Ikhtisar Proyek

Aplikasi ini menyediakan platform terintegrasi untuk mengelola sumber daya perpustakaan. Backend menggunakan **Spring Boot** dengan **MySQL** sebagai database, sementara frontend dibangun dengan **React** menggunakan **Tailwind CSS** untuk antarmuka responsif. Sistem mendukung dua peran pengguna:
- **Admin**: Mengelola buku, anggota, menyetujui/tolak peminjaman, dan menghasilkan laporan.
- **Anggota**: Mencari buku, meminjam, mengembalikan, memberikan ulasan, dan menerima notifikasi.

Proyek ini terinspirasi dari kebutuhan akan digitalisasi perpustakaan, dengan fokus pada kemudahan penggunaan, keamanan, dan skalabilitas. Struktur kode yang modular dan terdokumentasi dengan baik memudahkan pengembangan dan pemeliharaan.

---

## Fitur

### Fitur Publik
- **Pencarian Buku**: Cari buku berdasarkan judul, penulis, atau ISBN tanpa login.
- **Halaman Utama**: Menampilkan informasi perpustakaan dan fitur pencarian.

### Fitur Anggota
- **Autentikasi**: Login/logout dengan JWT.
- **Peminjaman Buku**: Ajukan peminjaman dengan status "Menunggu Persetujuan".
- **Pengembalian Buku**: Kembalikan buku dengan perhitungan denda otomatis (Rp 1.000/hari setelah 7 hari).
- **Ulasan Buku**: Berikan rating (1-5) dan komentar untuk buku yang dipinjam.
- **Notifikasi**: Terima pemberitahuan tentang status peminjaman, pengembalian, atau pengingat.
- **Riwayat Peminjaman**: Lihat daftar peminjaman sebelumnya.

### Fitur Admin
- **Manajemen Buku**: Tambah, ubah, hapus buku dengan detail seperti judul, penulis, ISBN, dan kategori.
- **Manajemen Anggota**: Daftarkan anggota baru dengan nomor anggota unik.
- **Persetujuan Peminjaman**: Setujui atau tolak permintaan peminjaman.
- **Laporan**: Hasilkan laporan harian/bulanan (misalnya, statistik peminjaman, buku populer).
- **Notifikasi Admin**: Kirim notifikasi kepada anggota (misalnya, pengingat pengembalian).

---

## Arsitektur Proyek

Aplikasi ini mengadopsi arsitektur **client-server** dengan pemisahan jelas antara backend dan frontend:

### Backend
- **Framework**: Spring Boot
- **Database**: MySQL (`smartlibrary_db`)
- **Autentikasi**: JWT dengan Spring Security
- **Struktur**:
  - **Config**: Konfigurasi keamanan (`SecurityConfig.java`).
  - **Controller**: Endpoint API REST (misalnya, `AuthController.java`, `BookController.java`).
  - **Entity**: Model data (misalnya, `Buku.java`, `Anggota.java`).
  - **Repository**: Interaksi database (misalnya, `BukuRepository.java`).
  - **Service**: Logika bisnis (misalnya, `BookService.java`).
  - **DTO**: Objek transfer data (misalnya, `BookDTO.java`).
- **API**: RESTful dengan endpoint seperti `/api/auth`, `/api/books`, `/api/borrow`, dll.

### Frontend
- **Framework**: React
- **Styling**: Tailwind CSS
- **Navigasi**: React Router DOM
- **Komunikasi API**: Axios
- **Struktur**:
  - **Components**: Komponen reusable (misalnya, `Login.js`, `BookSearch.js`).
  - **Pages**: Halaman utama (misalnya, `Home.js`, `MemberDashboard.js`).
  - **App.js**: Routing dan manajemen state global.
  - **index.css**: Styling global dengan Tailwind.

### Komunikasi
- Frontend mengirimkan permintaan HTTP ke backend melalui API REST.
- CORS dikonfigurasi untuk mengizinkan komunikasi dari `http://localhost:3000` (atau domain produksi).

### Diagram Arsitektur
```
[User] --> [React Frontend] --> [Spring Boot Backend] --> [MySQL Database]
           HTTP/REST API         JPA/Hibernate
```

---

## Struktur Direktori

### Backend
```
backend/src/main/java/com/library/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── BookController.java
│   ├── BorrowController.java
│   ├── ReturnController.java
│   ├── ReviewController.java
│   ├── NotificationController.java
│   └── ReportController.java
├── entity/
│   ├── User.java
│   ├── Admin.java
│   ├── Anggota.java
│   ├── Buku.java
│   ├── Kategori.java
│   ├── Peminjaman.java
│   ├── Pengembalian.java
│   ├── BuktiPeminjaman.java
│   ├── Ulasan.java
│   ├── Notifikasi.java
│   └── Laporan.java
├── repository/
│   ├── UserRepository.java
│   ├── AdminRepository.java
│   ├── AnggotaRepository.java
│   ├── BukuRepository.java
│   ├── KategoriRepository.java
│   ├── PeminjamanRepository.java
│   ├── PengembalianRepository.java
│   ├── BuktiPeminjamanRepository.java
│   ├── UlasanRepository.java
│   ├── NotifikasiRepository.java
│   └── LaporanRepository.java
├── service/
│   ├── AuthService.java
│   ├── BookService.java
│   ├── BorrowService.java
│   ├── ReturnService.java
│   ├── ReviewService.java
│   ├── NotificationService.java
│   └── ReportService.java
├── dto/
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   ├── BookDTO.java
│   └── BorrowRequest.java
└── Application.java
```

### Database
```
backend/DataBase/
└── databasesmartlibrary.sql
```

### Frontend
```
frontend/src/
├── components/
│   ├── Login.js
│   ├── Register.js
│   ├── BookSearch.js
│   ├── BookManagement.js
│   ├── BorrowForm.js
│   ├── ReturnForm.js
│   ├── ReviewForm.js
│   ├── NotificationList.js
│   ├── ReportDashboard.js
│   └── AdminDashboard.js
├── pages/
│   ├── Home.js
│   ├── MemberDashboard.js
│   └── AdminPage.js
├── App.js
├── index.js
└── index.css
```

---

## Prasyarat

Sebelum menginstal aplikasi, pastikan Anda memiliki:
- **Backend**:
  - Java 17+
  - Maven 3.6+
  - MySQL 8.0+
- **Frontend**:
  - Node.js 16+ dan npm
- **Lainnya**:
  - Git
  - Postman (opsional, untuk pengujian API)
  - Browser modern (Chrome, Firefox, Edge)
  - Koneksi internet untuk dependensi

---

## Instalasi

### 1. Kloning Repositori
```bash
git clone https://github.com/username/digital-library.git
cd digital-library
```

### 2. Pengaturan Backend
1. **Konfigurasi Database**:
   - Buat database di MySQL:
     ```sql
     CREATE DATABASE smartlibrary_db;
     ```
   - Jalankan skrip `backend/DataBase/databasesmartlibrary.sql` untuk membuat tabel:
     ```sql
     CREATE TABLE Kategori (...);
     CREATE TABLE Buku (...);
     -- Lihat file untuk skrip lengkap
     ```
   - Perbarui `backend/src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/smartlibrary_db
     spring.datasource.username=root
     spring.datasource.password=kata_sandi_anda
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     jwt.secret=kunci_rahasia_jwt_anda
     jwt.expiration=86400000
     ```

2. **Bangun dan Jalankan**:
   - Masuk ke direktori backend:
     ```bash
     cd backend
     ```
   - Instal dependensi dan jalankan:
     ```bash
     mvn clean install
     mvn spring-boot:run
     ```
   - Backend berjalan di `http://localhost:8080`.

3. **Verifikasi**:
   - Uji endpoint `/api/auth/login` dengan Postman:
     ```http
     POST http://localhost:8080/api/auth/login
     Content-Type: application/json
     {
       "email": "admin@library.com",
       "password": "admin123"
     }
     ```

### 3. Pengaturan Frontend
1. **Instal Dependensi**:
   - Masuk ke direktori frontend:
     ```bash
     cd frontend
     ```
   - Instal dependensi:
     ```bash
     npm install
     npm install axios react-router-dom uuid
     ```

2. **Konfigurasi Tailwind CSS**:
   - Instal Tailwind CSS secara lokal (opsional untuk produksi):
     ```bash
     npm install -D tailwindcss
     npx tailwindcss init
     ```
   - Perbarui `tailwind.config.js`:
     ```javascript
     module.exports = {
       content: ["./src/**/*.{js,jsx,ts,tsx}"],
       theme: { extend: {} },
       plugins: [],
     };
     ```
   - Pastikan `src/index.css` memiliki:
     ```css
     @tailwind base;
     @tailwind components;
     @tailwind utilities;
     ```

3. **Jalankan Frontend**:
   ```bash
   npm start
   ```
   - Frontend berjalan di `http://localhost:3000`.

4. **Verifikasi**:
   - Buka `http://localhost:3000` dan uji login atau pencarian buku.
   - Pastikan CORS berfungsi (dikonfigurasi di `SecurityConfig.java`).

### 4. Inisialisasi Data
- Buat akun admin default:
  ```sql
  INSERT INTO Anggota (id, nama, email, password, noAnggota, statusAktif, role)
  VALUES ('1', 'Admin', 'admin@library.com', '$2a$10$...', 'ADM001', true, 'ADMIN');
  ```
  - Ganti `$2a$10$...` dengan hash BCrypt untuk `admin123` (gunakan [BCrypt Generator](https://www.bcrypt-generator.com/)).
- Tambahkan data contoh:
  ```sql
  INSERT INTO Kategori (idKategori, namaKategori) VALUES ('1', 'Fiksi');
  INSERT INTO Buku (idBuku, judul, penulis, isbn, tahunTerbit, sinopsis, status, idKategori)
  VALUES ('1', 'Laskar Pelangi', 'Andrea Hirata', '1234567890', 2005, 'Kisah inspiratif...', 'TERSEDIA', '1');
  ```

---

## Penggunaan

### 1. Akses Aplikasi
- **Halaman Utama**: `http://localhost:3000` untuk pencarian buku publik.
- **Login**:
  - Admin: `admin@library.com` / `admin123` → `/admin`.
  - Anggota: Daftar via admin, lalu login → `/member`.
- **Logout**: Klik "Logout" di header.

### 2. Panduan Fitur
- **Pencarian Buku**: Cari buku di halaman utama atau dashboard anggota.
- **Peminjaman**:
  - Anggota: Pilih buku "TERSEDIA" dan ajukan peminjaman.
  - Admin: Setujui/tolak di dashboard admin.
- **Pengembalian**: Anggota kembalikan buku via dashboard; denda otomatis jika terlambat.
- **Ulasan**: Anggota beri rating dan komentar untuk buku.
- **Notifikasi**: Anggota lihat pemberitahuan di dashboard.
- **Laporan**: Admin hasilkan laporan harian/bulanan.
- **Bukti Peminjaman**: Simpan bukti peminjaman (tercatat di `BuktiPeminjaman`).

---

## Pengujian

### 1. Pengujian Backend
- **Unit Testing**:
  - Gunakan JUnit dan Mockito untuk layanan (`BookService`, `BorrowService`, dll.).
  - Contoh: `backend/src/test/java/com/library/service/BookServiceTest.java`.
  - Jalankan:
    ```bash
    mvn test
    ```
- **Pengujian API**:
  - Uji dengan Postman:
    - `POST /api/auth/login`
    - `GET /api/books/search?query=judul`
    - `POST /api/borrow`
  - Sertakan header `Authorization: Bearer <token>`.

### 2. Pengujian Frontend
- **Unit Testing**:
  - Gunakan Jest dan React Testing Library untuk komponen (`Login.js`, `BookSearch.js`).
  - Contoh: `frontend/src/components/__tests__/Login.test.js`.
  - Jalankan:
    ```bash
    npm test
    ```
- **Pengujian E2E**:
  - Gunakan Cypress:
    ```bash
    npm install cypress --save-dev
    npx cypress open
    ```
  - Uji alur login, peminjaman, dan pengembalian.

### 3. Pengujian Manual
- **Alur**:
  - Daftar anggota, login, cari buku, pinjam, setujui, kembalikan, ulas.
  - Hasilkan laporan admin.
- **Kasus Batas**:
  - Login salah.
  - Pinjam buku tidak tersedia.
  - Kembalikan terlambat (periksa denda).

---

## Penerapan

### 1. Backend
1. **Bangun**:
   ```bash
   cd backend
   mvn clean package
   ```
   - File JAR: `target/digital-library-0.0.1-SNAPSHOT.jar`.
2. **Terapkan**:
   - **Lokal**:
     ```bash
     java -jar target/digital-library-0.0.1-SNAPSHOT.jar
     ```
   - **Heroku**:
     ```bash
     heroku create nama-aplikasi-anda
     heroku deploy:jar target/digital-library-0.0.1-SNAPSHOT.jar --app nama-aplikasi-anda
     ```
3. **Database**:
   - Gunakan AWS RDS atau ClearDB.
   - Perbarui `application.properties`.
4. **Variabel Lingkungan**:
   - Setel `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`, `JWT_SECRET`.

### 2. Frontend
1. **Bangun**:
   ```bash
   cd frontend
   npm run build
   ```
   - Build di `build/`.
2. **Terapkan**:
   - **Netlify**:
     ```bash
     netlify deploy --prod --dir=build
     ```
   - Perbarui URL API di komponen frontend.
3. **CORS**:
   - Perbarui `SecurityConfig.java` untuk domain frontend produksi.

### 3. CI/CD
- Gunakan GitHub Actions.
- Workflow:
  - `backend.yml`:
    ```yaml
    name: CI/CD Backend
    on:
      push:
        branches: [ main ]
    jobs:
      build:
        runs-on: ubuntu-latest
        steps:
          - uses: actions/checkout@v3
          - name: Set up JDK 17
            uses: actions/setup-java@v3
            with:
              java-version: '17'
              distribution: 'temurin'
          - name: Build with Maven
            run: mvn clean package
          - name: Deploy to Heroku
            env:
              HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
            run: heroku deploy:jar target/digital-library-0.0.1-SNAPSHOT.jar --app nama-aplikasi-anda
    ```
  - `frontend.yml`:
    ```yaml
    name: CI/CD Frontend
    on:
      push:
        branches: [ main ]
    jobs:
      build:
        runs-on: ubuntu-latest
        steps:
          - uses: actions/checkout@v3
          - name: Set up Node.js
            uses: actions/setup-node@v3
            with:
              node-version: '16'
          - name: Install dependencies
            run: cd frontend && npm install
          - name: Build
            run: cd frontend && npm run build
          - name: Deploy to Netlify
            env:
              NETLIFY_AUTH_TOKEN: ${{ secrets.NETLIFY_AUTH_TOKEN }}
              NETLIFY_SITE_ID: ${{ secrets.NETLIFY_SITE_ID }}
            run: cd frontend && npx netlify deploy --prod --dir=build
    ```
- Tambahkan rahasia di GitHub Settings > Secrets:
  - `HEROKU_API_KEY`
  - `NETLIFY_AUTH_TOKEN`
  - `NETLIFY_SITE_ID`

---

## Dokumentasi API
- Gunakan **Swagger UI** di `http://localhost:8080/swagger-ui.html`.
- Endpoint utama:
  - `POST /api/auth/login`: Autentikasi.
  - `POST /api/auth/register`: Daftar anggota.
  - `GET/POST/PUT/DELETE /api/books`: Manajemen buku.
  - `GET /api/books/search`: Pencarian buku.
  - `POST /api/borrow`: Peminjaman.
  - `POST /api/return/{id}`: Pengembalian.
  - `POST /api/reviews`: Ulasan.
  - `GET /api/notifications/user/{id}`: Notifikasi.
  - `POST /api/reports/{type}`: Laporan.

---

## Pemecahan Masalah

- **Backend gagal start**:
  - Periksa kredensial di `application.properties`.
  - Pastikan MySQL berjalan di port 3306.
  - Lihat log di konsol atau `logs/application.log`.
- **CORS error**:
  - Verifikasi `SecurityConfig.java` mengizinkan `http://localhost:3000`.
  - Perbarui untuk domain produksi.
- **Login gagal**:
  - Pastikan akun ada di `Anggota`.
  - Periksa hash kata sandi BCrypt.
- **API tidak merespons**:
  - Uji dengan Postman.
  - Pastikan header `Authorization` benar.
- **Laporan kosong**:
  - Pastikan data peminjaman ada.
  - Periksa `type` (HARIAN/BULANAN).

---

## FAQ

**Q: Bagaimana cara menambahkan admin baru?**  
A: Tambahkan ke `Anggota` dengan `role=ADMIN` dan hash BCrypt.

**Q: Apakah ada fitur perpanjangan peminjaman?**  
A: Belum, tetapi bisa ditambahkan di `BorrowController`.

**Q: Bagaimana mengunggah sampul buku?**  
A: Tambahkan `MultipartFile` di `BookController`.

**Q: Bagaimana menyesuaikan denda?**  
A: Ubah logika di `ReturnService.java`.

---

## Kontribusi

Kami mengundang kontribusi untuk memperbaiki aplikasi. Langkah-langkah:
1. Fork repositori.
2. Buat branch:
   ```bash
   git checkout -b fitur-anda
   ```
3. Commit perubahan:
   ```bash
   git commit -m "Menambahkan fitur-anda"
   ```
4. Push:
   ```bash
   git push origin fitur-anda
   ```
5. Buat Pull Request.

### Pedoman
- Gunakan linter (ESLint, Checkstyle).
- Sertakan tes.
- Perbarui dokumentasi.
- Ikuti konvensi penamaan.

---

## Roadmap

- **Fitur Baru**:
  - Notifikasi email.
  - Unggah sampul buku.
  - Kode QR untuk `BuktiPeminjaman`.
  - Perpanjangan peminjaman.
  - Cache (Redis).
- **Peningkatan**:
  - Validasi formulir (`react-hook-form`).
  - Notifikasi toast (`react-toastify`).
  - Pengujian E2E (Cypress).
- **Performa**:
  - Pagination untuk buku/peminjaman.
  - Optimasi query laporan.

---

## Lisensi
[MIT License](LICENSE)

---

## Kontak
- Email: support@library.com
- GitHub Issues: [Buat isu](https://github.com/username/digital-library/issues)
- Discord: [Gabung server](https://discord.gg/example)

Terima kasih telah menggunakan Aplikasi Perpustakaan Digital! 📚