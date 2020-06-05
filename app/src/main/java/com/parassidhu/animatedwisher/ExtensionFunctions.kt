package com.parassidhu.animatedwisher

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun EditText.isBlank() = this.text.toString().isBlank()

fun EditText.message() = this.text.toString()

fun View.customAnimate(right: Boolean = false) {
    visibility = View.VISIBLE
    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)

    val rotateX = PropertyValuesHolder.ofFloat(View.ROTATION_X, 20f)
    val rotateY = PropertyValuesHolder.ofFloat(View.ROTATION_Y, if (right) -20f else 20f)

    val animator = ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY, rotateX, rotateY)

    animator.apply {
        duration = 1200
        repeatCount = 1
        repeatMode = ObjectAnimator.REVERSE
        start()
    }
}

fun View.endState(right: Boolean = false) {
    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 3.5f)
    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 3.5f)

    val rotateX = PropertyValuesHolder.ofFloat(View.ROTATION_X, 20f)
    val rotateY = PropertyValuesHolder.ofFloat(View.ROTATION_Y, if (right) -20f else 20f)

    val animator = ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY, rotateX, rotateY)

    animator.apply {
        duration = 1000
        start()
    }
}