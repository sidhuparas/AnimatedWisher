package com.parassidhu.animatedwisher

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animateTitle()
    }

    private fun animateTitle() {
        ObjectAnimator.ofArgb(
            appName,
            "textColor",
            Color.BLACK,
            color(R.color.royal_blue)
        ).apply {
            duration = 3000
            start()
        }

        appName.animate()
            .scaleX(1.5f)
            .scaleY(1.5f)
            .setInterpolator(AccelerateInterpolator())
            .setDuration(1500L)
            .withEndAction {
                appName.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(1500L)
                    .setInterpolator(DecelerateInterpolator())
                    .start()
            }
            .start()
    }
}