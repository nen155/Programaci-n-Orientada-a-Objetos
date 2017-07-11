#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Napakalaki
  class Monster < Card
    attr_reader :name, :combatLevel, :bc, :prize, :levelCP
    def initialize(name, combatLevel, bc, prize, level = 0)
      @name = name
      @combatLevel = combatLevel
      @bc = bc
      @prize = prize
      @levelCP = level
    end
    def kills
      @bc.myBadConsequenceIsDeath
    end
    def getLevelsGained
        @prize.level
    end
    def getTreasuresGained
        @prize.treasures
    end
    def to_s
      "Name = "+ name+ " CombatLevel = "+ combatLevel.to_s+ " BadConsecuence= " + bc.to_s+ " Prize= "+ prize.to_s;
    end
    
    def basicvalue
      return getLevelsGained
    end
    def specialValue
      return getLevelsGained + @levelCP
    end
    
  end
end
