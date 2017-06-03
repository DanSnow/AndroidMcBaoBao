package io.github.dansnow.mcbaobao.ui.login

import javax.inject.Singleton
import dagger.Component
import io.github.dansnow.mcbaobao.ApplicationComponent
import io.github.dansnow.mcbaobao.ui.base.ActivityComponent
import io.github.dansnow.mcbaobao.ui.ActivityScope

@ActivityScope
@Component(
  modules = arrayOf(LoginModule::class),
  dependencies = arrayOf(ApplicationComponent::class)
)
interface LoginComponent: ActivityComponent {
  fun injectTo(activity: LoginActivity)
}
