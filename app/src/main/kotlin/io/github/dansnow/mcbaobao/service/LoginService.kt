package io.github.dansnow.mcbaobao.service

import javax.inject.Inject
import android.os.Build
import android.telephony.TelephonyManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.Single
import io.github.dansnow.mcbaobao.network.NetworkInteractor
import io.github.dansnow.mcbaobao.remote.McApiService
import io.github.dansnow.mcbaobao.remote.model.LoginRequest

class LoginFailError : Throwable("Login fail")

class LoginService @Inject constructor(
  private val rxPrefs: RxSharedPreferences,
  private val apiService: McApiService,
  private val networkInteractor: NetworkInteractor,
  private val telephonyManager: TelephonyManager
) {
  fun login(account: String, password: String): Single<String> {
    return networkInteractor
      .hasNetworkConnectionCompletable()
      .andThen(
        apiService.login(
          LoginRequest.build(
            account = account,
            password = password,
            osVersion = Build.VERSION.RELEASE,
            modelId = Build.MODEL,
            deviceUuid = telephonyManager.getDeviceId()
          )
        )
      )
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .map { loginResponse ->
        if (loginResponse.rc != 1 || loginResponse.rm != "登入成功") {
          throw LoginFailError()
        } else {
          loginResponse.memberInfo.token
        }
      }
  }
}
