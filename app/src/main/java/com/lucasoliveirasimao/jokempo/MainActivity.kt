package com.lucasoliveirasimao.jokempo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import android.view.View
import com.lucasoliveirasimao.jokempo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var disapper: Animation = AlphaAnimation(1.0F,0.0F)
    private var appear: Animation = AlphaAnimation(0.0F, 1.0F)

    private lateinit var media: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disapper.duration = 1500
        appear.duration = 250

        binding.ivPlayer1.setOnClickListener { binding.ivPlayer1.startAnimation(disapper) }
        binding.ivPlayer2.setOnClickListener { binding.ivPlayer2.startAnimation(appear) }

        binding.ivStone.setOnClickListener { click("Stone") }
        binding.ivScissors.setOnClickListener { click("Scissors") }
        binding.ivPaper.setOnClickListener { click("Paper") }

        disapper.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                binding.ivPlayer1.visibility = View.INVISIBLE
                playMusic()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivPlayer1.visibility = View.VISIBLE
            }

        })

        appear.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(p0: Animation?) {
                binding.ivPlayer2.visibility = View.INVISIBLE
                playMusic()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivPlayer2.visibility = View.VISIBLE
            }

        })

    }
    
    //TODO: criar a função de verifica a jogada

    //TODO: criar a função de sortear a jogada

    private fun playMusic(){
        media = MediaPlayer.create(baseContext, R.raw.alex_play)
        media.start()
    }

    private fun click(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}