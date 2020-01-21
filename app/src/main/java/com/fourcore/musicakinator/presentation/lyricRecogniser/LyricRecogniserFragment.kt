package com.fourcore.musicakinator.presentation.lyricRecogniser


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fourcore.musicakinator.R

/**
 * A simple [Fragment] subclass.
 */
class LyricRecogniserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lyric_recogniser, container, false)
    }
}