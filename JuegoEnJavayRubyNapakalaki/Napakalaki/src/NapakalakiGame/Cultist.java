/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/**
 *
 * @author Julian
 */
public class Cultist implements Card {

    private String name;
    private int gainedLevels;
    
    public Cultist(String name, int gainedLevels){
        this.name = name;
        this.gainedLevels = gainedLevels;
    }
    
    @Override
    public int getBasicValue() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return gainedLevels;
    }

    @Override
    public int getSpecialValue() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return getBasicValue() * CultistPlayer.getTotalCultistPlayers();
    }
    
}
