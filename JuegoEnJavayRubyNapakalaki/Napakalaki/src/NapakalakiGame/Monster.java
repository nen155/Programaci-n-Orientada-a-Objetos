/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/**
 *
 * @author nen155
 */
public class Monster implements Card {
    private String name;
    private int combatLevel;
    private BadConsequence bc;
    private Prize prize;
    private int levelChangeAgainstPlayer = 0;
    public String getName() {
        return name;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public Prize getPrize() {
        return prize;
    }
    
    public BadConsequence getBc() {
        return bc;
    }

    public int getLevelsGained(){
        return prize.getLevel();
    }
    public int getTreasuresGained(){
        return prize.getTreasures();
    }
    public boolean kills(){
        return bc.myBadConsequenceisDeath();
    }
    
    
    public Monster(String name, int combatLevel, BadConsequence bc, Prize prize) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.bc = bc;
        this.prize = prize;

        
    }

    public Monster(String name, int level, BadConsequence bc, Prize prize, int levelChangeAgainstPlayer)
    {
        this(name, level, bc, prize);
        this.levelChangeAgainstPlayer = levelChangeAgainstPlayer;
    }
    
    public String toString(){
        return "Name = "+ name+ " CombatLevel = "+ combatLevel+ " BadConsecuence= " + bc.toString()+ " Prize= "+ prize.toString();
    }

    @Override
    public int getBasicValue() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return getCombatLevel();
    }

    @Override
    public int getSpecialValue() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return getCombatLevel() + levelChangeAgainstPlayer;
    }
}
