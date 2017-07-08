package curve.kd.mmi.curve.jfreechart.refresher;

import java.util.Observable;

public class TurnDateObservable extends Observable{

	public TurnDateObservable(){
		
	}
	
	public void changed(){
		setChanged();
	}
}
