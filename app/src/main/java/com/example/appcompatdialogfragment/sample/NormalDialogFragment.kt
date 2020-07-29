package com.example.appcompatdialogfragment.sample

import com.example.appcompatdialogfragment.R
import com.example.appcompatdialogfragment.databinding.DialogFragmentNormalBinding
import com.murphy.appcompat.dialogfragment.AppCompatDialogFragmentAdapter

class NormalDialogFragment(marginRatio: Float) :
    AppCompatDialogFragmentAdapter<DialogFragmentNormalBinding>(marginRatio) {

    override fun initView(dataBinding: DialogFragmentNormalBinding) {

        dataBinding.button.setOnClickListener {
            dismiss()
        }
    }

    override fun bindLayout(): Int {
        return R.layout.dialog_fragment_normal
    }


}