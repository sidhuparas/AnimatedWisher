package com.parassidhu.animatedwisher

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.BLACK
        animateTitle()
        setListeners()
    }

    private fun setListeners() {
        submitBtn.setOnClickListener {
            if (checkFields().not()) {
                toast("Please fill all the fields!")
                return@setOnClickListener
            }

            startActivity(WishActivity.newInstance(this, getWishData()))
        }
    }

    private fun checkFields(): Boolean {
        if (firstGreetingMessage.isBlank() || lastGreetingMessage.isBlank() || firstName.isBlank()
            || lastName.isBlank()
        )
            return false

        return true
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

    private fun getWishData() = WishData(
        firstGreetingMessage.message(),
        lastGreetingMessage.message(),
        firstName.message(),
        lastName.message()
    )
}