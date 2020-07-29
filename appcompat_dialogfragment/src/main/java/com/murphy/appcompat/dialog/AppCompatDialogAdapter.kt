package com.murphy.appcompat.dialog

import android.content.Context
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatDialog

class AppCompatDialogAdapter(context: Context?, theme: Int, private val cancelable : Boolean) : AppCompatDialog(context, theme) {

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isOutOfBounds(event) && cancelable) {
            dismiss()
        }
        return super.onTouchEvent(event)
    }

    private fun isOutOfBounds(event: MotionEvent) : Boolean {
        val x = event.x
        val y = event.y
        //val slop =ViewConfiguration.get(context).scaledTouchSlop
        val touchSlop = 0
        val decorView = window!!.decorView
        val width = decorView.width
        val height = decorView.height
        return x < -touchSlop || y < -touchSlop || x > (width.plus(touchSlop)) || y > (height.plus(touchSlop))
    }
}
