#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Napakalaki
  class BadConsequence
    attr_reader :text, :levels, :nVisibleTreasures, :nHiddenTreasures
    def substractHiddenTreasures(t)
         if(@specificHiddenTreasures.size>0)
            if(@specificHiddenTreasures.include? (t.type)==true)
                @specificHiddenTreasures.delete(t.type);
                CardDealer.instance.giveTreasureBack(t);
            end
         end
    end
    def substractVisibleTreasures(t)
         if(@specificVisibleTreasures.size>0)
            if(@specificVisibleTreasures.include? (t.type)==true)
                @specificVisibleTreasures.delete(t.type);
                CardDealer.instance.giveTreasureBack(t);
            end
         end
    end
    def adjustToFitTreasureLists(v,h)
        if(@nVisibleTreasures != 0) 
        
            tama
            if(@nVisibleTreasures>=v.size)
                tama=v.size-1
            else
                tama = @nVisibleTreasures-1
            end
            for  i in 0..tama
                subtractVisibleTreasure(v[0])
                v.delete(0)
            end
            @nVisibleTreasures=0
        
        else
        
            tama = v.size-1
            aux = Array.new
            if(@specificVisibleTreasures.size!=0 && tama!=0)
            for i in 0..@specificVisibleTreasures.size-1
                for j in 0..tama
                    if(@specificVisibleTreasures[i]== v[j].type)
                        aux<<v[j]
                    end
                end
            end
            end
            if(aux.size!=0)
            for i in 0..aux.size-1
                substractVisibleTreasures(aux[i]);
                v.delete(aux[i]);
            end
            end
            @specificVisibleTreasures.clear
            
        end    
        if(@nHiddenTreasures != 0) 
        
            tama
            if(@nHiddenTreasures>=h.size)
                tama=h.size-1
            else
                tama = @nHiddenTreasures-1
            end
            if(tama!=0)
            for  i in 0..tama
                substractHiddenTreasures(h[0])
                h.delete(0)
            end
            end
            @nHiddenTreasures=0
        
        else
        
            tama = h.size-1
            aux = Array.new
            if(@specificHiddenTreasures.size!=0&&tama!=0)
            for i in 0..@specificHiddenTreasures.size-1
                for j in 0..tama
                    if(@specificHiddenTreasures[i]== h[j].type)
                        aux<<h[j]
                    end
                end
            end
            end
            if(aux.size!=0)
            for i in 0..aux.size-1
                substractHiddenTreasures(aux[i])
                h.delete(aux[i])
            end
            end
            @specificHiddenTreasures.clear
            
        end
        
        self
    end
    def isEmpty
         if( @nVisibleTreasures==0 && @nHiddenTreasures==0 &&
                @specificHiddenTreasures.size==0 && @specificVisibleTreasures.size==0)
            true
         else
           false
         end
    end
    
    def myBadConsequenceIsDeath
         if(@death==true)
           true
         else
           false
         end
    end
    
    def initialize(text,levels,nVisibleTreasures,nHiddenTreasures,death,specificHiddenTreasures,specificVisibleTreasures)
      @text = text
      @levels = levels
      @nVisibleTreasures = nVisibleTreasures
      @nHiddenTreasures = nHiddenTreasures
      @death = death
      @specificHiddenTreasures = specificHiddenTreasures
      @specificVisibleTreasures = specificVisibleTreasures
    end
    def self.BadConsequence2(text,death)
      new(text,0,0,0,death,Array.new,Array.new)
    end
    def self.BadConsequence3(text,levels,nVisibleTreasures,nHiddenTreasures)
      new(text,levels,nVisibleTreasures,nHiddenTreasures,false,Array.new,Array.new)
    end
    def self.BadConsequence4(text,levels,specificHiddenTreasures,specificVisibleTreasures)
      new(text,levels,0,0,false,specificHiddenTreasures,specificVisibleTreasures)
    end
    
    private_class_method:new
    
    def to_s
     v=""
     h=""
      d=""
      if(@specificVisibleTreasures.size!=0)
      for i in @specificVisibleTreasures
       v +=i+","
       end
      end
      if(@specificHiddenTreasures.size!=0)
       for i in @specificHiddenTreasures
        h +=i+","
      end
      end
      if(@death==true)
        d="Estas muerto"
      else
        d="Vives"
      end
        'Text = ' + text + ', levels = ' + levels.to_s+ ", nVisibelTreasures= "+ 
                 nVisibleTreasures.to_s+ ", nHiddenTreasures= "+ nHiddenTreasures.to_s+", Death= "+d +", Visible Treasures= #{v}, Hidden Treasures= #{h} "
    end
      
    end
end