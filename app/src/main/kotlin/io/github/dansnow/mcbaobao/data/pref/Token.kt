package io.github.dansnow.mcbaobao.data.pref

import android.content.Context
import io.github.dansnow.mcbaobao.AppConstants

class Token(ctx: Context): BasePref(ctx) {
  fun setToken(token: String) {
    val pref = getPref()
    val editor = pref.edit()
    editor.putString(AppConstants.TOKEN_PREF, token)
    editor.commit()
  }

  fun getToken(): String =
    getPref().getString(AppConstants.TOKEN_PREF, "")

  fun hasToken(): Boolean =
    AppConstants.TOKEN_PREF in getPref()
}
