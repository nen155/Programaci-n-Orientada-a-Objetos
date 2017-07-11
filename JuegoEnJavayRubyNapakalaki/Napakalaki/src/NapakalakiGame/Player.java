/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;

/**
 *
 * @author nen155
 */
public class Player {

    private boolean dead;
    private String name;
    private int level;
    private ArrayList<Treasure> hiddenTreasures;
    private ArrayList<Treasure> visibleTreasures;
    private BadConsequence pendingBadConsequence;

    public String getName() {
        return name;
    }
    
    protected int getOponentLevel(Monster m){
        return m.getBasicValue();
    }
    
    public Player (Player p){
        dead = p.dead;
        name = p.name;
        level = p.level;
        hiddenTreasures = p.hiddenTreasures;
        visibleTreasures = p.visibleTreasures;
        pendingBadConsequence = p.pendingBadConsequence;
    }
    
    protected boolean shouldConvert(){
        Dice d = Dice.getInstance();
        if( d.nextNumber() == 6 )
            return true;
        return false;
    }
    
    public Player(String name) {
        this.name = name;
        this.dead = true;
        this.level = 1;
        hiddenTreasures = new ArrayList();
        visibleTreasures = new ArrayList();
        pendingBadConsequence = new BadConsequence(null,0,0,0);
    }

    private void bringToLife(){
        dead=false;
    }
    protected int getCombatLevel(){
        int total=0;
        boolean tengoNeck=false;
        int j=0;
        for(;j<visibleTreasures.size() && visibleTreasures.get(j).getType() != TreasureKind.necklace;j++);
        if(j==visibleTreasures.size()){
            tengoNeck = false;
        }
        else
            tengoNeck=true;
        for(int i=0;i<visibleTreasures.size();i++){
            if(tengoNeck)
                total += (visibleTreasures.get(i)).getMaxBonus();
            else
                total += (visibleTreasures.get(i)).getMinBonus();
        }
        total+=level;
        return total;
    }
    private void incrementLevels(int l){
        if(level<10)
            level+=l;
    }
    private void decrementLevels(int l){
        if(level>1)
            level-=l;
    }
    private void setPendingBadConsequence(BadConsequence b){
        pendingBadConsequence = b;
    }
    private void dielfNoTreasures(){
        if(hiddenTreasures.size()==0 && visibleTreasures.size()==0)
            dead=true;
    }
    
    private void die(){
        setLevel(1);
        CardDealer dealer = CardDealer.getInstance();
        for(int i=0;i<visibleTreasures.size();i++){
            Treasure t = visibleTreasures.get(i);
            dealer.giveTreasureBack(t);
        }
        visibleTreasures.clear();
        for(int i=0;i<hiddenTreasures.size();i++){
            Treasure t = hiddenTreasures.get(i);
            dealer.giveTreasureBack(t);
        }
        hiddenTreasures.clear();
        
        dielfNoTreasures();
    }

