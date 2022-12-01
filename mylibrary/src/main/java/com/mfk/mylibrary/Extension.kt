package com.mfk.mylibrary

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

/**
 * Created by M.Furkan KÜÇÜK on 1.12.2022
 **/


@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.remove() {
    this.visibility = View.GONE
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun TextView.leftDrawable(@DrawableRes id: Int = 0, @ColorRes colorId: Int = 0) {
    this.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0)
    if (colorId != 0) this.compoundDrawables[0]?.setTint(ContextCompat.getColor(context, colorId))
}

fun TextView.leftDrawableWithAttr(@DrawableRes id: Int = 0, @AttrRes attrId: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0)
    if (attrId != 0) this.compoundDrawables[0]?.setTint(context.getColorFromAttr(attrId))
}