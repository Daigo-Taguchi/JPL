package src;

import java.util.ArrayList;

public class Card {
	private ArrayList heartCardList = new ArrayList();
	private ArrayList diamondCardList = new ArrayList();
	private ArrayList clubCardList = new ArrayList();
	private ArrayList spadeCardList = new ArrayList();
	
	Card(){
		for(int i = 0; i < 13; i ++) {
			this.heartCardList.add(i + 1);
			this.diamondCardList.add(i + 1);
			this.clubCardList.add(i + 1);
			this.spadeCardList.add(i + 1);
		}
	}
	
	// シャッフルの関数を作成
	public enum Suit {
		heart,
		diamond,
		club,
		spade,
		joker
	}
}