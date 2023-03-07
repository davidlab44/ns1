package com.david.nsn.ui

import android.os.Bundle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.david.nsn.ui.theme.NsnTheme

import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.david.nsn.data.remote.QuotesApi
import com.david.nsn.repository.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            if (result != null) {
                // Checking the results
                val result = result.body().toString()
                Log.d("response: ", result)
            }
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

@Composable
fun ListWithColumn(items: String) { // (1)
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) { // (2)
        items.forEach { item -> // (4)
            ListItemRow(item.toString()) // (5)
        }
    }
}

@Composable // (3)
fun ListItemRow(item: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = item, style = MaterialTheme.typography.bodySmall)
    }
}