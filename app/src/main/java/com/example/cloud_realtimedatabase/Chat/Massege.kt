package com.example.cloud_realtimedatabase.Chat

data class Massege( val text: String = "",
                    val senderId: String = "",
                    val receiverId :String = "",
                    val timestamp: Long = 0  )
