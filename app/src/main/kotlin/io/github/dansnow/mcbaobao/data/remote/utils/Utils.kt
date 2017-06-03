package io.github.dansnow.mcbaobao.data.remote.utils

import java.security.MessageDigest
private val CHARS = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

fun md5(s: String): String {
  val digester = MessageDigest.getInstance("MD5")
  return digester.digest(s.toByteArray()).toHexString()
}

internal fun Byte.toHexString() : String {
    val i = this.toInt()
    val char2 = CHARS[i and 0x0f]
    val char1 = CHARS[i shr 4 and 0x0f]
    return "$char1$char2"
}

internal fun ByteArray.toHexString() : String {
  val builder = StringBuilder()
  for (b in this) {
      builder.append(b.toHexString())
  }
  return builder.toString()
}
