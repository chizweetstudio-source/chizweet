# System Design: Toko Online Chizweet

## 1. Arsitektur Sistem
Sistem ini menggunakan arsitektur **Client-Server** dengan pola **MVC (Model-View-Controller)**.
- **Client (Frontend)**: HTML/CSS/JS di-serve langsung oleh Spring Boot sebagai static files.
- **Server (Backend - Java Spring Boot)**: Menyediakan REST API untuk data produk dan kontak.

---

## 2. Desain Komponen (Java / Spring Boot)

### 2.1. Controller

#### `PageController`
- `GET /` → Menampilkan `index.html` (Halaman beranda)

#### `ProductController`
- `GET /api/products` → Mengembalikan semua produk dalam format JSON
- `GET /api/products/{id}` → Mengembalikan satu produk berdasarkan ID
- `GET /api/products?category={cat}` → Filter produk berdasarkan kategori

#### `ContactController`
- `POST /api/contact` → Menerima pesan/pesanan dari pengguna

---

### 2.2. Service

#### `ProductService`
- `getAllProducts(): List<Product>`
- `getProductById(Long id): Optional<Product>`
- `getProductsByCategory(String category): List<Product>`

---

### 2.3. Model

#### `Product`
```java
public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;
}
```

#### `ContactRequest`
```java
public class ContactRequest {
    private String name;
    private String email;
    private String message;
}
```

---

## 3. Desain Antarmuka (UI/UX)
- **Tema**: Dark mode premium dengan aksen ungu-biru (gradient).
- **Font**: Inter dari Google Fonts.
- **Animasi**: Hover effect pada kartu produk, fade-in saat scroll, tombol dengan ripple effect.
- **Komponen Utama**:
  - Navbar sticky dengan logo dan navigasi
  - Hero section dengan CTA button
  - Grid produk responsif (3 kolom di desktop, 1 kolom di mobile)
  - Modal popup untuk detail produk
  - Footer dengan info kontak

---

## 4. User Flow
Home → Browse Products → View Product Details → Contact Admin → Order / Custom Request
