package com.parassidhu.animatedwisher

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class WishTextView(context: Context, attributeSet: AttributeSet?) :
    AppCompatTextView(context, attributeSet) {

    init {
        setPadding(2, 2, 2, 2)
        setShadowLayer(1f, 1f, 1f, Color.WHITE)
    }
}