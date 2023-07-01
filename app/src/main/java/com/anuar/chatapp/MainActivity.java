package com.anuar.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private messageAdapter adapter;
    private ImageButton sendImageButton;
    private ImageView photoImageView;
    private EditText messageEditText;

    private String userName;

    private String imageUrl;

    private static final int REQUEST_SELECT_PHOTO = 1;
    ChildEventListener childEventListener;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photoImageView = findViewById(R.id.photoImageView);
        sendImageButton = findViewById(R.id.sendImageButton);
        messageEditText = findViewById(R.id.messageEditText);

        userName = "Default User";

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        listView = findViewById(R.id.listView);
        List<message> messageList = new ArrayList<>();
        adapter = new messageAdapter(this, R.layout.message_item, messageList);
        listView.setAdapter(adapter);

        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    sendImageButton.animate().alpha(1).setDuration(0);
                } else {
                    sendImageButton.animate().alpha(0).setDuration(0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sendImageButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                message message = new message(messageEditText.getText().toString(), userName, null);
                messageEditText.setText("");

                myRef.push().setValue(message);
            }
        });

        photoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_SELECT_PHOTO);

                message message = new message(null, userName, imageUrl);
                messageEditText.setText("");

                myRef.push().setValue(message);
            }
        });

        childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                message message=snapshot.getValue(message.class);
                adapter.add(message);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        myRef.addChildEventListener(childEventListener);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            Uri selectedPhotoUri = data.getData();
            imageUrl = selectedPhotoUri.toString();
        }
    }
}