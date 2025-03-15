package app.bettermetesttask.datamovies.repository.stores

import app.bettermetesttask.domainmovies.entries.Movie
import kotlinx.coroutines.delay
import java.io.IOException
import javax.inject.Inject
import kotlin.random.Random

class MoviesRestStore @Inject constructor() {

    private val statusCodes = listOf(200, 201, 202, 304, 400)

    suspend fun getMovies(): List<Movie> {
        val statusCode = statusCodes.random()
        delay(Random.nextLong(500, 3_000)) // Симуляція затримки API

        return if (statusCode < 400) {
            MoviesFactory.createMoviesList()
        } else {
            throw IOException("Failed to fetch movies: HTTP $statusCode") // Кидаємо IOException
        }
    }

}