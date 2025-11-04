package com.example.foodorderapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderapp.data.CartItem
import com.example.foodorderapp.data.MenuItem

class UserViewModel : ViewModel() {
    // Data Nama User
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    fun setUserName(name: String) {
        _userName.value = name
    }

    // Data Keranjang Belanja (Cart)
    private val _cartItems = MutableLiveData<List<CartItem>>(emptyList())
    val cartItems: LiveData<List<CartItem>> = _cartItems

    private val _totalPrice = MutableLiveData<Int>(0)
    val totalPrice: LiveData<Int> = _totalPrice

    // Menambah item ke keranjang. Jika sudah ada, tambah jumlahnya.
    fun addToCart(item: MenuItem) {
        val currentItems = _cartItems.value?.toMutableList() ?: mutableListOf()
        val existingItem = currentItems.find { it.menuItem.id == item.id }

        if (existingItem != null) {
            existingItem.quantity++
        } else {
            currentItems.add(CartItem(menuItem = item, quantity = 1))
        }
        _cartItems.value = currentItems
        calculateTotalPrice()
    }

    // Mengurangi jumlah item. Jika sisa 1, hapus dari keranjang.
    fun decreaseCartItem(item: MenuItem) {
        val currentItems = _cartItems.value?.toMutableList() ?: mutableListOf()
        val existingItem = currentItems.find { it.menuItem.id == item.id }

        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                currentItems.remove(existingItem)
            }
        }
        _cartItems.value = currentItems
        calculateTotalPrice()
    }
    
    // Fungsi ini sama dengan addToCart, bisa digunakan untuk tombol '+'
    fun increaseCartItem(item: MenuItem) {
        addToCart(item)
    }

    private fun calculateTotalPrice() {
        _totalPrice.value = _cartItems.value?.sumOf { it.menuItem.price * it.quantity } ?: 0
    }
}
