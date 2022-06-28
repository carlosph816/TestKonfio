package com.example.testlistdogskonfio.common

data class BaseError(
    var cause: String = "Hubo un error al obtener la información",
    var code: Int = -400,
    var exception: Exception? = null
)