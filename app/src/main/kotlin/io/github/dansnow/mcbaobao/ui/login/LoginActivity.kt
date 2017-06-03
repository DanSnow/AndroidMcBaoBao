package io.github.dansnow.mcbaobao.ui.login

import kotlin.reflect.KClass
import javax.inject.Inject
import android.os.Bundle
import android.support.annotation.CallSuper
import com.f2prateek.rx.preferences2.RxSharedPreferences
import org.jetbrains.anko.*
import io.github.dansnow.mcbaobao.ui.base.BaseActivity
import io.github.dansnow.mcbaobao.ui.base.ActivityComponent
import io.github.dansnow.mcbaobao.data.pref.Account
import io.github.dansnow.mcbaobao.ApplicationComponent
import io.github.dansnow.mcbaobao.service.LoginService
import io.github.dansnow.mcbaobao.AppConstants
import io.github.dansnow.mcbaobao.R

class LoginActivity : BaseActivity() {
  @Inject
  lateinit var rxPrefs: RxSharedPreferences

  @Inject
  lateinit var loginService: LoginService

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    LoginUI(rxPrefs, loginService).setContentView(this)
  }

  override fun injectDepencies(graph: ApplicationComponent) {
    DaggerLoginComponent
      .builder()
      .applicationComponent(graph)
      .build()
      .injectTo(this)
  }
}

class LoginUI(val rxPrefs: RxSharedPreferences, val loginService: LoginService) : AnkoComponent<LoginActivity> {
  val accountPref = rxPrefs.getString(AppConstants.ACCOUNT_PREF)
  val passwordPref = rxPrefs.getString(AppConstants.PASSWORD_PREF)
  val tokenPref = rxPrefs.getString(AppConstants.TOKEN_PREF)

  override fun createView(ui: AnkoContext<LoginActivity>) = with(ui) {
    verticalLayout {
      val statusText = textView(R.string.login_hint)
      val account = editText()
      val password = editText()
      button(R.string.login) {
        setOnClickListener {
          loginService
            .login(account.text.toString(), password.text.toString())
            .doOnSubscribe {
              statusText.textResource = R.string.verifying
            }
            .subscribe(
              { token ->
                tokenPref.set(token)
              },
              {
                err ->
                statusText.textResource = R.string.login_fail
              }
            )
        }
      }
    }
  }
}
