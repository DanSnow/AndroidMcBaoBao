package io.github.dansnow.mcbaobao.remote

import io.github.dansnow.mcbaobao.remote.model.LoginRequest
import io.github.dansnow.mcbaobao.remote.model.LoginResponse
import io.github.dansnow.mcbaobao.remote.model.DrawRequest
import io.github.dansnow.mcbaobao.remote.model.DrawResponse
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Body

interface McApiService {
    @POST("/login_by_mobile")
    fun login(
      @Body body: LoginRequest
    ): Single<LoginResponse>

    @POST("/lottery/get_item")
    fun draw(@Body body: DrawRequest): Single<DrawResponse>
}
