// src/main/java/com/example/foodorderapp/data/Menu.kt

package com.example.foodorderapp.data

data class MenuItem(
    val id: Int,
    val name: String,
    val price: Int,
    val description: String
)

val sampleMenuItems = listOf(
    MenuItem(1, "Nasi Goreng Spesial", 25000, "Nasi digoreng dengan bumbu spesial, telur, dan ayam."),
    MenuItem(2, "Mie Ayam Bakso", 22000, "Mie kenyal dengan topping ayam dan bakso sapi."),
    MenuItem(3, "Sate Ayam Madura (10 tusuk)", 30000, "Sate ayam dengan bumbu kacang khas Madura."),
    MenuItem(4, "Gado-Gado Komplit", 18000, "Campuran sayuran segar, lontong, dan bumbu kacang."),
    MenuItem(5, "Sop Buntut Sapi", 45000, "Potongan buntut sapi empuk dengan kuah kaldu rempah."),
    MenuItem(6, "Ayam Geprek Sambal Matah", 28000, "Ayam goreng tepung yang digeprek dengan sambal matah pedas."),
    MenuItem(7, "Nasi Padang Rendang", 35000, "Rendang daging sapi dengan nasi, daun singkong, dan sambal hijau."),
    MenuItem(8, "Rawon Daging Kuah Hitam", 32000, "Potongan daging sapi dengan kuah kluwek khas Jawa Timur."),
    MenuItem(9, "Martabak Manis (Cokelat Keju)", 35000, "Martabak tebal dengan topping cokelat, keju, dan susu kental manis."),
    MenuItem(10, "Kopi Susu Gula Aren", 18000, "Kopi espresso dengan susu dan gula aren."),
    MenuItem(11, "Jus Alpukat Segar", 15000, "Alpukat blender dengan es dan sedikit susu."),
)