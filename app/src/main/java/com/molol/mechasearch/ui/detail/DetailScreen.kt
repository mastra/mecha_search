package com.molol.mechasearch.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import com.molol.mechasearch.R
import com.molol.mechasearch.api.model.Description
import com.molol.mechasearch.api.model.ItemDetail
//import com.molol.mechasearch.api.util.DescriptionMapper
//import com.molol.mechasearch.api.util.ItemDetailMapper
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.ui.search.Loading
import com.molol.mechasearch.ui.theme.MechaSearchTheme
import com.molol.mechasearch.ui.theme.VeryLightGray
import com.molol.mechasearch.util.SampleItemResult
import com.molol.mechasearch.util.toPrice
import org.koin.androidx.compose.getViewModel


@Composable
fun DetailScreen(navController: NavController, itemId: String?) {
    DetailContent(itemId) { navController.popBackStack() }
}

@Composable
fun DetailContent(itemId: String?, onBack: () -> Unit) {//
    val viewModel = getViewModel<DetailViewModel>()

    val item = Gson().fromJson(SampleItemResult.itemDetail, ItemDetail::class.java)
    val detail = viewModel.detail
    val detailrem = remember {
        viewModel.getDetail(itemId ?: "")
    }
    //val itemDescription = Gson().fromJson(SampleItemResult.itemDescription, Description::class.java)
    //detail.value.description = itemDescription.toModel()

    MechaSearchTheme() {
        Scaffold(
            contentColor = MaterialTheme.colors.primary,
            topBar = {
                TopAppBar(

                    title = {
                        Text(text = stringResource(R.string.producto) + " " + itemId)
                    },
                    backgroundColor = MaterialTheme.colors.primarySurface,
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->

            Surface {
                Box {
                    ItemDetailColumn(item = detail.value)
                    if (viewModel.showLoading.value) {
                        Loading()
                    }
                }


            }

        }
    }
}

@Composable
fun ItemDetailColumn(item: Item) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Nuevo",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
        )
        Text(
            text = item.title ?: "",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        item.pictures_url?.let {
            ImageSlider(it)
        }

        Text(
            text = "\$ ${item.price?.toPrice() ?: ""}",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Text(
            text = stringResource(id = R.string.descripcion),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(text = item.description ?: "")

    }
}

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

@Preview
@Composable
fun DetailPreview() {
    DetailContent("888") {}
}