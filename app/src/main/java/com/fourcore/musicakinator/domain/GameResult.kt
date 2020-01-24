package com.fourcore.musicakinator.domain

import androidx.annotation.DrawableRes
import com.fourcore.musicakinator.R

enum class GameResult: ResIdsProvider{
    USERS_WIN {
        override fun imageId() = R.drawable.nokia_broken
        override fun descriptionId() = R.string.usersWin
    },
    APPS_WIN {
        override fun imageId() = R.drawable.nokia3310
        override fun descriptionId() = R.string.appsWin
    };
}