package io.github.dansnow.mcbaobao.data.pref

import android.content.Context
import io.github.dansnow.mcbaobao.AppConstants

class Account(ctx: Context): BasePref(ctx) {
  fun setAccount(account: String, password: String) {
    val pref = getPref()
    val editor = pref.edit()
    editor.putString(AppConstants.ACCOUNT_PREF, account)
    editor.putString(AppConstants.PASSWORD_PREF, password)
    editor.commit()
  }

  fun getAccount(): Pair<String, String> {
    val pref = getPref()
    val account = pref.getString(AppConstants.ACCOUNT_PREF, "")
    val password = pref.getString(AppConstants.PASSWORD_PREF, "")
    return Pair(account, password)
  }

  fun hasAccount(): Boolean =
    AppConstants.ACCOUNT_PREF in getPref()
}
