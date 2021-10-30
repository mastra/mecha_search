package com.molol.mechasearch.ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.molol.mechasearch.ui.theme.VeryLightGray


@Composable
fun ImageSlider(pictures: List<String>) {
    val sliderState = rememberLazyListState()
    val currentPage = remember {
        derivedStateOf {
            sliderState.firstVisibleItemIndex
        }
    }
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = "${currentPage.value + 1} / ${pictures.size}",
            Modifier
                .background(VeryLightGray, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 3.dp)

        )
        Box(modifier = Modifier.fillMaxWidth()) {
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    state = sliderState
                ) {
                    items(pictures) { picture ->
                        Image(
                            painter = rememberImagePainter(picture),
                            //painter = painterResource(id = R.drawable.trek),
                            contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .width(maxWidth)
                        )
                    }

                }
            }
        }
    }
}
