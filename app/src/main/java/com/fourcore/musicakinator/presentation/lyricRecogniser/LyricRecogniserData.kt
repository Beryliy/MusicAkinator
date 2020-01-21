package com.fourcore.musicakinator.presentation.lyricRecogniser

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.fourcore.musicakinator.di.FragmentScope
import javax.inject.Inject

@FragmentScope
class LyricRecogniserData @Inject constructor(): BaseObservable() {
    @get:Bindable
    var lyrics = String()
        set(value) {
            field = value
            notifyPropertyChanged(BR.lyrics)
        }
}