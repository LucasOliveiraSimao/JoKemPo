package com.lucasoliveirasimao.jokempo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import android.view.View
import com.lucasoliveirasimao.jokempo.databinding.ActivityMainBinding

const val DURATION_DISAPPER: Long = 1500
const val DURATION_APPEAR: Long = 250

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var disapper: Animation = AlphaAnimation(1.0F, 0.0F)
    private var appear: Animation = AlphaAnimation(0.0F, 1.0F)

    private lateinit var media: MediaPlayer

    private var played1: Int = 0
    private var played2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disapper.duration = DURATION_DISAPPER
        appear.duration = DURATION_APPEAR

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
                drawEnemyGame()
                binding.ivPlayer2.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                checkPlay()
                binding.ivPlayer2.visibility = View.VISIBLE
            }

        })

    }

    private fun checkPlay() {
        if (played1 == played2) {
            Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show()
        }
        if ((played1 == 1 && played2 == 3) || (played1 == 2 && played2 == 1) || (played1 == 3 && played2 == 2)) {
            Toast.makeText(this, "Ganhei!", Toast.LENGTH_SHORT).show()
        }
        if ((played2 == 1 && played1 == 3) || (played2 == 2 && played1 == 1) || (played2 == 3 && played1 == 2)) {
            Toast.makeText(this, "Perdi!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun drawEnemyGame() {

        when ((0..2).random()) {
            0 -> {
                binding.ivPlayer2.setImageResource(R.drawable.stone)
                played2 = 1
            }
            1 -> {
                binding.ivPlayer2.setImageResource(R.drawable.paper)
                played2 = 2
            }
            2 -> {
                binding.ivPlayer2.setImageResource(R.drawable.scissors)
                played2 = 3
            }
        }
    }

    private fun playMusic() {
        media = MediaPlayer.create(baseContext, R.raw.alex_play)
        media.start()
    }

    private fun click(view: View) {
        playMusic()

        binding.ivPlayer1.scaleX = -1.0F

        when (view.id) {
            R.id.iv_stone -> {
                binding.ivPlayer1.setImageResource(R.drawable.stone)
                played1 = 1
            }

            R.id.iv_paper -> {
                binding.ivPlayer1.setImageResource(R.drawable.paper)
                played1 = 2
            }

            R.id.iv_scissors -> {
                binding.ivPlayer1.setImageResource(R.drawable.scissors)
                played1 = 3
            }
        }

        binding.ivPlayer2.startAnimation(disapper)
        binding.ivPlayer2.setImageResource(R.drawable.query)

    }

}
