package app.bettermetesttask.datamovies.repository

import app.bettermetesttask.datamovies.database.entities.MovieEntity
import app.bettermetesttask.datamovies.repository.MoviesRepositoryImpl
import app.bettermetesttask.datamovies.repository.stores.MoviesLocalStore
import app.bettermetesttask.datamovies.repository.stores.MoviesMapper
import app.bettermetesttask.datamovies.repository.stores.MoviesRestStore
import app.bettermetesttask.domainmovies.entries.Movie
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.io.IOException
import app.bettermetesttask.domaincore.utils.Result
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.whenever
import org.mockito.quality.Strictness

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MoviesRepositoryTest {

//    private lateinit var moviesRestStore: MoviesRestStore
//    private lateinit var moviesLocalStore: MoviesLocalStore
//    private lateinit var moviesMapper: MoviesMapper
//
//    private lateinit var repository: MoviesRepositoryImpl
//
//    private val sampleMovies = listOf(
//        Movie(id = 1, title = "Movie 1", description = "description", posterPath = "posterPath", liked = false),
//        Movie(id = 2, title = "Movie 2", description = "description", posterPath = "posterPath", liked = true)
//    )
//
//    @BeforeEach
//    fun setUp() {
//        moviesRestStore = mock()
//        moviesLocalStore = mock()
//        moviesMapper = mock()
//
//        repository = MoviesRepositoryImpl(moviesLocalStore, moviesMapper)
//
//        sampleMovies.forEach { movie ->
//            whenever(moviesMapper.mapToLocal(eq(movie))).thenReturn(
//                MovieEntity(movie.id, movie.title, movie.description, movie.posterPath)
//            )
//        }
//
//        sampleMovies.forEach { movie ->
//            whenever(moviesMapper.mapFromLocal(any<MovieEntity>())).thenAnswer { invocation ->
//                val entity = invocation.getArgument<MovieEntity>(0)
//                Movie(entity.id, entity.title, entity.description, entity.posterPath, liked = false)
//            }
//        }
//    }
//
//    @Test
//    fun `should return movies from API when local storage is empty`() = runTest {
//        whenever(moviesLocalStore.getMovies()).thenReturn(emptyList())
//        whenever(moviesRestStore.getMovies()).thenReturn(sampleMovies)
//
//        val result = repository.getMovies()
//
//        assertTrue(result is Result.Success)
//        assertEquals(sampleMovies, (result as Result.Success).data)
//
//        verify(moviesLocalStore).saveMovies(anyList())
//    }
//
//    @Test
//    fun `should return movies from local storage when API fails`() = runTest {
//        whenever(moviesRestStore.getMovies()).thenThrow(IOException("Network error"))
//        whenever(moviesLocalStore.getMovies()).thenReturn(sampleMovies.map { moviesMapper.mapToLocal(it) })
//
//        val result = repository.getMovies()
//
//        assertTrue(result is Result.Success)
//        assertEquals(sampleMovies, (result as Result.Success).data)
//    }
//
//    @Test
//    fun `should return error when both API and local storage fail`() = runTest {
//        whenever(moviesRestStore.getMovies()).thenThrow(IOException("Network error"))
//        whenever(moviesLocalStore.getMovies()).thenReturn(emptyList())
//
//        val result = repository.getMovies()
//
//        assertTrue(result is Result.Error)
//    }
}