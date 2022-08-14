package ru.nosebs.core.Messaging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import ru.nosebs.core.DataStream.ByteStreamWriter;
import ru.nosebs.core.Logic.Player;

abstract public class PiranhaServerMessage
{
	protected ByteStreamWriter bsw;
	private ByteArrayOutputStream baos;
	private Player player;
	
	protected int id;
	
	public PiranhaServerMessage(Player player) {
		baos = new ByteArrayOutputStream();
		this.bsw = new ByteStreamWriter();
		this.player = player;
	}
	
	abstract public void process();
	public void send() {
		try {
			this.process();
			this.bsw.flush();
			byte[] packet = baos.toByteArray();
			byte[] header = new byte[7];
			header[0] = (byte)(id >> 8 & 0xFF);
			header[1] = (byte)(id & 0xFF);

			int len = packet.length;

			header[2] = (byte)(len >> 16 & 0xFF);
			header[3] = (byte)(len >> 8 & 0xFF);

			header[4] = (byte)(len & 0xFF);

			header[5] = (byte)(3 >> 8 & 0xFF);
			header[6] = (byte)(3 & 0xFF);
			
			byte[] finalpacket = ByteBuffer.allocate(packet.length + header.length).put(header).put(packet).array();
			player.os.write(finalpacket);
			player.os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}