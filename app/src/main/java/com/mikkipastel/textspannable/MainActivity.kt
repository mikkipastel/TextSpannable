package com.mikkipastel.textspannable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textSpanHtml.setOnClickListener {
            startActivity(TextSpannableActivity.newIntent(this, TYPE_SPAN_HTML))
        }

        textSpannable.setOnClickListener {
            startActivity(TextSpannableActivity.newIntent(this, TYPE_SPAN_TEXT))
        }

        textSpanKtx.setOnClickListener {
            startActivity(TextSpannableActivity.newIntent(this, TYPE_SPAN_KTX))
        }
    }
}
