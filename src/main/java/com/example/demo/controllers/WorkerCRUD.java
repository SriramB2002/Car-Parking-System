package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Worker;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkerCRUD {

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping("/checkin")
    @ResponseBody
    public Worker login(@RequestParam(name = "name") String name, @RequestParam(name = "pass") String pass) throws Exception {
        Firestore database = FirestoreClient.getFirestore();
        // asynchronously retrieve the document
        List<QueryDocumentSnapshot> workers = database.collection("workers").whereEqualTo("name", name).whereEqualTo("pass", pass).get().get().getDocuments();

        if(!workers.isEmpty()) {
            return workers.get(0).toObject(Worker.class); 
        }

        else {
            return null;
        }
        
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping("/getworkers")
    public @ResponseBody List<Worker> getAll() throws Exception{

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
        ApiFuture<WriteResult> result = database.collection("workers").document(String.valueOf(worker.getId())).set(worker);
        return result.get().getUpdateTime().toString();

    }

    @CrossOrigin(origins = "http://localhost:5000")
    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "id") int id) throws Exception{

        Firestore database = FirestoreClient.getFirestore();
        DocumentReference docRef = database.collection("workers").document(String.valueOf(id));
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = docRef.get().get();

        if(document.exists()) {
            docRef.delete();
            return "DELETED";
        }

        else return "ERROR";

    }
    
}
