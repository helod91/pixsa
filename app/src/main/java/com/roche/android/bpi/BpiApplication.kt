package com.roche.android.bpi

import android.app.Application
import com.roche.android.bpi.data.di.apiModules
import com.roche.android.bpi.data.di.dataSourceModule
import com.roche.android.bpi.data.di.mapperModules
import com.roche.android.bpi.data.di.networkModules
import com.roche.android.bpi.data.di.repositoryModules
import com.roche.android.bpi.domain.di.useCaseModule
import com.roche.android.bpi.presentation.common.commonModule
import com.roche.android.bpi.presentation.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BpiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@BpiApplication)
            modules(
                listOf(
                    apiModules,
                    dataSourceModule,
                    mapperModules,
                    networkModules,
                    repositoryModules,
                    useCaseModule,
                    commonModule,
                    viewModelModules
                )
            )
        }
    }
}