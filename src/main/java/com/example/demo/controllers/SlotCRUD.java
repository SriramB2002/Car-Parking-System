package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Query;
import com.example.demo.entity.Slot;
import com.example.demo.entity.SlotWithStatus;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.beans.propertyeditors.ZoneIdEditor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @CrossOrigin(origins = "http://localhost:5000")
    @DeleteMapping("/deleteSpace")
    public String deleteSpace(@RequestParam(name = "space") String space) throws Exception{

        List<QueryDocumentSnapshot> spaces;
        Firestore database = FirestoreClient.getFirestore();
        spaces = database.collection("slots").whereEqualTo("location", space).get().get().getDocuments();

        for (QueryDocumentSnapshot document : spaces) {
            document.getReference().delete();
        }

        return "SUCCESS";

    }

    @CrossOrigin(origins = "http://localhost:5000")
    @DeleteMapping("/deleteSlot")
    public String deleteSlot(@RequestParam(name = "id") int id) throws Exception{

        Firestore database = FirestoreClient.getFirestore();
        DocumentReference docRef = database.collection("slots").document(String.valueOf(id));
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = docRef.get().get();

        if(document.exists()) {
            docRef.delete();
            return "DELETED";
        }

        else return "ERROR";

    }

    @CrossOrigin(origins = "http://localhost:5000")
    @RequestMapping(value = "/searchSlots", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public List<SlotWithStatus> searchSlot(@RequestBody Query query) throws Exception {
        Firestore database = FirestoreClient.getFirestore();
        List<SlotWithStatus> list = new ArrayList<SlotWithStatus>();

        List<QueryDocumentSnapshot> slots;
        List<QueryDocumentSnapshot> bookings;
        
        bookings = database.collection("bookings").get().get().getDocuments();
        slots = database.collection("slots").whereEqualTo("location", query.getSpace()).get().get().getDocuments();
    
        for(QueryDocumentSnapshot document: slots) {
            boolean free = true;
            boolean recommend = document.getString("model").equals(query.getModel());
            long freeTime = 0;

            for(QueryDocumentSnapshot booking: bookings) {
                
                LocalDate date = LocalDate.parse(query.getDate());
                LocalTime in = LocalTime.parse(query.getCheckIn());
                LocalTime out = LocalTime.parse(query.getCheckOut());

                LocalDateTime date1 = LocalDateTime.of(date, in);
                LocalDateTime date2 = LocalDateTime.of(date, out);

                long req_in = date1.toEpochSecond(OffsetDateTime.now().getOffset()) * 1000;
                long req_out = date2.toEpochSecond(OffsetDateTime.now().getOffset()) * 1000;

                long given_in = booking.getLong("checkIn");
                long given_out = booking.getLong("checkOut");

                if(booking.get("slotID").equals(document.get("id"))) {
                    if(!((req_in < given_in && req_out < given_in) || (req_in > given_out && req_out > given_out))) {
                        free = false;
                        freeTime = given_out;
                        break;
                    }
                }
            }
            SlotWithStatus s = new SlotWithStatus();
            s.setFree(free);
            s.setFreeTime(freeTime);
            s.setRecommended(recommend);
            s.setSlot(((Long) document.get("id")).intValue());
            list.add(s);

        }

        return list;
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/updateSlot")
    public String update(@RequestBody Slot slot) throws Exception {
        Firestore database = FirestoreClient.getFirestore();
        DocumentReference docRef = database.collection("slots").document(String.valueOf(slot.getId()));
        // ...
        // future.get() blocks on response
        ApiFuture<WriteResult> future = docRef.update("model", slot.getModel());

        return future.get().toString();
    }

    
}
