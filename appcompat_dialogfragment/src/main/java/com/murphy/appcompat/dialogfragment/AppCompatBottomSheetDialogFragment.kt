package com.murphy.appcompat.dialogfragment

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class AppCompatBottomSheetDialogFragment<DB : ViewDataBinding> : BottomSheetDialogFragment() {

    private var dpiRatio : Float? = null

    private var dataBinding : DB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //去除白色背景圆角
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
        val displayMetrics = requireActivity().resources.displayMetrics
        dpiRatio = displayMetrics.heightPixels.toFloat() / displayMetrics.widthPixels.toFloat()
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
        if (dpiRatio!! > 18/9) {
            return
        }

        val db = dataBinding!!
        val parent = db.root.parent as View
        val behavior = BottomSheetBehavior.from(parent)
        var peekHeight = behavior.peekHeight

        db.root.measure(0, 0)
        var measuredHeight = db.root.measuredHeight

        val windowManager = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels
        var peekHeightRatio = getPeekHeightRatio()
        if (peekHeightRatio > 1) peekHeightRatio = 1f

        peekHeight = peekHeight.coerceAtLeast(measuredHeight)//peekHeight <= measureHeight
        peekHeight = peekHeight.coerceAtMost(screenHeight)//measuredHeight <= screenHeight

        if (peekHeight < screenHeight) {
            peekHeightRatio = 1f
        }
        behavior.peekHeight = (peekHeightRatio * peekHeight).toInt()
    }

    abstract fun initView(dataBinding: DB)
    abstract fun bindLayout(): Int

    open fun getPeekHeightRatio() : Float {
        return 1f
    }

}