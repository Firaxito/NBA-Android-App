package eu.petrfaruzel.nba

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.JsonParseException
import eu.petrfaruzel.nba.domain.features.players.models.PlayerDO
import eu.petrfaruzel.nba.domain.features.players.models.TeamDO
import eu.petrfaruzel.nba.features.players.PlayerDetailScreen
import eu.petrfaruzel.nba.features.players.PlayersScreen
import eu.petrfaruzel.nba.features.teams.TeamDetailScreen
import eu.petrfaruzel.nba.navigation.ApplicationScreen
import eu.petrfaruzel.nba.shared.tryParseJson
import eu.petrfaruzel.nba.ui.theme.NBATheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ApplicationScreen()
                }
            }
        }
    }
}