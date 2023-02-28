package com.example.tipcalculator.utils

import android.content.Context
import android.widget.Toast

object Utils {

    fun Any.toast(mContext: Context,message: String){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }
}