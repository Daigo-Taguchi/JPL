package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.BJHand;
import model.Deck;
import model.GameResult;
import model.GameState;
import model.Player;
import view.Observer;

public class GUIGameFlow implements ActionListener{
	private Deck deck;
	private Player player;
	private Player dealer;
	private final int INIT_CARD_NUM = 2;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private final int BLACKJACK_NUM = 21;

	public void initGame() {
		// Cardクラスをインスタンス化して山札の作成、シャッフルして配る
		this.deck = new Deck();
		this.deck.shuffle();

		// プレイヤーとディーラーをインスタンス化する
		this.dealer = new Player(this.deck);
		this.player = new Player(this.deck);

		// 1つのデッキからプレイヤーとディーラーに対してカードを配る(最初は2枚ずつ)
		for(int i = 0; i < INIT_CARD_NUM; i++) {
			this.dealer.setHand(this.deck.drawCard());
			this.player.setHand(this.deck.drawCard());
		}
		System.out.println("test");
		notifyObservers();
	}

	/***
	 * Playerの手札のリストを返す
	 * @return
	 */
	public List<BJHand> getPlayerHands() {
		return this.player.getHands();
	}

	/***
	 * dealerの手札のリストを返す
	 * @return
	 */
	public List<BJHand> getDealerHands() {
		return this.dealer.getHands();
	}

	public boolean getDealerIsPlay() {
		return this.dealer.getIsPlay();
	}

	/***
	 * Observerを管理するリストに引数で受け取ったObserverを追加するメソッド
	 * @param observer
	 */
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}

	/***
	 * Observerを管理するリストから引数で受け取ったObserverを削除するメソッド
	 * @param observer
	 */
	public void deleteObserver(Observer observer) {
		this.observers.remove(observer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "HIT") {
			for(int i = 0; i < this.player.getHands().size(); i ++) {
				if(this.player.getHands().get(i).getActive() == true) {
					this.player.getHands().get(i).setCard(this.deck.drawCard());
					notifyObservers();

					if(this.player.getHands().get(i).judgeState() == GameState.BURST) {
						System.out.println("通った");
						notifyObservers();
						this.player.getHands().get(i).setActive(false);
					}
					if((this.player.getHands().get(i).judgeState() == GameState.BURST) && i == this.player.getHands().size() -1) {
						startDealer();
					}
				}
			}
			// notifyObservers();
		}
		else if(e.getActionCommand() == "STAND") {
			boolean isTurn = true;
			System.out.println("STANDが押された");
			for(int i = 0; i < this.player.getHands().size(); i ++) {
				notifyUnlockButton();
				if(this.player.getHands().get(i).getActive() == true) {
					this.player.getHands().get(i).setActive(false);
				}
				notifyLockButton();
			}

			for(int i =0; i < this.player.getHands().size(); i ++) {
				isTurn  = this.player.getHands().get(i).getActive();
			}
			if(isTurn == false) {
				// dealerの処理を開始するメソッド
				startDealer();
			}
		}
	}

	/***
	 * Dealerのプレイを開始する
	 */
	private void startDealer() {
		this.dealer.setIsPlay(true);
		List<BJHand> handList = this.dealer.getHands();
		BJHand nowHand = handList.get(0);
		while(true) {
			if(nowHand.judgeDealerState() == GameState.BURST) {
				judgeGame();
				notifyObservers();
				break;
			}
			if(nowHand.judgeDealerState() == GameState.BLACK_JACK) {
				judgeGame();
				notifyObservers();
				break;
			}
			if(nowHand.judgeDealerState() == GameState.DELAER_FIN) {
				judgeGame();
				notifyObservers();
				break;
			}
			nowHand.setCard(this.deck.drawCard());
			notifyObservers();
		}
		this.dealer.setIsPlay(false);
	}
	
	/***
	 * プレイヤーの手札ごとに勝敗を決定する
	 * 手札の勝敗を管理する状態を書き換える
	 */
	private void judgeGame() {
		List<BJHand> dealerHandList = this.dealer.getHands();
		List<BJHand> playerHandList = this.player.getHands();
		int dealerScore = dealerHandList.get(0).calcHandScore();
		
		for(int i = 0; i < playerHandList.size(); i++) {
			int playerScore = playerHandList.get(i).calcHandScore();

			if(playerScore > BLACKJACK_NUM) {
				playerHandList.get(i).setGameResult(GameResult.LOSE);
			}
			else if(dealerScore > BLACKJACK_NUM) {
				playerHandList.get(i).setGameResult(GameResult.WIN);
			}
			else if(playerScore == dealerScore) {
				playerHandList.get(i).setGameResult(GameResult.DRAW);
			}
			else if(playerScore < dealerScore) {
				playerHandList.get(i).setGameResult(GameResult.LOSE);
			}
			else if(playerScore > dealerScore) {
				playerHandList.get(i).setGameResult(GameResult.WIN);;
			}
		}
	}

	/***
	 * Observerに通知を行うメソッド
	 * viewにカードの状態を更新することを通知するメソッド
	 * カードの描画処理とゲームの状態を出力を行う
	 */
	private void notifyObservers() {
		for(int i =0; i < this.observers.size(); i ++) {
			this.observers.get(i).update(this);
			this.observers.get(i).showResult(this);
		}
	}

	/***
	 * Observerに通知を行うメソッド
	 * viewにbuttonをグレーダウンさせることを伝えるメソッド
	 */
	private void notifyLockButton() {
		for(int i =0; i < this.observers.size(); i ++) {
			this.observers.get(i).unableButton();
		}
	}

	/***
	 * Observerに通知を行うメソッド
	 * viewにbuttonをグレーダウンさせることを伝えるメソッド
	 */
	private void notifyUnlockButton() {
		for(int i =0; i < this.observers.size(); i ++) {
			this.observers.get(i).ableButton();
		}
	}
}
