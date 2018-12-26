package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import model.BJHand;
import model.Card;
import model.Deck;
import model.GameResult;
import model.Hand;
import model.Player;

public class Game {
	/***
	 * プレイヤーの人数
	 */
	private int playerNum; 
	/***
	 * ブラックジャックの閾値
	 */
	private final int BLACKJACK_NUM = 21;
	/***
	 * ディーラーの手札の数値の最小値。
	 * ディーラーはこの数字以上になるまでレイズしなければならない
	 */
	private final int DEALER_MIN = 17;

	private Deck deck;
	private Player player;
	private Player dealer;

	public Game(int playerNum){
		this.playerNum = playerNum;
	}

	/***
	 * Gameの初期化として、山札の生成とプレイヤーに手札を配る
	 */
	public void initGame() {
		// Cardクラスをインスタンス化して山札の作成→シャッフルして配る
		this.deck = new Deck();
		this.deck.shuffle();

		// プレイヤーとディーラーをインスタンス化する
		// TODO:プレイヤーの数を可変にしたい
		this.dealer = new Player(this.deck);
		this.player = new Player(this.deck);

		// 1つのデッキからプレイヤー数 + ディーラーに対してカードを配る(最初は2枚ずつ)
		// TODO:2枚同時にドローできて、手札に加えられるようにしたい
		for(int i = 0; i < 2; i ++) {
			this.dealer.setHand(this.deck.drawCard());
			this.player.setHand(this.deck.drawCard());
		}
		this.dealer.printDealerHandList();
		this.player.printHandList();
	}

	/***
	 * ゲームをスタートするメソッド
	 */
	public void startGame() {
		startPlayer();
		startDealer();
		judgeGame();
	} 

	/***
	 * プレイヤーのゲームをスタートする
	 */
	private void startPlayer() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		System.out.println("--- Playerプレイ開始---");
		List<BJHand> hands = player.getHands();
		for(int i = 0; i < hands.size(); i ++) {
			BJHand nowHand = hands.get(i);
			nowHand.setActive(true);
			while(true) {
				System.out.println("【Player】 Hand[" + i + "] の合計値：" + nowHand.calcHandScore());

				if(nowHand.judgeState() == GameResult.BURST) {
					System.out.println("【Player】 BURST");
					break;
				}
				if(nowHand.judgeState() == GameResult.BLACK_JACK) {
					System.out.println("【Player】 Black Jack");
					break;
				}
				if(nowHand.judgeState() == GameResult.SPLIT) {
					System.out.println("splitをしますか？【YES:1 / NO:0】");
					try {
						String buf = br.readLine();
						int result = Integer.parseInt(buf);
						if(result == 1) {
//							System.out.println("【Player】 現在の手札");
//							this.player.returnActiveHand().printHand();
							this.player.doSplit(this.deck);
							this.player.printHandList();
						}
					}catch(Exception e) {
						System.out.println("Please write 1 or 0");
					}
				}
				// System.out.println("現在プレーしている手札：Hand[" + i + "]" );
				System.out.println("Hand[" + i + "] のカードを引きますか？【YES:1 / NO:0】");
				try {
					String buf = br.readLine();
					int result = Integer.parseInt(buf);
					if(result == 1) {
						nowHand.setHand(this.deck.drawCard());
						this.player.printHandList();
					}
					else if(result == 0) {
						// nowHand.calcHandScore();
						System.out.println("【Player】 Hand[" + i + "] の合計値：" + nowHand.calcHandScore());
						break;
					}
					else {
						System.out.println("①Please write 1 or 0");
					}
				}catch(Exception e) {
					System.out.println("②Please write 1 or 0");
				}
			}
			nowHand.setActive(false);
		}
		System.out.println("--- Playerプレイ終了---");
	}

	private void startDealer() {
		System.out.println("--- Dealerプレイ開始---");
		dealer.printHandList();
		while(true) {
			List<BJHand> handList = this.dealer.getHands();
			BJHand nowHand = handList.get(0);
			int handScore = nowHand.calcHandScore();
			System.out.println("【Dealer】 現在の手札の合計値：" + handScore);

			if(handScore > BLACKJACK_NUM) {
				System.out.println("【Dealer】 BURST");
				break;
			}
			if(handScore == BLACKJACK_NUM) {
				System.out.println("【Dealer】 Black Jack");
				break;
			}
			if(handScore >= DEALER_MIN) {
				break;
			}

			nowHand.setHand(this.deck.drawCard());
			nowHand.printHand();
		}
		System.out.println("--- Dealerプレイ終了---");
	}

	private void judgeGame() {
		List<BJHand> dealerHandList = this.dealer.getHands();
		List<BJHand> playerHandList = this.player.getHands();
		int dealerScore = dealerHandList.get(0).calcHandScore();
		int playerScore = playerHandList.get(0).calcHandScore();

		if(playerScore > BLACKJACK_NUM) {
			System.out.println("** YOU LOSE **");
		}
		else if(dealerScore > BLACKJACK_NUM) {
			System.out.println("** YOU WIN **");
		}
		else if(playerScore == dealerScore) {
			System.out.println("** DRAW **");
		}
		else if(playerScore < dealerScore) {
			System.out.println("** YOU LOSE **");
		}
		else if(playerScore > dealerScore) {
			System.out.println("** YOU WIN!! **");
		}
	}
}