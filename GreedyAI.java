package gj.kalah.player.bartilucci;

import java.util.*;

/* Classe che realizza gli algoritmi di AI.
 * La scelta e' stata di utilizzare un algoritmo Greedy che considera lo stato attuale della board e tutte le possibili
 * mosse legali effettaubili.
 * Attraverso una funzione euristica seleziona la mossa migliore e la restituisce in output come indice della conca.
 * Si tratta di un AI basata su algoritmo deterministico, economico dal punto di vista computazionale.
 */

public class GreedyAI {

	public GreedyAI() { 

	}

	/* Algoritmo Greedy.
	 * Applica l'idea basilare di catturare il maggior numero di pietre possibile in una mossa.
	 * Dato lo stato attuale del gioco (cioe' della board) calcola le possibili mosse che il giocatore puo' eseguire e le memorizza
	 * in un ArrayList di interi. Per ogni elemento di tale lista, crea una copia della board originaria presa in ingresso e la passa
	 * come parametro all'euristica che ha il compito di effettuare la computazione.
	 * Qualora il valore restituito dall'euristica sia superiore a quello ottenuto al passo precedente, aggiorna la variabile maxEval e
	 * l'indice della conca su cui e' stata valutata tale mossa.
	 * Dopo aver considerato tutte le mosse possibili restituisce l'indice (relativo) della conca su cui effettuare la mossa.
	 */

	public int getBestMove(GameBoard gb, int player) {

		int maxEval = Integer.MIN_VALUE;
		int pitIndex = 0;
		int evaluatedMove = 0;

		ArrayList<Integer> moves = new ArrayList<>(gb.possibleMoves(player)); 

		for ( int j = 0; j < moves.size(); j++ ) {
			GameBoard tmp = new GameBoard(gb); 
			evaluatedMove = eval( moves.get(j), tmp, player);
			if ( evaluatedMove >= maxEval ) {
				pitIndex = moves.get(j);	
				maxEval = evaluatedMove;
			}
		}
		return pitIndex;
	}


	/* Questo metodo costituisce l'euristica per il giocatore corrente, serve per decidere la prossima mossa migliore.
	 * Copia la board ricevuta come parametro e su di essa effettua lo spostamento delle pietre.
	 * Qualora ci sia una mossa che permette di avere un turno extra, questa viene selezionata con priorita'.
	 * In caso negativo viene scelta la mossa che genera maggior differenza, a mossa completata, tra il numero di pietre nel Mancala del giocatore 
	 * e quello dell'avversario.
	 * Restituisce in output tale differenza.
	 */

	public int eval( int pitIndex, GameBoard game, int player ){

		GameBoard gmb = new GameBoard( game ) ;
		int storeDif = 0;
		gmb.moveStones( player, pitIndex );

		if ( gmb.isExtraTurn() )
			storeDif = Integer.MAX_VALUE;
		else if ( player == 0 )
			storeDif = gmb.getMancala(0) - gmb.getMancala(1);
		else if ( player == 1 )
			storeDif = gmb.getMancala(1) - gmb.getMancala(0);

		return storeDif;
	}



}


