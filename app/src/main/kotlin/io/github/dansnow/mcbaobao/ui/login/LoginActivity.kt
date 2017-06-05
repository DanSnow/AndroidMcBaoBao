package io.github.dansnow.mcbaobao.ui.login

import kotlin.reflect.KClass
import javax.inject.Inject
import android.os.Bundle
import android.text.InputType
import android.widget.TextView.BufferType
import android.support.annotation.CallSuper
import com.f2prateek.rx.preferences2.RxSharedPreferences
import org.jetbrains.anko.*
import io.github.dansnow.mcbaobao.ui.base.BaseActivity
import io.github.dansnow.mcbaobao.ui.base.ActivityComponent
import io.github.dansnow.mcbaobao.ApplicationComponent
import io.github.dansnow.mcbaobao.service.LoginService
import io.github.dansnow.mcbaobao.AppConstants
import io.github.dansnow.mcbaobao.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
  @Inject
  lateinit var rxPrefs: RxSharedPreferences

  @Inject
  lateinit var loginService: LoginService

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    val tokenPref = rxPrefs.getString(AppConstants.TOKEN_PREF)
    loginBtn.setOnClickListener {
          loginService
            .login(
              accountInput.text.toString(),
              passwordInput.text.toString()
            )
            .doOnSubscribe {
              statusText.setText(R.string.verifying, BufferType.NORMAL)
            }
            .subscribe(
              { token ->
                tokenPref.set(token)
                finish()
              },
              {
                err ->
                statusText.setText(R.string.login_fail, BufferType.NORMAL)
              }
            )
    }
  }

  override fun injectDepencies(graph: ApplicationComponent) {
    DaggerLoginComponent
      .builder()
      .applicationComponent(graph)
      .build()
      .injectTo(this)
  }
}
