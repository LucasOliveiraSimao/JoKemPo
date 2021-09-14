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

        binding.ivStone.setOnClickListener { click(binding.ivStone) }
        binding.ivScissors.setOnClickListener { click(binding.ivScissors) }
        binding.ivPaper.setOnClickListener { click(binding.ivPaper) }

        disapper.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                binding.ivPlayer2.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivPlayer2.visibility = View.INVISIBLE
                binding.ivPlayer2.startAnimation(appear)
            }

        })

        appear.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(p0: Animation?) {
                binding.ivPlayer2.visibility = View.INVISIBLE
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

    private fun click(view: View) {
        playMusic()

        binding.ivPlayer1.scaleX = -1.0F

        when(view.id){
            R.id.iv_stone -> binding.ivPlayer1.setImageResource(R.drawable.stone)
            R.id.iv_paper -> binding.ivPlayer1.setImageResource(R.drawable.paper)
            R.id.iv_scissors -> binding.ivPlayer1.setImageResource(R.drawable.scissors)
        }

        binding.ivPlayer2.startAnimation(disapper)
        binding.ivPlayer2.setImageResource(R.drawable.query)

    }

}