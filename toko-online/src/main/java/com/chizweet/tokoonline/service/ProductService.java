package com.chizweet.tokoonline.service;

import com.chizweet.tokoonline.model.Product;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = Arrays.asList(
        new Product(1L, "Kaos Polos Premium", "Kaos berbahan cotton 30s ringspun, nyaman dan adem dipakai seharian.", 85000, "Pakaian", "👕"),
        new Product(2L, "Kemeja Batik Modern", "Kemeja batik motif kontemporer, cocok untuk formal maupun kasual.", 175000, "Pakaian", "👘"),
        new Product(3L, "Celana Chino Slim", "Celana chino slim fit bahan stretch, tersedia berbagai warna.", 195000, "Pakaian", "👖"),
        new Product(4L, "Sneakers Lokal Keren", "Sneakers buatan lokal dengan kualitas internasional, sol EVA anti-slip.", 350000, "Sepatu", "👟"),
        new Product(5L, "Sandal Kulit Handmade", "Sandal kulit asli buatan tangan, awet dan stylish.", 220000, "Sepatu", "🥿"),
        new Product(6L, "Topi Snapback Bordir", "Topi snapback dengan bordir custom logo, bahan twill premium.", 125000, "Aksesori", "🧢"),
        new Product(7L, "Tas Kanvas Tote Bag", "Tas kanvas tebal dengan custom print, cocok untuk belanja atau kampus.", 95000, "Aksesori", "👜"),
        new Product(8L, "Jam Tangan Casual", "Jam tangan casual dengan desain minimalis, tali nilon yang bisa diganti.", 285000, "Aksesori", "⌚"),
        new Product(9L, "Hoodie Fleece Oversize", "Hoodie fleece tebal dan hangat, potongan oversize yang trendy.", 265000, "Pakaian", "🧥")
    );

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Product> getProductsByCategory(String category) {
        if (category == null || category.isBlank() || category.equalsIgnoreCase("semua")) {
            return products;
        }
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
