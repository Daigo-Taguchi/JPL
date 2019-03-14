package practice20_bitmap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Image {
	private  FileInputStream fi;
	private  BufferedInputStream bi = null;
	private int[] fileHeader = new int[14];
	private int[] infoHeader = new int[40];// 4番目がwidth, 8番目がheightデータが格納されている
	private int[][] imgData = new int[16][3];
	private List<Integer> imgData2 = new ArrayList<Integer>();
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
					this.imgData2.add(this.bmpData);
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
		this.infoHeader[4] = width & 0xff;
		this.infoHeader[5] = (width >> 8) & 0xff;
		this.infoHeader[6] = (width >> 16) & 0xff;
		this.infoHeader[7] = (width >> 24) & 0xff;

		this.infoHeader[8] = height & 0xff;
		this.infoHeader[9] = (height >> 8) & 0xff;
		this.infoHeader[10] = (height >> 16) & 0xff;
		this.infoHeader[11] = (height >> 24) & 0xff;

		int bmpDataSize = width * height * 3;
		this.infoHeader[20] = bmpDataSize & 0xff;
		this.infoHeader[21] = (bmpDataSize >> 8) & 0xff;
		this.infoHeader[22] = (bmpDataSize >> 16) & 0xff;
		this.infoHeader[23] = (bmpDataSize >> 24) & 0xff;

		int bmpFileDataSize = bmpDataSize + 54;
		this.fileHeader[2] = bmpFileDataSize & 0xff;
		this.fileHeader[3] = (bmpFileDataSize >> 8) & 0xff;
		this.fileHeader[4] = (bmpFileDataSize >> 16) & 0xff;
		this.fileHeader[5] = (bmpFileDataSize >> 24) & 0xff;
	}

	public void reversePixel() {
		for(int i = 0; i < this.imgData2.size(); i ++) {
			this.imgData2.set(i, 255 - this.imgData2.get(i));
		}
	}

	/***
	 * bitmapファイルの出力を行う
	 * パラメータで受け取ったファイルパスに出力する
	 * @param filePathName
	 */
	public void outputImage(String filePathName) {
		try(OutputStream fo = new BufferedOutputStream(new FileOutputStream(filePathName))) {
			List<Integer> data = new ArrayList<Integer>();
			for(int i = 0; i < this.fileHeader.length; i ++) {
				data.add(this.fileHeader[i]);
			}

			for(int i = 0; i < this.infoHeader.length; i ++) {
				data.add(this.infoHeader[i]);
			}

			for(int i = 0; i < this.imgData2.size(); i ++) {
				data.add(this.imgData2.get(i));
			}

			for(int i = 0; i < data.size(); i ++) {
				// System.out.printf("%02x%n", data.get(i));
				fo.write(data.get(i));
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}