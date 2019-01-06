package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.BJHand;
import model.Deck;
import model.GameState;
import model.Player;
import view.Observer;

public class GUIGameFlow implements ActionListener{
	private Deck deck;
	private Player player;
	private Player dealer;
	private final int INIT_CARD_NUM = 2;
	private ArrayList<Observer> observers = new ArrayList<Observer>();

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

	/***
	 * viewにmodelが変更されたことを通知するメソッド
	 * Observerのupdateメソッドを内部で呼び出すことで処理を開始する
	 */
	public void notifyObservers() {
		Iterator<Observer> iterator = observers.iterator();
		while(iterator.hasNext()) {
			Observer observer = iterator.next();
			observer.update(this);
			observer.showResult(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "HIT") {
			for(int i = 0; i < this.player.getHands().size(); i ++) {
				if(this.player.getHands().get(i).getActive() == true) {
					this.player.getHands().get(i).setCard(this.deck.drawCard());
					
					if(this.player.getHands().get(i).judgeState() == GameState.BURST) {
						System.out.println("通った");
					}
				}
			}
			notifyObservers();
		}
		else if(e.getActionCommand() == "STAND") {
			System.out.println("STANDが押された");
			for(int i = 0; i < this.player.getHands().size(); i ++) {
				if(this.player.getHands().get(i).getActive() == true) {
					this.player.getHands().get(i).setActive(false);
				}
			}
		}
	}
}
