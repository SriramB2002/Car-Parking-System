package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Booking;
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

        String i = ref.getId();
        ref.update("bookingID", i);

        return "BOOKING DONE";
    }

    @DeleteMapping("/deleteBooking")
    public String delete(@RequestParam(name = "id") String id) throws Exception{
        Firestore database = FirestoreClient.getFirestore();
        DocumentReference docRef = database.collection("bookings").document(id);

        DocumentSnapshot document = docRef.get().get();

        if(document.exists()) {
            docRef.delete();
            return "DELETED";
        }

        else return "ERROR";

    }

    @PostMapping("/updateBooking")
    public String update(@RequestBody Booking booking) throws Exception {
        Firestore database = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> result = database.collection("bookings").document(booking.getBookingID()).set(booking);

        return "UPDATED";
    }

    @GetMapping("/getBookings")
    public @ResponseBody List<Booking> getBookings(@RequestParam(name = "id", required = false) String id) throws Exception {
        List<Booking> getList = new ArrayList<Booking>();
        List<QueryDocumentSnapshot> bookings;
        Firestore database = FirestoreClient.getFirestore();

        if(id == null) {
            bookings = database.collection("bookings").get().get().getDocuments();
        }

        else {
            bookings = database.collection("bookings").whereEqualTo("userID", Integer.parseInt(id)).get().get().getDocuments();
        }

        for (QueryDocumentSnapshot document : bookings) {
            getList.add(document.toObject(Booking.class));
        }

        return getList;

    }
}
