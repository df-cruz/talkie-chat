package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MessageType {
    @SerialName("TEXT")
    TEXT,
}