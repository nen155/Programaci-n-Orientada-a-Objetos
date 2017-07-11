#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
#ultima

require_relative 'treasure_kind'
require_relative 'card_dealer'
require_relative 'player'
module Napakalaki
  
  require 'singleton'
  class Napakalaki
    include Singleton
     

    def developCombat
      r=@currentPlayer.combat(@currentMonster)
      
      if (r == CombatResult::LOSEANDCONVERT)
        c = @dealer.nextCultist
        cp = CultistPlayer.new(@currentplayer, c)
        @players.delete(@currentPlayer)
        @players << cp
        @currentPlayer = cp
      end
      
      
        CardDealer.instance.giveMonsterBack(@currentMonster)
      r
    end
    
    def discardVisibleTreasures(treasures)
      if(treasures.size!=0)
      for i in 0..treasures.size-1
            @currentPlayer.discardVisibleTreasure(treasures[i])
            CardDealer.instance.giveTreasureBack(treasures[i])
      end
      end
    end
    def discardHiddenTreasures(treasures)
      if(treasures.size!=0)
      for i in 0..treasures.size-1
            @currentPlayer.discardHiddenTreasure(treasures[i])
            CardDealer.instance.giveTreasureBack(treasures[i])
      end
      end
    end
    def makeTreasuresVisible(treasures)
      if(treasures.size!=0)
      for i in 0..treasures.size-1
            @currentPlayer.makeTreasureVisible(treasures[i]);
      end
      end
    end
    def buyLevels(visible,hidden)
      @currentPlayer.buyLevels(visible,hidden)
    end
    def initGame(players)
      initPlayers(players)
      CardDealer.instance.initCards
      nextTurn
    end
    def getCurrentPlayer
      @currentPlayer
    end
    def getCurrentMonster
      @currentMonster
    end
    def nextTurn
      if (nextTurnAllowed==true)
            @currentMonster = CardDealer.instance.nextMonster
            @currentPlayer = nextPlayer
            if(@currentPlayer.isDead==true)
                @currentPlayer.initTreasures
            end
            true
        else
            false
      end
    end
    def endOfGame(result)
      if(result == CombatResult::WINAANDWINGAME)
           true;
        else
            false;
      end
        
    end
    private
    def initialize
      @primerJugador=true
      @players=Array.new
      @indicePlayer=0
    end
     
     def initPlayers(names)
       @currentPlayer=Player.new(names[0])
       for i in 0..names.size-1
            @players<<Player.new(names[i])
       end
     end
     
     def nextPlayer
        p=nil
        if(@primerJugador==true)
            @indicePlayer = rand(@players.size-1)
            @primerJugador=false
        else
            
            if(@indicePlayer <@players.size-1)
                @indicePlayer=@indicePlayer+1
            else
                @indicePlayer=0
            end
        end
            p = @players[@indicePlayer]
        p
     end
     
     def nextTurnAllowed
       @currentPlayer.validState
     end
    
  end
end
