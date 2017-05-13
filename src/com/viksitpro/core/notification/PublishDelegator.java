package com.viksitpro.core.notification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PublishDelegator {

	String deployment_type;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	private String serverConfig;
	
	public PublishDelegator(){
		try {
			Properties properties = new Properties();
			String propertyFileName = "app.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
			}
			deployment_type = properties.getProperty("deployment_type");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	//Send notification to an INDIVIDUAL USER
	// The argument value inside the HashMap<String, Object> item is based upon "type" of entity
	// if type = "lesson", the keys should be--> type, lessonId lessonType
	// if type = "assessment", the keys should be --> type, assessmentId, assessmentType
	public void sendNotification(int istarUserID, String message, String type, HashMap<String, Object> item, Timestamp eventDate){
		if(serverConfig.equalsIgnoreCase("prod")){			
			DatabaseReference databaseReferenceForUser = FirebaseDatabase.getInstance().getReference("istar-notification");
			
			databaseReferenceForUser.child(istarUserID+"");
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
	
	public void sendNotificationToUser(String istarUserId, String message, String type, HashMap<String, Object> item){
		if(deployment_type.equalsIgnoreCase("production")){
			
				DatabaseReference databaseReferenceForUser = FirebaseDatabase.getInstance().getReference("istar-notification").child(istarUserId);
				
				//databaseReferenceForUser.child(istarUserId);
				Map<String, Object> hopperUpdates = new HashMap<String, Object>();
				hopperUpdates.put("item", item);
				hopperUpdates.put("message", message);
				hopperUpdates.put("type", type);
				hopperUpdates.put("time", dateFormat.format(new Date()));
				//hopperUpdates.put("eventDate", eventDate);
				//databaseReferenceForUser.setValue(hopperUpdates);
				databaseReferenceForUser.push().setValue(hopperUpdates);
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("error in sending notification");
			}			
			System.out.println("Notification sent to all the users");
		}else{
			System.out.println("DEV SERVER");

				DatabaseReference databaseReferenceForUser = FirebaseDatabase.getInstance().getReference("istar-notification-dev").child(istarUserId);

				Map<String, Object> hopperUpdates = new HashMap<String, Object>();
				hopperUpdates.put("item", item);
				hopperUpdates.put("message", message);
				hopperUpdates.put("type", type);
				hopperUpdates.put("time", dateFormat.format(new Date()));
				//hopperUpdates.put("eventDate", eventDate);
				//databaseReferenceForUser.setValue(hopperUpdates);
				databaseReferenceForUser.push().setValue(hopperUpdates);

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("error in sending notification");
			}			
			System.out.println("Notification sent to all the users");
		}
	}
	
	//Send notification to a ALL USERS of GROUP (List)
		// The argument value inside the HashMap<String, Object> item is based upon "type" of entity
		// if type = "lesson", the keys should be--> lessonId, lessonType, courseId, moduleId, cmsessionId
		// if type = "assessment", the keys should be --> assessmentId, assessmentType (if required), courseId
		public void sendNotificationToGroup(List<String> allIstarUserIds, String message, String type, HashMap<String, Object> item){
			if(deployment_type.equalsIgnoreCase("production")){
				
				for(String istarUserId : allIstarUserIds){
					DatabaseReference databaseReferenceForUser = FirebaseDatabase.getInstance().getReference("istar-notification").child(istarUserId);
					
					//databaseReferenceForUser.child(istarUserId);
					Map<String, Object> hopperUpdates = new HashMap<String, Object>();
					hopperUpdates.put("item", item);
					hopperUpdates.put("message", message);
					hopperUpdates.put("type", type);
					hopperUpdates.put("time", dateFormat.format(new Date()));
					//hopperUpdates.put("eventDate", eventDate);
					//databaseReferenceForUser.setValue(hopperUpdates);
					databaseReferenceForUser.push().setValue(hopperUpdates);
				}
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("error in sending notification");
				}			
				System.out.println("Notification sent to all the users");
			}else{
				System.out.println("DEV SERVER");
				for(String istarUserId : allIstarUserIds){
					DatabaseReference databaseReferenceForUser = FirebaseDatabase.getInstance().getReference("istar-notification-dev").child(istarUserId);

					Map<String, Object> hopperUpdates = new HashMap<String, Object>();
					hopperUpdates.put("item", item);
					hopperUpdates.put("message", message);
					hopperUpdates.put("type", type);
					hopperUpdates.put("time", dateFormat.format(new Date()));
					//hopperUpdates.put("eventDate", eventDate);
					//databaseReferenceForUser.setValue(hopperUpdates);
					databaseReferenceForUser.push().setValue(hopperUpdates);

				}
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("error in sending notification");
				}			
				System.out.println("Notification sent to all the users");
			}
		}	
}
