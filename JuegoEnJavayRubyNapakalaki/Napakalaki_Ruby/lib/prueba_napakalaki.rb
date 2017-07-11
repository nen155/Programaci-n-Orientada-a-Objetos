# Emilio Chica Jimenez y Julián Torices Hernández
#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "prize.rb"
require_relative "monster.rb"
require_relative "treasure_kind.rb"
require_relative "bad_consequence.rb"
module Napakalaki
module PruebaNapakalaki
  numeroMonstruos=18
  monsters = Array.new
  #Niveles que da el monstruo
  levels =Array [ 1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1 ]
  #Tesoros que da el monstruo
  treasures =Array [ 2,1,1,4,3,2,1,4,1,2,2,2,1,3,4,2,1,1,1 ]
  #Nivel del monstruo
  monsterLevel =Array[8,2,2,14,10,6,2,13,2,8,4,1,3,12,1,8,5,20,20]
  #Niveles que pierces
  loseLevels =Array [
            0,0,0,0,0,0,0,5,2,0,2,0,3,0,0,2,0,2,3
  ]
  #Tesoros escondidos
  hTreasures =Array[
            0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0
  ]
  #Si te mata o no el monstruo
  muertos =Array [
        false,false,false,false,false,false,false,false,false,true,false,false,false,true,true,false,false,false,false
  ]
  #Numero tesoros visibles
  vTreasures =Array [
            0,0,0,0,6,0,0,3,0,0,0,0,0,0,0,0,0,5,0
  ]
  nombres =Array ["3 Byakhees de bonanza","Chibithulhu",
          "El sopor de Dunwich" , "´ Angeles de la noche ibicenca",
          "El gorr´on en el umbral", "H.P. Munchcraft",
          "Bichgooth","El rey de rosa","La que redacta en las tinieblas",
          "Los hondos","Semillas Cthulhu","Dameargo","Pollip´olipo volante",
          "Yskhtihyssg- Goth","Familia feliz","Roboggoth","El espia",
          "El Lenguas","Bicéfalo"
        ]
  malosRollos =Array[
            "Pierdes tu armadura visible y otra oculta",
            "Embobados con el lindo primigenio te descartas de tu casco visible",
            " El primordial bostezo contagioso. Pierdes el calzado visible",
            "Te atrapan para llevarte de ﬁesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta",
            " Pierdes todos tus tesoros visibles",
            "Pierdes la armadura visible",
            " Sientes bichos bajo la ropa. Descarta la armadura visible",
            "Pierdes 5 niveles y 3 tesoros visibles",
            "Toses los pulmones y pierdes 2 niveles.",
            " Estos monstruos resultan bastante superﬁciales y te aburren mortalmente. Estas muerto",
            "Pierdes 2 niveles y 2 tesoros ocultos",
            "Te intentas escaquear. Pierdes una mano visible",
            " Da mucho asquito. Pierdes 3 niveles.",
            " No le hace gracia que pronuncien mal su nombre. Estas muerto",
            "La familia te atrapa. Est´as muerto",
            " La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible",
            " Te asusta en la noche. Pierdes un casco visible",
            "Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles",
            "Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos"
  ]
  prizes = Array.new
  bcs = Array.new
  
  sHidT =Array [[TreasureKind::ARMOR],Array.new,Array.new,[TreasureKind::ONEHAND],Array.new,Array.new,Array.new,Array.new,Array.new,Array.new,Array.new,Array.new,Array.new,Array.new,Array.new,Array.new,Array.new,Array.new,Array.new]
  sVisiT =Array [[TreasureKind::ARMOR],[TreasureKind::HELMET],[TreasureKind::SHOE],[TreasureKind::ONEHAND],Array.new,[TreasureKind::ARMOR],[TreasureKind::ARMOR],
    Array.new,Array.new,Array.new,Array.new,
    [TreasureKind::ONEHAND],Array.new,Array.new,Array.new,
    [TreasureKind::BOTHHAND],[TreasureKind::HELMET],Array.new,[TreasureKind::BOTHHAND,TreasureKind::ONEHAND]]
  #Ejemplo
  #prize = Prize.new(4, 2)
  #badConsequence = BadConsequence.BadConsequence3('Pierdes 5 niveles y 3 tesoros visibles',5,3,0)
  #monsters << Monster.new('El rey de la rosa', 13, badConsequence, prize)
  for i in 0..numeroMonstruos
    prizes << Prize.new(treasures[i], levels[i])
    if(muertos[i])
      bcs << BadConsequence.BadConsequence2(malosRollos[i],muertos[i])
    else
      if(vTreasures[i] !=0 || hTreasures[i]!= 0)
        bcs << BadConsequence.BadConsequence3(malosRollos[i],loseLevels[i],vTreasures[i],hTreasures[i])
      else
        bcs << BadConsequence.BadConsequence4(malosRollos[i],loseLevels[i],sHidT[i],sVisiT[i])
      end
    end
    monsters << Monster.new(nombres[i], monsterLevel[i], bcs[i], prizes[i])
  end
  #PRUEBAS
       puts "////////////NUMERO COMBAT LEVEL////////"
       for monster in monsters
         if(monster.combatLevel > 10)
           puts "Monstruos="+monster.to_s
         end
       end
       puts "////////////NUMERO NIVELES////////"
        for monster in monsters
           if(monster.bc.levels>0)
               puts "Monstruos="+monster.to_s;
           end
        end
        puts "////////////NUMERO TESOROS////////"
         for monster in monsters
           if(monster.bc.nHiddenTreasures>0)

              puts "Monstruos="+monster.to_s
           end
         end
         puts "////////////NUMERO PREMIOS////////"
          for monster in monsters

           if(monster.prize.level>1)

              puts "Monstruos="+monster.to_s
           end
          end

end
end