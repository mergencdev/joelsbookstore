package com.mergenc.androidcore.data

interface TokenRepository {

    /**
     * en son geçerli olan auth token'ı döner
     */
    fun getToken():String?

    /**
     * mevcut auth token'ı siler
     */
    fun deleteToken()

    /**
     * mevcut auth token expire olduğunda, yeni alınan auth token'ı geçici olarak saklar
     */
    fun saveNewToken(newToken:String)

    /**
     * yeni alınan auth token'i döndürür
     */
    fun getNewToken():String?

    /**
     * geçici olarak saklanan yeni auth token'ı siler
     */
    fun deleteNewToken()

    /**
     * yeni alınan token'ı eski token'la yer değiştirir
     */
    fun updateToken(token:String)

    /**
     * auth token expire olması durumunda server'dan yeni token alır
     */
    suspend fun refreshToken(refreshToken: String): String
}