package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Slot;
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
public class SlotCRUD {
    
    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/add")
    public String addSpace(@RequestParam(name = "space") String space, @RequestParam(name = "slots") int slots) throws Exception {
        Firestore database = FirestoreClient.getFirestore();

        for(int i = 0; i < slots; i++) {
            Slot s = new Slot(space);
            database.collection("slots").document(String.valueOf(s.getId())).set(s);
        }
        return "SUCCESS";
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping("/getSlots")
    @ResponseBody
    public List<Slot> getSlots(@RequestParam(name = "space") String space) throws Exception {
        List<Slot> getList = new ArrayList<Slot>();
        List<QueryDocumentSnapshot> slots;
        Firestore database = FirestoreClient.getFirestore();

        if(!space.isEmpty()) {
            slots = database.collection("slots").whereEqualTo("location", space).get().get().getDocuments();
        }

        else {
            slots = database.collection("slots").get().get().getDocuments();
        }

        for (QueryDocumentSnapshot document : slots) {
            getList.add(document.toObject(Slot.class));
        }

        return getList;
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping("/getSlot")
    @ResponseBody
    public Slot getSlots(@RequestParam(name = "id") int id) throws Exception {
        Firestore database = FirestoreClient.getFirestore();
        DocumentSnapshot slot = database.collection("slots").document(String.valueOf(id)).get().get();

        if(slot.exists()) {
            return slot.toObject(Slot.class);
        }

        else {
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping("/getSpaces")
    @ResponseBody
    public List<String> getSpaces() throws Exception {
        List<String> getList = new ArrayList<String>();
        List<QueryDocumentSnapshot> slots;
        Firestore database = FirestoreClient.getFirestore();

        slots = database.collection("slots").get().get().getDocuments();

        for (QueryDocumentSnapshot document : slots) {
            String space = document.getString("location");

            if(!getList.contains(space)) getList.add(space);
        }

        return getList;
    }

    
}
