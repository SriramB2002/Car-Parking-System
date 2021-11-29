package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLogin {
    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        try {
            Firestore database = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> result = database.collection("users").document(user.getUsername()).set(user);
            return "USER ADDED";
        }

        catch(Exception e) {
            return e.getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user) throws Exception{
        Firestore database = FirestoreClient.getFirestore();
        DocumentReference docRef = database.collection("users").document(user.getUsername());
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = future.get();

        if(document.exists()) {
            if(document.toObject(User.class).getPassword().equals(user.getPassword())) return document.toObject(User.class);
            else return null;
        }

        else return null;
    }
}
