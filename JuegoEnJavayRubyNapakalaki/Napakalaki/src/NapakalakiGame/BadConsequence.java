/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;

/**
 *
 * @author nen155
 */
public class BadConsequence {
    private String text;
    private int levels;
    private int nVisibleTreasures;
    private int nHiddenTreasures;
    private boolean death;
    private ArrayList <TreasureKind> specificHiddenTreasures;
    private ArrayList<TreasureKind> specificVisibleTreasures; 
    
    public void subtractVisibleTreasure(Treasure t){
        if(specificVisibleTreasures.size()>0){
            if(specificVisibleTreasures.contains(t.getType())){
                specificVisibleTreasures.remove(t.getType());
                CardDealer.getInstance().giveTreasureBack(t);
            }
        }
    }
    
    public void subtractHiddenTreasure(Treasure t){
        if(specificVisibleTreasures.size()>0){
            if(specificVisibleTreasures.contains(t.getType())){
               specificHiddenTreasures.remove(t.getType());
               CardDealer.getInstance().giveTreasureBack(t);
            }
        }
    }
    
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        if(this.nVisibleTreasures != 0) //Si pierde tesoros no especificos visibles
        {
            int tama;
            if(nVisibleTreasures>=v.size())
                tama=v.size();
            else
                tama = nVisibleTreasures;
            for(int i=0;i<tama;i++){
                subtractVisibleTreasure(v.get(0));
                v.remove(0);
            }
            nVisibleTreasures=0;
        }
        else
        {
            int tama = v.size();
            
            ArrayList<Treasure> aux = new ArrayList();
            for(int i=0;i<specificVisibleTreasures.size();i++){
                for(int j=0;j<tama;j++){
                    if(specificVisibleTreasures.get(i)== v.get(j).getType()){
                        aux.add(v.get(j));
                    }
                }
            }
            for(int i=0;i<aux.size();i++){
                subtractVisibleTreasure(aux.get(i));
                v.remove(aux.get(i));
            }
            specificVisibleTreasures.clear();
        }
        if(this.nHiddenTreasures != 0) //Si pierde tesoros no especificos ocultos
        { 
            int tama;
            if(nHiddenTreasures>=h.size())
                tama=h.size();
            else
                tama = nHiddenTreasures;
            for(int i=0;i<tama;i++){
                subtractHiddenTreasure(h.get(0));
                h.remove(0);
            }
            nHiddenTreasures=0;
        }
        else
        {
             int tama = h.size();
            
            ArrayList<Treasure> aux = new ArrayList();
            for(int i=0;i<specificHiddenTreasures.size();i++){
                for(int j=0;j<tama;j++){
                    if(specificHiddenTreasures.get(i)== h.get(j).getType()){
                        aux.add(h.get(j));
                    }
                }
            }
            for(int i=0;i<aux.size();i++){
                subtractHiddenTreasure(aux.get(i));
                h.remove(aux.get(i));
            }
            specificHiddenTreasures.clear();
        }
        return this;
    }
    
    public boolean isEmpty(){
        if(nVisibleTreasures==0 && nHiddenTreasures==0  &&
                specificHiddenTreasures.size()==0 && specificVisibleTreasures.size()==0)
            return true;
        else
            return false;
    }
    
    public ArrayList<TreasureKind> getSpecificHiddenTreasures(){
        return specificHiddenTreasures;
    }
    
    public ArrayList<TreasureKind> getSpecificVisibleTreasures(){
        return specificVisibleTreasures;
    }
    
    public boolean myBadConsequenceisDeath(){
        return death;
    }
    public BadConsequence(String text, boolean death) {
        this.text = text;
        this.death = death;
        this.levels = 0;
        this.nVisibleTreasures = 0;
        this.nHiddenTreasures = 0;
        specificHiddenTreasures = new ArrayList<TreasureKind>();
        specificVisibleTreasures = new ArrayList<TreasureKind>();
    }

    public BadConsequence(String text, int levels, int nVisibleTreasures, int nHiddenTreasures) {
        this.text = text;
        this.levels = levels;
        this.death = false;
        this.nVisibleTreasures = nVisibleTreasures;
        this.nHiddenTreasures = nHiddenTreasures;
        specificHiddenTreasures = new ArrayList<TreasureKind>();
        specificVisibleTreasures = new ArrayList<TreasureKind>();
    }

    public BadConsequence(String text, int levels, ArrayList<TreasureKind> sHidT, ArrayList<TreasureKind> sVisT) {
        this.text = text;
        this.death = false;
        this.levels = levels;
        this.nVisibleTreasures = 0;
        this.nHiddenTreasures = 0;
        specificHiddenTreasures = new ArrayList<TreasureKind>(sHidT);
        specificVisibleTreasures = new ArrayList<TreasureKind>(sVisT);
    }

    public int getnVisibleTreasures() {
        return nVisibleTreasures;
    }

    public int getnHiddenTreasures() {
        return nHiddenTreasures;
    }
    
    public String getText() {
        return text;
    }

    public int getLevels() {
        return levels;
    }
    public String toString(){ 
        String s="";
        String h="";
        String v="";
        if (death){
            s="Estas Muerto";
        }
        else
            s="No estas Muerto";
        
         for(TreasureKind tk:specificHiddenTreasures){
             h += tk +",";
         }  
         for(TreasureKind tk:specificVisibleTreasures){
             v += tk +",";
         }
         return "Text = " + text + " levels = " + Integer.toString(levels)+ " nVisibelTreasures= "+ 
                 Integer.toString(nVisibleTreasures)+ " nHiddenTreasures= "+ Integer.toString(nHiddenTreasures
                 )+" Death= "+ s+ " Visible Treasures= "+ v+ " Hidden Treasures= "+ h;
     }
    
}
