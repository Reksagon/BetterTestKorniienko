package app.bettermetesttask.datamovies.repository

import app.bettermetesttask.datamovies.repository.stores.MoviesLocalStore
import app.bettermetesttask.datamovies.repository.stores.MoviesMapper
import app.bettermetesttask.datamovies.repository.stores.MoviesRestStore
import app.bettermetesttask.domaincore.utils.Result
import app.bettermetesttask.domainmovies.entries.Movie
import app.bettermetesttask.domainmovies.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val localStore: MoviesLocalStore,
    private val mapper: MoviesMapper
) : MoviesRepository {

    private val restStore = MoviesRestStore()

    override suspend fun getMovies(): Result<List<Movie>> {
        return runCatching {
            val movies = restStore.getMovies()
            localStore.saveMovies(movies.map { mapper.mapToLocal(it) }) // Збереження в БД
            Result.Success(movies)
        }.getOrElse { error ->
            val cachedMovies = localStore.getMovies().map { mapper.mapFromLocal(it) }
            if (cachedMovies.isNotEmpty()) {
                Result.Success(cachedMovies) // Якщо API не працює, повертаємо кеш
            } else {
                Result.Error(error) // Якщо кешу нема - повертаємо помилку
            }
        }
    }




    override suspend fun getMovie(id: Int): Result<Movie> {
        return try {
            val movieEntity = localStore.getMovie(id) ?: throw Exception("Movie not found")
            Result.Success(mapper.mapFromLocal(movieEntity))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }



    override fun observeLikedMovieIds(): Flow<List<Int>> {
        return localStore.observeLikedMoviesIds()
    }

    override suspend fun addMovieToFavorites(movieId: Int) {
        localStore.likeMovie(movieId)
    }

    override suspend fun removeMovieFromFavorites(movieId: Int) {
        localStore.dislikeMovie(movieId)
    }
}