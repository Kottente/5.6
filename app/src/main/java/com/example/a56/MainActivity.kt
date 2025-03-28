package com.example.a56

import android.content.Context
import android.os.Bundle
import android.widget.NumberPicker.OnValueChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a56.ui.theme._56Theme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    init {
        context = this
    }

    companion object {
        private var context: MainActivity? = null
        fun getContext(): Context {
            return context!!.applicationContext
        }
    }

    var arrayMockSquirrel = ArrayList<Squirrel>().apply {
        add(Squirrel(123, "Black", "Murka"))
        add(Squirrel(124, "White", "Chucha"))
        add(Squirrel(125, "Red", "Chacha"))
    }

    val squirrelMutableState = MutableStateFlow<List<Squirrel>>(arrayMockSquirrel)
    val squirrelState = squirrelMutableState.asStateFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _56Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        var squirrelText by remember { mutableStateOf("") }
        var squirrelId by remember { mutableStateOf("") }
        var arraySquirrel = squirrelState.collectAsState().value
        //var squirrels by remember { mutableStateOf(arraySquirrel) }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = squirrelText,
                onValueChange = { it -> squirrelText = it })

            Button(onClick = {
                GlobalScope.launch {
                    SingleToneBD.db.squirrelDao().insertSquirrel(Squirrel(Integer.parseInt(squirrelId), "Black", squirrelText))
                    squirrelMutableState.value = SingleToneBD.db.squirrelDao().getAllOfSquirrel()
                }
            }) {
                Text(text = "Insert Squirrel")
            }
            TextField(value = squirrelId,
                onValueChange = { it -> squirrelId = it })
            Button(onClick = {
                GlobalScope.launch {
                    SingleToneBD.db.squirrelDao()
                        .updateSquirrel(Squirrel(Integer.parseInt(squirrelId), "Black", squirrelText))
                    squirrelMutableState.value = SingleToneBD.db.squirrelDao().getAllOfSquirrel()
                }
            }) {
                Text(text = "Update squirrel")
            }

            Button(onClick = {
                GlobalScope.launch {
                    SingleToneBD.db.squirrelDao()
                        .deleteSquirrel(Squirrel(Integer.parseInt(squirrelId), "Black", "wiwiwi"))
                    squirrelMutableState.value = SingleToneBD.db.squirrelDao().getAllOfSquirrel()
                }
            }) {
                Text(text = "Delete Squirrel")
            }


            LazyColumn {
                items(arraySquirrel) { squirrel ->
                    Card(modifier = Modifier.size(200.dp, 100.dp)) {
                        Column {
                            Text(text = squirrel.name)
                            Text(text = squirrel.colorTrail)
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        _56Theme {
            Greeting("Android")
        }
    }
}

