// ===== GLOBALS =====
let allProducts = [];
let currentProductForOrder = null;

// ===== INIT =====
document.addEventListener('DOMContentLoaded', () => {
    initNavbar();
    loadProducts();
    initScrollAnimations();
});

// ===== NAVBAR SCROLL EFFECT =====
function initNavbar() {
    const navbar = document.getElementById('navbar');
    const links = document.querySelectorAll('.nav-link');
    const sections = document.querySelectorAll('section[id]');

    window.addEventListener('scroll', () => {
        if (window.scrollY > 50) navbar.classList.add('scrolled');
        else navbar.classList.remove('scrolled');

        let current = '';
        sections.forEach(sec => {
            if (window.scrollY >= sec.offsetTop - 150) current = sec.getAttribute('id');
        });
        links.forEach(link => {
            link.classList.remove('active');
            if (link.getAttribute('href') === '#' + current) link.classList.add('active');
        });
    });
}

// ===== LOAD PRODUCTS FROM API =====
async function loadProducts(category = 'semua') {
    const grid = document.getElementById('productGrid');
    grid.innerHTML = `<div class="product-loader"><div class="spinner"></div><p>Memuat produk...</p></div>`;

    try {
        const url = category === 'semua' ? '/api/products' : `/api/products?category=${encodeURIComponent(category)}`;
        const res = await fetch(url);
        allProducts = await res.json();
        renderProducts(allProducts);
        populateProductSelect(allProducts);
    } catch (err) {
        grid.innerHTML = `<div class="product-loader"><p style="color:#f87171">Gagal memuat produk. Pastikan server berjalan.</p></div>`;
    }
}

// ===== RENDER PRODUCT CARDS =====
function renderProducts(products) {
    const grid = document.getElementById('productGrid');
    if (products.length === 0) {
        grid.innerHTML = `<div class="product-loader"><p>Tidak ada produk ditemukan.</p></div>`;
        return;
    }
    grid.innerHTML = products.map((p, i) => `
        <div class="product-card" style="animation-delay: ${i * 0.07}s" onclick="openModal(${p.id})">
            <div class="product-emoji-wrap">${p.emoji}</div>
            <div class="product-category">${p.category}</div>
            <div class="product-name">${p.name}</div>
            <p class="product-desc">${p.description}</p>
            <div class="product-footer">
                <span class="product-price">${formatRupiah(p.price)}</span>
                <button class="product-btn">+</button>
            </div>
        </div>
    `).join('');
}

// ===== FILTER PRODUCTS =====
function filterProducts(category, btn) {
    document.querySelectorAll('.filter-tab').forEach(t => t.classList.remove('active'));
    btn.classList.add('active');
    loadProducts(category);
}

// ===== MODAL =====
function openModal(id) {
    const product = allProducts.find(p => p.id === id);
    if (!product) return;
    currentProductForOrder = product;

    document.getElementById('modalEmoji').textContent = product.emoji;
    document.getElementById('modalCategory').textContent = product.category;
    document.getElementById('modalName').textContent = product.name;
    document.getElementById('modalDescription').textContent = product.description;
    document.getElementById('modalPrice').textContent = formatRupiah(product.price);

    document.getElementById('modalOverlay').classList.add('active');
    document.body.style.overflow = 'hidden';
}

function closeModal() {
    document.getElementById('modalOverlay').classList.remove('active');
    document.body.style.overflow = '';
}

function orderProduct() {
    closeModal();
    if (currentProductForOrder) {
        document.getElementById('contactProduct').value = currentProductForOrder.name;
    }
    scrollToSection('contact');
}

// ===== CONTACT FORM =====
async function sendContact(e) {
    e.preventDefault();
    const btn = document.getElementById('submitBtn');
    const successEl = document.getElementById('formSuccess');
    const submitText = document.getElementById('submitText');

    btn.disabled = true;
    submitText.textContent = 'Mengirim...';

    const payload = {
        name: document.getElementById('contactName').value,
        email: document.getElementById('contactEmail').value,
        product: document.getElementById('contactProduct').value,
        message: document.getElementById('contactMessage').value
    };

    try {
        const res = await fetch('/api/contact', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        if (res.ok) {
            successEl.classList.remove('hidden');
            document.getElementById('contactForm').reset();
            setTimeout(() => successEl.classList.add('hidden'), 5000);
        }
    } catch (err) {
        alert('Gagal mengirim pesan. Coba lagi.');
    } finally {
        btn.disabled = false;
        submitText.textContent = 'Kirim Pesan 🚀';
    }
}

// ===== POPULATE SELECT =====
function populateProductSelect(products) {
    const sel = document.getElementById('contactProduct');
    const current = sel.value;
    sel.innerHTML = '<option value="">Pilih produk (opsional)</option>';
    products.forEach(p => {
        const opt = document.createElement('option');
        opt.value = p.name;
        opt.textContent = `${p.emoji} ${p.name} — ${formatRupiah(p.price)}`;
        sel.appendChild(opt);
    });
    if (current) sel.value = current;
}

// ===== SCROLL ANIMATIONS (Intersection Observer) =====
function initScrollAnimations() {
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, { threshold: 0.1 });

    document.querySelectorAll('.info-card, .section-header, .filter-tabs').forEach(el => {
        el.style.opacity = '0';
        el.style.transform = 'translateY(20px)';
        el.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
        observer.observe(el);
    });
}

// ===== UTILS =====
function scrollToSection(id) {
    document.getElementById(id)?.scrollIntoView({ behavior: 'smooth' });
}

function formatRupiah(num) {
    return 'Rp ' + num.toLocaleString('id-ID');
}

// ===== KEYBOARD ESC =====
document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') closeModal();
});
