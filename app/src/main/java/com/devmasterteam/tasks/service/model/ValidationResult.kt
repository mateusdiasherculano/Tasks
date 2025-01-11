package com.devmasterteam.tasks.service.model

class ValidationResult(message: String = "") {

    private var status: Boolean = true
    private var validationMessage: String = ""

    init {
        if (message.isNotEmpty()) {
            status = false
            validationMessage = message
        }
    }
    fun status() = status
    fun message() = validationMessage

}