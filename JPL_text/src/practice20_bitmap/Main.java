package practice20_bitmap;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			FileInputStream fi = new FileInputStream("img/star.bmp");
			BufferedInputStream bi = new BufferedInputStream(fi);
			DataInputStream dis = new DataInputStream(bi);
			int data;
			while((data = dis.readInt()) != -1) {
				System.out.printf("%02x",data);
			}
			dis.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}