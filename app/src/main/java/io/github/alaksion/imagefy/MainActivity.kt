package io.github.alaksion.imagefy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.github.alaksion.imagefy.ui.theme.ImagefyTheme
import io.github.alaksion.unsplashwrapper.api.authorization.domain.model.AuthorizationScope
import io.github.alaksion.unsplashwrapper.sdk.UnsplashWrapperSdk
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.datetime.Instant


fun main() {
    val string = "2023-04-28T13:09:43Z"
    val parse = Instant.parse(string)
    println(parse)
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sdk = UnsplashWrapperSdk.Instance
            sdk.initialize(
                apiKey = "",
                privateKey = ""
            )

            ImagefyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse(
                                        sdk.auth.buildAuthorizeUrl(
                                            redirectUri = "urn:ietf:wg:oauth:2.0:oob",
                                            scopes = persistentSetOf(AuthorizationScope.Public)
                                        )
                                    )

                                }
                                startActivity(intent)
                            }) {
                            Text("Open auth")
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImagefyTheme {
        Greeting("Android")
    }
}