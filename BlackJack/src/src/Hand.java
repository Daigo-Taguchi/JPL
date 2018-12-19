package src;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> hand = new ArrayList<Card>();
	private int handScore;
	private Deck deck;
	
	Hand(Deck deck){
		this.deck = deck;
	}
	
	/***
	 * 手札に引数で指定したカードを一枚加える
	 * @param card
	 */
	public void setHand(Card card) {
		this.hand.add(card);
	}
	
	/***
	 * 手札を表すリストを返す
	 * @return
	 */
	public List<Card> getHand(){
		return this.hand;
	}
	
	/***
	 * プレイヤーの手札の合計値を計算して返す
	 * @param player
	 * @return
	 */
	public int calcHandScore() {
		int sum = 0;
		for(int i = 0; i < this.hand.size(); i ++) {
			List<Integer> number = new ArrayList();
			number.add(this.hand.get(i).getNum());
			
			for(int j = 0; j < number.size(); j++) {
				
				// 手札の合計が11以下の場合に1を持っている場合は11として計算。
				if(sum <= 10 && number.get(j) == 1) {
					number.set(j, 11);
				}
				// 手札が絵柄の場合は10として計算する
				else if(number.get(j) > 10) {
					number.set(j, 10);
				}
				
				sum += number.get(j);
			}
		}
		this.handScore = sum;
		return this.handScore;
	}
	
	public void printHand() {
		for(int i = 0; i < this.hand.size(); i ++) {
			this.hand.get(i).printCardNum();
		}
	}
}
