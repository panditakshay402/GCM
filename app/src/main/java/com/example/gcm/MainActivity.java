package com.example.gcm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference messagesRef;
    private List<Message> messageList;
    private MessageAdapter messageAdapter;

    @Override
    protected void onResume() {
        super.onResume();

        // Clear notifications when the app is opened
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Bind the message data to the ViewHolder

        // ...

        // Handle message item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mark the message as read in the Firebase Realtime Database
                Message clickedMessage = messageList.get(position);
                clickedMessage.setRead(true);
                messagesRef.child(clickedMessage.getId()).setValue(clickedMessage);

                // Update the UI (e.g., change the message's read status color)
                notifyDataSetChanged();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Realtime Database reference
        messagesRef = FirebaseDatabase.getInstance().getReference("messages");

        // Initialize message list and adapter
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        // Get the current user's username
        String currentUsername = "Your username"; // Replace with your implementation

        // Get the friend's username from EditText
        EditText editTextFriendUsername = findViewById(R.id.editTextFriendUsername);
        String friendUsername = editTextFriendUsername.getText().toString();

        // Define a child event listener to listen for new messages
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Get the new message
                Message message = snapshot.getValue(Message.class);

                // Check if the message is for the current user and from the friend
                if (message != null && message.getReceiver().equals(currentUsername)
                        && message.getSender().equals(friendUsername)) {
                    // Add the message to the list
                    messageList.add(message);

                    // Check if the message is read
                    if (message.isRead()) {
                        // Update the UI (e.g., change the message's read status color)
                        messageAdapter.notifyItemChanged(messageList.size() - 1);
                    } else {
                        // Notify the adapter that a new message is added
                        messageAdapter.notifyDataSetChanged();
                    }

                    // Scroll to the last message
                    recyclerView.scrollToPosition(messageList.size() - 1);
                }
            }

            // ...
        });



        });

        // Handle send message button click
        Button buttonSendMessage = findViewById(R.id.buttonSendMessage);
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the message content from EditText
                EditText editTextMessage = findViewById(R.id.editTextUsername);
                String messageContent = editTextMessage.getText().toString();

                // Create a new message object
                Message message = new Message(currentUsername, friendUsername, new Date().getTime(), messageContent);

                // Generate a unique message ID
                String messageId = messagesRef.push().getKey();

                // Save the message to the Firebase Realtime Database under the unique ID
                messagesRef.child(messageId).setValue(message);

                // Clear the message input field
                editTextMessage.setText("");
            }
        });
    }
}
