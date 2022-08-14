package ru.nosebs.core.DataStream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class ByteStreamReader extends DataInputStream
{

	public ByteStreamReader(ByteArrayInputStream is) {
		super(is);
	}
	
	public String readString() throws IOException {
		int len = this.readInt();
		if(len <= 0) return "";
		byte[] str = this.readNBytes(len);
		return new String(str);
	}

	public int readVInt() throws IOException {
		int b = this.readByte();
		int sign  = b >> 6, ret = b & 0x3F, off = 3;
		while ((b & 0x80) != 0) {
			b = this.readByte();
			ret |= b & 0x7F;
			off += 7;
		}
		if (sign == 0 || sign == 2) {
			return ret;
		} else {
			return ret | (-1 << off);
		}
	}
}
