package com.dfcruz.talkie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dfcruz.talkie.data.database.converter.DateConverter
import com.dfcruz.talkie.data.database.dao.ConversationDao
import com.dfcruz.talkie.data.database.dao.MessageDao
import com.dfcruz.talkie.data.database.dao.UserDao
import com.dfcruz.talkie.data.database.entity.ConversationEntity
import com.dfcruz.talkie.data.database.entity.ConversationMemberEntity
import com.dfcruz.talkie.data.database.entity.MessageEntity
import com.dfcruz.talkie.data.database.entity.UserEntity

const val DATABASE_NAME = "talkie-db"

@Database(
    entities = [
        ConversationEntity::class,
        MessageEntity::class,
        UserEntity::class,
        ConversationMemberEntity::class,
    ],
    version = 1,
)
@TypeConverters(DateConverter::class)
abstract class TalkieDatabase : RoomDatabase() {
    abstract fun conversationDao(): ConversationDao
    abstract fun messageDao(): MessageDao
    abstract fun userDao(): UserDao
}