package com.fourcore.musicakinator.presentation.game.lyricRecogniser

import android.app.AlertDialog
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
import com.fourcore.musicakinator.databinding.FragmentLyricsRecogniserBinding
import com.fourcore.musicakinator.domain.GameResult
import com.fourcore.musicakinator.network.pojo.Result
import com.fourcore.musicakinator.presentation.BaseFragment
import com.fourcore.musicakinator.presentation.dialog.ProgressDialog
import com.fourcore.musicakinator.presentation.dialog.ResultDialog
import com.fourcore.musicakinator.presentation.game.GameViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LyricsRecogniserFragment : BaseFragment() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var gameViewModel: GameViewModel
    lateinit var viewModel: LyricsRecogniserViewModel
    @Inject lateinit var progressDialog: ProgressDialog

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
        viewModel.bindGameViewModel(gameViewModel)
        gameViewModel.lyricRecogniserData = viewModel.lyricRecogniserData
        val databinding = DataBindingUtil.inflate<FragmentLyricsRecogniserBinding>(
            inflater,
            R.layout.fragment_lyrics_recogniser,
            container,
            false
        )
        databinding.viewModel = viewModel
        databinding.lyricRecogniserData = viewModel.lyricRecogniserData
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showProgressEvent.observe(this, Observer {
            progressDialog.show(requireActivity())
        })
        viewModel.songRecognisedEvent.observe(this, Observer {
            progressDialog.hide()
            val action = LyricsRecogniserFragmentDirections.actionLyricsRecogniserFragmentToPlayerFragment()
            findNavController().navigate(action)
        })
        viewModel.trackSoundNotFoundEvent.observe(this, Observer {
            progressDialog.hide()
            showTrackData(it)
        })
        viewModel.errorEvent.observe(this, Observer {
            progressDialog.hide()
            showError(it.errorDescription)
        })
        gameViewModel.gameOverEvent.observe(this, Observer {
            showGameResult(it)
        })
    }

    private fun showTrackData(trackShortData: Result) {
        AlertDialog.Builder(requireActivity())
            .setMessage(resources.getString(
                R.string.trackSoundNotFound,
                trackShortData.title,
                trackShortData.artist
            ))
            .setPositiveButton(
                resources.getString(R.string.right)) { dialog, which ->
                gameViewModel.confirmTrackGuess()
                dialog.dismiss()
            }
            .setNegativeButton(
                resources.getString(R.string.no)) {dialog, which ->
                gameViewModel.declineTrackGuess()
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun showError(errorMessage: String) {
        AlertDialog.Builder(requireActivity())
            .setMessage(errorMessage)
            .setCancelable(true)
            .show()
    }

    private fun showGameResult(gameResult: GameResult) {
        val resultDialog = ResultDialog(gameResult.imageId(), gameResult.descriptionId())
        resultDialog.show(requireActivity())
    }

    companion object {
        const val TAG = "LyricsRecogniserFragm"
    }
}
