package com.razorquake.stoxx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.razorquake.stoxx.presentation.StockViewModel
import com.razorquake.stoxx.ui.theme.StoxxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoxxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StockLookupApp(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = hiltViewModel()
                    )
                }
            }
        }
    }
}
@Composable
fun StockLookupApp(modifier: Modifier, viewModel: StockViewModel) {
    val stockQuote by viewModel.stockQuote.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    var symbol by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Stock Lookup",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom =  16.dp)
        )
        TextField(
            keyboardActions = KeyboardActions(onDone = { viewModel.getStockQuote(symbol.text) }),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            value = symbol,
            onValueChange = { symbol = it },
            label = { Text("Enter Stock Symbol") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_query_stats_24),
                    contentDescription = null,
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.getStockQuote(symbol.text) }) {
            Text("Get Stock Quote")
        }
        Spacer(modifier = Modifier.height(16.dp))
        when {
            loading -> CircularProgressIndicator()
            error != null -> Text(error!!)
            stockQuote != null -> {
                Text("Symbol: ${stockQuote!!.globalQuote.symbol}")
                Text("Price: ${stockQuote!!.globalQuote.price}")
                Text("Change Percent: ${stockQuote!!.globalQuote.changePercent}")
            }
        }
    }
}
