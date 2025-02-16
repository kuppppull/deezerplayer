package ru.ya.kuppppull.deezerplayer.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@SerialName("TrackSource")
sealed class TrackSource {
    @Serializable
    @SerialName("Api")
    data object Api : TrackSource()

    @Serializable
    @SerialName("Local")
    data object Local : TrackSource()

    companion object {
        private val json = Json {
            encodeDefaults = true
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            classDiscriminator = "type"
        }

        fun fromJson(jsonString: String): TrackSource = json.decodeFromString(jsonString)
    }
    fun toJson(): String = json.encodeToString(this)
}