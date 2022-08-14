package ru.nosebs.core.Messaging;

import ru.nosebs.core.Logic.Player;
import ru.nosebs.core.Messaging.Packets.Client.ClientHelloMessage;
import ru.nosebs.core.Messaging.Packets.Client.LoginMessage;

public class PiranhaMessageFactory
{

	public static PiranhaClientMessage createMessageById(short packetid, byte[] payload, Player player) {
		PiranhaClientMessage pcm = null;
		switch(packetid) {
		  case 10100:
			  pcm = new ClientHelloMessage(payload, player);
			  break;
		  case 10101:
			  pcm = new LoginMessage(payload, player);
			  break;
		}
		return pcm;
	}
}
