package me.darthwithap.invapp.data.domain.utils

import me.darthwithap.api.models.entities.GodownDto
import me.darthwithap.invapp.data.domain.models.Godown

object GodownDtoListMapper : DomainMapper<List<GodownDto>, List<Godown>> {
    override fun mapToDomainModel(model: List<GodownDto>): List<Godown> {
        return model.map { godownDto ->
            with(godownDto) {
                Godown(id, name)
            }
        }
    }

    override fun mapFromDomainModel(domainModel: List<Godown>): List<GodownDto> {
        return domainModel.map { godown ->
            with(godown) {
                GodownDto(godownId, name)
            }
        }
    }

}