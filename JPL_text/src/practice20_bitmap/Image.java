package practice20_bitmap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Image {	
	private  FileInputStream fi;
	private  BufferedInputStream bi = null;
	private int[] fileHeader = new int[14];
	private int[] infoHeader = new int[40];// 4番目がwidth, 8番目がheightデータが格納されている
	private int[][] paletData = new int[16][3];
	private List<Integer> paletData2 = new ArrayList<Integer>();
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
				if(count < 14) {
					this.fileHeader[count] = this.bmpData;
				}

				if(14 <= count && count < 54) {
					this.infoHeader[count - 14] = this.bmpData;
				}

				if(54 <= count) {
					this.paletData2.add(this.bmpData);
				}
				count ++;
				// System.out.printf("%02x%n",this.bmpData);
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
		this.infoHeader[20] = this.infoHeader[4] * this.infoHeader[8] * 3;
	}

	/***
	 * bitmapファイルの出力を行う
	 * パラメータで受け取ったファイルパスに出力する
	 * @param filePathName
	 */
	public void outputImage(String filePathName) {
		FileOutputStream fo;
		BufferedOutputStream bo = null;

		try {
			fo = new FileOutputStream(filePathName);
			bo = new BufferedOutputStream(fo);

			List<Integer> data = new ArrayList<Integer>();
			for(int i = 0; i < this.fileHeader.length; i ++) {
				data.add(this.fileHeader[i]);
			}

			for(int i = 0; i < this.infoHeader.length; i ++) {
				data.add(this.infoHeader[i]);
			}

			for(int i = 0; i < this.paletData2.size(); i ++) {
				data.add(this.paletData2.get(i));
			}

			for(int i = 0; i < data.size(); i ++) {
				// System.out.printf("%02x%n", data.get(i));
				bo.write(data.get(i));
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		finally {
			try {
				if(bo != null) {
					bo.close();
				}
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}