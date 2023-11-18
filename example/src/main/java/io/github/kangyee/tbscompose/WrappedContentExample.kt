package io.github.kangyee.tbscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kangyee.tbscompose.library.TbsWebView
import io.github.kangyee.tbscompose.library.rememberWebViewStateWithHTMLData
import io.github.kangyee.tbscompose.ui.theme.TbsComposeTheme
import kotlinx.coroutines.launch

/**
 * @KangYee 2023/11/18
 */
class WrappedContentExample : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TbsComposeTheme {
                Surface {
                    val sheetState = rememberModalBottomSheetState(
                        initialValue = ModalBottomSheetValue.Hidden
                    )
                    ModalBottomSheetLayout(
                        sheetState = sheetState,
                        sheetContent = {
                            WrappingWebContent("Hello")
                        }
                    ) {
                        val scope = rememberCoroutineScope()
                        Box(Modifier.fillMaxSize()) {
                            Button(onClick = {
                                scope.launch { sheetState.show() }
                            }, Modifier.align(Alignment.Center)) {
                                Text("Open Sheet")
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun WrappingWebContent(
    body: String
) {
    val webViewState = rememberWebViewStateWithHTMLData(
        data = "<html><head>\n" +
                "<style>\n" +
                "body {\n" +
                "  background-color: #f00;\n" +
                "}\n" +
                "</style>\n" +
                "</head><body><p>$body</p></body></html>"
    )
    TbsWebView(
        state = webViewState,
        modifier = Modifier.fillMaxWidth()
            .heightIn(min = 1.dp), // A bottom sheet can't support content with 0 height.
        captureBackPresses = false,
    )
}