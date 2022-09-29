package com.cubiclab.bullseye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import com.cubiclab.bullseye.databinding.ActivityMainBinding
import java.util.Random

//import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var sliderValue = 0
    private val random = Random()
    private var targetValue = random.nextInt(100)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textView2.text = targetValue.toString()

        binding.button.setOnClickListener {
            Log.i("Button Click Event", "You clicked the hit me button")
            showResult()
        }

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sliderValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    private fun pointsForCurrentRound(): Int {
        val maxScore = 100
        val difference: Int

        if(sliderValue > targetValue) {
            difference = sliderValue - targetValue
        } else if(targetValue > sliderValue) {
            difference = targetValue - sliderValue
        } else {
            difference = 0
        }
        return maxScore - difference
    }

    private fun showResult() {
        val dialogTitle = getString(R.string.result_dialog_title)
        val dialogMessage = getString(R.string.result_dialog_message, sliderValue, pointsForCurrentRound())
//        val dialogMessage = "The slider value is $sliderValue"

        val builder = AlertDialog.Builder(this)

        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.setPositiveButton(R.string.result_dialog_button_text) {
            dialog, _ -> dialog.dismiss()
        }

        builder.create().show()
    }
}


