package com.example.foodorderapp.data

/**
 * Merepresentasikan satu item di dalam keranjang belanja,
 * menggabungkan MenuItem dengan jumlah yang dipesan.
 */
data class CartItem(
    val menuItem: MenuItem,
    var quantity: Int
)
