package com.example.cloud_realtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cloud_realtimedatabase.Chat.MassegeAdapter
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

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
                sendMessage(messageText)
                messageEditText.setText("")
            }
        }
        val messageList = mutableListOf<com.example.cloud_realtimedatabase.Chat.Message>()
        val messageAdapter = MassegeAdapter(this , messageList,senderUid)

        messageRecyclerView.layoutManager = LinearLayoutManager(this)
        messageRecyclerView.adapter = messageAdapter
        FirebaseDatabase.getInstance().getReference("chat")
            .addChildEventListener(object : ChildEventListener{
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                    val message = snapshot.getValue(com.example.cloud_realtimedatabase.Chat.Message::class.java)
                    if (message!=null){
                        messageList.add(message)
                    }
                    messageAdapter.notifyItemInserted(messageList.size -1)
                    messageRecyclerView.scrollToPosition(messageList.size -1)
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onChildRemoved(snapshot: DataSnapshot) {

                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onCancelled(error: DatabaseError) {

                }

            }

            )
    }

    private fun sendMessage( messageText: String ) {
        val timeStamp = System.currentTimeMillis()
        val message = com.example.cloud_realtimedatabase.Chat.Message(messageText,senderUid,receiverUid,timeStamp)
        FirebaseDatabase.getInstance().getReference("chat").push().setValue(message)

    }
}