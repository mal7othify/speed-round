/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.navigation.Screen
import com.example.androiddevchallenge.ui.Home
import com.example.androiddevchallenge.ui.Login
import com.example.androiddevchallenge.ui.Welcome
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.Transparent.toArgb()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView.rootView) { _, insets ->
            insets
        }

        setContent {
            MyTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(navController, startDestination = Screen.Welcome.route) {
                        composable(Screen.Welcome.route) {
                            Welcome(
                                isSystemInDarkTheme(),
                                navController
                            )
                        }
                        composable(Screen.Login.route) {
                            Login(
                                isSystemInDarkTheme(),
                                navController
                            )
                        }
                        composable(
                            "${Screen.Home.route}/{email}",
                            arguments = listOf(navArgument("email") {
                                type = NavType.StringType
                            })
                        ) {
                            Home(it.arguments?.getString("email") ?: "")
                        }
                    }
                }
            }
        }
    }
}
