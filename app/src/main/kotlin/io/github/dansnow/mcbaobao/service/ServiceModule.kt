package io.github.dansnow.mcbaobao.service

import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
  @Provides
  fun provideLoginSerice(service: LoginService) = service
}
