package com.fourcore.musicakinator.network.interceptor

import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.global.proxy.ResourcesRepository
import okhttp3.Interceptor

class HeaderInterceptor(val resourcesRepository: ResourcesRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("api_token", resourcesRepository.getString(R.string.auddIoAPIToken))
                .build()
        )
    }

}