package src;

import java.util.ArrayList;
import java.util.List;

public class Player{
	/***
	 * 手札のインスタンスのリスト
	 * スプリットを使用した場合には複数の手札を持つので、手札を複数管理する
	 */
	private List<Hand> handList = new ArrayList<Hand>();
	
	Player(Deck deck){
		this.handList.add(new Hand(deck));
	}
	
	/***
	 * 手札のリストを返す
	 * @return
	 */
	public List<Hand> getHandList(){
		return this.handList;
	}
	
	/***
	 * 引数で指定したリスト番号の手札に引数で指定したカードを1枚加える
	 * @param handNum
	 * @param card
	 */
	public void setHand(int handNum, Card card) {
		this.handList.get(handNum).setHand(card);
	}
	
	/***
	 * 手札のリストを表示する
	 */
	public void printHandList() {
		for(int i =0; i < this.handList.size(); i++) {
			System.out.println("== Hand[" + i + "] ==");
			this.handList.get(i).printHand();
			System.out.println();
		}
	}
	
	public void printDealerHandList() {
		System.out.println("Dealer's Hand");
		this.handList.get(0).printDealerHand();
		System.out.println();
	}
}