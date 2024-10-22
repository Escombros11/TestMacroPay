package com.example.testnach

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class Inicio : AppCompatActivity() {
    var imageUrl: String? = ""
    var name: String? = ""
    var circularImageView: ImageView? = null
    var initialsTextView: TextView? = null
    var placeholderResId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        circularImageView = findViewById(R.id.circularImageView)
        initialsTextView = findViewById(R.id.initialsTextView)

        imageUrl=  "https://smartit.divisionautomotriz.com.mx/images/imagotipo.png"
        //imageUrl=  ""
        name = "Fabian Jimenez"
        placeholderResId = R.drawable.placepokebola
        loadCircularImage()
    }

    fun loadCircularImage(backgroundColor: Int = R.color.rojopokebola, textColor: Int = android.R.color.white)
    {
        val initials = name?.split(" ")
            ?.filter { it.isNotEmpty() && it[0].isLetter() }
            ?.take(2)?.joinToString("") { it[0].uppercase() }

        // Configurar colores y background
        //initialsTextView!!.setBackgroundColor(this.resources.getColor(backgroundColor, null))
        initialsTextView!!.setTextColor(this.resources.getColor(textColor, null))

        if (!imageUrl!!.isNullOrEmpty()) {
            // Cargar la imagen con Glide
            Glide.with(this)
                .load(imageUrl!!)
                .apply(RequestOptions.circleCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderResId!!)
                .error(placeholderResId)
                .into(circularImageView!!)

            // Ocultar las iniciales
            initialsTextView!!.visibility = View.GONE
        }
        else
        {

            if (!initials.isNullOrBlank()) {
                // Mostrar iniciales
                initialsTextView!!.text = initials
                initialsTextView!!.visibility = View.VISIBLE
                circularImageView!!.visibility = View.GONE
            } else {
                // Mostrar placeholder si no hay iniciales
                circularImageView!!.setImageResource(placeholderResId!!)
                circularImageView!!.visibility = View.VISIBLE
                initialsTextView!!.visibility = View.VISIBLE
                initialsTextView!!.visibility = View.GONE
            }
        }



    }
}