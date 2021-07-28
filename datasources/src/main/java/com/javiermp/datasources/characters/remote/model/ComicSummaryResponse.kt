package com.javiermp.datasources.characters.remote.model

import com.google.gson.annotations.SerializedName

data class ComicSummaryResponse(
    @SerializedName("resourceURI")
    var resourceUri: String?,
    @SerializedName("name")
    val name: String?)