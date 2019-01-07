package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.BJHand;
import model.Card;
import model.GameResult;
import src.GUIGameFlow;

public class CardImage extends JPanel implements Observer{
	private final int CARD_WIDTH = 120;
	private final int CARD_WIDTH_SPAN = 125;
	private final int CARD_HEIGHT = 180;
	private final int CARD_HEIGHT_SPAN = 185;
	private final int DEALER_SCORE_AREA_X = 0;
	private final int DEALER_SCORE_AREA_Y = 60;
	private final int PLAYER_SCORE_AREA_X = 0;
	private final int PLAYER_SCORE_AREA_Y = 350;
	private final int PLAYER_RESULT_AREA_X = 0;
	private final int FONT_SIZE = 50;
	private List<BJHand> playerHands;
	private List<BJHand> dealerHand;
	private boolean dealerIsPlay = false;

	/***
	 * modelの状態が変わったときにObserverから通知され起動発火するメソッド
	 * paintの再描画処理を行う
	 */
	@Override
	public void update(GUIGameFlow flow) {
		this.playerHands =  flow.getPlayerHands();
		this.dealerHand = flow.getDealerHands();
		this.dealerIsPlay = flow.getDealerIsPlay();
		repaint();
	}

	/***
	 * modelの状態が変わったときにObserverから通知され、発火するメソッド
	 * ここではやることがないため中身を書いていない
	 */
	@Override
	public void showResult(GUIGameFlow flow) {
	}
	
	/***
	 * modelの状態が変わったときにObserverから通知され、発火するメソッド
	 * ここではやることがないため中身を書いていない
	 */
	@Override
	public void unableButton() {
		// TODO 自動生成されたメソッド・スタブ
	}
	
	/***
	 * modelの状態が変わったときにObserverから通知され、発火するメソッド
	 * ここではやることがないため中身を書いていない
	 */
	@Override
	public void ableButton() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	protected void paintComponent(Graphics g) {
//		int dealerCardPositionX = 0;
//		int dealerCardPositionY = 100;

		super.paintComponent(g);
		g.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));

		// ディーラーのカードの表示
		// 最初に2枚配られた時の描画処理
		if(this.dealerIsPlay == false) {
			int dealerCardPositionX = 0;
			int dealerCardPositionY = 100;
			
			if(this.dealerHand != null) {
				Image image = getCardImage(this.dealerHand.get(0).getCards().get(0));
				g.drawImage(image,dealerCardPositionX, dealerCardPositionY, CARD_WIDTH, CARD_HEIGHT, this);
				dealerCardPositionX += CARD_WIDTH_SPAN;
			}
		}
		// dealerがプレイしたときのカードの描画処理
		else {
			int dealerCardPositionX = 0;
			int dealerCardPositionY = 100;
			
			for(int i = 0; i < this.dealerHand.get(0).getCards().size(); i ++) {
				if(this.dealerHand != null) {
					Image image = getCardImage(this.dealerHand.get(0).getCards().get(i));
					g.drawImage(image,dealerCardPositionX, dealerCardPositionY, CARD_WIDTH, CARD_HEIGHT, this);
					dealerCardPositionX += CARD_WIDTH_SPAN;
				}
			}
		}

		// ディーラーのスコア表示
		// 最初に2枚配られたときのスコア表示
		if(this.dealerIsPlay == false) {
			g.drawString("DealerScore: ??" , DEALER_SCORE_AREA_X , DEALER_SCORE_AREA_Y);			
		}
		// dealerがプレイした後のスコア表示
		else {
			int dealerScore = this.dealerHand.get(0).calcHandScore();
			String ds = String.valueOf(dealerScore);
			g.drawString("DealerScore:" + ds , DEALER_SCORE_AREA_X , DEALER_SCORE_AREA_Y);
		}

		// プレイヤーのカード表示
		int playerCardPositionX = 0;
		int playerCardPositionY = 400;
		int playerResutPositionY = playerCardPositionY + 250;

		if(this.playerHands != null) {
			for(int i = 0; i < this.playerHands.size(); i ++) {
				for(int j = 0; j < this.playerHands.get(i).getCards().size(); j ++) {
					Image image = getCardImage(this.playerHands.get(i).getCards().get(j));
					g.drawImage(image, playerCardPositionX, playerCardPositionY, CARD_WIDTH, CARD_HEIGHT, this);
					playerCardPositionX += CARD_WIDTH_SPAN;
				}
				
				// 勝敗の表示
				if(this.playerHands.get(i).getGameResult() == GameResult.LOSE) {
					g.drawString("YOU LOSE", PLAYER_RESULT_AREA_X, playerResutPositionY);
					playerCardPositionY += CARD_HEIGHT_SPAN;
				}
				else if(this.playerHands.get(i).getGameResult() == GameResult.WIN) {
					g.drawString("YOU WIN", PLAYER_RESULT_AREA_X, playerResutPositionY);
					playerCardPositionY += CARD_HEIGHT_SPAN;
				}
				else if(this.playerHands.get(i).getGameResult() == GameResult.DRAW) {
					g.drawString("DRAW", PLAYER_RESULT_AREA_X,playerResutPositionY);
					playerCardPositionY += CARD_HEIGHT_SPAN;
				}
				else {
					g.drawString("---", PLAYER_RESULT_AREA_X,playerResutPositionY);
					playerCardPositionY += CARD_HEIGHT_SPAN;
				}

				// スコアの表示
				int playerScore = this.playerHands.get(0).calcHandScore();
				String ps = String.valueOf(playerScore);
				g.drawString("PlayerScore:" + ps , PLAYER_SCORE_AREA_X , PLAYER_SCORE_AREA_Y);
			}
		}
	}

	/***
	 * 引数に指定したcardから対応するカードのイメージを取得する
	 * 返り値としてImage型のデータを返す
	 * @param card
	 * @return
	 */
	private  Image getCardImage(Card card) {
		try {
			String path = String.format("img/card_%s_%02d.png", card.getSuit(), card.getNum());
			return ImageIO.read(new File(path));
		}
		catch(IOException e) {
			return null;
		}
	}
}
