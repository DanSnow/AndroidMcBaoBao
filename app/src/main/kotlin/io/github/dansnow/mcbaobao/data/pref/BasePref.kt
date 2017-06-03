package io.github.dansnow.mcbaobao.data.pref

import android.content.Context
import io.github.dansnow.mcbaobao.AppConstants

open class BasePref(val ctx: Context) {
  fun getPref() = ctx.getSharedPreferences(AppConstants.PREF_KEY, Context.MODE_PRIVATE)
}
