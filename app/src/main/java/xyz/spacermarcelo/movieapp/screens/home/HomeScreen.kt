package xyz.spacermarcelo.movieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import xyz.spacermarcelo.movieapp.model.Movie
import xyz.spacermarcelo.movieapp.model.getMovies
import xyz.spacermarcelo.movieapp.navigation.MovieScreens
import xyz.spacermarcelo.movieapp.widget.MovieRow

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Yellow, elevation = 5.dp) {
            Text(text = "Filmes")
        }
    }) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(it) { movie ->
                    navController.navigate(
                        route = MovieScreens.DetailsScreen.name
                                + "/$movie"
                    )
                }
            }
        }

    }


}
