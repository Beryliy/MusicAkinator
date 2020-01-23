package com.fourcore.musicakinator.presentation.player


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.deezer.sdk.model.Track

import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.databinding.FragmentPlayerBinding
import com.fourcore.musicakinator.global.util.setImage
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_player.*
import javax.inject.Inject

class PlayerFragment : Fragment() {
    val args: PlayerFragmentArgs by navArgs()
    @Inject lateinit var viewModel: PlayerViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
    }
}
