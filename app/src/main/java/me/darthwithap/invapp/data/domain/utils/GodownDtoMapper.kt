package me.darthwithap.invapp.data.domain.utils

import me.darthwithap.api.models.entities.NewGodownDto
import me.darthwithap.invapp.data.domain.models.Godown

object GodownDtoMapper : DomainMapper<NewGodownDto, Godown> {
    override fun mapToDomainModel(model: NewGodownDto): Godown {
        return with(model) {
            return@with id?.let { id ->
                name?.let { Godown(id, it) }
            }!!
        }
    }

    override fun mapFromDomainModel(domainModel: Godown): NewGodownDto {
        return with(domainModel) {
            return@with NewGodownDto(id = godownId, name = name)
        }
    }
}