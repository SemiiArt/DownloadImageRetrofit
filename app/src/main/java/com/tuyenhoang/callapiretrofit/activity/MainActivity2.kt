package com.tuyenhoang.callapiretrofit.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.tuyenhoang.callapiretrofit.R
import com.tuyenhoang.callapiretrofit.databinding.ActivityMain2Binding
import com.tuyenhoang.callapiretrofit.model.Image
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MainActivity2 : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main2)
        val intent=intent
        val image=intent.getSerializableExtra("data") as Image
        Log.d("Tuyenkk",image.url!!)
        Glide.with(this)
            .load(image.url)
            .error(R.drawable.image2)
            .placeholder(R.drawable.image2)
            .into(binding.selectImage)
        binding.button.setOnClickListener(this)
    }
    @SuppressLint("SimpleDateFormat")
    fun downLoadImage(url:String) {
        val task = ImageDownloader()
        val myImage: Bitmap
        try {
            myImage =
                task.execute(url)
                    .get()!!
            val path = Environment.getExternalStorageDirectory().path+
                    "/Download"
            val dir = File(path)
            if (!dir.exists()) dir.mkdirs()
            val timeStamp: String =
                SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().time)
            val file = File(dir,"134.jpg")
            Log.d("Tuyenkkk",timeStamp)
            val fOut = FileOutputStream(file)

            myImage.compress(Bitmap.CompressFormat.PNG, 85, fOut)

            fOut.flush()
            fOut.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    class ImageDownloader : AsyncTask<String?, Void?, Bitmap?>() {


        override fun doInBackground(vararg params: String?): Bitmap? {
            return try {
                val url = URL(params[0])
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.connect()
                val input: InputStream = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                null
            }
        }
    }
    override fun onClick(v: View) {
        val image=intent.getSerializableExtra("data") as Image
        when (v.id){
            R.id.button->{
                Glide.with(this)
                    .asBitmap()
                    .load(image.url)
                    .into(object : SimpleTarget<Bitmap>(1920, 1080) {
                        override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                            downLoadImage(image.url!!)
                            Toast.makeText(this@MainActivity2,"Luu thanh cong",Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }
    }
}