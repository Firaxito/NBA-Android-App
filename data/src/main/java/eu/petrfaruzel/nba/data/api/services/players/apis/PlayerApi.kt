package eu.petrfaruzel.nba.data.api.services.players.apis

import eu.petrfaruzel.nba.data.api.services.players.models.PlayerResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerApi {

    @GET("api/v1/players")
    suspend fun getPlayers(@Query("page") page: Int, @Query("per_page") perPage: Int, @Query("search") search: String? = null) : Response<PlayerResult>

}