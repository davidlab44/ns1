package com.david.nsn.ui

import android.os.Bundle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.david.nsn.ui.theme.NsnTheme

import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.david.nsn.data.remote.QuotesApi
import com.david.nsn.repository.RetrofitHelper
import com.david.nsn.util.BASE_URL
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        setContent {
            NsnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }*/
        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = quotesApi.getQuotes()
            if (result != null)
            // Checking the results
                Log.d("response: ", result.body().toString())
        }
    }
}





@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NsnTheme {
        Greeting("Android")
    }
}