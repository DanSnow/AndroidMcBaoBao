package io.github.dansnow.mcbaobao.service

import javax.inject.Inject
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Single
import io.github.dansnow.mcbaobao.network.NetworkInteractor
import io.github.dansnow.mcbaobao.remote.McApiService
import io.github.dansnow.mcbaobao.remote.model.DrawRequest
import io.github.dansnow.mcbaobao.AppConstants

class DrawFailError(msg: String) : Throwable(msg)

data class DrawResult(val message: String, val result: String)

class DrawService @Inject constructor(
  private val rxPrefs: RxSharedPreferences,
  private val apiService: McApiService,
  private val networkInteractor: NetworkInteractor
) {
  fun draw(): Single<DrawResult> {
    val tokenPref = rxPrefs.getString(AppConstants.TOKEN_PREF)
    if (!tokenPref.isSet()) {
      return Single<DrawResult>.error(DrawFailError("Token not set"))
    }

    return networkInteractor
      .hasNetworkConnectionCompletable()
      .andThen(
        apiService.draw(
          DrawRequest(tokenPref.get())
        )
      )
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .map { drawResponse ->
        if (drawResponse.rc != 1) {
          throw DrawFailError("Draw fail")
        } else {
          DrawResult(
            message = drawResponse.rm,
            result = drawResponse.results
          )
        }
      }
  }
}
