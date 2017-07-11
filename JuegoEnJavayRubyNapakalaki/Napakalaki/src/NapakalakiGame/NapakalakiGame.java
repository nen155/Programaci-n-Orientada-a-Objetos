
package NapakalakiGame;

import GUI.NapakalakiView;
import GUI.PlayerNamesCapture;
//import Test.GameTester;
import java.util.ArrayList;

public class NapakalakiGame {

    public static void main(String[] args) {
      NapakalakiView napakalakiView = new NapakalakiView();
      Dice.createInstance(napakalakiView);
      Napakalaki napakalakiModel = Napakalaki.getInstance();
      //GameTester test = GameTester.getInstance();
      
      
      ArrayList<String> names = new ArrayList();
      PlayerNamesCapture namesCapture = new  PlayerNamesCapture(napakalakiView,true);
      names = namesCapture.getNames();
      napakalakiModel.initGame(names);
      napakalakiView.setNapakalaki(napakalakiModel);
      
      napakalakiView.showView();
      // Poner el numero de jugadores con el que se quiera probar
      //test.play(napakalakiModel, 2); 
              
    }
}
