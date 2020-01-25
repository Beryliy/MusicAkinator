package com.fourcore.musicakinator.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.presentation.dialog.ProgressDialog
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : FragmentActivity(), HasAndroidInjector {
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPause() {
        super.onPause()
        progressDialog.destroy()
    }

    override fun onBackPressed() {
    }

    override fun androidInjector() = androidInjector
}