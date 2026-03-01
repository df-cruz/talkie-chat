package com.dfcruz.talkie.data.database.converter

import androidx.room.TypeConverter
import com.dfcruz.talkie.data.database.entity.MessageType

class MessageTypeConverter {
    @TypeConverter
    fun fromMessageType(type: MessageType): String = type.name

    @TypeConverter
    fun toMessageType(type: String): MessageType = when {
        MessageType.TEXT.name == type -> MessageType.TEXT
        else -> throw Throwable("Unsupported Message Type: $type")
    }
}