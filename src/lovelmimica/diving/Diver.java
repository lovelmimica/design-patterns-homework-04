/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.diving;

import lovelmimica.diving.Dive;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lovelmimica.app.Data;
import lovelmimica.gear.Gear;
import lovelmimica.gear.GearLeasingService;
import lovelmimica.gear.gearSpecializations.BasicGear;
import lovelmimica.gear.gearSpecializations.DrySuit;
import lovelmimica.gear.gearSpecializations.OxygenTank;
import lovelmimica.gear.gearSpecializations.WetSuit;
import lovelmimica.skills.NightDiving;
import lovelmimica.skills.UnderwatherCamerman;
import lovelmimica.skills.Skill;
import lovelmimica.skills.WearDrySuit;

/**
 *
 * @author lovel_mimica
 */
public class Diver {
    private String name; 
    private String agency;
    private Integer level; 
    private Integer bornYear; 
    
    private boolean equiped = false;
    private List<Skill> skills = new ArrayList<Skill>();
    private List<Gear> gearList = new ArrayList<Gear>();

    public Diver(String name, String agency, Integer level, Integer bornYear) {
        this.name = name;
        this.agency = agency;
        this.level = level;
        this.bornYear = bornYear;
    }
    
    public Integer getMaxDepth(){
        if (level == 0) {
            return 0;
        }
        if (level == 1) {
            return 20;
        }
        if (level == 2) {
            return 40;
        }
        return 40;
    }

    public void addSkill(Skill skill){
        this.skills.add(skill);
    }
    public boolean hasSkill(Class skill){
        for(Skill s : this.skills){
            if(s.getClass().equals(skill)){
                return true;
            }
        }
        return false;
    }
    
    public Integer getDiveCount(){
       return getMyDives().size();
   }
   public Date getOldesDive(){
        List<Dive> myDives = getMyDives();
        myDives.sort(new Comparator<Dive>(){
            @Override
            public int compare(Dive o1, Dive o2) {
                if(o1.getDate().after(o2.getDate())){
                    return 1;
                }
                else return -1;
            }
        });
        Date oldest = new Date();
        if(myDives.size()>0) myDives.get(0).getDate();
        return oldest;
    }
    
    private List<Dive> getMyDives(){
        List<Dive> myDives = new ArrayList<Dive>();
        List<Dive> dives = Data.getInstance().getDives();
        for(Dive dive : dives){
            if(dive.getDivers().contains(this)) myDives.add(dive);
        }
        return myDives;
    }
    
    public void equipSelf(Dive dive, GearLeasingService leasingService) {
        try {
            //dodijeli osnovnu opremu
            if (hasBasicGear() == false) {
                leasingService.giveBasicGear(this, dive);
            }

            //dodijeli odjecu
            if (hasSuit() == false) {
                leasingService.giveSuit(this, dive);
            }

            //pp dodijeli nocnu opremu
            if (hasNightGear() == false && dive.isNight()) {
                leasingService.giveNightGear(this, dive);
            }
            //pp dodijeli foto opremu
            if (hasSkill(UnderwatherCamerman.class) == true && hasPhotoGear() == false ) {
                try {
                    leasingService.giveFilmingGear(this, dive);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
            leasingService.setLeasing(this);
            this.equiped = true;
        } catch (Exception ex) {
            leasingService.releaseAllGear(this);
            this.equiped = false;
        }



    }
    public boolean hasBasicGear(){
        //posjeduje temeljnu opremu i ronilacku bocu
        Integer counter = 0;
        boolean hasOxTank = false;
        for(Gear g : this.gearList){
            if(g instanceof BasicGear) counter++;
            if(g instanceof OxygenTank) hasOxTank = true;
        }
        if(counter <3 || hasOxTank == false) return false;
        else return true;   
    }
    public boolean hasSuit(){
        for(Gear g : this.gearList){
            if(g instanceof DrySuit || g instanceof WetSuit){
                return true;
            }
        }
        return false;
    }
    public boolean hasNightGear(){
        for(Gear g : this.gearList){
            if(g.isForNight() == true) return true;
        }
        return false;
    }
    public boolean hasPhotoGear(){
        for(Gear g : this.gearList){
            if(g.isForFilming() == true) return true;
        }
        return false;
    }
    
    public void addGear(Gear gear) throws Exception{
        if(canHaveGear(gear) == false) throw new Exception("Nemoguce dodijeli opremu zbog manjka speciljanosti");
        this.gearList.add(gear);
    }
    private boolean canHaveGear(Gear gear){
        if(gear instanceof DrySuit && this.hasSkill(WearDrySuit.class) == false) return false;
        if(gear.isForFilming() && this.hasSkill(UnderwatherCamerman.class) == false) return false;
        if(gear.isForNight() && this.hasSkill(NightDiving.class) == false) return false;
        return true;
    }
    public void removeGear(Gear gear){
        if(hasGear(gear) == true) this.gearList.remove(gear);
    }
    public boolean hasGear(Gear gear){
        if(this.gearList.contains(gear)) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Diver{" + "name=" + name + ", agency=" + agency + ", level=" + level + ", bornYear=" + bornYear + ", equiped=" + equiped + ", skills=" + skills + ", gearList=" + gearList + '}';
    }
   
    //geteri i seteri
    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public List<Gear> getGearList() {
        return gearList;
    }
    
    
}
