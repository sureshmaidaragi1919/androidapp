package com.example.androidcleanarchapp.util

import android.view.View
import android.widget.TextView

fun TextView.makeGone() {
    if (this.visibility == View.VISIBLE) {
        this.visibility = View.GONE
    }
}

fun TextView.makeVisible() {
    if (this.visibility == View.GONE || this.visibility == View.INVISIBLE) {
        this.visibility = View.VISIBLE
    }
}