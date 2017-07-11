# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'card'
module Napakalaki
  class Cultist < Card
    attr_reader :name, :gainedLevels
    def initialize(levels)
      @name = "Sectario"
      @gainedLevels = levels
    end
    
    def basicValue
      return @gainedLevels
    end
    
    def specialValue
      return basicValue * CultistPlayer.totalCultistPlayers
    end
    
  end
end
