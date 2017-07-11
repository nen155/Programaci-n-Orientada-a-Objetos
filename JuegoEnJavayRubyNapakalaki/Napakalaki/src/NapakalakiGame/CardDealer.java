/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import static java.util.Collections.shuffle;

/**
 *
 * @author nen155
 */
public class CardDealer {
    private static final CardDealer cardDealer = new CardDealer();
    private ArrayList<Treasure> unusedTreasures;
    private ArrayList<Treasure> usedTreasures;
    private ArrayList<Monster> unusedMonster;
    private ArrayList<Monster> usedMonster;
    private ArrayList<Cultist> unusedCultist;
    
    
    private CardDealer(){
        unusedTreasures = new ArrayList();
        usedTreasures = new ArrayList();
        unusedMonster = new ArrayList();
        usedMonster = new ArrayList();
    }
    
    private void shuffleCultist(){
        shuffle(unusedCultist);
    }
    
    private void initCultistCardDeck(){
        unusedCultist = new ArrayList();
        
        unusedCultist.add(new Cultist("Sectario",1));
        unusedCultist.add(new Cultist("Sectario",2));
        unusedCultist.add(new Cultist("Sectario",1));
        unusedCultist.add(new Cultist("Sectario",2));
        unusedCultist.add(new Cultist("Sectario",1));
        unusedCultist.add(new Cultist("Sectario",1));
    }
    
    public Cultist nextCultist(){
        if (unusedCultist.isEmpty())
        {
            this.initCultistCardDeck();
            this.shuffleCultist();
        }
        
        Cultist c = this.unusedCultist.get(0);
        this.unusedCultist.remove(c);
        return c;
    }
    
