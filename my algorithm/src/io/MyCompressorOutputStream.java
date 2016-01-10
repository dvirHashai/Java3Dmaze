package io;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//zip the important data from the object to the file
public class MyCompressorOutputStream extends OutputStream  {

	private OutputStream out;
	
	
    public MyCompressorOutputStream(OutputStream s) {
	
	this.out = s; 
    }
    
	//write int from the byte array to file 
 
	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) throws IOException {
		out.write(b);
	
	}
	
	// write the number of times that 0 or 1 appear sequence in the array of bytes and the type of the number 0 or 1.  
	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[])
	 */
	public void write(byte[] arr) throws IOException {
		DataOutputStream data = new DataOutputStream(out);
		int counter = 0;
		byte num = arr[0];
	
	for (byte b : arr) {
		if(b == num){
		   counter++;	
		}
		else{	
		data.writeByte(num);
		data.writeInt(counter);
		num = b;
		counter = 1;	
		}
		
	}
	
	data.writeByte(num);
	data.writeInt(counter);
			

	
				
}
			
	
}
