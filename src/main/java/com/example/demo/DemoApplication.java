package com.example.demo;

import java.io.FileInputStream;
import java.io.InputStream;
import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		try {
			InputStream serviceAccount = new FileInputStream("config/fire_config.json");
			GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
			FirebaseOptions options = new FirebaseOptions.Builder()
    			.setCredentials(credentials)
    			.build();
			FirebaseApp.initializeApp(options);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

		SpringApplication.run(DemoApplication.class, args);
	}

}
