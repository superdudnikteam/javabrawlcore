package ru.nosebs.core;

import java.net.Socket;
import ru.nosebs.core.Utils.Log;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.nosebs.core.Logic.Player;
import ru.nosebs.core.Messaging.PiranhaClientMessage;
import ru.nosebs.core.Messaging.PiranhaMessageFactory;

public class Client extends Thread
{
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	private Player player;
	
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try
		{
			Log.log(Log.Tag.INFO, "New client (" + socket.getRemoteSocketAddress().toString()  + ") has connected");
			this.dos = new DataOutputStream(this.socket.getOutputStream());
			this.dis = new DataInputStream(this.socket.getInputStream());
			
			this.player = new Player();
			this.player.os = this.socket.getOutputStream();
			while (socket.isConnected()) {
				byte[] header = new byte[7];
				dis.read(header);
				
				short packetId = (short) (((header[0] & 0xFF) << 8) | (header[1] & 0xFF));
				if(packetId == 0) break;
				
				int length = (((header[2] & 0xFF) << 16) | ((header[3] & 0xFF) << 8) | (header[4] & 0xFF));
				
				byte[] payload = new byte[length];
				dis.read(payload);
				
				PiranhaClientMessage pcm = PiranhaMessageFactory.createMessageById(packetId, payload, player);
				if(pcm != null) {
					Log.log(Log.Tag.INFO, packetId + " was received");
					pcm.process();
				} else
					Log.log(Log.Tag.ERROR, packetId + " is unhandled");

			}
			this.dos.close();
			this.dis.close();
			this.socket.close();
			Log.log(Log.Tag.INFO, "Client (" + socket.getLocalAddress().getHostAddress()  + ") has disconnected");
		}
		catch (IOException e) {
			
		}
	}
}
