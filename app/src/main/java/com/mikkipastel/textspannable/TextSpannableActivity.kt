package com.mikkipastel.textspannable

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.scale
import androidx.core.text.toSpannable
import kotlinx.android.synthetic.main.activity_text_spannable.*

const val TYPE_SPAN_HTML = 0
const val TYPE_SPAN_TEXT = 1
const val TYPE_SPAN_KTX = 2

class TextSpannableActivity : AppCompatActivity() {

    private val type by lazy {
        intent.extras.getInt(EXTRA_TYPE)
    }

    private val titleText = "Concepts covered:"
    private val sampleText = " Android Development, Databases, Android Networking Libraries, " +
            "Crash Analysis, Multilingual Support, Android Libraries, Gradle, APIs"
    private val text = "Concepts covered:$sampleText"

    companion object {
        private const val EXTRA_TYPE = "TextSpannableActivity.EXTRA_TYPE"

        fun newIntent(context: Context, type: Int) : Intent {
            return Intent(context, TextSpannableActivity::class.java).apply {
                putExtra(EXTRA_TYPE, type)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_spannable)

        when (type) {
            TYPE_SPAN_HTML -> setSpannableByHtml()
            TYPE_SPAN_TEXT -> setSpannableText()
            else -> setSpannableByKtx()
        }

    }

    private fun setSpannableByHtml() {
        val text = "<font color='#657482'><b>Concepts covered</b>:</font> <font color='#000000'>$sampleText</font>"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textViewSample.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            textViewSample.text = Html.fromHtml(text)
        }
    }

    private fun setSpannableText() {
        val spannable = SpannableString(text).apply {
            setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                titleText.length - 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(Color.parseColor("#657482")),
                0,
                titleText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                RelativeSizeSpan(1.5f),
                0,
                titleText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(Color.parseColor("#000000")),
                titleText.length,
                titleText.length + sampleText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textViewSample.text = spannable
    }

    private fun setSpannableByKtx() {
        val spannable = SpannableStringBuilder()
        spannable.color(Color.parseColor("#657482")) {
            scale(1.5f) {
                bold {
                    append(titleText)
                }
            }
        }
        spannable.color(Color.parseColor("#000000")) {
            append(sampleText)
        }
        textViewSample.text = spannable
    }

}