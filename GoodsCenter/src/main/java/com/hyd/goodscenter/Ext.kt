package com.hyd.goodscenter

import android.widget.EditText
import org.jetbrains.anko.find
import ren.qinc.numberbutton.NumberButton
import ren.qinc.numberbutton.R

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
/*
    三方控件扩展
 */
fun NumberButton.getEditText(): EditText {
    return find(R.id.text_count)
}