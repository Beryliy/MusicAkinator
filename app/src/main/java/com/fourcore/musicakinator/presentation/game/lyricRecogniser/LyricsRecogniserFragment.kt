package com.fourcore.musicakinator.presentation.game.lyricRecogniser


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.databinding.FragmentLyricRecogniserBinding
import com.fourcore.musicakinator.domain.GameResult
import com.fourcore.musicakinator.presentation.BaseFragment
import com.fourcore.musicakinator.presentation.dialog.ResultDialog
import com.fourcore.musicakinator.presentation.game.GameViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LyricsRecogniserFragment : BaseFragment() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var gameViewModel: GameViewModel
    lateinit var viewModel: LyricsRecogniserViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(GameViewModel::class.java)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LyricsRecogniserViewModel::class.java)
        gameViewModel.lyricRecogniserData = viewModel.lyricRecogniserData
        val databinding = DataBindingUtil.inflate<FragmentLyricRecogniserBinding>(
            inflater,
            R.layout.fragment_lyric_recogniser,
            container,
            false
        )
        databinding.viewModel = viewModel
        databinding.lyricRecogniserData = viewModel.lyricRecogniserData
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.songLiveData.observe(this, Observer {
            val action = LyricRecogniserFragmentDirections.actionLyricRecogniserFragmentToPlayerFragment(
                it.title,
                it.artist
            )
            findNavController().navigate(action)
        })
        gameViewModel.gameLiveResult.observe(this, Observer {
            showGameResult(it)
        })
    }

    private fun showGameResult(gameResult: GameResult) {
        val resultDialog = ResultDialog(gameResult.imageId(), gameResult.descriptionId())
        resultDialog.show(requireActivity())
    }
}
