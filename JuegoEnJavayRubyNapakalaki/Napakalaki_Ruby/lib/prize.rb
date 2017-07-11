# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Napakalaki
  class Prize
    attr_reader :treasures,:level
    def initialize(tr,lvl)
      @treasures = tr
      @level = lvl
    end
    def to_s
      "Treasures = " + treasures.to_s + " levels = " + level.to_s;
    end
  end
end
