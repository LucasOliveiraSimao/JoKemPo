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

const val VISIBLE: Int = View.VISIBLE
const val INVISIBLE: Int = View.INVISIBLE

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var disapper: Animation = AlphaAnimation(1.0F, 0.0F)
    private var appear: Animation = AlphaAnimation(0.0F, 1.0F)

    private lateinit var media: MediaPlayer

    private var userMove: Int = 0
    private var opponentMove: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disapper.duration = DURATION_DISAPPER
        appear.duration = DURATION_APPEAR

        binding.ivStone.setOnClickListener { click(binding.ivStone) }
        binding.ivScissors.setOnClickListener { click(binding.ivScissors) }
        binding.ivPaper.setOnClickListener { click(binding.ivPaper) }

        disapper.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                binding.ivPlayer2.visibility = INVISIBLE
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivPlayer2.visibility = INVISIBLE
                binding.ivPlayer2.startAnimation(appear)
            }

        })

        appear.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(p0: Animation?) {
                drawEnemyGame()
                binding.ivPlayer2.visibility = INVISIBLE
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                checkPlay()
                binding.ivPlayer2.visibility = VISIBLE
            }

        })

    }

    val win: Boolean = (userMove == 1 && opponentMove == 3) || (userMove == 2 && opponentMove == 1) || (userMove == 3 && opponentMove == 2)
    val lose: Boolean = (opponentMove == 1 && userMove == 3) || (opponentMove == 2 && userMove == 1) || (opponentMove == 3 && userMove == 2)
    val draw: Boolean = (userMove == opponentMove)

    private fun checkPlay() {
        if (draw) {
            Toast.makeText(this, R.string.draw, Toast.LENGTH_SHORT).show()
        }
        if (win) {
            Toast.makeText(this, R.string.win, Toast.LENGTH_SHORT).show()
        }
        if (lose) {
            Toast.makeText(this, R.string.lose, Toast.LENGTH_SHORT).show()
        }
    }

    private fun drawEnemyGame() {

        when ((0..2).random()) {
            0 -> {
                binding.ivPlayer2.setImageResource(R.drawable.stone)
                opponentMove = 1
            }
            1 -> {
                binding.ivPlayer2.setImageResource(R.drawable.paper)
                opponentMove = 2
            }
            2 -> {
                binding.ivPlayer2.setImageResource(R.drawable.scissors)
                opponentMove = 3
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
                userMove = 1
            }

            R.id.iv_paper -> {
                binding.ivPlayer1.setImageResource(R.drawable.paper)
                userMove = 2
            }

            R.id.iv_scissors -> {
                binding.ivPlayer1.setImageResource(R.drawable.scissors)
                userMove = 3
            }
        }

        binding.ivPlayer2.startAnimation(disapper)
        binding.ivPlayer2.setImageResource(R.drawable.query)

    }

}
