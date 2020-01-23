package com.fourcore.musicakinator.presentation.player

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.fourcore.musicakinator.di.FragmentScope
import javax.inject.Inject

@FragmentScope
class PlayerData @Inject constructor(): BaseObservable() {
    @get:Bindable
    var trackTitle = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.trackTitle)
    }

    @get:Bindable
    var artist = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.artist)
    }
}