package com.dfcruz.talkie.data.network

import com.dfcruz.talkie.data.network.rest.KtorService
import com.dfcruz.talkie.data.network.rest.TalkieService
import com.dfcruz.talkie.data.network.rest.createHttpClient
import com.dfcruz.talkie.data.network.serializer.InstantEpochSecondsSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.dsl.module
import kotlin.time.Instant

val networkModule = module {
    single {
        SerializersModule {
            contextual(Instant::class, InstantEpochSecondsSerializer)
        }
    }

    single {
        Json {
            serializersModule = get()
            ignoreUnknownKeys = true
        }
    }

    single { createHttpClient(json = get()) }

    single<TalkieService> {
        KtorService(client = get())
    }
}