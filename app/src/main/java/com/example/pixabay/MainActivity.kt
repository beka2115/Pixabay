package com.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pixabay.databinding.ActivityMainBinding
import com.example.pixabay.models.ImageModel
import com.example.pixabay.models.PixaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var imgList: ArrayList<ImageModel> = arrayListOf()

    var adapter = PixabayAdapter(arrayListOf())
    var page = 1
    var per_page = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()

    }

    private fun initClicker() {
        with(binding) {
            searchBtn.setOnClickListener {
                per_page = 3
                page = 1
                doRequest()
            }
            nextPageBtn.setOnClickListener {
                per_page = 3
                page++
                doRequest()
            }
            updateBtn.setOnClickListener {
                per_page += 3
                doRequest()
            }
        }
    }


    fun doRequest() {
        RetrofitService().api.searchImage(
            keyWord = binding.nameEd.text.toString(),
            page = page,
            per_page = per_page
        )
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.hits?.let {
                            adapter = PixabayAdapter(it)
                            binding.recyclerView.adapter = adapter
                        }
                        Log.e(
                            "ololo",
                            "onResponse: ${response.body()?.hits!![0].largeImageURL}"
                        )
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onResponse: ${t.message}")
                }

            })
    }
}