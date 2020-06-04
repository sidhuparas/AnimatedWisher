package com.parassidhu.animatedwisher

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.RuntimeException

class WishActivity : AppCompatActivity() {

    private lateinit var data: WishData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wish)
        init()
    }

    private fun init() {
        data = intent.getParcelableExtra(DATA_OBJ) ?: throw RuntimeException("Required data isn't passed!")
    }

    companion object {
        private const val DATA_OBJ = "data_obj"

        fun newInstance(context: Context, data: WishData) =
            Intent(context, WishActivity::class.java).apply {
                putExtra(DATA_OBJ, data)
            }
    }
}