package com.example.appcompatdialogfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appcompatdialogfragment.sample.NormalBottomSheetFragment
import com.example.appcompatdialogfragment.sample.NormalDialogFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun normal(view: View) {
        val normalDialogFragment = NormalDialogFragment(0.05f)
        normalDialogFragment.isCancelable = false
        normalDialogFragment.show(supportFragmentManager, "normal")
    }

    fun bottomSheet(view: View) {
        val normalBottomSheetFragment = NormalBottomSheetFragment()
        normalBottomSheetFragment.show(supportFragmentManager, "bottom")
    }
}