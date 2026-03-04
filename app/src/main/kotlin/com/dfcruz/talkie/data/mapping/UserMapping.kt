package com.dfcruz.talkie.data.mapping

import com.dfcruz.talkie.data.database.entity.UserEntity
import com.dfcruz.talkie.data.network.rest.dto.UserResponse
import com.dfcruz.talkie.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        phoneNumber = this.phoneNumber,
        avatarUrl = this.avatarUrl,
        isOnline = this.isOnline,
        createdAt = this.createdAt,
        serverCreatedAt = this.serverCreatedAt,
        updatedAt = this.updatedAt,
        lastSeenAt = this.lastSeenAt
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        phoneNumber = this.phoneNumber,
        avatarUrl = this.avatarUrl,
        isOnline = this.isOnline,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        lastSeenAt = this.lastSeenAt
    )
}

fun UserResponse.toDomain(): User {
    return User(
        id = this.id,
        name = this.displayName,
        phoneNumber = this.phoneNumber,
        avatarUrl = this.avatarUrl,
        isOnline = this.isOnline,
        lastSeenAt = this.lastSeenAt,
        createdAt = this.serverCreatedAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
    )
}

fun UserResponse.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.displayName,
        phoneNumber = this.phoneNumber,
        avatarUrl = this.avatarUrl,
        isOnline = this.isOnline,
        lastSeenAt = this.lastSeenAt,
        serverCreatedAt = this.serverCreatedAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
    )
}