    private boolean canIBuyLevels(int l){
        if(level+l<10)
            return true;
        else
            return false;
    }
    private void applyPrize(Monster currentMonster){
        int nLevels = currentMonster.getLevelsGained();
        incrementLevels(nLevels);
        int nTreasures = currentMonster.getTreasuresGained();
        if(nTreasures>0){
            CardDealer d = CardDealer.getInstance();
            for(int i=0;i< nTreasures;i++){
               hiddenTreasures.add( d.nextTreasure());
            }
        }
    }
    private void applyBadConsequence(BadConsequence bad){
        int nLevels = getLevels();
        decrementLevels(nLevels);
        pendingBadConsequence= bad.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);
        setPendingBadConsequence(pendingBadConsequence);
    }
    protected int computeGoldCoinsValue(ArrayList<Treasure> t){
        int total=0;
        for(int i=0; i < t.size(); i++)
            total += t.get(i).getGoldCoins();
        return (int)total;
    }
    private void discardNecklacelfVisible(){
        if(this.hasVisibleTreasures())
           for (int i=0; i< visibleTreasures.size();i++)
            if(visibleTreasures.get(i).getType()==TreasureKind.necklace){
                CardDealer.getInstance().giveTreasureBack(visibleTreasures.get(i));
                discardVisibleTreasure(visibleTreasures.get(i));
            }
    }
    private boolean canMakeTreasureVisible(Treasure t) 
    {
            if(t.getType()==TreasureKind.oneHand){
                int cont=0;
                for(int i=0; i < visibleTreasures.size();i++){
                    if(visibleTreasures.get(i).getType() == TreasureKind.bothHand)
                        return false;
                    if(t.getType()== visibleTreasures.get(i).getType())
                        cont++;
                }
                if(cont==2)
                    return false;
                else
                    return true;
                    
            }
            else{
                if(t.getType()==TreasureKind.bothHand)
                {
                    for(int i=0; i < visibleTreasures.size();i++){
                        if(t.getType() == visibleTreasures.get(i).getType() || TreasureKind.oneHand == visibleTreasures.get(i).getType()){
                            return false;
                        }
                        else
                            return true;
                    }
                }
                    
                  for(int i=0; i < visibleTreasures.size();i++){
                       if(t.getType() == visibleTreasures.get(i).getType())
                           return false;
                       else
                           return true;
                  }
             }
            
        return true;
    }
    public void discardVisibleTreasure(Treasure t){
         visibleTreasures.remove(t);
        if ((pendingBadConsequence!=null) && (!pendingBadConsequence.isEmpty()) )
            pendingBadConsequence.subtractVisibleTreasure(t);
        this.dielfNoTreasures();
    }
    public void discardHiddenTreasure(Treasure t){
        hiddenTreasures.remove(t);
        if ((pendingBadConsequence!=null) && (!pendingBadConsequence.isEmpty()) )
            pendingBadConsequence.subtractHiddenTreasure(t);
        this.dielfNoTreasures();
    }
    public void initTreasures(){
        CardDealer dealer = CardDealer.getInstance();
        Dice dice = Dice.getInstance();
        this.bringToLife();
        Treasure treasure = dealer.nextTreasure();
        hiddenTreasures.add(treasure);
        int number = dice.nextNumber();
        if(number > 1)
        {
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
        if (number == 6)
        {
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
    }
    private int howManyVisibleTreasures(TreasureKind tKind){
        int cont=0;
        for(int i=0;i<visibleTreasures.size();i++){
            if(visibleTreasures.get(i).getType()==tKind)
                cont++;
        }
        return cont;
    }
    public boolean isDead(){
        return dead;
    }
    public ArrayList<Treasure> getHiddenTreasures(){
      return hiddenTreasures;  
    }
    public ArrayList<Treasure> getVisibleTreasures(){
        return visibleTreasures;
    }
    public CombatResult combat(Monster m){
        int myLevel= getCombatLevel();
        int monsterLevel = this.getOponentLevel(m);
        CombatResult r=null;
        if(myLevel>monsterLevel){
            applyPrize(m);
            if(level>=10){
                r=CombatResult.WinAndWinGame;
            }else
                r=CombatResult.Win;
        }else
        {
            Dice d = Dice.getInstance();
            int escape = d.nextNumber();
            if(escape<5){
                boolean amIDead = m.kills();
                if(amIDead){
                    die();
                    r=CombatResult.LoseAndDie;
                } else if(shouldConvert()){
                    
                    r = CombatResult.LoseAndConvert; 
                    
                }
                
                
                else
                {
                    BadConsequence bad = m.getBc();
                    applyBadConsequence(bad);
                    r=CombatResult.Lose;
                }
            }else
            {
                r=CombatResult.LoseAndEscape;
            }
        }
        discardNecklacelfVisible();
        return r;
    }

    public void makeTreasureVisible(Treasure t){
        boolean canI = canMakeTreasureVisible(t);
        if(canI){
            visibleTreasures.add(t);
            hiddenTreasures.remove(t);
        }
    }

    public boolean buyLevels(ArrayList<Treasure> visible,ArrayList<Treasure> hidden){
        int levelsMayBought = computeGoldCoinsValue(visible);
        levelsMayBought+= computeGoldCoinsValue(hidden);
        int levls = levelsMayBought/1000;
        boolean canI = canIBuyLevels(levls);
        if(canI){
            incrementLevels(levls);
        }
        visibleTreasures.removeAll(visible);
        hiddenTreasures.removeAll(hidden);
        CardDealer dealer = CardDealer.getInstance();
        for(int i=0;i<visible.size();i++){
            Treasure t = visible.get(i);
            dealer.giveTreasureBack(t);
        }
        for(int i=0;i<hidden.size();i++){
            Treasure t = hidden.get(i);
            dealer.giveTreasureBack(t);
        }
        return canI;
    }
    public boolean validState(){
        if(pendingBadConsequence.isEmpty()==true && hiddenTreasures.size()<=4){
            return true;
        }
        else
            return false;
    }
    public boolean hasVisibleTreasures(){
        if(visibleTreasures.size()>0)
            return true;
        else
            return false;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getLevels(){
        return level;
    }
    public String toString(){
        return name;
    }
}
