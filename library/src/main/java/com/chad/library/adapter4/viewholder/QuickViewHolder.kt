package com.chad.library.adapter4.viewholder

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.mozhimen.xmlk.vhk.VHKLifecycle2

/**
 * Quick-use ViewHolder class
 * 快速使用的 ViewHolder 类
 */
open class QuickViewHolder : VHKLifecycle2 {

    constructor(view: View) : super(view)

    constructor(parent: ViewGroup, @LayoutRes intResLayout: Int) : this(LayoutInflater.from(parent.context).inflate(intResLayout, parent, false))

    //////////////////////////////////////////////////////////////////////////

    fun <T : View> Int.findView(): T? {
        return itemView.findViewById(this)
    }

    fun setText(@IdRes viewId: Int, value: CharSequence?) = apply {
        findViewById<TextView>(viewId).text = value
    }

    fun setText(@IdRes viewId: Int, @StringRes strId: Int) = apply {
        findViewById<TextView>(viewId).setText(strId)
    }

    fun setTextColor(@IdRes viewId: Int, @ColorInt color: Int) = apply {
        findViewById<TextView>(viewId).setTextColor(color)
    }

    fun setTextColorRes(@IdRes viewId: Int, @ColorRes colorRes: Int) = apply {
        findViewById<TextView>(viewId).setTextColor(ContextCompat.getColor(itemView.context, colorRes))
    }

    fun setImageResource(@IdRes viewId: Int, @DrawableRes imageResId: Int) = apply {
        findViewById<ImageView>(viewId).setImageResource(imageResId)
    }

    fun setImageDrawable(@IdRes viewId: Int, drawable: Drawable?) = apply {
        findViewById<ImageView>(viewId).setImageDrawable(drawable)
    }

    fun setImageBitmap(@IdRes viewId: Int, bitmap: Bitmap?) = apply {
        findViewById<ImageView>(viewId).setImageBitmap(bitmap)
    }

    fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int) = apply {
        findViewById<View>(viewId).setBackgroundColor(color)
    }

    fun setBackgroundResource(@IdRes viewId: Int, @DrawableRes backgroundRes: Int) = apply {
        findViewById<View>(viewId).setBackgroundResource(backgroundRes)
    }

    fun setVisible(@IdRes viewId: Int, isVisible: Boolean) = apply {
        findViewById<View>(viewId).visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }

    fun setGone(@IdRes viewId: Int, isGone: Boolean) = apply {
        findViewById<View>(viewId).visibility = if (isGone) View.GONE else View.VISIBLE
    }

    fun setEnabled(@IdRes viewId: Int, isEnabled: Boolean) = apply {
        findViewById<View>(viewId).isSelected
        findViewById<View>(viewId).isEnabled = isEnabled
    }

    fun isEnabled(@IdRes viewId: Int) = findViewById<View>(viewId).isEnabled

    fun setSelected(@IdRes viewId: Int, isSelected: Boolean) = apply {
        findViewById<View>(viewId).isSelected = isSelected
    }

    fun isSelected(@IdRes viewId: Int) = findViewById<View>(viewId).isSelected
}

