package com.viksitpro.core.notification;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
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
	
	//Send notification to an INDIVIDUAL USER
	// The argument value inside the HashMap<String, Object> item is based upon the argument "type"
	// if type = "lesson", the keys should be--> taskId, lessonId, lessonType, 
	// if type = "assessment", the keys should be --> taskId, assessmentId, assessmentType
	public void sendNotification(int istarUserID, String message, String type, HashMap<String, Object> item, Timestamp eventDate){
		if(serverConfig.equalsIgnoreCase("prod")){			
			DatabaseReference databaseReferenceForUser = FirebaseDatabase.getInstance().getReference(istarUserID+"");
			
			Map<String, Object> hopperUpdates = new HashMap<String, Object>();
			hopperUpdates.put("item", item);
			hopperUpdates.put("message", message);
			hopperUpdates.put("type", type);
			hopperUpdates.put("eventDate", eventDate);
			databaseReferenceForUser.push().setValue(hopperUpdates);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("error in sending notification");
			}
			System.out.println("Notification sent to user");
		}
	}
	
	//Send notification to a ALL USERS of GROUP in the List
	// The argument value inside the HashMap<String, Object> item is based upon the argument "type"
	// if type = "lesson", the keys should be--> taskId, lessonId, lessonType, 
	// if type = "assessment", the keys should be --> taskId, assessmentId, assessmentType
	public void sendNotificationToGroup(List<Integer> allIstarUserIds, String message, String type, HashMap<String, Object> item, Timestamp eventDate){
		if(serverConfig.equalsIgnoreCase("prod")){
			
			for(Integer istarUserId : allIstarUserIds){
				DatabaseReference databaseReferenceForUser = FirebaseDatabase.getInstance().getReference(istarUserId+"");
				
				Map<String, Object> hopperUpdates = new HashMap<String, Object>();
				hopperUpdates.put("item", item);
				hopperUpdates.put("message", message);
				hopperUpdates.put("type", type);
				hopperUpdates.put("eventDate", eventDate);
				databaseReferenceForUser.push().setValue(hopperUpdates);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("error in sending notification");
			}
			
			System.out.println("Notification sent to all the users");
		}
	}	
}
