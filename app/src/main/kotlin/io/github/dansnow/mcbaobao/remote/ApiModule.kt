package io.github.dansnow.mcbaobao.remote

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides @Singleton
    fun provideMcApiService(retrofit: Retrofit): McApiService {
        return retrofit.create(McApiService::class.java)
    }

    @Provides @Singleton
    fun provideRetrofit(
            rxJavaCallAdapterFactory: RxJava2CallAdapterFactory,
            gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api1.mcddailyapp.com")
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides @Singleton
    fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

}
