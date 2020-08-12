package com.example.appcompatdialogfragment.sample

import android.content.res.Resources
import android.util.TypedValue
import com.example.appcompatdialogfragment.R
import com.example.appcompatdialogfragment.databinding.DialogFragmentNormalBinding
import com.murphy.appcompat.dialogfragment.AppCompatDialogFragmentAdapter

class NormalDialogFragment() : AppCompatDialogFragmentAdapter<DialogFragmentNormalBinding>() {

    override fun bindLayout(): Int {
        return R.layout.dialog_fragment_normal
    }

    override fun initView(dataBinding: DialogFragmentNormalBinding) {
        dataBinding.button.setOnClickListener {
            dismiss()
        }
    }

    override fun getMarginHorizontal(): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            50f,
            Resources.getSystem().displayMetrics
        )
    }
}