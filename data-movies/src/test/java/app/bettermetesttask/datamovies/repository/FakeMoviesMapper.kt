package app.bettermetesttask.datamovies.repository

import app.bettermetesttask.datamovies.database.entities.MovieEntity
import app.bettermetesttask.datamovies.repository.stores.MoviesMapper
import app.bettermetesttask.domainmovies.entries.Movie

class FakeMoviesMapper : MoviesMapper() {

    override val mapToLocal: (Movie?) -> MovieEntity = { movie ->
        requireNotNull(movie)
        MovieEntity(movie.id, movie.title, movie.description, movie.posterPath)
    }

    override val mapFromLocal: (MovieEntity?) -> Movie = { entity ->
        requireNotNull(entity)
        Movie(entity.id, entity.title, entity.description, entity.posterPath, liked = false)
    }
}
