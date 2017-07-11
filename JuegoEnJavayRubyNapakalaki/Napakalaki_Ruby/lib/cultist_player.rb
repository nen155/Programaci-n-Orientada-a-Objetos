# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Napakalaki
  class CultistPlayer < Player
    attr_accessor :myCultistCard
    @@totalCultistPlayers = 0
    def initialize(player, cultist)
      super(player.name)
      copy_constructor(player)
      @myCultistCard = cultist
      @@totalCultistPlayers += 1
    end
    
    def combatLevel
      lvl = super
      lvl += @myCultistCard.specialValue
      return lvl
    end
    
    def computeGoldCoinsValue(treasures)
      return super.computeGoldCoinsValue(treasures) * 2
    end
    
    def self.totalCultistPlayers
      return @@totalCultistPlayers
    end
    
    def shouldConvert
      return false
    end
    
    def oponentLevel(monster)
      return monster.specialValue
    end
    
  end
end
