package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.BJHand;
import model.GameState;
import src.GUIGameFlow;

public class BJbutton extends JPanel implements Observer{
	private final int BUTTON_SIZE_X = 200;
	private final int BUTTON_SIZE_Y = 50;
	private final int BUTTON_TEXT_SIZE = 30;
	private final int TEXT_AREA_X = 50;
	private final int TEXT_AREA_Y = 50;
	private final int FONT_SIZE = 50;
	
	private GUIGameFlow gf;
	private GameState state;
	private JButton hitButton;
	private JButton standButton;
	private List<BJHand> hands;
	
	BJbutton(GUIGameFlow gf){
		this.gf = gf;
		this.state = GameState.STOP;
		this.hitButton = new JButton("HIT");
		this.standButton = new JButton("STAND");
		Font buttonFont = new Font("SansSerif",Font.BOLD, BUTTON_TEXT_SIZE);
		
		// ボタンのサイズを指定
		this.hitButton.setPreferredSize(new Dimension(BUTTON_SIZE_X, BUTTON_SIZE_Y));
		this.standButton.setPreferredSize(new Dimension(BUTTON_SIZE_X, BUTTON_SIZE_Y));
		
		// ボタンのテキストのフォント設定
		this.hitButton.setFont(buttonFont);
		this.standButton.setFont(buttonFont);
		
		add(this.hitButton);
		add(this.standButton);
		
		this.hitButton.addActionListener(gf);
		this.hitButton.setActionCommand("HIT");
		
		this.standButton.addActionListener(gf);
		this.standButton.setActionCommand("STAND");
	}

	/***
	 * modelが変更されたときにObserverから通知され発火するメソッド
	 * 何もすることがないので空白
	 * @param flow
	 */
	@Override
	public void update(GUIGameFlow flow) {
		// TODO 自動生成されたメソッド・スタブ
	}

	/***
	 * modelが変更されたときにObserverから通知され発火するメソッド
	 * Gameの状態を表すテキストを変更する
	 */
	@Override
	public void showResult(GUIGameFlow flow) {
		// TODO 自動生成されたメソッド・スタブ
		this.hands =  flow.getPlayerHands();
		for(int i =0; i < this.hands.size(); i++) {
			if(this.hands.get(i).getActive() == true) {
				this.state = this.hands.get(i).judgeState();
			}
		}
		if(this.state == GameState.BURST) {
			this.hitButton.setEnabled(false);
			this.standButton.setEnabled(false);
		}
		repaint();
	}

	@Override
	public void unableButton() {
		this.hitButton.setEnabled(false);
		this.standButton.setEnabled(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		super.paintComponent(g);
		g.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
		if(this.state == GameState.STOP) {
			g.drawString("STOP", TEXT_AREA_X, TEXT_AREA_Y);			
		}
		if(this.state == GameState.BURST) {
			g.drawString("BURST", TEXT_AREA_X, TEXT_AREA_Y);
		}
	}
}
