package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Game {
	private int playerNum; // プレイヤーの人数
	private final int INIT_CARD_NUM = 2; // 最初の手札の枚数
	private Deck deck;
	private Player player;
	private Player dealer;
	
	Game(int playerNum){
		this.playerNum = playerNum;
	}
	
	/***
	 * Gameの初期化として、山札の生成とプレイヤーに手札を配る
	 */
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
		this.dealer = new Player(INIT_CARD_NUM , this.deck);
		this.player = new Player(INIT_CARD_NUM , this.deck);
		
		// 1つのデッキからプレイヤー数 + ディーラーに対してカードを配る(最初は2枚ずつ)
		// TODO:2枚同時にドローできて、手札に加えられるようにしたい
		for(int i = 0; i < 2; i ++) {
			this.dealer.setHand(this.deck.drawCard());
			this.player.setHand(this.deck.drawCard());
		}
		this.player.printHand();
	}
	
	public void startGame() {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		while(true) {
			int handPoint = player.calcHandPoint();
			System.out.println("現在の手札の合計値：" + handPoint);
			
			if(handPoint > 21) {
				System.out.println("BURST");
				System.out.println(" ** YOU LOSE **");
				break;
			}
			if(handPoint == 21) {
				System.out.println("Black Jack");
				System.out.println("** YOU WIN!! **");
				break;
			}
			
			System.out.println("カードを引きますか？【YES:1 / NO:0】");
			try {
				String buf = br.readLine();
				int result = Integer.parseInt(buf);
				if(result == 1) {
					this.player.setHand(this.deck.drawCard());
					this.player.printHand();
				}
				else if(result == 0) {
					player.calcHandPoint();
					System.out.println("現在の手札の合計値：" + handPoint);
					break;
				}
				else {
					System.out.println("①Please write 1 or 0");
				}
			}catch(Exception e) {
				System.out.println("②Please write 1 or 0");
			}
		}
	}
	
//	/***
//	 * プレイヤーの手札の合計値を計算する
//	 * @param player
//	 * @return
//	 */
//	private int calcCardNum(Player player) {
//		List<Card> hand = player.getHand();
//		int sum = 0;
//		
//		for(int i = 0; i < hand.size(); i ++) {
//			sum += hand.get(i).getNum();
//		}
//		player.setHundSum(sum);
//		System.out.println("手札の合計値：" + sum);
//		return sum;
//	} 
}