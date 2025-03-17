package com.android.androidtestassignment.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.androidtestassignment.R
import com.android.androidtestassignment.ui.datamodels.User
import com.android.androidtestassignment.ui.theme.Typography
import com.android.androidtestassignment.utils.getFormattedHourMinutes

@Composable
fun UserListItem(
    user: User,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    CompositionLocalProvider(LocalContentColor provides Color.Gray) {
        Column(
            modifier = modifier
        ) {
            Row(modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp)) {
                AsyncImage(
                    model = user.profilePictureUrl,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = user.name,
                        maxLines = 1,
                        style = Typography.bodyLarge
                    )
                    Text(
                        text = stringResource(
                            R.string.template_age_country,
                            user.age,
                            user.country
                        ),
                        maxLines = 1,
                        style = Typography.bodyMedium
                    )
                }
                Icon(
                    modifier = Modifier.size(22.dp),
                    painter = painterResource(R.drawable.ic_paperclip),
                    contentDescription = null
                )
                Text(
                    text = user.time.getFormattedHourMinutes(),
                    modifier = Modifier.padding(start = 12.dp),
                    style = Typography.bodySmall
                )
            }

            Icon(
                modifier = Modifier.padding(end = 16.dp, bottom = 16.dp).size(28.dp)
                    .align(Alignment.End),
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null
            )

            HorizontalDivider()
        }
    }
}