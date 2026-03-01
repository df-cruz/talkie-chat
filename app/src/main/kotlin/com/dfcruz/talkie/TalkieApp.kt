package com.dfcruz.talkie

import android.app.Application
import com.dfcruz.talkie.data.database.databaseModule
import com.dfcruz.talkie.data.network.networkModule
import com.dfcruz.talkie.data.repository.repositoryModule
import com.dfcruz.talkie.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TalkieApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TalkieApp)
            modules(appModule)
            modules(databaseModule)
            modules(networkModule)
            modules(repositoryModule)
            modules(domainModule)
        }
    }
}