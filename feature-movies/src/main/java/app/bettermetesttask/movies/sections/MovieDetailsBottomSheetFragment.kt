package app.bettermetesttask.movies.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import app.bettermetesttask.domainmovies.entries.Movie
import app.bettermetesttask.featurecommon.injection.viewmodel.viewModel
import app.bettermetesttask.featurecommon.utils.images.GlideApp
import app.bettermetesttask.movies.R
import app.bettermetesttask.movies.databinding.MovieDetailsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MovieDetailsBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: MovieDetailsBottomSheetBinding
    private var movie: Movie? = null
    private var onLikeClicked: ((Movie) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getSerializable(ARG_MOVIE) as? Movie
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MovieDetailsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie?.let { movie ->
            with(binding) {
                titleTv.text = movie.title
                descriptionTv.text = movie.description
                GlideApp.with(requireContext())
                    .load(movie.posterPath)
                    .into(posterIv)

                btnLike.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        if (movie.liked) R.drawable.ic_favorite_liked else R.drawable.ic_favorite_not_liked
                    )
                )

                btnLike.setOnClickListener {
                    onLikeClicked?.invoke(movie)
                    dismiss()
                }
            }
        }
    }

    companion object {
        private const val ARG_MOVIE = "arg_movie"

        fun newInstance(movie: Movie, onLikeClicked: (Movie) -> Unit): MovieDetailsBottomSheetFragment {
            return MovieDetailsBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_MOVIE, movie)
                }
                this.onLikeClicked = onLikeClicked
            }
        }
    }

}
