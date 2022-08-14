package ru.nosebs.core.Messaging;

import java.io.ByteArrayInputStream;
import ru.nosebs.core.DataStream.ByteStreamReader;
import ru.nosebs.core.Logic.Player;

abstract public class PiranhaClientMessage
{
	protected ByteStreamReader bsr;
	private ByteArrayInputStream bair;
	
	protected Player player;
	
	public PiranhaClientMessage(byte[] payload, Player player) {
		bair = new ByteArrayInputStream(payload);
		this.bsr = new ByteStreamReader(bair);
		this.player = player;
	}
	
	abstract public void process();
}
