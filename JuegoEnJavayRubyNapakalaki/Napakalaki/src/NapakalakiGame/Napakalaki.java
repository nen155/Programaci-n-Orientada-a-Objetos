/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nen155
 */
public class Napakalaki {
    private static final Napakalaki napakalaki =new Napakalaki();
    private Monster currentMonster;
    private CardDealer dealer;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private int indicePlayer;
    private boolean primerJugador;
    

    public static Napakalaki getInstance(){
        return napakalaki;
    }
    

    public CombatResult developCombat(){
        CombatResult r =currentPlayer.combat(currentMonster);
        
        if(r == CombatResult.LoseAndConvert)
        {
            Cultist c = this.dealer.nextCultist();
            CultistPlayer p = new CultistPlayer(this.currentPlayer, c);
            int i = this.players.indexOf(this.currentPlayer);
            this.players.set(i, p);
            this.currentPlayer = p;
        }
        
        
        dealer.giveMonsterBack(currentMonster);
        
        return r;
    }
    public void discardVisibleTreasures(ArrayList<Treasure> treasures){
        for(int i=0;i< treasures.size();i++){
            currentPlayer.discardVisibleTreasure(treasures.get(i));
            dealer.giveTreasureBack(treasures.get(i));
        }
    }
    public void discardHiddenTreasures(ArrayList<Treasure> treasures){
         for(int i=0;i< treasures.size();i++){
            currentPlayer.discardHiddenTreasure(treasures.get(i));
            dealer.giveTreasureBack(treasures.get(i));
        }
    }
    public void makeTreasuresVisible(ArrayList<Treasure> treasures){
        for(int i = 0;i<treasures.size();i++){
            currentPlayer.makeTreasureVisible(treasures.get(i));
        }
    }
    public boolean buyLevels(ArrayList<Treasure> visible,ArrayList<Treasure> hidden){
        return currentPlayer.buyLevels(visible, hidden);
    }
    public void initGame(ArrayList<String> players){
        initPlayers(players);
        dealer.initCards();
        nextTurn();
    }
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    public Monster getCurrentMonster(){
        return currentMonster;
    }
    public boolean nextTurn(){
        if(nextTurnAllowed()){
            currentMonster = dealer.nextMonster();
            currentPlayer = nextPlayer();
            if(currentPlayer.isDead()){
                currentPlayer.initTreasures();
            }    
            return true;
        }
        else
            return false;
    }
    public boolean endOfGame(CombatResult result){
        if(result.WinAndWinGame == CombatResult.WinAndWinGame){
            return true;
        }else
            return false;
        
    }
    private Napakalaki(){
         //currentMonster= new Monster();
         dealer = CardDealer.getInstance();
         players = new ArrayList();
         primerJugador = true;
        //currentPlayer = nextPlayer();
    }
    private void initPlayers(ArrayList<String> names){
        for(int i=0;i<names.size();i++){
            players.add(new Player(names.get(i)));
        }
        currentPlayer = nextPlayer();
    }
    private Player nextPlayer(){
        Random r = new Random();
        Player p=null;
        if(primerJugador){
            indicePlayer = r.nextInt(players.size()-1);
            primerJugador=false;
        }else{
            
            if(indicePlayer <players.size()-1)
                indicePlayer++;
            else
                indicePlayer=0;
        }
            p = players.get(indicePlayer);
        return p;
    }
    private boolean nextTurnAllowed(){
        return currentPlayer.validState();
    }
}
