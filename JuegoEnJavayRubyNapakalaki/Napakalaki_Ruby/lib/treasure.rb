# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'card'
module Napakalaki
  class Treasure < Card
    attr_reader :name,:goldCoins,:minBonus,:maxBonus,:type
    def initialize(n,g,min,max,t)
      @name=n
      @goldCoins=g
      @minBonus=min
      @maxBonus=max
      @type=t
    end
    def to_s
      "Nombre: "+@name+" Monedas: "+@goldCoins.to_s+" MinBonus: "+ @minBonus.to_s+ " MaxBonus: "+ @maxBonus.to_s + " Tipo: "+@type
    end
  end
end
