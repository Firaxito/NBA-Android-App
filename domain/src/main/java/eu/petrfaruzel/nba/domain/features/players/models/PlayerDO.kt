package eu.petrfaruzel.nba.domain.features.players.models

import android.content.Context
import androidx.annotation.PluralsRes
import eu.petrfaruzel.nba.data.api.services.players.models.Player
import eu.petrfaruzel.nba.domain.features.teams.models.TeamDO
import eu.petrfaruzel.nba.domain.features.teams.models.toDO

data class PlayerDO(
    val firstName: String? = null,
    val lastName: String? = null,
    val position: String? = null,
    val heightFeet: Long? = null,
    val heightInches: Long? = null,
    val weightPounds: Long? = null,

    val team: TeamDO? = null
) {

    fun getStringHeight(): String? {
        return if (heightFeet != null)
            "${heightFeet}\" ${heightInches ?: 0}\'"
        else null
    }

    fun getStringWeight(context: Context, @PluralsRes unitRes: Int): String? {
        return if (weightPounds != null) "$weightPounds ${
            context.resources.getQuantityString(
                unitRes,
                weightPounds.toInt()
            )
        }" else null
    }

}

fun Player.toDO() = PlayerDO(
    firstName = this.firstName,
    lastName = this.lastName,
    position = this.position,
    heightFeet = this.heightFeet,
    heightInches = this.heightInches,
    weightPounds = this.weightPounds,
    team = this.team?.toDO()
)