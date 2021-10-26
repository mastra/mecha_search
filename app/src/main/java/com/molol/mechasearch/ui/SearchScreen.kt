package com.molol.mechasearch.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import com.molol.mechasearch.ui.theme.MechaSearchTheme
import com.molol.mechasearch.R
import com.molol.mechasearch.api.ItemResult
import com.molol.mechasearch.api.SearchResult
import com.molol.mechasearch.api.util.ItemResultMapper
import com.molol.mechasearch.domain.model.Item
import com.molol.mechasearch.domain.model.TestItems
import com.molol.mechasearch.util.SampleItemResult
import com.molol.mechasearch.util.toPrice
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.molol.mechasearch.ui.theme.VeryLightGray

@Composable
fun SearchScreen(navController: NavController) {
    SearchContent() {
        navController.navigate("detail/$it")
    }
}

@Composable
fun SearchContent(onClick: (String) -> Unit) {
    val searchResult = Gson().fromJson(SampleItemResult.itemResultShort, SearchResult::class.java)
    Surface {
        Column {
            SearchBar()

            searchResult?.let {
                SearchInfo(n = it.paging?.total ?: 0)
                ListResult(searchResult = it, onClick = onClick)
            }
        }

    }
}

@Preview()
@Composable
fun DefaultPreview() {
    MechaSearchTheme() {
        Scaffold(
            contentColor = MaterialTheme.colors.primary
        ) { innerPadding ->

            SearchContent() {}
        }
    }
}


@Composable
fun ItemRow(
    item: Item,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        modifier = modifier.semantics { heading() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Surface(
                modifier = Modifier.size(128.dp),
                shape = MaterialTheme.shapes.medium,
                color = VeryLightGray
            ) {

                Image(
                    painter = rememberImagePainter(item.thumbnail),
                    //painter = painterResource(id = R.drawable.trek),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column() {
                Text(
                    text = item.title ?: "",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(bottom = 8.dp)

                )
                Text(
                    text = "\$ ${item.price?.toPrice()}",
                    style = MaterialTheme.typography.h1,
                    //color = MaterialTheme.colors.secondary
                )
                if (item.free_shipping)
                    Text(
                        text = stringResource(id = R.string.free_shipping),
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.secondary
                    )

            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {

    val result1 = Gson().fromJson(SampleItemResult.item1, ItemResult::class.java)
    val item1 = ItemResultMapper().toModel(result1)

    MechaSearchTheme {
        Surface {
            ItemRow(item1, Modifier.clickable { Log.d("TAG", "click ${item1.title}") })

        }
    }
}

@Composable
fun ListResult(searchResult: SearchResult, onClick: (String) -> Unit) {
    val items = searchResult.results?.map { ItemResultMapper().toModel(it) } ?: listOf()

    LazyColumn {
        items(items) { item ->
            ItemRow(item, Modifier.clickable {
                item.id?.let {
                    onClick(it)
                }
            })
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {

    val searchResult = Gson().fromJson(SampleItemResult.itemResultShort, SearchResult::class.java)

    MechaSearchTheme {
        Surface {
            searchResult?.let {
                ListResult(searchResult = it, {})
            }


        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        backgroundColor = MaterialTheme.colors.primarySurface
    )
}


@Composable
fun SearchInfo(n: Int) {
    Surface(elevation = 18.dp) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "$n ${stringResource(id = R.string.resultados)}",
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Divider(modifier = Modifier.height(3.dp))
        }

    }

}

@Composable
fun SearchBar() {
    val query = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.primary,
        contentColor = Color.Transparent
    ) {

        TextField(value = query.value,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)
                .background(
                    color = MaterialTheme.colors.background,
                    shape = CircleShape
                ),
            maxLines = 1,
            // label = {
            //    Text( text = stringResource(id = R.string.search_caption))},
            onValueChange = { query.value = it },
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = stringResource(id = R.string.search))
            })
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    MechaSearchTheme {
        Surface {
            SearchBar()
        }
    }
}