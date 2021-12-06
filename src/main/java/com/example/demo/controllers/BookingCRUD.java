package com.example.demo.controllers;

import com.example.demo.entity.Booking;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5000")
public class BookingCRUD {
    
    @PostMapping("/book")
    public String book(@RequestBody Booking booking) throws Exception {
        Firestore database = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> result = database.collection("bookings").document().set(booking);
        DocumentReference ref = database.collection("bookings")
        .whereEqualTo("bookingID", "")
        .get().get().getDocuments()
        .get(0).getReference();
        ref.update("bookingID", ref.getId());

        return "BOOKING DONE";
    }
}
