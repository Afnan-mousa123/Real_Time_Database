package com.example.cloud_realtimedatabase.Chat

data class Message( val text: String = "",
                    val senderId: String = "",
                    val receiverId :String = "",
                    val timestamp: Long = 0  )
