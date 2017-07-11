# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Napakalaki
  require 'singleton'
  class CardDealer
    include Singleton
    
         
    def initialize
     @unusedTreasures=Array.new
     @usedTreasures=Array.new
     @unusedMonster=Array.new
     @usedMonster=Array.new
    end
    
    def initTreasureCardDeck
      names = Array["¡Sí mi amo!","Botas de investigación","Capucha de Cthulhu",
        "A prueba de babas","Botas de lluvia ácida","Casco minero","Ametralladora Thompson",
        "Camiseta de la UGR","Clavo de rail ferroviario","Cuchillo de sushi arcano","Fez alópodo",
        "Hacha prehist´orica","El aparato del Pr. Tesla","Gaita","Insecticida","Escopetade 3can˜ones",
        "Garabato m´ıstico","La fuerza de Mr.T","La rebeca met´alica","Mazo de los antiguos",
        "Necro- playboyc´on","Lanzallamas","Necro- comic´on","Necronomic´on",
        "Necrotelecom","Porra preternatural","Tentaculo de pega","Zapato deja-amigos",
        "Shogulador","Varita de atizamiento"]
        goldCoinss = Array[0,600,500,400,800,400,600,100,400,300,700,500,900,500,300,700,300,1000,
        400,200,300,800,100,800,400,200,300,200,200,500,600,400]
        minBonuss = Array[0,0]
        maxBonuss = Array[0,0]
        treasuresKinds = Array[TreasureKind::HELMET,TreasureKind::SHOE,TreasureKind::HELMET,TreasureKind::ARMOR,
        TreasureKind::BOTHHAND,TreasureKind::HELMET,TreasureKind::BOTHHAND,TreasureKind::ARMOR,TreasureKind::ONEHAND,TreasureKind::ONEHAND,
        TreasureKind::HELMET,TreasureKind::ONEHAND,TreasureKind::ARMOR,TreasureKind::BOTHHAND,TreasureKind::ONEHAND,TreasureKind::ONEHAND,
        TreasureKind::ONEHAND,TreasureKind::NECKLACE,TreasureKind::ARMOR,TreasureKind::ONEHAND,TreasureKind::ONEHAND,TreasureKind::BOTHHAND,
        TreasureKind::ONEHAND,TreasureKind::BOTHHAND,TreasureKind::BOTHHAND,TreasureKind::ONEHAND,TreasureKind::HELMET,TreasureKind::ONEHAND,
        TreasureKind::HELMET,TreasureKind::SHOE,TreasureKind::BOTHHAND,TreasureKind::ONEHAND]
        
                
        for i in 0..32
            @unusedTreasures << Treasure.new(names[i],goldCoinss[i],minBonuss[i],maxBonuss[i],treasuresKinds[i])
        
    end
    def initMonsterCardDeck
      numeroMonstruos=18
      @unusedMonster = Array.new
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
        @unusedMonster << Monster.new(nombres[i], monsterLevel[i], bcs[i], prizes[i])
      end
    end
        
    
    def shuffleTreasures
      @unusedTreasures.shuffle
    end
    
    def shuffleMonster
      @unusedMonster.shuffle
    end
        
    
    def nextTreasure
      
    end
    def nextMonster
      
    end
        
    
    def giveTreasureBack(t)
      @usedTreasures << t
      @unusedTreasures - t
    end
     
    def giveMonsterBack( m)
      @usedMonster << m
      @unusedMonster - m
    end
        
    
    def initCards
        
    end

  end
end
end
