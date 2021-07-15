package me.darthwithap.invapp.utils.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
fun Date.toIsoFormat(): String? {
    return isoDateFormat.format(this)
}