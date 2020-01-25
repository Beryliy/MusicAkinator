package com.fourcore.musicakinator.presentation.game.lyricRecogniser

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.global.proxy.ResourcesRepository
import javax.inject.Inject

@FragmentScope
class LyricRecogniserData @Inject constructor(
    val resourcesRepository: ResourcesRepository
): BaseObservable() {

    @get:Bindable
    var textProgress: String =
        resourcesRepository.resources.getString(
        R.string.progress,
            1,
            resourcesRepository.getInteger(R.integer.num_rounds)
    )
        set(value) {
            field = value
            notifyPropertyChanged(BR.textProgress)
        }

    @get:Bindable
    var gameProgress: Int = 1
        set(value) {
            field = value
            textProgress = resourcesRepository.getString(
                R.string.progress,
                value,
                resourcesRepository.getInteger(R.integer.num_rounds)
            )
            notifyPropertyChanged(BR.gameProgress)
        }

    @get:Bindable
    var lyrics = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.lyrics)
        }
}