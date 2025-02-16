package ru.ya.kuppppull.deezerplayer.data

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream


class Mp3Downloader(private val context: Context) {
    fun save(url: String, fileName: String) {
        val cacheDir = context.filesDir
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) return

            val file = File(cacheDir, fileName)
            response.body?.byteStream()?.use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()

        }

    }
}