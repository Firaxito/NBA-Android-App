package eu.petrfaruzel.nba.core.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import eu.petrfaruzel.nba.R


data class AttributeRowData(
    val key: String,
    val value: String?,
    val highlighted: Boolean = false,
    val onClicked: () -> Unit = {}
)


@Composable
fun AttributeRow(
    attributes: AttributeRowData
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable { attributes.onClicked() }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = attributes.key, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text =  attributes.value ?: stringResource(id = R.string.player_unknown),
                fontWeight = if (attributes.highlighted) FontWeight.Bold else FontWeight.Normal,
                textDecoration = if (attributes.highlighted) TextDecoration.Underline else null,
                color = if (attributes.highlighted) Color.Blue else Color.Black
            )
        }
        Divider()
    }
}

