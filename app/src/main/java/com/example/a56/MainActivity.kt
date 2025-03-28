package com.example.a56

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a56.local.Phrase
import com.example.a56.local.Squirrel
import com.example.a56.local.SquirrelWithPhrase
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
    var arrayMockPhrase = ArrayList<Phrase>().apply {
        add(Phrase(1, "No nuts bitch"))

    }
    var arrayMockSquirrel = ArrayList<SquirrelWithPhrase>().apply {
        add(SquirrelWithPhrase(Squirrel(123, "Black", "Murka", 1),Phrase(0, "1 2 3")))
        add(SquirrelWithPhrase(Squirrel(123, "Black", "Murka", 2),Phrase(0, "1 dad 3")))
        add(SquirrelWithPhrase(Squirrel(123, "Black", "Murka", 2),Phrase(0, "1 dad 3")))
    }
    val squirrelWithPhraseMutableState = MutableStateFlow<List<SquirrelWithPhrase>>(arrayMockSquirrel)
    val squirrelMutableState = MutableStateFlow<List<SquirrelWithPhrase>>(arrayMockSquirrel)
    val squirrelState = squirrelMutableState.asStateFlow()
    val phraseMutableState = MutableStateFlow<List<Phrase>>(arrayMockPhrase)
    val phraseState = phraseMutableState.asStateFlow()
    val squirrelWithPhraseState = squirrelWithPhraseMutableState.asStateFlow()
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
        var phraseText by remember { mutableStateOf("") }
        var idPhraseNext by remember { mutableStateOf(0) }
        var squirrelId by remember { mutableStateOf("0") }
        var arraySquirrelWithPhrase = squirrelState.collectAsState().value
        var arrayPhrase = phraseState.collectAsState().value

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
                    SingleToneBD.db.squirrelDao().insertSquirrel(
                        Squirrel(
                            Integer.parseInt(squirrelId),
                            "Black",
                            squirrelText,
                            1
                        )
                    )
                    squirrelMutableState.value = SingleToneBD.db.squirrelDao().getSquirrelWithPhrase()
                }
            }) {
                Text(text = "Insert Squirrel")
            }
            TextField(value = squirrelId,
                onValueChange = { it -> squirrelId = it })
            Button(onClick = {
                GlobalScope.launch {
                    SingleToneBD.db.squirrelDao()
                        .updateSquirrel(
                            Squirrel(
                                Integer.parseInt(squirrelId),
                                "Black",
                                squirrelText,
                                1
                            )
                        )
                    squirrelMutableState.value = SingleToneBD.db.squirrelDao().getSquirrelWithPhrase()
                }
            }) {
                Text(text = "Update squirrel")
            }

            Button(onClick = {
                GlobalScope.launch {
                    SingleToneBD.db.squirrelDao()
                        .deleteSquirrel(
                            Squirrel(
                                Integer.parseInt(squirrelId),
                                "Black",
                                "wiwiwi",
                                1
                            )
                        )
                    squirrelMutableState.value = SingleToneBD.db.squirrelDao().getSquirrelWithPhrase()
                    idPhraseNext = squirrelWithPhraseState.value.size + 1
                }
            }) {
                Text(text = "Delete")
            }
            TextField(value = phraseText,
                onValueChange = { it -> phraseText = it })

            Button(onClick = {
                GlobalScope.launch {
                    SingleToneBD.db.squirrelDao().insertPhrase(Phrase(0, phraseText))
                    //phraseMutableState.value = SingleTone
                    phraseMutableState.value = SingleToneBD.db.squirrelDao().getAllPhrase()
                }
            }) {
                Text(text = "Insert Phrase")
            }

            LazyColumn {
                items(arraySquirrelWithPhrase) { SquirrelWithPhrase ->
                    Card(modifier = Modifier.size(200.dp, 100.dp)) {
                        Row {
                        Column(
                            modifier = Modifier.padding(5.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = SquirrelWithPhrase.squirrel.name)
                            Text(text = SquirrelWithPhrase.squirrel.colorTrail)
                        }
                        Column(
                            modifier = Modifier.weight(1.0f),
                            verticalArrangement = Arrangement.Center

                        ){
                            Text(text = "FASAFF")
                        }
                    }
                    }
                }
            }
            LazyColumn {
                items(arrayPhrase) { Phrase ->
                    Card(modifier = Modifier.size(200.dp, 100.dp)) {
                        Row {
                            Column(
                                modifier = Modifier.padding(5.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "" + Phrase.phraseId)
                                Text(text = Phrase.phrase)
                            }
                            Column(
                                modifier = Modifier.weight(1.0f),
                                verticalArrangement = Arrangement.Center

                            ){
                                Text(text = "FASAFF")
                            }
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

