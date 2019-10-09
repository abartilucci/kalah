**Purpose** : Java Project of an AI based player for the game Kalah for the exam of Java Programming of University of Florence Computer Science graduate course.



# Le regole del gioco


![Table](https://imagizer.imageshack.com/img921/6103/njGNOW.png)


![Table2](https://imagizer.imageshack.com/img921/2261/62fXno.png)


 - _**Accumulare più pietre possibili nel proprio mancala**_
    - Si vince quando se ne accumulano più di 24
    - Possibilità di pareggio   
 - _**Un solo tipo di mossa**_
    - Prendere le pietre da una delle proprie conche e distribuirle in senso antiorario nelle conche adiacenti (incluso il **proprio** mancala)
    - Possibilità di ripetere la mossa
    - Possibilità di "rubare" le pietre da una conca dell'avversario
 - _**Termine del gioco**_
    - Tutte le conche di un giocatore sono vuote
        - Vince il giocatore che ha accumulato più di 24 pietre
        - Pareggio se entrambi i giocatori hanno accumulato esattamente 24 pietre
        
        
![Move](https://imagizer.imageshack.com/img923/9641/St7ftf.png)



![Move1](https://imagizer.imageshack.com/img922/7632/uCmTTx.png)



![Move2](https://imagizer.imageshack.com/img921/829/xpHR6W.png)



![Move3](https://imagizer.imageshack.com/img922/3120/Wre1Vo.png)


![Move4](https://imagizer.imageshack.com/img924/7336/aKObvR.png)


![Move5](https://imagizer.imageshack.com/img924/3074/cJAJX9.png)


![Move6](https://imagizer.imageshack.com/img923/6846/6as1If.png)


![Move7](https://imagizer.imageshack.com/img923/2308/iRQPIR.png)


![Move8](https://imagizer.imageshack.com/img924/3504/zvCeyu.png)


![Move9](https://imagizer.imageshack.com/img923/4341/LdTPTq.png)


![Move10](https://imagizer.imageshack.com/img921/8565/S4MFBC.png)


![Move11](https://imagizer.imageshack.com/img923/281/OsycTv.png)


![Move12](https://imagizer.imageshack.com/img922/5128/OAk69J.png)


![Move13](https://imagizer.imageshack.com/img923/5530/Dtmi3D.png)


        
# Il software

## L'interfaccia Player

  - _public void start(boolean isFirst);_
  
  Comunica al giocatore, all'inizio di ciascuna partita, se gioca per primo (ovvero se è il giocatore A)
  
  - _public int move();_
  
  Restituisce indice di propria conca da cui prelevare e distribuire le pietre
  Conche numerate da 0 a 5 in senso antiorario. Conca non deve essere vuota
  
  - _public void tellMove(int move);_
  
  Comunica mossa dell’avversario, stesse specifiche di move

## Il giocatore

_Classe <Cognome>Player_ che implementa interfaccia _Player_
  - Inclusa in pacchetto gj.kalah.player.<cognome>
  - Tutte le classi che usa devono essere in questo pacchetto
 
Un esempio: il giocatore da console
Classe _ConsolePlayer_ inclusa in pacchetto _gj.kalah.player.console_
Consente di giocare digitando le mosse da tastiera

## Il ciclo di gioco
- Il GameManager
  - Invoca il costruttore senza parametri dei due giocatori, _**una**_ volta sola per ogni insieme di partite.
  - Per ogni partita
      - Invoca il metodo start dei due giocatori con il giusto parametro
      - Finché la partita non finisce, a turno
          - Invoca il metodo move e controlla la correttezza della mossa
          - Comunica la mossa all’avversario mediante il metodo tellMove        
- Ogni irregolarità di un giocatore genera un’eccezione che fa terminare la partita e assegna la vittoria finale all’avversario


## Per eseguire un gruppo di partite
Premere _Run_
- La partita ha inizio, viene mostrata la tavola da gioco iniziale e al giocatore rosso viene chiesto di inserire la prima mossa
- Per eseguire n partite tra Cognome1 e Cognome2 senza messaggi sul video, gli argomenti sono
        _n Cognome1 Cognome2 false_
