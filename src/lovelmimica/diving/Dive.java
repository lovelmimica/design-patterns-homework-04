/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.diving;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lovelmimica.gear.Gear;
import lovelmimica.gear.gearSpecializations.DrySuit;
import lovelmimica.skills.WearDrySuit;
import lovelmimica.skills.NightDiving;
import lovelmimica.skills.SkillFactory;

/**
 *
 * @author lovel_mimica
 */
public class Dive {
    private Date date; 
    private Integer depth; 
    private Integer diverCount;
    private Integer temperature;
    private boolean night;
    private Integer camermanCount;
    
  
    private List<Diver> divers = new ArrayList<Diver>();

    public Dive(Date date, Integer depth, Integer diverCount, Integer temperature, boolean night, Integer camermanCount) {
        this.date = date;
        this.depth = depth;
        this.diverCount = diverCount;
        this.temperature = temperature;
        this.night = night;
        this.camermanCount = camermanCount;
    }

    @Override
    public String toString() {
        return "Dive{" + "date=" + date + ", depth=" + depth + ", diverCount=" + diverCount + ", temperature=" + temperature + ", night=" + night + ", filming=" + camermanCount + '}';
    }
    
   public boolean isDiverSuited(Diver diver){
        if (night) {
            if (diver.hasSkill(NightDiving.class) == false) {

                return false;
            }
        }
        if (needsDrySuite() == true) {
            if (diver.hasSkill(WearDrySuit.class) == false) {
                return false;
            }

        }
        if (this.depth > diver.getMaxDepth()) {
            return false;
        }

        return true;
    }
   public boolean isGearSuited(Gear gear){
       if(this.temperature < gear.getMinTemperature()) return false;
       else return true;
   }
   
   public boolean needsDrySuite(){
       if(temperature <=10) return true;
       else return false;
   }
   
   public boolean checkDiverEquipment(){
       Integer counter = 0;
       for(Diver d : this.divers){
           
           for(Gear g : d.getGearList()){
               if(this.temperature < g.getMinTemperature() && (g instanceof DrySuit) == false) return false;
               
           }
           //posjeduje osnovnu opremu
           if(d.hasBasicGear() == false) return false;
           //posjeduje nocnu opremu
           if(night && d.hasNightGear() == false) return false;
           //posjeduje odijelo
           if(d.hasSuit() == false) return false;
           //brojanje kamermana
           if(d.hasPhotoGear() == true)counter++;
       }
       if(counter < this.camermanCount) return false;
       return true;
   }
   public void removeAllDivers(){
       this.divers.clear();
   } 
   
    //geteri i seteri

    public Integer getDepth() {
        return depth;
    }

    public List<Diver> getDivers() {
        return divers;
    }

    public Date getDate() {
        return date;
    }

    public Integer getDiverCount() {
        return diverCount;
    }

    public boolean isNight() {
        return night;
    }

    public void setDivers(List<Diver> divers) {
        this.divers = divers;
    }

    public Integer getCamermanCount() {
        return camermanCount;
    }
    
    
}
