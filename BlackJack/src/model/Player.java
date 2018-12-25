package model;

import java.util.ArrayList;
import java.util.List;

public class Player{
	/***
	 * 手札のインスタンスのリスト
	 * スプリットを使用した場合には複数の手札を持つので、手札を複数管理する
	 */
	private List<BJHand> hands = new ArrayList<BJHand>();
	
	public Player(Deck deck){
		this.hands.add(new BJHand(deck));
	}
	
	public void addHand(Deck deck) {
		this.hands.add(new BJHand(deck));
	}
	
	/***
	 * 手札のリストを返す
	 * @return
	 */
	public List<BJHand> getHands(){
		return this.hands;
	}
	
	/***
	 * プレー可能状態の手札に引数で指定したカードを1枚加える
	 * @param card
	 */
	public void setHand(Card card) {
		for(int i = 0; i < this.hands.size(); i++) {
//			if(hands.get(i).getActive() == true) {
//				this.hands.get(i).setHand(card);
//			}
			this.hands.get(i).setHand(card);
		}
	}
	
	public void doSplit(Deck deck) {
		addHand(deck);
		// 2枚の手札のうち、2枚目のカードを変数に一時保存する
		Card card =  this.hands.get(this.hands.size() - 2).getCards().get(1);
		// 2枚目の手札を削除
		this.hands.get(this.hands.size() -2).getCards().remove(1);
		// 削除した2枚目の枠に、新しく山札から引いたカードを加える
		this.hands.get(this.hands.size() -2).getCards().add(deck.drawCard());
		// 保持しておいたカードを増やした手札の1枚目に加える
		this.hands.get(this.hands.size() -1).setHand(card);
		// 増やした手札の2枚目は山札から加える
		this.hands.get(this.hands.size() -1).setHand(deck.drawCard());
	}
	
	/***
	 * 手札のリストを表示する
	 */
	public void printHandList() {
		for(int i =0; i < this.hands.size(); i++) {
			System.out.println("== Hand[" + i + "] ==");
			this.hands.get(i).printHand();
			System.out.println();
		}
	}
	
	public void printDealerHandList() {
		System.out.println("Dealer's Hand");
		this.hands.get(0).printDealerHand();
		System.out.println();
	}
}