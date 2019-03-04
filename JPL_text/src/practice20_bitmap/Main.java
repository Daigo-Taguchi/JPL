package practice20_bitmap;

public class Main {
	public static void main(String[] args) {
		Image image = new Image("img/lena.bmp");
		image.reversePixel();
		image.outputImage("img/lena2.bmp");
	}
//		FileInputStream fi;
//		FileOutputStream fo;
//		BufferedInputStream bi = null;
//		BufferedOutputStream bo = null;
//
//		try {
//			fi = new FileInputStream("img/lena.bmp");
//			fo = new FileOutputStream("img/lena3.bmp");
//			bi = new BufferedInputStream(fi);
//			bo = new BufferedOutputStream(fo);
//			// DataInputStream dis = new DataInputStream(bi);
//			int data;
//			while((data = bi.read()) != -1) {
//				// System.out.printf("%02x%n",data);
//				bo.write(data);
//			}
//		}
//		catch(IOException e){
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(bi != null) {
//					bi.close();
//				}
//				if(bo != null) {
//					bo.close();
//				}
//			} catch (IOException e) {
//				// TODO 自動生成された catch ブロック
//				e.printStackTrace();
//			}
//		}
//	}
}