package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.domain.model.User
import com.dfcruz.talkie.domain.repository.UserRepository

private const val ID = "a3bb189e-8bf9-3888-9912-ace4e6543002"

class GetCurrentUserUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(): User {
        val user = userRepository.getUser(ID)
        return if (user == null) {
            val newUser = User(
                id = ID,
                name = "LoggedIn"
            )
            userRepository.addUser(newUser)
            newUser
        } else {
            user
        }
    }
}