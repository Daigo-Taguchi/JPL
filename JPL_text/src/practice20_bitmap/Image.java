package practice20_bitmap;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Image {	
	private  FileInputStream fi;
	private  BufferedInputStream bi = null;
	private int[] fileHeader = new int[11];
	private int[] infoHeader = new int[37];// 4番目がwidth, 8番目がheightデータが格納されている
	private int[][] paletData = new int[4][4];
	private int bmpData;

	/***
	 * ここでビットマップファイルの読み込みを行って、画像の幅、高さを保持したい。
	 * 以降に画像の情報を変更するメソッドを作成していく予定なので、ここでは変更することのできる情報を配列として保持するところまで行いたい。
	 * @param filePath
	 */
	Image(String filePath){
		try {
			this. fi = new FileInputStream(filePath);
			this.bi = new BufferedInputStream(fi);

			int count = 0;
			while((this.bmpData = bi.read()) != -1) {
				if(count <= 10) {
					this.fileHeader[count] = this.bmpData;
				}

				if(14 <= count && count <= 50) {
					this.infoHeader[count - 14] = this.bmpData;
				}
				count ++;
				System.out.printf("%02x%n",this.bmpData);
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		finally{
			try {
				bi.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	public void changeSize(int width, int height) {
		this.infoHeader[4] = width;
		this.infoHeader[8] = height;
	}
}
