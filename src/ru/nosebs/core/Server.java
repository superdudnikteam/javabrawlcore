package ru.nosebs.core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import ru.nosebs.core.Utils.Log;

public class Server extends Thread
{

	private ServerSocket socket;
	
	public void run() {
		try {
			socket = new ServerSocket(9339);
			InetAddress ia = socket.getInetAddress();
			Log.log(Log.Tag.INFO, "Server started on " + ia.getHostAddress() + ":" + socket.getLocalPort());
			while(socket.isBound()) {
				Socket csocket = socket.accept();
				new Client(csocket).start();
			}
		} catch (IOException ex) {
			Log.log(Log.Tag.ERROR, "Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
