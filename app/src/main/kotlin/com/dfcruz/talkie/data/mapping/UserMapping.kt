package com.dfcruz.talkie.data.mapping

import com.dfcruz.talkie.data.database.entity.UserEntity
import com.dfcruz.talkie.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        avatar = this.avatar,
        isOnline = this.online,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        lastSeenAt = this.lastSeenAt
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        avatar = this.avatar,
        online = this.isOnline,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        lastSeenAt = this.lastSeenAt
    )
}