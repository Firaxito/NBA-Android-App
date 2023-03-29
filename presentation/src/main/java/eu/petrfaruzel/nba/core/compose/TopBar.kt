package eu.petrfaruzel.nba.core.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.petrfaruzel.nba.R


@Composable
fun TopBar(navController: NavHostController, title: String, backNavigationEnabled: Boolean) {
    Column {
        Box(contentAlignment = Alignment.CenterStart) {
            if (backNavigationEnabled) {
                IconButton(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = {
                        navController.navigateUp()
                    }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        stringResource(id = R.string.cs_back_arrow)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    text = title
                )
            }
        }
        Divider(
            modifier = Modifier.height(2.dp),
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(
        navController = rememberNavController(),
        title = stringResource(id = R.string.players_list),
        backNavigationEnabled = true
    )
}