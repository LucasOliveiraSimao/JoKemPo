package com.lucasoliveirasimao.jokempo

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import android.view.View
import android.widget.TextView
import com.lucasoliveirasimao.jokempo.databinding.ActivityMainBinding

const val DURATION_DISAPPER: Long = 1500
const val DURATION_APPEAR: Long = 250

const val VISIBLE: Int = View.VISIBLE
const val INVISIBLE: Int = View.INVISIBLE

const val ID_STONE: Int = 1
const val ID_PAPER: Int = 2
const val ID_SCISSORS: Int = 3

val RANDOM_NUMBERS: IntRange = 0..2

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
                userMove = ID_STONE
            }

            R.id.iv_paper -> {
                binding.ivPlayer1.setImageResource(R.drawable.paper)
                userMove = ID_PAPER
            }

            R.id.iv_scissors -> {
                binding.ivPlayer1.setImageResource(R.drawable.scissors)
                userMove = ID_SCISSORS
            }
        }

        binding.ivPlayer2.startAnimation(disapper)
        binding.ivPlayer2.setImageResource(R.drawable.query)

    }

    private fun drawEnemyGame() {

        when ((RANDOM_NUMBERS).random()) {
            0 -> {
                binding.ivPlayer2.setImageResource(R.drawable.stone)
                opponentMove = ID_STONE
            }
            1 -> {
                binding.ivPlayer2.setImageResource(R.drawable.paper)
                opponentMove = ID_PAPER
            }
            2 -> {
                binding.ivPlayer2.setImageResource(R.drawable.scissors)
                opponentMove = ID_SCISSORS
            }
        }
    }

    private fun message(message: Int) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)

        val toastView = toast.view
        toastView?.setBackgroundResource(R.drawable.bg_toast)

        val toastMessage = toast.view?.findViewById<TextView>(android.R.id.message)
        toastMessage?.setTextColor(Color.parseColor("#FFFFFF"))
        toast.show()

    }

    private fun checkPlay() {
        if ((userMove == ID_STONE && opponentMove == ID_SCISSORS) ||
            (userMove == ID_PAPER && opponentMove == ID_STONE) ||
            (userMove == ID_SCISSORS && opponentMove == ID_PAPER)
        ) {

            message(R.string.win)

        } else if ((opponentMove == ID_STONE && userMove == ID_SCISSORS) ||
            (opponentMove == ID_PAPER && userMove == ID_STONE) ||
            (opponentMove == ID_SCISSORS && userMove == ID_PAPER)
        ) {

            message(R.string.lose)

        } else {
            message(R.string.draw)
        }

    }

}
