package com.fourcore.musicakinator.presentation.player


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs

import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.databinding.FragmentPlayerBinding
import dagger.android.support.AndroidSupportInjection
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
}
