package com.w4eret1ckrtb1tch.homework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@AppScope
@Component(
    modules = [
        DomainModule::class,
        ViewModelBuilder::class,
        AppSubcomponent::class
    ]
)
interface AppComponent {

    // QUESTION: 10.11.2021 Какое решение предпочтительнее Factory или Builder?
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun activityComponent(): ActivityComponent.Builder

    fun fragmentComponent(): FragmentComponent.Builder

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