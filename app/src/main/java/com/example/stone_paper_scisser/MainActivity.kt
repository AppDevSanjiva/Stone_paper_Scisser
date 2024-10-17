package com.example.stone_paper_scisser

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var stone: ImageView
    private lateinit var paper: ImageView
    private lateinit var scissor: ImageView
    private lateinit var ans: TextView
    private lateinit var p_score: TextView
    private lateinit var c_score: TextView
    private lateinit var bot_dis: TextView

    private var player = 0
    private var computer = 0
    var player_score =0
    var com_score = 0
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views here after setContentView
        stone = findViewById(R.id.stone)
        paper = findViewById(R.id.paper)
        scissor = findViewById(R.id.scissor)
        ans = findViewById(R.id.ans)
        p_score = findViewById(R.id.p_score)
        c_score = findViewById(R.id.c_score)
        bot_dis = findViewById(R.id.bot_dis) // Add this line

        // Create an array of ImageViews
        val array = arrayOf(stone, paper, scissor)

        // Set click listeners for each image view
        for (i in array.indices) {
            array[i].setOnClickListener {
                player = i
                computer = (0..2).random() // Simplified random choice
                determineWinner()
                bot_dis.text = when (computer) {
                    0 -> "Bot-Stone"
                    1 -> "Bot-Paper"
                    else -> "Bot-Scissors"
                } // Update bot_dis for better feedback
            }
        }

        // Apply window insets for edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun determineWinner() {
        /*
        stone 0 paper 1 scissor 2
        paper beats stone,
        scissor beats paper,
        stone beats scissor
        */


        when {
            (player == 0 && computer == 2) ||
                    (player == 1 && computer == 0) ||
                    (player == 2 && computer == 1) -> {
                ++player_score;
                ans.text = "You Win"
            }
            player == computer -> {
                ans.text = "Draw"
            }
            else -> {
                ans.text = "You Lose"
                ++com_score;
            }
        }
        p_score.text = player_score.toString()
        c_score.text = com_score.toString()
    }
}
