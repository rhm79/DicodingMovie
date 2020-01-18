package com.rhm.mysubmission03

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MovieViewModel : ViewModel() {
    companion object {
        //private const val API_KEY = "b9f348cb3669613861ca4cd300c88fd4"
        private const val API_KEY = "5d26e368e770364acb046cd589e1e619"
    }

    val listMovies = MutableLiveData<ArrayList<MovieItems>>()

    internal fun setMovie() {
        val client = AsyncHttpClient()
        val listItems = ArrayList<MovieItems>()
        //val url = "https://api.themoviedb.org/3/discover/movie?api_key=$API_KEY&language=en-US&page=3"
        val url =
            "https://api.openweathermap.org/data/2.5/group?id=1650357&units=metric&appid=$API_KEY"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responObject = JSONObject(result)
                    //val list = responObject.getJSONArray("results")
                    val list = responObject.getJSONArray("list")

                    for (i in 0 until list.length()) {
                        val movie = list.getJSONObject(i)
                        val movieItems = MovieItems()
                        movieItems.id = movie.getInt("id")
                        movieItems.title = movie.getString("name")
                        movieItems.date_release = movie.getString("name")
                        movieItems.description = movie.getString("name")
                        listItems.add(movieItems)
                    }
                    listMovies.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("onFailure", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())

            }
        })
    }

    internal fun getMovie(): LiveData<ArrayList<MovieItems>> {
        return listMovies
    }
}