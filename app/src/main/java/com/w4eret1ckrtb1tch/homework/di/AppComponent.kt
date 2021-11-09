package com.w4eret1ckrtb1tch.homework.di

import android.content.Context
import com.w4eret1ckrtb1tch.homework.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Scope

@AppScope
@Component(
    modules = [
        DomainModule::class,
        PresentationModule::class,
        BuildersModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope