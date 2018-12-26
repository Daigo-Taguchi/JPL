package model;

/***
 * HandクラスをBlackJack用の機能を追加したクラス
 * @author daigo
 *
 */
public class BJHand extends Hand{
	BJHand(Deck deck){
		super(deck);
	}
	
	/***
	 * 現在のHandがプレー可能な状態かを示す
	 */
	private boolean active = false;
	private final int BLACKJACK_NUM = 21;
	private final int DEALER_MIN = 17;
	
	/***
	 * プレイヤーの手札の合計値を計算して返す
	 * @param player
	 * @return
	 */
	public int calcHandScore() {
		int sum = 0;
		int aceCount = 0;

		// Aを1として計算する
		for(int i = 0; i < cards.size(); i ++) {
			if(this.cards.get(i).getNum() == 1) {
				aceCount ++;
			}

			if(this.cards.get(i).getNum() > 10) {
				sum += 10;
			}
			else {
				sum += this.cards.get(i).getNum();				
			}
		}
		// 手札の合計値が10以下、かつAが1枚以上ある場合はあとから10加算
		if(sum <= 10 && aceCount > 0) {
			sum += 10;
		}
		// this.handScore = sum;
		return sum;
	}
	
	/***
	 * プレー可能かどうかの状態を変更する
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	/***
	 * 手札の状態を判定して、その状態を表すenumを返す
	 * @return
	 */
	public GameState judgeState() {
		if(calcHandScore() > BLACKJACK_NUM) {
			return GameState.BURST;
		}
		else if(calcHandScore() == BLACKJACK_NUM && cards.size() == 2) {
			return GameState.BLACK_JACK;
		}
		else if(cards.get(0).getNum() == cards.get(1).getNum() && cards.size() == 2) { 
			return GameState.SPLIT;
		}
		else {
			return GameState.STOP;
		}
	}
	
	public GameState judgeDealerState() {
		if(calcHandScore() > BLACKJACK_NUM) {
			return GameState.BURST;
		}
		else if(calcHandScore() == BLACKJACK_NUM && cards.size() == 2) {
			return GameState.BLACK_JACK;
		}
		else if(cards.get(0).getNum() == cards.get(1).getNum() && cards.size() == 2) { 
			return GameState.SPLIT;
		}
		else if(calcHandScore() >= DEALER_MIN){
			return GameState.DELAER_FIN;
		}
		else {
			return GameState.STOP;
		}
	}
}
