/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.gear;

import lovelmimica.gear.gearSpecializations.Accessories;
import lovelmimica.gear.gearSpecializations.BasicGear;
import lovelmimica.gear.gearSpecializations.Boots;
import lovelmimica.gear.gearSpecializations.DivingHood;
import lovelmimica.gear.gearSpecializations.DrySuit;
import lovelmimica.gear.gearSpecializations.FilmingGear;
import lovelmimica.gear.gearSpecializations.Gloves;
import lovelmimica.gear.gearSpecializations.NightGear;
import lovelmimica.gear.gearSpecializations.OxygenTank;
import lovelmimica.gear.gearSpecializations.Undersuit;
import lovelmimica.gear.gearSpecializations.WetSuit;

/**
 *
 * @author lovel_mimica
 */
public class GearFactory {
    public static Gear createGear(String id, String name, Integer minTemperature, 
            boolean needHood, boolean needUndersuit, boolean forNight, 
            boolean forFilming, Integer count){
        
                if(id.startsWith("0.")) return new BasicGear(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        if(id.startsWith("1.4")) return new DivingHood(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        if(id.startsWith("1.2")) return new Undersuit(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        if(id.startsWith("1.1.1") || id.startsWith("1.1.2")) return new WetSuit(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        if(id.startsWith("1.1.3")) return new DrySuit(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        
        if(id.startsWith("1.3")) return new Gloves(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        if(id.startsWith("1.5")) return new Boots(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        
        if(id.startsWith("2.2.")) return new OxygenTank(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        
        if(forFilming == true) return new FilmingGear(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        if(forNight == true) return new NightGear(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        
        return new Accessories(id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count);
        
    }
}
