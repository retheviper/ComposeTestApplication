package com.retheviper.composetestapplication.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.retheviper.composetestapplication.R
import com.retheviper.composetestapplication.data.User
import com.retheviper.composetestapplication.util.UserDataCreator
import com.retheviper.composetestapplication.viewmodel.UserListViewModel
import kotlinx.coroutines.launch


/**
 * TODO Swipe to refresh
 */
@Composable
fun UserListScreen(
    userListViewModel: UserListViewModel,
    owner: LifecycleOwner
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    /**
     * Show [Snackbar] when current function has not implemented
     * TODO Not here
     */
    fun notImplemented() {
        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Not implemented yet",
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBarView() },
        bottomBar = { BottomAppBarView { notImplemented() } } // TODO overlapping scaffold
    ) {
        var users by remember { mutableStateOf(UserDataCreator.getLoadingUsers()) }

        userListViewModel.currentValue.observe(owner) {
            users = it
        }

        LazyColumn {
            items(items = users) {
                UserCardView(it) { notImplemented() }
            }
        }
    }
}

@Composable
fun UserCardView(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() }, // TODO Show user detail
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProfileImage(user = user)
            Column {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.h6
                )
                Text(user.location)
            }
        }
    }
}

@Composable
fun ProfileImage(user: User) {
    val bitmapState = remember { mutableStateOf<ImageBitmap?>(null) }

    /**
     * TODO Not here
     */
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(user.profileImageMedium)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource.asImageBitmap()
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    val modifier = Modifier
        .size(50.dp, 50.dp)
        .clip(CircleShape)

    bitmapState.value?.let {
        Image(
            bitmap = it,
            contentDescription = user.name,
            contentScale = ContentScale.Fit,
            modifier = modifier
        )
    } ?: Image(
        painter = painterResource(id = R.drawable.ic_empty_user_profile_image),
        contentDescription = user.name,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}