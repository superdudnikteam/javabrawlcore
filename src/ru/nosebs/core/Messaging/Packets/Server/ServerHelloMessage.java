package ru.nosebs.core.Messaging.Packets.Server;

import java.io.IOException;

import ru.nosebs.core.Logic.Player;
import ru.nosebs.core.Messaging.PiranhaServerMessage;

public class ServerHelloMessage extends PiranhaServerMessage {

	public ServerHelloMessage(Player player) {
		super(player);
		this.id = 20100;
	}

	@Override
	public void process() {
		try {
			this.bsw.writeInt(24);
			for(int i = 0; i < 24; i++) {
				this.bsw.writeByte(0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
