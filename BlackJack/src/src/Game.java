package src;

import java.util.List;

public class Game {
	private int playerNum; // プレイヤーの人数
	private final int INIT_CARD_NUM = 2; // 最初の手札の枚数
	private Deck deck;
	
	Game(int playerNum){
		this.playerNum = playerNum;
	}
	
	public void initGame() {
		// Cardクラスをインスタンス化して山札の作成→シャッフルして配る
		Deck deck = new Deck();
		this.deck = deck;
		// deck.printDeck();
		this.deck.shuffle();
		// deck.printDeck();
		
		// プレイヤーとディーラーをインスタンス化する
		// TODO:プレイヤーの数を可変にしたい
		// Dealer dealer = new Dealer(INIT_CARD_NUM, deck);
		Player dealer = new Player(INIT_CARD_NUM , this.deck);
		Player player = new Player(INIT_CARD_NUM , this.deck);
		
		// 1つのデッキからプレイヤー数 + ディーラーに対してカードを配る(最初は2枚ずつ)
		// TODO:2枚同時にドローできて、手札に加えられるようにしたい
		for(int i = 0; i < 2; i ++) {
			dealer.setHand(this.deck.drawCard());
			player.setHand(this.deck.drawCard());
		}
		player.printHand();
	}
	
	/***
	 * プレイヤーの手札の合計値を計算する
	 * @param player
	 * @return
	 */
	public int calcCardNum(Player player) {
		List<Card> hand = player.getHand();
		int sum = 0;
		
		for(int i = 0; i < hand.size(); i ++) {
			sum += hand.get(i).getNum();
		}
		return sum;
	} 
}