package com.dfcruz.talkie.data.database.converter

import androidx.room.TypeConverter
import kotlin.time.Instant

class InstantConverter {
    @TypeConverter
    fun fromDate(instant: Instant?): Long? = instant?.epochSeconds

    @TypeConverter
    fun toDate(timestamp: Long?): Instant? = timestamp?.let { Instant.fromEpochSeconds(it) }
}