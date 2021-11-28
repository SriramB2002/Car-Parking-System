package com.example.demo.controllers;

import com.example.demo.entity.Worker;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkerLogin {

    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/checkin")
    @ResponseBody
    public Worker login(@RequestBody Worker worker) throws Exception {
        Firestore database = FirestoreClient.getFirestore();
        DocumentReference docRef = database.collection("workers").document(worker.getName());
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = future.get();

        if(document.exists()) {
            if(document.toObject(Worker.class).getPass().equals(worker.getPass())) return document.toObject(Worker.class);
            else {
                Worker u = new Worker();
                u.setName("incorrect password");
                return u;
            }
        }

        else {
            Worker u = new Worker();
            u.setName("not found");
            return u;
        }
    }
}