package com.gornostai.downloadimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gornostai.downloadimage.databinding.ActivityMainBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDownload.setOnClickListener { download() }
        binding.btnClear.setOnClickListener { clear() }

    }

    fun download(){
        if (binding.edLink.text.isNotEmpty()){
            binding.progress.visibility = View.VISIBLE
            Picasso.get()
                .load(binding.edLink.text.toString())
                .into(binding.ivImage, object: Callback{
                    override fun onSuccess() {
                        binding.progress.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.progress.visibility = View.GONE
                        makeToast()
                    }
                })
        } else {
            makeToast()
        }

    }

    fun clear(){
        binding.edLink.text.clear()
        binding.ivImage.setImageDrawable(null)
    }

    fun makeToast(){
        Toast.makeText(this@MainActivity,R.string.toast_msg,Toast.LENGTH_SHORT).show()
    }
}