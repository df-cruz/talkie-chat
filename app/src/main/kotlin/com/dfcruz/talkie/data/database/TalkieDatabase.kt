package com.dfcruz.talkie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dfcruz.talkie.data.database.converter.DateConverter
import com.dfcruz.talkie.data.database.dao.ConversationDao
import com.dfcruz.talkie.data.database.entity.ConversationEntity

const val DATABASE_NAME = "talkie-db"

@Database(
    entities = [
        ConversationEntity::class,
    ],
    version = 1,
)
@TypeConverters(DateConverter::class)
abstract class TalkieDatabase : RoomDatabase() {
    abstract fun conversationDao(): ConversationDao
}