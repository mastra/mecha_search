package com.molol.mechasearch

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.GsonBuilder
import com.molol.mechasearch.api.ApiService
import com.molol.mechasearch.ui.detail.DetailScreen
import com.molol.mechasearch.ui.search.SearchScreen
import com.molol.mechasearch.ui.theme.MechaSearchTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            Surface {
                //SearchScreen()
                //DetailScreen()
                MeChaSearchApp()
            }
        }
    }
}


@Composable
fun MeChaSearchApp() {

    MechaSearchTheme() {
        val navController = rememberNavController()
        Scaffold() { innerPadding ->
            NavHost(navController, startDestination = "search", Modifier.padding(innerPadding)) {
                composable("search") { SearchScreen(navController) }
                composable("detail/{itemId}") {
                    DetailScreen(
                        navController,
                        it.arguments?.getString("itemId")
                    )
                }
            }

        }
    }

}
