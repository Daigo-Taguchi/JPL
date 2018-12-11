package src;

public class Game {
	private int playerNum;
	private final int INIT_CARD_NUM = 2;
	
	Game(int playerNum){
		this.playerNum = playerNum;
	}
	
	public void initGame() {
		// Cardクラスをインスタンス化して山札の作成→シャッフルして配る
		Deck deck = new Deck();
		deck.shuffle();
		
		// プレイヤーとディーラーをインスタンス化する
		// TODO:プレイヤーの数を可変にしたい
		Dealer dealer = new Dealer(INIT_CARD_NUM, deck);
		Player player = new Player(INIT_CARD_NUM , deck);
		
		// 1つのデッキからプレイヤー数 + ディーラーに対してカードを配る(最初は2枚ずつ)
		// TODO:2枚同時にドローできて、手札に加えられるようにしたい
		for(int i = 0; i < 2; i ++) {
			dealer.setHand(deck.drawCard());
			player.setHand(deck.drawCard());
		}
	}
	
	
}
