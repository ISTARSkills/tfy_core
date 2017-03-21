package com.viksitpro.chat.services;
import static spark.Spark.init;
import static spark.Spark.staticFiles;
import static spark.Spark.webSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.viksitpro.core.dao.entities.ChatMessages;
import com.viksitpro.core.dao.entities.ChatMessagesDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.Notification;
import com.viksitpro.core.dao.utils.user.IstarUserServices;
import com.viksitpro.core.utilities.ChatUtility;


public class Chat {

    // this map is shared between sessions and threads, so it needs to be thread-safe (http://stackoverflow.com/a/2688817)
   public  static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
   public  static Map<Session,Integer> sessionUserIdMap = new ConcurrentHashMap<>();
   public static Map<Integer, ArrayList<Integer>> userIdGroupIdMap = new ConcurrentHashMap<>();
    public static void main(String[] args) {
        staticFiles.location("/public"); //index.html is served at localhost:4567 (default port)
        //staticFiles.expireTime(600);
        webSocket("/chat/*", ChatWebSocketHandler.class);
        init();
    }

    
  

	public static void broadcastNewDoubt(int senderId, String message) {
		
		ArrayList<Integer> groupIds =	Chat.userIdGroupIdMap.get(senderId);
		
		ArrayList<Integer> userIdToBroadCast = new ArrayList<>();
		for(Integer userId : Chat.userIdGroupIdMap.keySet())
		{
			if(!userIdToBroadCast.contains(userId) &&  Chat.userIdGroupIdMap.get(userId).contains(groupIds))
			{
				userIdToBroadCast.add(userId);				
			}
		}
		
		
		for(Session sesson : sessionUserIdMap.keySet())
		{
			//sending message from user beloging to students group
			if(sesson!=null && sesson.isOpen() && userIdToBroadCast.contains(sessionUserIdMap.get(sesson)))
			{
				try {
					sesson.getRemote().sendString(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//sending message to content group
			
			if(sesson!=null && sesson.isOpen() && userIdGroupIdMap.get(sessionUserIdMap.get(sesson)).contains(ChatUtility.CONTENT_GROUP))
			{
				try {
					sesson.getRemote().sendString(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}


	public static void broadcastMessageInGroup(Integer sender, String message, Integer groupId)
    {
    	
    	sessionUserIdMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
            	int currentUserId = sessionUserIdMap.get(session);
            	if(userIdGroupIdMap.get(currentUserId)!=null && userIdGroupIdMap.get(currentUserId).contains(groupId))
	            	{	      
            			System.out.println("sending message to "+currentUserId);
	            		session.getRemote().sendString(message);
	            	}               
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    	
    	
    }
    
   

   

	public static void broadcastMessageToUser(Integer senderId, String message, int receiverId ,Integer messageId) {
	
		Session receiverSession = null;
		for(Session sess : sessionUserIdMap.keySet())
		{
			if(sessionUserIdMap.get(sess)==receiverId)
			{
				System.out.println("got user to send message "+receiverId);
				receiverSession = sess;
			}
		}
		
		if(receiverSession!=null && receiverSession.isOpen())
			try {
				receiverSession.getRemote().sendString(message);
				markChatMessageAsSent(messageId);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	private static void markChatMessageAsSent(Integer messageId) {
		ChatMessages msg = new ChatMessagesDAO().findById(messageId);
		msg.setSent(true);
		MessageService service = new MessageService();
		service.updateChatMessageToDAO(msg);				
	}




	/**
	 * @param newUser 
	 * @param string
	 */
	public static void broadcastJoiningMessage(IstarUser newUser, String jsonObject) {
	/*	IstarUserService IstarUserService = new IstarUserService();
		ArrayList<Integer> userToBroadcast = new ArrayList<>();
		List<IstarUser> IstarUsersToBroadCast = IstarUserService.onlineUsersInGroup(newUser.getEmail());
		for(IstarUser member : IstarUsersToBroadCast)
		{
			
			if(!userToBroadcast.contains(member.getId()))
			{
				userToBroadcast.add(member.getId());
			}
		}
		userToBroadcast.add(newUser.getId());
		
		for(Session session : Chat.sessionUserIdMap.keySet())
		{System.out.println("use in session is "+Chat.sessionUserIdMap.get(session));
			if(userToBroadcast.contains(Chat.sessionUserIdMap.get(session)))
			{
				System.out.println("sending msg to "+Chat.sessionUserIdMap.get(session));
				try {
					session.getRemote().sendString(jsonObject);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		*/
	}


	public static void broadcastNotification(String message) {
		JSONObject data;
		try {
			data = new JSONObject(message);
			JSONArray userArray = data.getJSONArray("userIds");
			List<Integer> subscribers = new ArrayList<>();
			for(int i=0; i<userArray.length();i++ )
			{					
					JSONObject user = (JSONObject) userArray.get(i);
					int userId = Integer.parseInt(user.getString("id"));
					if(!subscribers.contains(userId))
					{
						subscribers.add(userId);
					}
			}
			
			for(Session session : Chat.sessionUserIdMap.keySet())
			{
				int userId = Chat.sessionUserIdMap.get(session);
				System.out.println("checking for user id "+userId);
				if(subscribers.contains(userId))
				{
					System.out.println("got user to send notification "+userId);
					try {
						session.getRemote().sendString(message);
						markUserNotificationAsSent(userId);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}




	private static void markUserNotificationAsSent(Integer userID) {
		IstarUserServices service = new IstarUserServices();
		NotificationService notificationService = new NotificationService();
		IstarUser user = service.getIstarUser(userID);
		if(user!=null)
		{
			for(Notification notice : user.getNotificationsForReceiverId())
			{
				notice.setSent(true);
				notificationService.updateNotificationToDAO(notice);
			}
		}
		
	}

}
