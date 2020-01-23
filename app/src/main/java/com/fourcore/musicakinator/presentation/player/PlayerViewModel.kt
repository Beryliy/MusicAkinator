package com.fourcore.musicakinator.presentation.player

import androidx.lifecycle.MutableLiveData
import com.deezer.sdk.model.Track
import com.deezer.sdk.network.connect.DeezerConnect
import com.deezer.sdk.network.request.DeezerRequest
import com.deezer.sdk.network.request.DeezerRequestFactory
import com.deezer.sdk.network.request.SearchResultOrder
import com.deezer.sdk.network.request.event.JsonRequestListener
import com.deezer.sdk.network.request.event.RequestListener
import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.presentation.BaseViewModel
import java.lang.Exception
import javax.inject.Inject

@FragmentScope
class PlayerViewModel @Inject constructor(
    val deezerConnect: DeezerConnect,
    val playerData: PlayerData
): BaseViewModel() {
    val trackLiveData = MutableLiveData<Track>()

    fun findTrack(
        trackName: String,
        artist: String
    ) {
        val query = "$trackName $artist"
        val deezerRequest = DeezerRequestFactory.requestSearchTracks(
            query,
            SearchResultOrder.Ranking
        )
        deezerConnect.requestAsync(
            deezerRequest,
            object: JsonRequestListener() {
                override fun onResult(result: Any?, requestId: Any?) {
                    val tracks = result as List<Track>
                    if(tracks.isEmpty()){

                    } else {
                        trackLiveData.value = tracks.first()
                    }
                }

                override fun onUnparsedResult(p0: String?, p1: Any?) {
                }

                override fun onException(p0: Exception?, p1: Any?) {
                }

            })
    }
}