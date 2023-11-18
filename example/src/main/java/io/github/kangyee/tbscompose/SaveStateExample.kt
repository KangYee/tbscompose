package io.github.kangyee.tbscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.kangyee.tbscompose.library.TbsWebView
import io.github.kangyee.tbscompose.library.rememberSaveableWebViewState
import io.github.kangyee.tbscompose.library.rememberWebViewNavigator
import io.github.kangyee.tbscompose.library.rememberWebViewState
import io.github.kangyee.tbscompose.ui.theme.TbsComposeTheme

/**
 * @KangYee 2023/11/18
 */
class SaveStateExample : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TbsComposeTheme {
                Surface {
                    val navController = rememberNavController()
                    Column(Modifier.fillMaxSize()) {
                        Row {
                            Button(onClick = { navController.popBackStack() }) {
                                Text("Baidu")
                            }
                            Button(onClick = { navController.navigate("qq") }) {
                                Text("QQ")
                            }
                        }

                        Spacer(modifier = Modifier.size(16.dp))

                        NavHost(navController = navController, startDestination = "baidu") {
                            composable("baidu") {
                                Baidu()
                            }
                            composable("qq") {
                                QQ()
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun Baidu() {
    val webViewState = rememberSaveableWebViewState()
    val navigator = rememberWebViewNavigator()

    LaunchedEffect(navigator) {
        val bundle = webViewState.viewState
        if (bundle == null) {
            navigator.loadUrl("https://www.baidu.com")
        }
    }

    TbsWebView(
        state = webViewState,
        navigator = navigator,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun QQ() {
    val webViewState = rememberWebViewState(url = "https://www.qq.com")

    TbsWebView(
        state = webViewState,
        modifier = Modifier.fillMaxSize()
    )
}