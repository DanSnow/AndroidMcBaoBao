package io.github.dansnow.mcbaobao.data.network

import io.reactivex.Completable

interface NetworkInteractor {
    fun hasNetworkConnection(): Boolean
    fun hasNetworkConnectionCompletable(): Completable

    class NetworkUnavailableException : Throwable("No network available!")
}