    public static CardDealer getInstance(){
        return cardDealer;
    } 
    private void initTreasureCardDeck(){
        String [] names = {"¡Sí mi amo!","Botas de investigación","Capucha de Cthulhu",
        "A prueba de babas","Botas de lluvia ácida","Casco minero","Ametralladora Thompson",
        "Camiseta de la UGR","Clavo de rail ferroviario","Cuchillo de sushi arcano","Fez alópodo",
        "Hacha prehist´orica","El aparato del Pr. Tesla","Gaita","Insecticida","Escopetade 3can˜ones",
        "Garabato m´ıstico","La fuerza de Mr.T","La rebeca met´alica","Mazo de los antiguos",
        "Necro- playboyc´on","Lanzallamas","Necrocomicón","Necronomicón","Linterna a 2 manos","Necrognomicón",
        "Necrotelecom","Porra preternatural","Tentaculo de pega","Zapato deja-amigos",
        "Shogulador","Varita de atizamiento"};
        int [] goldCoinss = {0,600,500,400,800,400,600,100,400,300,700,500,900,500,300,700,300,1000,
        400,200,300,800,100,800,400,200,300,200,200,500,600,400};
        int [] minBonuss = {4,3,3,2,1,2,4,1,3,2,3,2,4,4,2,4,2,0,2,3,3,4,1,5,3,2,2,2,0,0,1,3};
        int [] maxBonuss = {7,4,5,5,1,4,8,7,6,3,5,5,8,5,3,6,2,0,3,4,5,8,1,7,6,4,3,3,1,1,1,4};
        TreasureKind [] treasuresKinds = {TreasureKind.helmet,TreasureKind.shoe,TreasureKind.helmet,TreasureKind.armor,
        TreasureKind.bothHand,TreasureKind.helmet,TreasureKind.bothHand,TreasureKind.armor,TreasureKind.oneHand,TreasureKind.oneHand,
        TreasureKind.helmet,TreasureKind.oneHand,TreasureKind.armor,TreasureKind.bothHand,TreasureKind.oneHand,TreasureKind.bothHand,
        TreasureKind.oneHand,TreasureKind.necklace,TreasureKind.armor,TreasureKind.oneHand,TreasureKind.oneHand,TreasureKind.bothHand,
        TreasureKind.oneHand,TreasureKind.bothHand,TreasureKind.bothHand,TreasureKind.oneHand,TreasureKind.helmet,TreasureKind.oneHand,
        TreasureKind.helmet,TreasureKind.shoe,TreasureKind.bothHand,TreasureKind.oneHand};
        
                
        for(int i =0; i< 32;i++){
            unusedTreasures.add(new Treasure(names[i],goldCoinss[i],minBonuss[i],maxBonuss[i],treasuresKinds[i]));
        }
    }
    private void initMonsterCardDeck(){
        ArrayList<Prize> prizes = new ArrayList();
        ArrayList<BadConsequence> bcs = new ArrayList();
        String [] nombres = {"3 Byakhees de bonanza","Chibithulhu",
          "El sopor de Dunwich" , "´ Angeles de la noche ibicenca",
          "El gorr´on en el umbral", "H.P. Munchcraft",
          "Bichgooth","El rey de rosa","La que redacta en las tinieblas",
          "Los hondos","Semillas Cthulhu","Dameargo","Pollip´olipo volante",
          "Yskhtihyssg- Goth","Familia feliz","Roboggoth","El espia",
          "El Lenguas","Bicéfalo"
        };
        //Nivel del monstruo
        int [] level ={8,2,2,14,10,6,2,13,2,8,4,1,3,12,1,8,5,20,20};
        String [] malosRollos ={
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
        };
        //Niveles que ganas con el monstruo
        int [] levels = {
            1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1
        } ;
        //Cantidad de tesoros que da el monstruo
        int [] treasures ={
            2,1,1,4,3,2,1,4,1,2,2,2,1,3,4,2,1,1,1
        };
        //Niveles que pierces
        int [] loseLevels = {
            0,0,0,0,0,0,0,5,2,0,2,0,3,0,0,2,0,2,3
        };
        //Tesoros escondidos
        int [] hTreasures ={
            0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0
        };
        //Si te mata o no el monstruo
        boolean [] muertos = {
        false,false,false,false,false,false,false,false,false,true,false,false,false,true,true,false,false,false,false
        };
        // Numero tesoros visibles
        int [] vTreasures = {
            0,0,0,0,6,0,0,3,0,0,0,0,0,0,0,0,0,5,0
        };
        //Array de Array para guardar el array de saber que tesoros te quita el monstruo
        ArrayList<ArrayList> sVisT = new ArrayList<ArrayList>();
        //1
        sVisT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.armor); }});
        //2
        sVisT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.helmet); }});
        //3
        sVisT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.shoe); }});
        //4
        sVisT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.oneHand); }});
        //5
        sVisT.add(new ArrayList());
        //6
        sVisT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.armor); }});
        //7
        sVisT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.armor); }});
        //8
        sVisT.add(new ArrayList());
        //9
        sVisT.add(new ArrayList());
        //10
        sVisT.add(new ArrayList());
        //11
        sVisT.add(new ArrayList());
        //12       
        sVisT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.oneHand); }});
        //13
        sVisT.add(new ArrayList());
        //14
        sVisT.add(new ArrayList());
        //15
        sVisT.add(new ArrayList());
        //16
        sVisT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.bothHand); }});
        //17
        sVisT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.helmet); }});
        //18
        sVisT.add(new ArrayList());
        //19
        sVisT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.bothHand); add(TreasureKind.oneHand); }});
        //Array de arrays para saber que tesoros ocultos especificos te quita
        ArrayList<ArrayList>  sHidT = new ArrayList<ArrayList>();
        //TESOROS OCULTOS EN SU ARRAY
        //Array para saber que tesoros ocultos te quita 
        sHidT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.armor); }});
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList<TreasureKind>() {{ add(TreasureKind.oneHand); }});
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        sHidT.add(new ArrayList());
        //TESOROS EN SU ARRAY////////


    ////INTRODUCCION DE MONSTRUOS
       for(int i =0; i< 19;i++){
            prizes.add(new Prize(treasures[i],levels[i]));
            //Si te mata usamos el constructor para especificar que te mata
            if(muertos[i]){
                bcs.add(new BadConsequence(malosRollos[i],muertos[i]));
            }else
                //Si te pone numero de tesoros ya sean ocultos o visibles usamos el constructor apropiado
                if(vTreasures[i] !=0 || hTreasures[i]!= 0){
                    bcs.add(new BadConsequence(malosRollos[i],loseLevels[i],vTreasures[i],hTreasures[i]));
                }else
                {
                    //Si no el que queda
                    bcs.add(new BadConsequence(malosRollos[i],loseLevels[i],sHidT.get(i),sVisT.get(i)));
                }

            unusedMonster.add(new Monster(nombres[i],level[i],bcs.get(i),prizes.get(i)));
        }
       //SECTARIOS
       
        ArrayList<TreasureKind> v = new ArrayList();
        v.add(TreasureKind.oneHand);
        ArrayList<TreasureKind> h = new ArrayList();
        BadConsequence bc = new BadConsequence("Pierdes 1 mano visible.",0, v, h);
        Prize prize = new Prize(3,1);
        unusedMonster.add(new Monster("El mal indecible impronunciable", 10, bc, prize, -2));
        v = new ArrayList();
        v.add(TreasureKind.shoe);
        v.add(TreasureKind.oneHand);
        v.add(TreasureKind.necklace);
        v.add(TreasureKind.helmet);
        v.add(TreasureKind.bothHand);
        v.add(TreasureKind.armor);
        h = new ArrayList();
        bc = new BadConsequence("Pierdes tus tesoros visibles. Jajaja.",0, v, h);
        prize = new Prize(2,1);
        unusedMonster.add(new Monster("Testigos Oculares", 6, bc, prize, +2));
        bc = new BadConsequence("Hoy no es tu dia de suerte. Mueres.",true);
        prize = new Prize(2,5);
        unusedMonster.add(new Monster("El gran cthulhu", 20, bc, prize, +4));
        v = new ArrayList();
        h = new ArrayList();
        bc = new BadConsequence("Tu gobierno te recorta 2 niveles.", 2, v, h);
        prize = new Prize(2,1);
        unusedMonster.add(new Monster("Serpiente Político", 8, bc, prize, -2));
        v = new ArrayList();
        v.add(TreasureKind.helmet);
        v.add(TreasureKind.armor);
        h = new ArrayList();
        h.add(TreasureKind.bothHand);
        h.add(TreasureKind.oneHand);
        bc = new BadConsequence("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas.", 0, v, h);
        prize = new Prize(1,1);
        unusedMonster.add(new Monster("Felpuggoth", 2, bc, prize, +5));
        v = new ArrayList();
        h = new ArrayList();
        bc = new BadConsequence("Pierdes 2 niveles.", 2, v, h);
        prize = new Prize(4,2);
        unusedMonster.add(new Monster("Shoggoth", 16, bc, prize, -4));
        v = new ArrayList();
        h = new ArrayList();
        bc = new BadConsequence("Pintalabios negro. Pierdes 2 niveles.", 2, v, h);
        prize = new Prize(1,1);
        unusedMonster.add(new Monster("Lolitagooth", 2, bc, prize, +3));

    }
    private void shuffleTreasures(){
        shuffle(unusedTreasures);
    }
    private void shuffleMonster(){
        shuffle(unusedMonster);
    }
    public Treasure nextTreasure(){
        if(unusedTreasures.isEmpty()) //Si el vector esta vacio barajamos cartas
        {
            if(usedTreasures.size() != 0)
            {
                unusedTreasures = new ArrayList(usedTreasures);
                usedTreasures.clear();
            }
            shuffleTreasures();
        }
        
        Treasure t = unusedTreasures.get(0);
        giveTreasureBack(t);
            
        return t;
    }
    public Monster nextMonster(){
        
        if(unusedMonster.isEmpty()) //Si el vector esta vacio barajamos cartas
        {
            if(usedMonster.size() != 0)
            {
                unusedMonster = new ArrayList(usedMonster);
                usedMonster.clear();
            }
            shuffleMonster();
        }
        
        Monster m = unusedMonster.get(0);
        giveMonsterBack(m);
            
        return m;
    }
    public void giveTreasureBack(Treasure t){
        usedTreasures.add(t);
        unusedTreasures.remove(t);
    }
    public void giveMonsterBack(Monster m){
        usedMonster.add(m);
        unusedMonster.remove(m);
    }
    public void initCards(){
        initTreasureCardDeck();
        shuffleTreasures();
        
        initMonsterCardDeck();
        shuffleMonster();
        
        initCultistCardDeck();
        shuffleCultist();
    }
    
}
