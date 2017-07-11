/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;

/**
 *
 * @author Julian
 */
public class CultistPlayer extends Player {

    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;
    
    public CultistPlayer(Player p, Cultist c){
       super(p);
       myCultistCard = c;
       totalCultistPlayers++;
    }
    
    protected int getCombatLevel(){
        return super.getCombatLevel() + myCultistCard.getSpecialValue();
    }
    
    @Override
    protected boolean shouldConvert(){
        return false;
    }
    
    @Override
    protected int getOponentLevel(Monster m){
        return m.getSpecialValue();
    }
    
    @Override
    protected int computeGoldCoinsValue(ArrayList<Treasure> t){
       return super.computeGoldCoinsValue(t)*2;
    }
    
    public static int getTotalCultistPlayers(){
        return totalCultistPlayers;
    }
    
    public Cultist getCultistCard()
    {
        return myCultistCard;
    }
}
