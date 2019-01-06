package view;

import model.GameState;
import src.GUIGameFlow;

public interface Observer {
	public void update(GUIGameFlow flow) ;
	public void showResult(GUIGameFlow flow);
}
