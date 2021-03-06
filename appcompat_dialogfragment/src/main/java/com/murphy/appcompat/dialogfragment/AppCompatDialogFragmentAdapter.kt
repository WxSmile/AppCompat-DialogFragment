package com.murphy.appcompat.dialogfragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.murphy.appcompat.dialog.AppCompatDialogAdapter

abstract class AppCompatDialogFragmentAdapter<DB : ViewDataBinding> : AppCompatDialogFragment() {

    private var dataBinding: DB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //去除白色背景圆角
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val window = dialog?.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dataBinding?.let { db: DB ->
            db.root.parent?.let { p: ViewParent ->
                (p as ViewGroup).removeView(db.root)
            }
            return db.root
        }

        dataBinding = DataBindingUtil.inflate(inflater, bindLayout(), container, false)
        initView(dataBinding!!)
        return dataBinding?.root
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.let {
            val layoutParams = it.attributes
            layoutParams.width = getWindowWidth()
            layoutParams.gravity = Gravity.CENTER
            it.attributes = layoutParams
        }
    }

    private fun getWindowWidth(): Int {
        val requireContext = requireContext()
        val windowManager = requireContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val marginHorizontal = getMarginHorizontal()
        return (displayMetrics.widthPixels - marginHorizontal * 2).toInt()
    }

    abstract fun getMarginHorizontal(): Float

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AppCompatDialogAdapter(context, theme, isCancelable)
    }

    abstract fun initView(dataBinding: DB)

    @LayoutRes
    abstract fun bindLayout(): Int

}