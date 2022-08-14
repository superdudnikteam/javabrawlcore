package ru.nosebs.core.Messaging.Packets.Client;

import java.io.IOException;

import ru.nosebs.core.Logic.Player;
import ru.nosebs.core.Messaging.PiranhaClientMessage;
import ru.nosebs.core.Utils.Log;

public class LoginMessage extends PiranhaClientMessage {
	
	private Player player;

	public LoginMessage(byte[] payload, Player player) {
		super(payload, player);
		this.player = player;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		try {
			this.bsr.readInt();
			player.lowID = this.bsr.readInt();
			String token = this.bsr.readString();
			
			this.bsr.readInt();
			this.bsr.readInt();
			this.bsr.readInt();
			
			this.bsr.readString();
			Log.log(Log.Tag.INFO, token + "l: " + player.lowID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
