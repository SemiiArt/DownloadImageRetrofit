package com.tuyenhoang.callapiretrofit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.tuyenhoang.callapiretrofit.R
import com.tuyenhoang.callapiretrofit.adapter.ListImageAdapter
import com.tuyenhoang.callapiretrofit.api.ApiService
import com.tuyenhoang.callapiretrofit.databinding.ActivityMainBinding
import com.tuyenhoang.callapiretrofit.model.Image
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ListImageAdapter.IImage {
    private lateinit var binding: ActivityMainBinding
    private var listImages = mutableListOf<Image>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getAllImage()
        var i = 0
        while (i < listImages.size) {
            Log.d("Tuyen", listImages[i].url!!)
            i++
        }
    }

    private fun getAllImage() {
        val imagesResponse: Call<List<String?>?>? = ApiService.inter.getImages()
        imagesResponse!!.enqueue(object : Callback<List<String?>?> {
            override fun onResponse(
                call: Call<List<String?>?>,
                response: Response<List<String?>?>
            ) {
                if (response.isSuccessful) {
                    val list =response.body() as MutableList<String>
                    var i=0
                    while (i<list.size){
                        listImages.add(Image(list[i]))
                        i++
                    }
                    binding.gridView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    binding.gridView.adapter = ListImageAdapter(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<List<String?>?>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }

        })

//        listImages.add(Image("https://toplist.vn/images/800px/vnexpressnet-23999.jpg"))
//        listImages.add(Image("https://toplist.vn/images/800px/newszingvn-23993.jpg"))
//        listImages.add(Image("https://toplist.vn/images/800px/dantricomvn-24003.jpg"))
//        listImages.add(Image("https://toplist.vn/images/800px/vnexpressnet-23998.jpg"))
//        listImages.add(Image("https://toplist.vn/images/800px/vietnamnetvn-24006.jpg"))
//        listImages.add(Image("https://toplist.vn/images/800px/tuoitrevn-24008.jpg"))
//        binding.gridView.layoutManager = GridLayoutManager(this@MainActivity, 2)
//        binding.gridView.adapter = ListImageAdapter(this@MainActivity)
    }

    override fun getCount() = listImages.size

    override fun getItem(position: Int): Image {
        return listImages[position]
    }

    override fun setClick(position: Int) {
        val intent=Intent(this,MainActivity2::class.java)
        intent.putExtra("data",listImages[position])
        startActivity(intent)
    }
}