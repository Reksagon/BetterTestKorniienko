package app.bettermetesttask.datamovies.repository.stores

import app.bettermetesttask.datamovies.database.entities.MovieEntity
import app.bettermetesttask.domainmovies.entries.Movie
import javax.inject.Inject

open class MoviesMapper @Inject constructor() {

    open val mapToLocal: (Movie) -> MovieEntity = {
        with(it) {
            MovieEntity(id, title, description, posterPath)
        }
    }

    open val mapFromLocal: (MovieEntity) -> Movie = {
        with(it) {
            Movie(id, title, description, posterPath)
        }
    }
}
