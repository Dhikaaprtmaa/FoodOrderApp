package com.example.foodorderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodorderapp.ui.* // Import semua screen
import com.example.foodorderapp.ui.theme.FoodOrderAppTheme

// Enum untuk rute navigasi
enum class Screen {
    Welcome, SignInOrUp, Login, Register, Home, OrderSummary, Delivery, Success
}

class MainActivity : ComponentActivity() {
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodOrderAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(userViewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(userViewModel: UserViewModel) {
    val navController = rememberNavController()
    val userName by userViewModel.userName.observeAsState("")
    val cartItems by userViewModel.cartItems.observeAsState(emptyList())
    val totalPrice by userViewModel.totalPrice.observeAsState(0)

    NavHost(navController = navController, startDestination = Screen.Welcome.name) {
        composable(Screen.Welcome.name) {
            WelcomeScreen(onStartClick = { navController.navigate(Screen.SignInOrUp.name) })
        }

        composable(Screen.SignInOrUp.name) {
            SignInOrUpScreen(
                onLoginClick = { navController.navigate(Screen.Login.name) },
                onRegisterClick = { navController.navigate(Screen.Register.name) }
            )
        }

        composable(Screen.Login.name) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.name) {
                        popUpTo(Screen.Welcome.name) { inclusive = true }
                    }
                },
                userViewModel = userViewModel
            )
        }

        composable(Screen.Register.name) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.Home.name) {
                        popUpTo(Screen.Welcome.name) { inclusive = true }
                    }
                },
                userViewModel = userViewModel
            )
        }

        composable(Screen.Home.name) {
            HomeScreen(
                userName = userName,
                onCheckoutClick = { navController.navigate(Screen.OrderSummary.name) }, // Alur baru
                userViewModel = userViewModel
            )
        }

        composable(Screen.OrderSummary.name) {
            OrderSummaryScreen(
                onNextClick = { navController.navigate(Screen.Delivery.name) },
                cartItems = cartItems,
                totalPrice = totalPrice,
                userViewModel = userViewModel
            )
        }

        composable(Screen.Delivery.name) {
            DeliveryScreen(
                onOrderClick = {
                    navController.navigate(Screen.Success.name) {
                        popUpTo(Screen.Home.name)
                    }
                },
                userName = userName
            )
        }

        composable(Screen.Success.name) {
            SuccessScreen(userName = userName)
        }
    }
}
