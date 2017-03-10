/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.gear;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lovelmimica.app.Data;
import lovelmimica.diving.Dive;
import lovelmimica.diving.Diver;
import lovelmimica.app.Params;
import lovelmimica.gear.gearSpecializations.BasicGear;
import lovelmimica.gear.gearSpecializations.DivingHood;
import lovelmimica.gear.gearSpecializations.DrySuit;
import lovelmimica.gear.gearSpecializations.FilmingGear;
import lovelmimica.gear.gearSpecializations.NightGear;
import lovelmimica.gear.gearSpecializations.OxygenTank;
import lovelmimica.gear.gearSpecializations.Undersuit;
import lovelmimica.gear.gearSpecializations.WetSuit;

/**
 *
 * @author lovel_mimica
 */
public class GearLeasingService {
    private static GearLeasingService instance;
    private GearIterator iterator;
    private Map<Diver, Date> leasingMap; 
    
    private GearLeasingService() {
        leasingMap = new HashMap<>();
    }

    public static GearLeasingService getInstance(){
        if(instance == null) instance = new GearLeasingService();
        return instance;
    }
    
    public void giveBasicGear(Diver diver, Dive dive) throws Exception{
        //dodjela maske, disaljke i peraja
        this.iterator = new GearIterator(BasicGear.class);
        
        Gear gear = iterator.first();
        
        for(int i = 0; i<3; i++){
            if(gear == null) throw new Exception("Nemoguce dodijeliti osnovnu opremu");
            GearStorage.getInstance().giveGear(diver, gear);
            gear = iterator.next();
        }
        
        //dodjela ronilacke boce
        iterator = new GearIterator(OxygenTank.class);
        gear = iterator.first();
        if(gear == null)throw new Exception("Nemoguce dodijeliti ronilacku bocu");
        GearStorage.getInstance().giveGear(diver, gear);
    }
    public void giveSuit(Diver diver, Dive dive) throws Exception{
        if(dive.needsDrySuite() == true) giveDrySuit(diver, dive);
        else giveWetSuit(diver, dive);
    }
    private void giveWetSuit(Diver diver, Dive dive) throws Exception{
        boolean diverEquiped = false;
        this.iterator = new GearIterator(WetSuit.class);

        Gear wetSuit = iterator.first();

        while (diverEquiped == false) {
            if (wetSuit == null) {
                throw new Exception("Nije moguce dodijeliti mokro odijelo");
            }

            if (dive.isGearSuited(wetSuit) == true){

                if (wetSuit.isNeedHood()) {
                    GearIterator secondIterator = new GearIterator(DivingHood.class);
                    Gear divingHood = secondIterator.first();
                    if (divingHood != null) {
                        GearStorage.getInstance().giveGear(diver, divingHood);
                        GearStorage.getInstance().giveGear(diver, wetSuit);
                        diverEquiped = true;
                    }
                } else {
                    GearStorage.getInstance().giveGear(diver, wetSuit);
                    diverEquiped = true;
                }
            }
            wetSuit = iterator.next();
        }        
    }
    private void giveDrySuit(Diver diver, Dive dive) throws Exception{
        //prvo dodaj pododijelo (jer podnosi nizu temperaturu)
        this.iterator = new GearIterator(Undersuit.class);
        Gear gear = iterator.first();
        if (gear == null) {
            throw new Exception("Nije moguce dodijeliti mokro odijelo");
        }

        while (dive.isGearSuited(gear) == false) {
            gear = iterator.next();
            if (gear == null) {
                throw new Exception("Nije moguce dodijeliti pododijelo");
            }
        }
        GearStorage.getInstance().giveGear(diver, gear);
        
        //drugo dodaj suho odijelo
        this.iterator = new GearIterator(DrySuit.class);
        gear = iterator.first();
        if (gear == null) {
            throw new Exception("Nije moguce dodijeliti mokro odijelo");
        }
        GearStorage.getInstance().giveGear(diver, gear);
    }
    
    
    public void giveNightGear(Diver diver, Dive dive) throws Exception{
        this.iterator = new GearIterator(NightGear.class);
        Gear nightGear = iterator.first();
        if(nightGear == null) throw new Exception("Nemoguce dodijeliti nocnu opremu");
        GearStorage.getInstance().giveGear(diver, nightGear);
    }
    public void giveFilmingGear(Diver diver, Dive dive) throws Exception{
        this.iterator = new GearIterator(FilmingGear.class);
        Gear filmingGear = iterator.first();
        if(filmingGear == null) throw new Exception("Nemoguce dodijeliti foto-video opremu");
        GearStorage.getInstance().giveGear(diver, filmingGear);
    }
    
    public void releaseAllGear(Diver diver){
        GearStorage.getInstance().takeAllGear(diver);
        this.leasingMap.remove(diver);
    }
    public void setLeasing(Diver diver){
        Random rand = new Random(Params.getInstance().getSeed());
        Integer leasingDays = rand.nextInt(14) + 1;
        Date leasingEnd = Data.getInstance().getCurrentDive().getDate();
        leasingEnd = addDays(leasingEnd, leasingDays);
        if(this.leasingMap.containsKey(diver)){
            Date oldLeasingEnd = this.leasingMap.get(diver);
            if(oldLeasingEnd.before(leasingEnd)){
                this.leasingMap.put(diver, leasingEnd);
                System.out.println("Dajem opremu na leasing od "+ leasingDays + " dana, roniocu \n  :" + diver.toString());
            }
        }else{   
            this.leasingMap.put(diver, leasingEnd);
        }
    }
    private Date addDays(Date date, Integer days){    
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return new Date(cal.getTimeInMillis()); 
    }
    
    public void checkLeaseExpirations(){
        for(Diver d : Data.getInstance().getDivers()){
            if(this.leasingMap.containsKey(d)){
                //provjeri istek lizing perioda
                Date now = Data.getInstance().getCurrentDive().getDate();
                Date leasingEnd = this.leasingMap.get(d);
                //uzmi opremu natrag ako je istekao rok
                if(now.after(leasingEnd)){
                    releaseAllGear(d);
                    System.out.println("\nRadi isteka lizinga uklonjena sva oprema sa ronioca: \n  :" +d.toString());
                }
            }
        }
    }
    
}
