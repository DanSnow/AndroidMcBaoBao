package io.github.dansnow.mcbaobao.ui.main

import kotlin.reflect.KClass
import android.os.Bundle
import android.support.annotation.CallSuper
import com.f2prateek.rx.preferences2.RxSharedPreferences
import org.jetbrains.anko.*
import io.github.dansnow.mcbaobao.ui.base.BaseActivity
import io.github.dansnow.mcbaobao.ui.base.ActivityComponent
import io.github.dansnow.mcbaobao.ui.login.LoginActivity
import io.github.dansnow.mcbaobao.AppConstants
import io.github.dansnow.mcbaobao.ApplicationComponent
import io.github.dansnow.mcbaobao.service.DrawService
import io.github.dansnow.mcbaobao.R
import javax.inject.Inject

class MainActivity : BaseActivity() {
  @Inject
  lateinit var rxPrefs: RxSharedPreferences

  @Inject
  lateinit var drawService: DrawService

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?){
      super.onCreate(savedInstanceState)
      MainUI(rxPrefs, drawService).setContentView(this)
  }


  override fun injectDepencies(graph: ApplicationComponent) {
    DaggerMainComponent
      .builder()
      .applicationComponent(graph)
      .build()
      .injectTo(this)
  }
}

class MainUI(
  val rxPrefs: RxSharedPreferences,
  val drawService: DrawService
) : AnkoComponent<MainActivity> {
  val tokenPref = rxPrefs.getString(AppConstants.TOKEN_PREF)

  override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
    verticalLayout {
      val statusText = textView(if (tokenPref.isSet()) {
        R.string.logined
      } else {
        R.string.not_login
      })

      val loginBtn = button(R.string.start_login) {
        setOnClickListener {
          ctx.startActivity(intentFor<LoginActivity>())
        }
      }

      val drawBtn = button(R.string.draw) {
        setOnClickListener {
          drawService
            .draw()
            .doOnSubscribe {
              statusText.textResource = R.string.verifying
            }
            .subscribe(
              { result ->
                statusText.text = result.result
              },
              {
                _ ->
                statusText.textResource = R.string.draw_fail
              }
            )
        }
      }

      tokenPref.asObservable().subscribe { _ ->
        val isLogin = tokenPref.isSet()
        statusText.textResource = if (isLogin) {
          R.string.logined
        } else {
          R.string.not_login
        }
        loginBtn
          .setClickable(!isLogin)
        drawBtn
          .setClickable(isLogin)
      }
    }
  }
}
