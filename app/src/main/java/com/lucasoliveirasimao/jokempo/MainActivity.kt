package com.lucasoliveirasimao.jokempo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lucasoliveirasimao.jokempo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivPlayer1.setOnClickListener { click("Player 1") }
        binding.ivPlayer2.setOnClickListener { click("Player 2") }

        binding.ivStone.setOnClickListener { click("Stone") }
        binding.ivScissors.setOnClickListener { click("Scissors") }
        binding.ivPaper.setOnClickListener { click("Paper") }

    }
    //TODO: criar a animação de sumir

    //TODO: criar a animação de aparecer

    //TODO: criar a função de tocar som

    //TODO: criar a função de verifica a jogada

    //TODO: criar a função de sortear a jogada

    private fun click(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}