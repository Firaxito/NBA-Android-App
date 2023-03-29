package eu.petrfaruzel.nba.data.api.services.players.models

import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("total_pages")
    val totalPages: Long? = null,

    @SerializedName("current_page")
    val currentPage: Long? = null,

    @SerializedName("next_page")
    val nextPage: Long? = null,

    @SerializedName("per_page")
    val perPage: Long? = null,

    @SerializedName("total_count")
    val totalCount: Long? = null,
)
