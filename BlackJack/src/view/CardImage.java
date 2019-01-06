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
import src.GUIGameFlow;

public class CardImage extends JPanel implements Observer{
	private final int CARD_WIDTH = 120;
	private final int CARD_WIDTH_SPAN = 125;
	private final int CARD_HEIGHT = 180;
	private final int CARD_HEIGHT_SPAN = 185;
	private final int PLAYER_SCORE_AREA_X = 0;
	private final int PLAYER_SCORE_AREA_Y = 60;
	private List<Card> cards;
	private List<BJHand> hands;

	@Override
	public void update(GUIGameFlow flow) {
		this.hands =  flow.getPlayerHands();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		int cardPositionX = 0;
		int cardPositionY = 100;

		super.paintComponent(g);
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		
		// カードの表示
		if(this.hands != null) {
			for(int i = 0; i < this.hands.size(); i ++) {
				for(int j = 0; j < this.hands.get(i).getCards().size(); j ++) {
					Image image = getCardImage(this.hands.get(i).getCards().get(j));
					g.drawImage(image, cardPositionX, cardPositionY, CARD_WIDTH, CARD_HEIGHT, this);
					cardPositionX += CARD_WIDTH_SPAN;
				}
				cardPositionY += CARD_HEIGHT_SPAN;
			}
		}
		
		// スコアの表示
		int score = this.hands.get(0).calcHandScore();
		String s = String.valueOf(score);
		g.drawString("PlayerScore:" + s , PLAYER_SCORE_AREA_X , PLAYER_SCORE_AREA_Y);
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
