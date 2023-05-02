package com.kubyapp.agrokuby.presentation.home_screen.components.user_screen

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kubyapp.agrokuby.data.model.user.UserInfo

@Composable
fun ProfilePicture(
    onExpandChange:() -> Unit,
    userInfo: UserInfo
) {
    IconButton(onClick = onExpandChange) {
        AsyncImage(
            model = userInfo.photoUser,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
        )
    }

}
