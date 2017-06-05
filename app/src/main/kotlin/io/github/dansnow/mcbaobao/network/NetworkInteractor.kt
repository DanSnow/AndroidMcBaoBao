package io.github.dansnow.mcbaobao.network

import io.reactivex.Completable

interface NetworkInteractor {
    fun hasNetworkConnection(): Boolean
    fun hasNetworkConnectionCompletable(): Completable

    class NetworkUnavailableException : Throwable("No network available!")
}
