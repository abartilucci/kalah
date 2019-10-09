package gj.kalah.player.bartilucci;

import gj.kalah.player.*;

public class BartilucciPlayer implements Player {
	
	private static GameBoard gbd;
	private int myplayer;
	private GreedyAI greedy;

	public void start( boolean isFirst ) {
		gbd = new GameBoard();
		if ( isFirst ) {
			System.out.println("Bartilucci is Player Red :  first to play");
			myplayer = 0;
		} else {
			System.out.println("Bartilucci is Player Blue :  second to play");
			myplayer = 1;
		}
	}

	public int move() {
		greedy = new GreedyAI();
		int myMove = greedy.getBestMove( gbd, myplayer) ;
		gbd.moveStones( myplayer, myMove );
		return myMove;
	}


	public void tellMove(int move) {
		if ( myplayer == 0 )
			gbd.moveStones( 1, move );
		else
			gbd.moveStones( 0, move );
	}

}
