package io.github.dansnow.mcbaobao.remote.model

import android.os.Build
import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDateTime
import io.github.dansnow.mcbaobao.remote.utils.md5

data class LoginRequest (
    @SerializedName("OrderNo")
    val orderNo: String,
    val mask: String,
    @SerializedName("source_info")
    val sourceInfo: SourceInfo
) {
  class Builder(
    val account: String,
    val password: String,
    val osVersion: String,
    val modelId: String,
    val deviceUuid: String
  ) {
    var appVersion = "1.1.3"
    val platform = "Android"

    fun build(): LoginRequest {
      val now = LocalDateTime()
      val deviceTime = now.toString("YYYY/MM/DD hh:mm:ss")
      val formatTime = now.toString("YYYYMMDDHHmmssSSS")
      val orderNo = "${deviceUuid}${formatTime}"
      val mask = calculateMask(orderNo, deviceTime)
      return LoginRequest(
        orderNo,
        mask,
        SourceInfo(
          appVersion,
          deviceTime,
          deviceUuid,
          modelId,
          osVersion,
          platform
        )
      )
    }

    private fun calculateMask(orderNo: String, deviceTime: String) = md5(
      "Mc${orderNo}${platform}${osVersion}${modelId}${deviceUuid}${deviceTime}" +
      "${appVersion}${account}${password}Donalds"
    )
  }

  companion object {
    fun build(
      account: String,
      password: String,
      osVersion: String,
      modelId: String,
      deviceUuid: String
    ) = Builder(
      account,
      password,
      osVersion,
      modelId,
      deviceUuid
    ).build()
  }
}

data class LoginResponse (
    val rc: Int,
    val rm: String,
    @SerializedName("member_info")
    val memberInfo: MemberInfo
)

class DrawRequest (
  @SerializedName("access_token")
  val token: String
)

data class DrawResponse (
  val rc: Int,
  val rm: String,
  val results: String
)

data class MemberInfo (
    @SerializedName("access_token")
      val token: String
)

data class SourceInfo (
    @SerializedName("app_version")
      val appVersion: String,
    @SerializedName("device_version")
      val deviceTime: String,
    @SerializedName("device_uuid")
      val deviceUuid: String,
    @SerializedName("model_id")
      val modelId: String,
    @SerializedName("os_version")
      val osVersion: String,
    val platform: String
)
