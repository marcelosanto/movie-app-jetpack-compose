package xyz.spacermarcelo.movieapp.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import xyz.spacermarcelo.movieapp.model.Movie

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //.height(130.dp)
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(movie.poster)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image movie",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )

            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp
                                    )
                                ) {
                                    append("Plot: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                ) {
                                    append(movie.plot)
                                }
                            }, modifier = Modifier.padding(6.dp)
                        )

                        Divider(modifier = Modifier.padding(3.dp))

                        Text(
                            "Director: ${movie.director}",
                            style = MaterialTheme.typography.caption
                        )

                        Text("Actors: ${movie.actors}", style = MaterialTheme.typography.caption)

                        Text("Rating: ${movie.rating}", style = MaterialTheme.typography.caption)
                    }
                }


                Icon(
                    imageVector =
                    if (expanded) Icons.Filled.KeyboardArrowUp
                    else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { expanded = !expanded },
                    tint = Color.DarkGray
                )
            }


        }


    }
}

@Composable
fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList.first().images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 5.dp
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(image)
                        .build(),
                    contentDescription = "Image movie",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RectangleShape)
                )
            }
        }
    }
}