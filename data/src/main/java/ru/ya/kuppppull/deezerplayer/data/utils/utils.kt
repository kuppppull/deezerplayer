package ru.ya.kuppppull.deezerplayer.data.utils

import android.util.Base64

fun encodeBase64(input: String): String {
    return Base64.encodeToString(input.toByteArray(), Base64.NO_WRAP or Base64.URL_SAFE)
}

fun decodeBase64(input: String): String {
    return String(Base64.decode(input, Base64.URL_SAFE))
}