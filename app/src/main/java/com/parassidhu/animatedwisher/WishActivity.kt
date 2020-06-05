package com.parassidhu.animatedwisher

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_wish.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import java.lang.RuntimeException

class WishActivity : AppCompatActivity() {

    private lateinit var data: WishData
    private var job: Job? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wish)
        init()
    }

    private fun init() {
        window.statusBarColor = Color.BLACK
        data = intent.getParcelableExtra(DATA_OBJ)
            ?: throw RuntimeException("Required data isn't passed!")
        assignValues()
        animate()
        animateBackground()
        initConfetti()
    }

    private fun animateBackground() {
        ObjectAnimator.ofArgb(
            rootLayout,
            "backgroundColor",
            color(R.color.red),
            color(R.color.royal_blue)
        ).apply {
            duration = 6000
            start()
        }
    }

    private fun assignValues() {
        data.apply {
            textView1.text = firstGreeting
            textView2.text = lastGreeting
            textView3.text = firstName
            textView4.text = lastName
        }
    }

    private fun initConfetti() {
        val colors = intArrayOf(R.color.orange, R.color.blue, R.color.green, R.color.pink)
        val selectedColors = colors.map { color(it) }.toIntArray()

        viewKonfetti.post {
            viewKonfetti.build()
                .addColors(*selectedColors)
                .setDirection(0.0, 359.0)
                .setSpeed(4f, 4f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(14))
                .setPosition(0f, viewKonfetti.width + 50f, 0f, -50f)
                .streamFor(200, 6000L)

            mediaPlayer = MediaPlayer.create(this, R.raw.hbd)
            mediaPlayer?.start()
        }
    }

    private fun animate() {
        job?.cancel()

        job = lifecycleScope.launchWhenCreated {
            textView1.customAnimate()
            delay(1500)
            textView2.customAnimate(true)
            delay(1500)
            textView3.customAnimate()
            delay(1500)
            textView4.customAnimate(true)
            delay(2500)
            textView1.endState()
            textView2.endState(true)
            textView3.endState()
            textView4.endState(true)
            delay(1200)
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.let { mediaPlayer ->
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    companion object {
        private const val DATA_OBJ = "data_obj"

        fun newInstance(context: Context, data: WishData) =
            Intent(context, WishActivity::class.java).apply {
                putExtra(DATA_OBJ, data)
            }
    }
}