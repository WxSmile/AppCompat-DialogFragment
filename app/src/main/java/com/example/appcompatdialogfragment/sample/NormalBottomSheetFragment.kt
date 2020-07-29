package com.example.appcompatdialogfragment.sample

import com.example.appcompatdialogfragment.R
import com.example.appcompatdialogfragment.databinding.DialogFragmentBottomBinding
import com.murphy.appcompat.dialogfragment.AppCompatBottomSheetDialogFragment

class NormalBottomSheetFragment :
    AppCompatBottomSheetDialogFragment<DialogFragmentBottomBinding>() {

    override fun initView(dataBinding: DialogFragmentBottomBinding) {
        dataBinding.button.setOnClickListener {
            dismiss()
        }
    }

    override fun bindLayout(): Int {
        return R.layout.dialog_fragment_bottom
    }

}