# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'dice'
require_relative 'treasure_kind'
module Napakalaki
  class Player
    attr_accessor :level,:pendingBadConsequence
    #EXAMEN
      def addHiddenTreasureExam(t)
        @hiddenTreasures << t
      end
    
    #FIN EXAMEN
    
    def oponentLevel(monster)
      return monster.basicValue
    end
    
    def copy_constructor(player)
      @level = player.level
      @pendingBadConsequence = player.pendingBadConsequence
      @visibleTreasures = player.visibleTreasures
      @hiddenTreasures = player.hiddenTreasures
      @dead = player.dead
      
    end
    def shouldConvert
      a = Dice.instance.nextNumber
      should = false
      
      if (a == 6)
        should = true
      end
      return should
    end
    def discardVisibleTreasure(t)
         @visibleTreasures.delete(t)
        if ((@pendingBadConsequence!=nil) && @pendingBadConsequence.isEmpty==false )
            @pendingBadConsequence.subtractVisibleTreasure(t)
        end
        dielfNoTreasures
    end
    def discardHiddenTreasure(t)
        @hiddenTreasures.delete(t)
        if ((@pendingBadConsequence!=nil) && @pendingBadConsequence.isEmpty==false )
            @pendingBadConsequence.subtractHiddenTreasure(t)
        end
        dielfNoTreasures
    end
    def initialize(name)
      @name = name
      @hiddenTreasures = Array.new
      @visibleTreasures = Array.new
      @dead=true
      @level=1
      @pendingBadConsequence = BadConsequence.BadConsequence2("",false);
    end
    def isDead
        @dead
    end
    def getHiddenTreasures
        @hiddenTreasures
    end
    def getVisibleTreasures
        @visibleTreasures
    end
    def combat( m)
        r=nil
        myLevel= getCombatLevel
        monsterLevel = self.oponentLevel(m)
        if(myLevel>monsterLevel)
            applyPrize(m)
            if(@level>=10)
                r=CombatResult::WINANDWINGAME
            else
                r=CombatResult::WIN
            end
        else 
            escape = Dice.instance.nextNumber
            if(escape<5)
                amIDead = m.kills
                if(amIDead==true)
                    die
                      r=CombatResult::LOSEANDDIE
                else
                  if (shouldConvert==true)
                    r=CombatResult::LOSEANDCONVERT
                else
                   bad = m.bc
                   applyBadConsequence(bad)
                   r=CombatResult::LOSE
                  end
                end
            else
                r=CombatResult::LOSEANDESCAPE
            end
       end
          discardNecklacelfVisible
          r      
    end
    def makeTreasureVisible(t)
        canI = canMakeTreasureVisible(t)
        if(canI == true)
            @visibleTreasures <<t
            @hiddenTreasures.delete(t)
        end
    end
    
    def buyLevels(visible,hidden)
        levelsMayBought = computeGoldCoinsValue(visible)
        levelsMayBought+= computeGoldCoinsValue(hidden)
        levls = levelsMayBought/1000
        canI = canIBuyLevels(levls)
        if(canI==true)
            incrementLevels(levls)
        end
        if(visible.size!=0)
        for i in 0..visible.size-1
          @visibleTreasures.delete(visible[i])
        end
        end
        if(hidden.size!=0)
        for i in 0..hidden.size-1
          @hiddenTreasures.delete(hidden[i])
        end
        end
        dealer = CardDealer.instance
        if(visible.size!=0)
        for i in 0..visible.size-1
            t = visible[i]
            dealer.giveTreasureBack(t)
        end
        end
         if(hidden.size!=0)
        for i in 0..hidden.size-1
            t = hidden[i]
            dealer.giveTreasureBack(t)
        end
         end
        canI
    end
    def validState
        if(@pendingBadConsequence.isEmpty ==true && @hiddenTreasures.size<=4)
            true
        else
            false
        end
    end
    
    def hasVisibleTreasures
        if(@visibleTreasures.size>0)
            true
        else
            false
        end
    end
    def initTreasures  
        bringToLife
        d = CardDealer.instance
        treasure = d.nextTreasure 
        @hiddenTreasures << treasure
        number = Dice.instance.nextNumber
        
        if (number > 1)
          treasure = d.nextTreasure 
          @hiddenTreasures << treasure
        end
        if (number == 6)
          treasure = d.nextTreasure 
          @hiddenTreasures << treasure
        end
    end
    def to_s
      "Nombre: "+@name+ " Nivel: "+@level.to_s
    end
    private
    def discardNecklacelfVisible
        if(hasVisibleTreasures==true)
          if(@visibleTreasures.size!=0)
          for i in 0..@visibleTreasures.size-1
            if(@visibleTreasures[i].type==TreasureKind::NECKLACE)
              CardDealer.instance.giveTreasureBack(@visibleTreasures[i])
              discardVisibleTreasure(@visibleTreasures[i])
            end
          end
          end
        end
    end
    def computeGoldCoinsValue(t)
        total=0
        if(t.size!=0)
        for i in 0..t.size-1
          total+=t[i].goldCoins
        end
        end
        return total
    end
    def canMakeTreasureVisible(t) 
      if(t.type ==TreasureKind::ONEHAND)
                cont=0
                if(@visibleTreasures.include?(TreasureKind::BOTHHAND)==true)
                        return false
                end
                if(@visibleTreasures.size!=0)
                  for i in 0..@visibleTreasures.size-1
                      if(t.type == @visibleTreasures[i].type)
                          cont=cont+1
                      end
                  end
                end
                if(cont==2)
                    false
                else
                    true
                end
        else
                if(t.type==TreasureKind::BOTHHAND)
                if(@visibleTreasures.size!=0)
                    for i in 0..@visibleTreasures.size-1
                        if(t.type == @visibleTreasures[i].type || TreasureKind::ONEHAND == @visibleTreasures[i].type)
                            false
                        else
                            true
                        end
                    end
                end
                end
                if(@visibleTreasures.size!=0)
                  for i in 0..@visibleTreasures.size-1
                       if(t.type == @visibleTreasures[i].type)
                           false
                       else
                           true
                       end
                  end
                end
      end
        true
    end
    def bringToLife
        @death = false
    end
    def getCombatLevel
        total=0
        tengoNeck=false
        if(@visibleTreasures.include?(TreasureKind::NECKLACE)==true)
          tengoNeck=true
        else
            tengoNeck=false
        end
        if(@visibleTreasures.size!=0)
        for i in 0..@visibleTreasures.size-1
          if(tengoNeck)
            total += @visibleTreasures[i].maxBonus
          else
            total += @visibleTreasures[i].minBonus
          end
        end
        end
        total+=@level
    end
    def incrementLevels(l)
        if(@level<10)
            @level+=l
        end
    end
    def decrementLevels(l)
        if(@level>1)
            @level-=l
        end
    end
    def dielfNoTreasures
        if(@hiddenTreasures.size==0 && @visibleTreasures.size==0)
            @dead=true
        end
    end
    
    def die
        
        @level=1
        dealer = CardDealer.instance
        if(@visibleTreasures.size!=0)
        for i in 0..@visibleTreasures.size-1
              t = @visibleTreasures[i]
              dealer.giveTreasureBack(t)
        end
        end
        @visibleTreasures.clear
        if(@visibleTreasures.size!=0)
        for i in 0..@hiddenTreasures.size-1
            t = @hiddenTreasures[i]
            dealer.giveTreasureBack(t)
        end
        end
        @hiddenTreasures.clear
        
        dielfNoTreasures
    end
    
    def canIBuyLevels(l)
        if(@level+l<10)
            true
        else
            false
        end
        
    end
    def applyPrize(currentMonster)
        nLevels = currentMonster.getLevelsGained
        incrementLevels(nLevels)
        nTreasures = currentMonster.getTreasuresGained
        if(nTreasures>0)
            d = CardDealer.instance
            if(nTreasures!=0)
            for i in 0..nTreasures-1
               @hiddenTreasures<< d.nextTreasure
            end
            end
        end
        
    end
    def applyBadConsequence(bad)
        nLevels = bad.levels
        decrementLevels(nLevels)
        @pendingBadConsequence= bad.adjustToFitTreasureLists(@visibleTreasures, @hiddenTreasures);
    end
    
    def howManyVisibleTreasures(tKind)
        cont=0
        if(@visibleTreasures.size!=0)
        for i in 0..@visibleTreasures.size-1
            if(@visibleTreasures[i].type==tKind)
                cont+=1
            end
        end
        end
        cont
    end
    
  end
end
