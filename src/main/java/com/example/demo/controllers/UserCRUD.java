package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCRUD {
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
    @GetMapping("/login")
    @ResponseBody
    public User login(@RequestParam(name = "uname") String uname, @RequestParam(name = "pass") String pass) throws Exception{
        Firestore database = FirestoreClient.getFirestore();
        DocumentReference docRef = database.collection("users").document(uname);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = future.get();

        if(document.exists()) {
            if(document.toObject(User.class).getPassword().equals(pass)) return document.toObject(User.class);
            else return null;
        }

        else return null;
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping("/search")
    @ResponseBody
    public List<User> search(@RequestParam(name = "user") String username) throws Exception {
        List<User> getList = new ArrayList<User>();
        List<QueryDocumentSnapshot> users;
        Firestore database = FirestoreClient.getFirestore();

        if(!username.isEmpty()) {
            users = database.collection("users").whereEqualTo("username", username).get().get().getDocuments();
        }

        else {
            users = database.collection("users").get().get().getDocuments();
        }

        for (QueryDocumentSnapshot document : users) {
            getList.add(document.toObject(User.class));
        }

        return getList;
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/updateUser")
    public String update(@RequestBody User user) throws Exception{

        Firestore database = FirestoreClient.getFirestore();
        DocumentReference docRef = database.collection("users").document(user.getUsername());
        // ...
        // future.get() blocks on response
        ApiFuture<WriteResult> future = docRef.set(user);

        return future.get().toString();

    }
    
}
