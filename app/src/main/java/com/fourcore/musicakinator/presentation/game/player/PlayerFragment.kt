package com.fourcore.musicakinator.presentation.game.player


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import com.deezer.sdk.model.PlayableEntity
import com.deezer.sdk.player.TrackPlayer
import com.deezer.sdk.player.event.PlayerWrapperListener

import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.databinding.FragmentPlayerBinding
import com.fourcore.musicakinator.domain.GameResult
import com.fourcore.musicakinator.presentation.dialog.ResultDialog
import com.fourcore.musicakinator.presentation.game.GameViewModel
import dagger.android.support.AndroidSupportInjection
import java.lang.Exception
import javax.inject.Inject

class PlayerFragment : Fragment() {
    val args: PlayerFragmentArgs by navArgs()
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var gameViewModel: GameViewModel
    lateinit var viewModel: PlayerViewModel
    @Inject lateinit var trackPlayer: TrackPlayer

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trackPlayer.addPlayerListener(object : PlayerWrapperListener{
            override fun onAllTracksEnded() {
            }

            override fun onPlayTrack(p0: PlayableEntity?) {
            }

            override fun onRequestException(p0: Exception?, p1: Any?) {
            }

            override fun onTrackEnded(p0: PlayableEntity?) {
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        gameViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(GameViewModel::class.java)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(PlayerViewModel::class.java)
        val databinding = DataBindingUtil.inflate<FragmentPlayerBinding>(
            inflater,
            R.layout.fragment_player,
            container,
            false
        )
        databinding.viewModel = viewModel
        databinding.playerData = viewModel.playerData
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.findTrack(args.trackName, args.artist)
        viewModel.trackLiveData.observe(this, Observer {
            trackPlayer.playTrack(it)
        })
        viewModel.pauseLiveEvent.observe(this, Observer{
            trackPlayer.pause()
        })
        viewModel.gameLiveResult.observe(this, Observer {
            showGameResult(it)
        })
    }

    private fun showGameResult(gameResult: GameResult) {
        val resultDialog = ResultDialog(gameResult.imageId(), gameResult.descriptionId())
        resultDialog.show(requireActivity())
    }
}
