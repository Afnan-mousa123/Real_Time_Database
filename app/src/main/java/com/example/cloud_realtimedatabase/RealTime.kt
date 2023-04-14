package com.example.cloud_realtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class RealTime : AppCompatActivity() {
    private lateinit var messageRecyclerView:RecyclerView
    private lateinit var messageEditText :EditText
    private lateinit var sendButton:Button

    private lateinit var senderUid:String
    private lateinit var receiverUid:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        messageRecyclerView = findViewById(R.id.messages_recycler_view)
        messageEditText = findViewById(R.id.message_input)
        sendButton = findViewById(R.id.send_button)

        senderUid = "z72jRn3FZsfkgV7zOs0VoeJgJwS2"
        receiverUid = "jGCrCXvY2BhCoT2Y06i9XA3dFSo2"

        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString().trim()

            if (messageText.isNotEmpty()){
                sendMessage("")
                messageEditText.setText("")
            }
        }

    }

    private fun sendMessage( messageText: String ) {


    }
}