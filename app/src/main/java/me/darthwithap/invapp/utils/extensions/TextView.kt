package me.darthwithap.invapp.utils.extensions

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.util.*

//@SuppressLint("ConstantLocale")
//val appDateFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//    SimpleDateFormat("ddd MMM dd HH:mm:ss GMT+hh:mm yyyy", Locale.getDefault())
//} else {
//    TODO("VERSION.SDK_INT < N")
//}
//
//@SuppressLint("ConstantLocale")
//val dateFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//    SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
//} else {
//    TODO("VERSION.SDK_INT < N")
//}
//
//var TextView.timestamp: String
//    set(value) {
//        val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            appDateFormat.parse(value)
//        } else {
//            TODO("VERSION.SDK_INT < N")
//        }
//        text = dateFormat.format(date)
//    }
//    get() {
//        val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            dateFormat.parse(text.toString())
//        } else {
//            TODO("VERSION.SDK_INT < N")
//        }
//        return appDateFormat.format(date)
//    }

@RequiresApi(Build.VERSION_CODES.N)
val dateFormat = SimpleDateFormat("hh:mm:ss")