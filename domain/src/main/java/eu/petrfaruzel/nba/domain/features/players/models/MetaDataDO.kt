package eu.petrfaruzel.nba.domain.features.players.models

import eu.petrfaruzel.nba.data.api.services.players.models.MetaData

data class MetaDataDO(
    val totalPages: Long? = null,
    val currentPage: Long? = null,
    val nextPage: Long? = null,
    val perPage: Long? = null,
    val totalCount: Long? = null
)

fun MetaData.toDO() = MetaDataDO(
    totalPages = this.totalPages,
    currentPage = this.currentPage,
    nextPage = this.nextPage,
    perPage = this.perPage,
    totalCount = this.totalCount
)
