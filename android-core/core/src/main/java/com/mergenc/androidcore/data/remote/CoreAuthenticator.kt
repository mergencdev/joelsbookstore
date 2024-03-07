package com.mergenc.androidcore.data.remote

import kotlinx.coroutines.runBlocking
import com.mergenc.androidcore.data.TokenRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class CoreAuthenticator(private val tokenRepository: TokenRepository) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {

            val oldToken = tokenRepository.getToken()

            if (oldToken==null && tokenRepository.getNewToken()!=null){
                tokenRepository.updateToken(tokenRepository.getNewToken()!!)
                tokenRepository.deleteNewToken()
                return response.request
                    .newBuilder()
                    .header("Authorization","Bearer ${tokenRepository.getToken()}")
                    .build()
            }

            val newToken: String
            runBlocking {
                newToken = tokenRepository.refreshToken("")
                tokenRepository.deleteToken()
                tokenRepository.saveNewToken(newToken)
            }

            // Retry the request with the new token.
            return response.request
                .newBuilder()
                .header("Authorization","Bearer $newToken")
                .build()
        }
    }
}