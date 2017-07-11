#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "player"
require_relative "cultist"
require_relative "cultist_player"
require_relative "treasure"
class ExamenP5
  def self.principal()
    p1 = Player.new("Player1")
    p2 = Player.new("Player2")
    carta = Cultist.new(2)
    p1 = CultistPlayer.new(p1,c)
    t1 = Treasure.new("n1",100,2,5,TreasureKind::ONEHAND);
    t2 = Treasure.new("n2",100,2,5,TreasureKind::HELMET);
    t3 = Treasure.new("n3",100,2,5,TreasureKind::ONEHAND);
    t4 = Treasure.new("n4",100,2,5,TreasureKind::HELMET);
    t5 = Treasure.new("n5",100,2,5,TreasureKind::BOTHHAND);
    arrayTrea = Array[t1,t2,t3,t4,t5]
   p1.addHiddenTreasureExam(t1)
   p1.addHiddenTreasureExam(t2)
   p1.addHiddenTreasureExam(t3)
   p1.addHiddenTreasureExam(t4)
   p1.addHiddenTreasureExam(t5)
   
    
    p2.addHiddenTreasureExam(t1)
    p2.addHiddenTreasureExam(t2)
    p2.addHiddenTreasureExam(t3)
    p2.addHiddenTreasureExam(t4)
    p2.addHiddenTreasureExam(t5)
    
    p1.makeTreasuresVisible(arrayTrea)
    
    p2.makeTreasuresVisible(arrayTrea)
    
    puts p1.isDead.to_s
    puts p1.getCombatLevel.to_s
    puts p1.levels.to_s
    puts p1.pendingBadConsequence.to_s
    for i in 0..p1.getVisibleTreasures.size-1
      puts  p1.getVisibleTreasures[i].to_s
    end
    for i in 0..p1.getHiddenTreasures.size-1
      puts  p1.getHiddenTreasures[i].to_s
    end
    
    puts p2.isDead.to_s
    puts p2.getCombatLevel.to_s
    puts p2.levels.to_s
    puts p2.pendingBadConsequence.to_s
    for i in 0..p2.getVisibleTreasures.size-1
      puts  p2.getVisibleTreasures[i].to_s
    end
    for i in 0..p1.getHiddenTreasures.size-1
      puts  p2.getHiddenTreasures[i].to_s
    end
    
    p2 = CultistPlayer.new(p2,c)
    puts p1.getCombatLevel.to_s
    puts p2.getCombatLevel.to_s
    
    svh =Array [TreasureKind::ONEHAND,TreasureKind::SHOE,TreasureKind::SHOE,TreasureKind::BOTHHAND,
    TreasureKind::ARMOR,TreasureKind::ONEHAND,TreasureKind::BOTHHAND]
  
    malrollo = BadConsequence.BadConsequence4("Des",5,svh,svh)
    
    t7 = Treasure.new("n5",100,2,5,TreasureKind::SHOE);
   
    v=Array [t1,t7]
    h=Array [t7,t2]
    malrollo.adjustToFitTreasureLists(v,h)
    
    puts malrollo.to_s
  end
end
