package com.dfcruz.talkie.data.database

import androidx.room.Room
import com.dfcruz.talkie.data.database.dao.ConversationDao
import com.dfcruz.talkie.data.database.dao.MessageDao
import com.dfcruz.talkie.data.database.dao.UserDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<TalkieDatabase> {
        Room.databaseBuilder(
            androidContext(),
            TalkieDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    single<ConversationDao> {
        get<TalkieDatabase>().conversationDao()
    }
    single<UserDao> {
        get<TalkieDatabase>().userDao()
    }
    single<MessageDao> {
        get<TalkieDatabase>().messageDao()
    }
}