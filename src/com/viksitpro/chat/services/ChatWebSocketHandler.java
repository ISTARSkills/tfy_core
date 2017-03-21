package com.viksitpro.chat.services;
import javax.websocket.server.ServerEndpoint;

import org.eclipse.jetty.websocket.api.annotations.WebSocket;



@WebSocket
@ServerEndpoint(value = "/chat/*")
public class ChatWebSocketHandler   {/*

    
    
    
    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
    	System.out.println(user.getUpgradeRequest().getRequestURI());
    	String url = user.getUpgradeRequest().getRequestURI().toString();
    	String email = url.split("/")[4];
    	System.out.println(email);
    	
    	IstarUser IstarUser = new IstarUserService().getIstarUser(email);
    	
    	String username = IstarUser.getIstarUserProfile()!=null ? (IstarUser.getIstarUserProfile().getFirstName()!=null ? IstarUser.getIstarUserProfile().getFirstName(): IstarUser.getEmail()) : IstarUser.getEmail() ;
    	Chat.userUsernameMap.put(user, username);
    	Chat.sessionUserIdMap.put(user,IstarUser.getId());
    	if(!Chat.userIdGroupIdMap.keySet().contains(IstarUser.getId()))
    	{
    		ArrayList<Integer> groupIds = new ArrayList<>();
    		for(ChatGroupMember member : IstarUser.getChatGroupMembers())
    		{
    			groupIds.add(member.getChatGroup().getId());
    		}
    		Chat.userIdGroupIdMap.put(IstarUser.getId(), groupIds);
    	}
    	
    	MessageService msgService = new MessageService();
    	JSONObject obj = getJoiningMsgJSONObject(user, IstarUser);
    	msgService.addJoiningMessage(IstarUser,username+" joined the chat");
    	Chat.broadcastJoiningMessage(IstarUser, obj.toString());
    	
    }

    *//**
	 * @param IstarUser
	 * @return
	 *//*
	public String getReceivers(IstarUser IstarUser) {
		JSONArray userArray = new JSONArray();
		ArrayList<Integer> allreadyAdded = new ArrayList<>();
		for(ChatGroupMember member : IstarUser.getChatGroupMembers())
		{
			
			for(ChatGroupMember  cgm: member.getChatGroup().getChatGroupMembers())
			{
				IstarUser memberUser = cgm.getIstarUser();
				if(!allreadyAdded.contains(memberUser.getId()))
				{
					allreadyAdded.add(memberUser.getId());
			    	String username = memberUser.getIstarUserProfile()!=null ? (memberUser.getIstarUserProfile().getFirstName()!=null ? memberUser.getIstarUserProfile().getFirstName(): memberUser.getEmail()) : memberUser.getEmail() ;
					JSONObject receiverObject = new JSONObject();
					try {
						receiverObject.put("username", username);
						receiverObject.put("online", "false");
						receiverObject.put("userId",memberUser.getId()+"");
						if(Chat.sessionUserIdMap.containsValue(memberUser.getId()))
						{
							receiverObject.put("online", "true");
							
						}
						userArray.put(receiverObject);
					
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		}
		
		return userArray.toString();
	}

	@OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        String username = Chat.userUsernameMap.get(user);
        Chat.userUsernameMap.remove(user);
        int IstarUserId = Chat.sessionUserIdMap.get(user);
        Chat.userIdGroupIdMap.remove(IstarUserId);
        Chat.sessionUserIdMap.remove(user);
            
	}

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
    	MessageService msgService = new MessageService();
    	NotificationService noticeService = new NotificationService();
    	System.out.println("json string in onMessage >>>>>>>"+ message);
    	
    	Integer senderId = Chat.sessionUserIdMap.get(user);
    	if(senderId!=null)
    	{
    		IstarUser IstarUser = new IstarUserDAO().findById(senderId);
    		String username = Chat.userUsernameMap.get(user);
        	try {
    			JSONObject data = new JSONObject(message);
    			String type = (String)data.get("type");
    			switch (type) {
    			case ChatType.JOINING_MESSAGE:
    				JSONObject obj = getJoiningMsgJSONObject(user, IstarUser);
    		    	msgService.addJoiningMessage(IstarUser,username+" joined the chat");
    		    	Chat.broadcastJoiningMessage(IstarUser, obj.toString());
    				break;
    			case ChatType.USER_CHAT:
    				int receiverId = Integer.parseInt(data.getString("receiverId"));
    				ChatMessages msg = msgService.addUserMessage(senderId, data.getString("message"), receiverId);
    				Chat.broadcastMessageToUser(senderId, message, receiverId, msg.getId());
    				break;
    			case ChatType.GROUP_CHAT:
    				int groupId = Integer.parseInt(data.getString("groupId"));
    				ChatMessages grp_msg = msgService.addGroupMessage(senderId, data.getString("message"), groupId);
    				Chat.broadcastMessageInGroup(senderId, message, groupId);
    				break;
    			case ChatType.ASK_DOUBT:
    				int slideId = Integer.parseInt(data.getString("slideId"));
    				msgService.addNewDoubt(senderId, data.getString("message"), slideId);
    				Chat.broadcastNewDoubt(senderId, message);
    				break;
    			case ChatType.NOTIFICATION:    				    						
    				noticeService.addNotificationMessages(message);    				
    				Chat.broadcastNotification(message );
    				break;    				
    			default:
    				break;
    			}
    			    			
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
    	}
		
    }

	private JSONObject getJoiningMsgJSONObject(Session user, IstarUser IstarUser) {
		JSONObject obj = new  JSONObject();
		IstarUserService IstarUserservce = new IstarUserService();
		String username = Chat.userUsernameMap.get(user);
    	try {
			obj.put("senderId", IstarUser.getId());
			obj.put("username",username);
	    	obj.put("type", "JOINING_MESSAGE");
	    	obj.put("message", username+" joined the chat");
	    	String uniqueSessionId = Integer.toHexString(user.hashCode());
	    	obj.put("sessionId", uniqueSessionId);
	    	String receivers = getReceivers(IstarUser);
	    	obj.put("userList",receivers);
	    	int onlineUserCount = IstarUserservce.onlineUsersInGroup(IstarUser.getEmail()).size();
	    	obj.put("onlineUserCount",onlineUserCount);
	    	String groups = getGroups(IstarUser);
	    	obj.put("groups", groups);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return obj;
	}

	*//**
	 * @param IstarUser
	 * @return
	 *//*
	private String getGroups(IstarUser IstarUser) {
		
		ArrayList<Integer> groups = new ArrayList<>();
		JSONArray groupArray = new JSONArray();
		for(ChatGroupMember cgm : IstarUser.getChatGroupMembers())
		{
			if(!groups.contains(cgm.getChatGroup().getId()))
			{
				groups.add(cgm.getChatGroup().getId());
				JSONObject obj = new JSONObject();
				try {
					obj.put("groupId", cgm.getChatGroup().getId()+"");
					obj.put("groupName", cgm.getChatGroup().getName());
					groupArray.put(obj);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return groupArray.toString();
	}

	*/

}
