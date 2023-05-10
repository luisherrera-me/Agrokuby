package com.kubyapp.agrokuby.presentation.home_screen.components.user_screen

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.data.model.user.UserInfo

@Composable
fun ProfilePicture(
    onExpandChange:() -> Unit,
    userInfo: UserInfo
) {
    IconButton(onClick = onExpandChange) {
        if (userInfo.photoUser.isNullOrEmpty()) {
            Icon(
                painter = painterResource(id = R.drawable.account_circle),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                tint = Color.Black
            )
        } else {
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
}
