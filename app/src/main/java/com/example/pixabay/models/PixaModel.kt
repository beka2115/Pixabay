package com.example.pixabay.models

data class PixaModel (
    var hits: ArrayList<ImageModel>
        )

data class ImageModel(
    var largeImageURL:String
)
