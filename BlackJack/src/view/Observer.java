package view;

import model.GameState;
import src.GUIGameFlow;

public interface Observer {
	/***
	 * カードの描画を更新するメソッド
	 * @param flow
	 */
	public void update(GUIGameFlow flow) ;
	/***
	 * resultの表示を更新するメソッド
	 * @param flow
	 */
	public void showResult(GUIGameFlow flow);
	/***
	 * ボタンをグレーダウンするメソッド
	 */
	public void unableButton();
}
