package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Worker;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkerCRUD {

    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/getworkers")
    public List<Worker> getAll() throws Exception{

        List<Worker> getList = new ArrayList<Worker>();
        Firestore database = FirestoreClient.getFirestore();

        List<QueryDocumentSnapshot> workers = database.collection("workers").get().get().getDocuments();
        for (QueryDocumentSnapshot document : workers) {
            getList.add(document.toObject(Worker.class));
        }

        return getList;
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/addworker")
    public String add(@RequestBody Worker worker) throws Exception{

        Firestore database = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> result = database.collection("workers").document(worker.getName()).set(worker);
        return "WORKER ADDED";

    }

    
    
}
