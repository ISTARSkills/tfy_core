package com.viksitpro.core.notification;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PublishDelegator {

	private String serverConfig;
	
	public PublishDelegator(){
		try{
			Properties properties = new Properties();
			String propertyFileName = "app.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
				if (inputStream != null) {
					properties.load(inputStream);
					serverConfig = properties.getProperty("serverConfig");
					
					System.out.println("serverConfig"+serverConfig);
				}
			} catch (IOException e) {
				e.printStackTrace();
				serverConfig = "dev";
			}
	}
	
	
	public void sendNotification(String istarUserID, String message, String type, Integer itemId, Timestamp eventDate){
		if(serverConfig.equalsIgnoreCase("prod")){			
			DatabaseReference databaseReferenceForUser = FirebaseDatabase.getInstance().getReference(istarUserID);
			
			Map<String, Object> hopperUpdates = new HashMap<String, Object>();
			hopperUpdates.put("itemId", itemId);
			hopperUpdates.put("message", message);
			hopperUpdates.put("type", type);
			hopperUpdates.put("eventDate", eventDate);
			databaseReferenceForUser.push().setValue(hopperUpdates);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("error in sending notification");
			}
			System.out.println("Notification sent to user");
		}
	}	
}
