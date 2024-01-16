package io.github.alaksion.imagefy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.alaksion.imagefy.ui.theme.ImagefyTheme
import io.github.alaksion.unsplashwrapper.sdk.UnsplashWrapperSdk

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sdk = UnsplashWrapperSdk.Instance
            sdk.initialize("smXXBhupy1xAZ6npy5NjO7XGmJ0fjuvLnQfaz_UwWlY")

            ImagefyTheme {
                LaunchedEffect(key1 = Unit) {
                    try {
                        sdk.photosRepository.getPhotos().map {
                            println(it.description)
                        }
                    } catch (e: Throwable) {
                        println(e)
                    }
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
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