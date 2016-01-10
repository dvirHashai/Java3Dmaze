package io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

	private InputStream in ;
	
	
	public MyDecompressorInputStream(InputStream n) {
		this.in = n;
	}
	
	
	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
		
	}
	
	@Override
	public int read(byte[] arr) throws IOException{
		
	DataInputStream data = new DataInputStream(in);
		
		byte num;
		int i = 0;
		int index;
		
		while (data.available() >= 5 && i < arr.length){
			num = data.readByte();
			index = data.readInt();
			index += i;
			
		for (;i < index; i++) {
			arr[i] = num ;
			
		}
		
		}

		
		return arr.length;
		

	}

}
