package com.rhm.mysubmission03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var adapterMovie: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    companion object {
        private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int): MainFragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var index = 1
        if (arguments != null) {
            index = arguments?.getInt(ARG_SECTION_NUMBER, 0) as Int
        }

        val verify = "$index"
        if (verify == "1") {
            adapterMovie = MovieAdapter()
            adapterMovie.notifyDataSetChanged()

            rv_movie_tvshow.layoutManager = LinearLayoutManager(this.activity)
            rv_movie_tvshow.adapter = adapterMovie

            movieViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(MovieViewModel::class.java)

            movieViewModel.setMovie()
            showLoading(true)

            movieViewModel.getMovie().observe(this, Observer { movieItems ->
                if (movieItems != null) {
                    adapterMovie.setData(movieItems)
                    showLoading(false)
                }
            })
        } else if (verify == "2") {
            // Isinya untuk tab TV Show
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }


}
