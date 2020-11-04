package com.example.kotlinbasic

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import org.jetbrains.anko.longToast



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        checkOsVersion()


        val btn: Button = findViewById(R.id.button)
        val imageView: ImageView = findViewById(R.id.imageView)

        btn.setOnClickListener {
            val image: Bitmap = takeScreenshotOfRootView(imageView)
            imageView.setImageBitmap(image)
        }
    }



    private fun checkOsVersion(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            longToast("You are up to date!")
        }else{
            longToast("Please update your Android")
        }
    }


   override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val textView = findViewById<View>(R.id.textView) as TextView
        val imageView: ImageView = findViewById(R.id.imageView)
        val constraintlayout: ConstraintLayout = findViewById(R.id.main)
        val textViewName = findViewById<View>(R.id.textViewName)as TextView


        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            textView.setText(R.string.landscape)
            textView.setTextColor(Color.GRAY)
            textViewName.setTextColor(Color.DKGRAY)
            imageView.x = -200F
            imageView.y = 800F
            constraintlayout.background = ContextCompat.getDrawable(this, R.drawable.code)

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            textView.setText(R.string.portrait)
            constraintlayout.background = ContextCompat.getDrawable(this, R.drawable.network)
            textView.setTextColor(Color.WHITE)
            textViewName.setTextColor(Color.LTGRAY)
        }
    }

    companion object Screenshot {
        private fun takeScreenshot(view: View): Bitmap {
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache(true)
            val b = Bitmap.createBitmap(view.drawingCache)
            view.isDrawingCacheEnabled = false
            return b
        }
        fun takeScreenshotOfRootView(v: View): Bitmap {
            return takeScreenshot(v.rootView)
        }
    }
}
