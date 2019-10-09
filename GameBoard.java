package gj.kalah.player.bartilucci;

import java.util.*;

public class GameBoard {
	
	private static final int size = 14; // Dimensione della board

	private int[] board = new int[size];
	private int mancala0;
	private int mancala1;
	private boolean extraTurn = false;


	public GameBoard() {

		for (int i = 0; i < size; i++) {
			board[i] = 4;
		}

		board[size - 1] = 0; 		// Inizializzo a 0 il contenuto di entrambi i Mancalas
		board[size / 2 - 1] = 0;
		mancala0 = (size / 2) - 1;
		mancala1 = size - 1;
	}


	// Costruttore di copia : prende in ingresso una board e la copia

	public GameBoard( GameBoard gb ) {

		for ( int i = 0; i < size; i++ ) {
			board[i] = gb.board[i];
		}
		this.mancala0 = (size / 2) - 1;
		this.mancala1 = size - 1;
	}


	/* Metodo che effettua gli spostamenti delle pietre sulla board.
	 * Prende in ingresso il numero del giocatore e l'indice relativo della conca (0..5) rispetto al giocatore che sta effettuando
	 * la mossa. Converte l'indice relativo di partenza in indice assoluto. 
	 * Chiama il metodo pitSew per calcolare la posizione della conca su cui depositare la pietra.
	 * Memorizza la posizione della conca su cui e' stata depositata l'ultima pietra, qualora fosse precedentemente vuota, 
	 * tramite wasEmptyPit, ruba le pietre della conca avversaria sul lato opposto.    
	 * Qualora invece l'ultima pietra sia stata depositata nel proprio Mancala rende vera la variabile extraTurn
	 */


	public void moveStones ( int player, int index ) {

		int position = getPosition( player, index );
		int stones = board[position];
		board[position] = 0;

		for ( int i = 1; i <= stones; i++) {
			int pitIndex = pitSew( player, index, i );
			board[pitIndex] = board[pitIndex] + 1;	// Incremento di una pietra
		}

		int lastPit = pitSew( player, index, stones );
		wasEmptyPit( player, lastPit );
		extraTurn = lastInMancala( player, lastPit );	
	}


	/* Metodo che calcola la posizione assoluta della conca successiva su cui seminare la pietra.
	 * Prende in ingresso il numero del giocatore, la positione relativa della conca da cui seminare e l'offset rispetto alla conca
	 * (ovvero l'i-esima pietra da seminare).
	 * Esclude, attraverso le operazioni modulo la possibilita' di seminare nel Mancala dell'avversario.
	 */

	private int pitSew( int player, int position, int i ) {

		if ( player == 0 )
			return ( position + i ) % (size - 1) ;
		else
			return ( ( (position + i ) % (size - 1) ) + ( size / 2) ) % size; 	
	}



	/* Metodo che controlla se l'ultima pietra depositata e' stata posizonata in una conca precedentemente vuota. 
	 * Nel caso fosse cosi' viene trasferito nel Mancala del giocatore il numero di pietre avversarie
	 * presenti sul lato opposto della scacchiera sommato all'ultima pietra stessa.
	 * Utilizza il metodo checkBoard nel controllo per verificare che l'ultima pietra sia stata depositata effettivamente
	 * in una conca del giocatore corrente e non in una dell'avversario.
	 */

	private void wasEmptyPit ( int player, int index ) {

		int position = index; 
		int stones = board[position] - 1;

		if ( checkBoard( player, position ) && stones == 0) {
			board[position] = 0; 		// azzero la mia conca
			int stolenStones = board[( size - 2 - position )];		// pietre nella conca dell'avversario
			board[( size - 2 - position )] = 0;		 // azzero la conca avversaria
			board[getMancalaInd( player )] = board[getMancalaInd( player )] + 1 + stolenStones;	
		}
	}


	/* Metodo di verifica che controlla che la conca precedentemente vuota sia appartenente al lato di board 
	 * del giocatore stesso che ha effettuato la mossa.
	 */

	private boolean checkBoard ( int player, int index ) {

		int first = ( size / 2 ) * player;
		int last = getMancalaInd( player );
		if ( index >= first && index < last)
			return true;
		else 
			return false;
	}


	// Restituisce l'indice del mancala del giocatore corrente

	private int getMancalaInd( int player ) { 

		if ( player == 0 )
			return mancala0;
		else
			return mancala1;
	}

	// Restituisce il contenuto del mancala del giocatore corrente

	public int getMancala( int player ) { 
		return board[getMancalaInd( player )];
	}


	// Metodo che controlla se l'ultima pietra è finita nel mio mancala.

	private boolean lastInMancala( int player, int lastIndex ) {
		return lastIndex == getMancalaInd( player );	

	}

	// Metodo che restituisce l'indice assoluto (0..13) di un elemento della scacchiera,

	private int getPosition( int player, int index ) {
		return ( size / 2 ) * player + index;
	}


	/* Metodo che restituisce un ArrayList di interi, dove il contenuto di ogni elemento rappresenta l'indice di una conca
	 * che ha almeno una pietra, ovvero una possibile mossa eseguibile.
	 * Si noti che l'eventuale inserimento nell'ArrayList si limita soltanto alle conche e non ai Mancalas.
	 */

	public ArrayList<Integer> possibleMoves ( int player ) {

		ArrayList<Integer> moves = new ArrayList<>();
		int mancala = getMancalaInd( player );

		for ( int i = ( size / 2 ) * player;i < mancala; i++ ) {
			if (board[i] != 0) 
				moves.add( i - ( size / 2 ) * player);
		}
		return moves;	
	}

	
	
	// Metodi di Utilita'

	public int getSize() {
		return size;
	}


	public boolean isExtraTurn() {
		return extraTurn;
	}


	public void setExtraTurn(boolean extraTurn) {
		this.extraTurn = extraTurn;
	}




}
