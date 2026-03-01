package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.domain.model.User
import com.dfcruz.talkie.domain.repository.UserRepository
import kotlin.time.Clock

private const val ID = "a3bb189e-8bf9-3888-9912-ace4e6543002"

class GetCurrentUserUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(): User {
        val user = userRepository.getUser(ID)
        return if (user == null) {
            val newUser = User(
                id = ID,
                name = "LoggedIn",
                phoneNumber = "2222",
                createdAt = Clock.System.now(),
            )
            userRepository.addUser(newUser)
            newUser
        } else {
            user
        }
    }
}