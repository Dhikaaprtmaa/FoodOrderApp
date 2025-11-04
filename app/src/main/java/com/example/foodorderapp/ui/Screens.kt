package com.example.foodorderapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.foodorderapp.data.CartItem
import com.example.foodorderapp.data.MenuItem
import com.example.foodorderapp.data.sampleMenuItems

// --- Screen 2: Sign In / Sign Up ---
@Composable
fun SignInOrUpScreen(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign up",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Verifikasi dulu! Biar gasalah alamat >_<",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(bottom = 8.dp),
        ) {
            Text("Register")
        }

        OutlinedButton(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        ) {
            Text("Login", color = MaterialTheme.colorScheme.primary)
        }
    }
}

// --- Screen 3: Login User ---
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, userViewModel: UserViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {
        Text(
            text = "Login User",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                userViewModel.setUserName(username) // Simpan username
                onLoginSuccess()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Login")
        }
    }
}

// --- Screen 4: Register ---
@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit, userViewModel: UserViewModel) {
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {
        Text(
            text = "Register",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nama Lengkap") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                userViewModel.setUserName(fullName) // Simpan nama lengkap
                onRegisterSuccess()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Register")
        }
    }
}


// --- Screen 5 & 6: Home Screen (Menu) ---
@Composable
fun MenuItemCard(item: MenuItem, onAddToCartClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Rp ${item.price}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 1 // Membatasi deskripsi
                )
            }
            IconButton(onClick = onAddToCartClick) {
                Icon(Icons.Filled.Add, contentDescription = "Add to Cart")
            }
        }
    }
}

@Composable
fun HomeScreen(userName: String, onCheckoutClick: () -> Unit, userViewModel: UserViewModel) {
    Scaffold(
        topBar = {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Text("Halo $userName,", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text("Mau makan apa hari ini? banyak menu makanan yang dapat kamu pilih", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true, // Selalu aktif di halaman ini
                    onClick = { /* Tidak melakukan apa-apa */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Order") },
                    label = { Text("Order") },
                    selected = false,
                    onClick = onCheckoutClick // Navigasi ke halaman checkout
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { /* Logika untuk halaman Profile */ }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Text(
                    text = "Makanan",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            items(sampleMenuItems) { item ->
                MenuItemCard(item, onAddToCartClick = { userViewModel.addToCart(item) })
            }
        }
    }
}

// --- NEW: Order Summary Screen ---
@Composable
fun OrderSummaryScreen(
    onNextClick: () -> Unit,
    cartItems: List<CartItem>,
    totalPrice: Int,
    userViewModel: UserViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Ringkasan Pesanan",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Menampilkan item yang dipesan dengan kontrol jumlah
        cartItems.forEach { cartItem ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(cartItem.menuItem.name, modifier = Modifier.weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { userViewModel.decreaseCartItem(cartItem.menuItem) }) {
                        Icon(Icons.Filled.Remove, contentDescription = "Kurangi")
                    }
                    Text("${cartItem.quantity}")
                    IconButton(onClick = { userViewModel.increaseCartItem(cartItem.menuItem) }) {
                        Icon(Icons.Filled.Add, contentDescription = "Tambah")
                    }
                }
                Text("Rp ${cartItem.menuItem.price * cartItem.quantity}")
            }
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Total Harga", fontWeight = FontWeight.Bold)
            Text("Rp $totalPrice", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.weight(1f)) // Mendorong tombol ke bawah

        Button(
            onClick = onNextClick, // Navigasi ke halaman Alamat
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Lanjutkan ke Pengiriman")
        }
    }
}

// --- Screen 7: Delivery (Address Only) ---
@Composable
fun DeliveryScreen(
    onOrderClick: () -> Unit,
    userName: String
) {
    var fullName by remember { mutableStateOf(userName) }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Alamat Pengiriman",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nama Lengkap") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Masukan alamat lengkap") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(bottom = 32.dp)
        )
        
        Spacer(modifier = Modifier.weight(1f)) // Mendorong tombol ke bawah

        Button(
            onClick = onOrderClick, // Navigasi ke Success
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Confirm order")
        }
    }
}

// --- Screen 8: Success ---
@Composable
fun SuccessScreen(userName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start, // Perbaikan di sini
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Halo $userName,",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Terima kasih sudah memesan dari restoran favorit kamu, kami akan segera memproses pesanan Anda.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        Button(
            onClick = { /* Logika: Navigasi kembali ke Home */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Selesai")
        }
    }
}
