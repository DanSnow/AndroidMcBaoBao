package io.github.dansnow.mcbaobao.data.remote

import io.github.dansnow.mcbaobao.data.remote.model.LoginRequest
import io.github.dansnow.mcbaobao.data.remote.model.LoginResponse
import io.github.dansnow.mcbaobao.data.remote.model.DrawRequest
import io.github.dansnow.mcbaobao.data.remote.model.DrawResponse
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
