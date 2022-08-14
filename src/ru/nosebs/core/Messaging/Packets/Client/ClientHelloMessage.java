package ru.nosebs.core.Messaging.Packets.Client;
import ru.nosebs.core.Logic.Player;
import ru.nosebs.core.Messaging.PiranhaClientMessage;
import ru.nosebs.core.Messaging.Packets.Server.ServerHelloMessage;

import java.io.IOException;
import ru.nosebs.core.Utils.Log;

public class ClientHelloMessage extends PiranhaClientMessage
{
	private Player player;
	public ClientHelloMessage(byte[] pl, Player player) {
		super(pl, player);
		this.player = player;
	}
	
	@Override
	public void process()
	{
		// TODO: Implement this method
		try {
			int Protocol = this.bsr.readInt();
			int KeyVersion = this.bsr.readInt();
			int Major = this.bsr.readInt();
			int Minor = this.bsr.readInt();
			int Build = this.bsr.readInt();
			this.bsr.readUTF();
			this.bsr.readInt();
			this.bsr.readInt();
			Log.log(Log.Tag.INFO, "Protocol: " + Protocol + " Keyversion: " + KeyVersion + " Major: " + Major + " Minor: " + Minor + " Build: " + Build);
			ServerHelloMessage shm = new ServerHelloMessage(this.player);
			shm.send();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
