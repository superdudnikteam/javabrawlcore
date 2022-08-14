package ru.nosebs.core.DataStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteStreamWriter extends DataOutputStream
{
	private static ByteArrayOutputStream stream = new ByteArrayOutputStream();
	
	public ByteStreamWriter() {
		super(stream);
	}
	
	public void writeString(String str) throws IOException {
		if(str.isEmpty() || str == null) {
			this.writeInt(-1);
		} else {
			byte[] encodedStr = str.getBytes();
			this.writeInt(encodedStr.length);
			this.write(encodedStr);
		}
	}
	
	public void writeVInt(int a1) throws IOException {
		int v1, v2, v3;
		v1 = (((a1 >> 25) & 0x40) | (a1 & 0x3F));
		v2 = ((a1 ^ (a1 >> 31)) >> 6);
		a1 >>= 6;
		if (v2 == 0) {
			this.writeByte(v1);
		} else {
			this.writeByte(v1 | 0x80);
			v2 >>= 7;
			v3 = 0;
			if (v2 > 0) {
				v3 = 0x80;
			}
			this.writeByte((a1 & 0x7F) | v3);
			a1 >>= 7;
			while (v2 != 0) {
				v2 >>= 7;
				v3 = 0;
				if (v2 > 0) {
					v3 = 0x80;
				}
				this.writeByte((a1 & 0x7F) | v3);
				a1 >>= 7;
			}
		}
	}
}
