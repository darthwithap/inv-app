package me.darthwithap.invapp.data.domain.utils

import me.darthwithap.api.models.entities.dto.UserDto
import me.darthwithap.invapp.data.domain.models.User

class UserDtoMapper(private val token: String) : DomainMapper<UserDto?, User?> {
    override fun mapToDomainModel(model: UserDto?): User? {
        return model?.let {
            with(it) {
                return@with User(
                    createdAt,
                    token,
                    displayName,
                    isActivated,
                    role,
                    shop,
                    updatedAt,
                    username
                )
            }
        }
    }

    override fun mapFromDomainModel(domainModel: User?): UserDto? {
        return domainModel?.let {
            with(domainModel) {
                return@with UserDto(
                    createdAt,
                    displayName,
                    isActivated,
                    role,
                    shop,
                    updatedAt,
                    username
                )
            }
        }
    }
}