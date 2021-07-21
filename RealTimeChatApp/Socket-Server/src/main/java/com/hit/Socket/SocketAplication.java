package com.hit.Socket;

import org.json.simple.JSONObject;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;

public class SocketAplication {
	public static String ROOM;
	public static void startAplication () {
		Configuration configuration = new Configuration();
		configuration.setHostname("localhost");
		configuration.setPort(8081);
		configuration.setTransports(Transport.POLLING);
		
		final SocketIOServer socketIOServer= new SocketIOServer(configuration);
		
//		socketIOServer.addEventListener("send-message", String.class, (client, data, ackRequest)->{
//			System.out.println(data);
//			client.sendEvent("reply-client", "hello client");
//		});
		
		socketIOServer.addEventListener("client-send-room", String.class, (client, data, ackRequest)->{
			client.joinRoom(data);
			ROOM = data.toString();
		});
	
		socketIOServer.addEventListener("client-leave-room", String.class, (client, data, ackRequest)->{
			client.leaveRoom(data);
		});
		
		socketIOServer.addEventListener("client-send-message", JSONObject.class, (client, data, ackRequest)->{
			JSONObject jsonObject = (JSONObject) data;
			System.out.println(jsonObject);
			//one to room
			String roomNameString= client.getAllRooms().toArray()[1].toString();
			System.out.println(roomNameString);
			System.out.println(ROOM);
			socketIOServer.getRoomOperations(roomNameString).sendEvent("server-send-message", data);
			//one to all
//			socketIOServer.getBroadcastOperations().sendEvent("server-send-message", data);
		});
		
		socketIOServer.start();
	}
}
