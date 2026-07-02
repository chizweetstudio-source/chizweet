# System Document: Toko Online Chizweet

## 1. Pendahuluan
Aplikasi ini adalah sebuah **Toko Online** yang dibangun menggunakan bahasa pemrograman Java (Spring Boot) sebagai backend dan HTML/CSS/JavaScript sebagai frontend. Tampilan web dirancang modern, dinamis, dan premium (bukan web statis).

## 2. Fitur Utama
- **Halaman Beranda (Home)**: Hero section dengan tagline menarik, produk unggulan, dan navigasi.
- **Katalog Produk (Browse Products)**: Menampilkan daftar produk dengan filter kategori dan gambar.
- **Detail Produk**: Menampilkan informasi lengkap produk (nama, harga, deskripsi, gambar).
- **Kontak Admin**: Formulir atau tombol kontak (WhatsApp / Email) untuk pemesanan custom.
- **Order / Custom Request**: Alur pemesanan melalui kontak admin.

## 3. Teknologi yang Digunakan
- **Backend**: Java 17 + Spring Boot 3 (REST API + serving static files).
- **Frontend**: HTML5, Vanilla CSS (modern dark mode, glassmorphism, animasi), Vanilla JavaScript.
- **Data**: Produk disimpan di memori (in-memory data, tidak memerlukan database untuk versi awal).

## 4. Alur Pengguna (User Flow)
1. Pengguna membuka halaman utama → melihat hero section dan produk unggulan.
2. Pengguna menelusuri katalog produk → bisa filter berdasarkan kategori.
3. Pengguna mengklik produk → melihat detail produk (modal popup atau halaman baru).
4. Pengguna mengklik tombol "Pesan Sekarang" → diarahkan ke halaman kontak admin.
5. Admin menerima pesanan dan memproses order/custom request.
