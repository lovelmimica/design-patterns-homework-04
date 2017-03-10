/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.loading;

import lovelmimica.app.Data;
import lovelmimica.gear.GearFactory;
import lovelmimica.gear.GearStorage;

/**
 *
 * @author lovel_mimica
 */
public class LoadGear extends LoadAbstract{

    @Override
    protected void parseLines(Data data, String[] lines) {
        for(String line : lines){
            try {
                String[] gearParams = line.trim().split(";");
                if (gearParams.length != 8) throw new Exception("Ucitan naslovni redak opreme");
                
                //id i naziv
                String id = gearParams[0];
                String name = gearParams[1];

                //temperatura
                String minTemperatureStr = gearParams[2];
                if (minTemperatureStr.equalsIgnoreCase("#")) {
                    minTemperatureStr = "0";
                }
                Integer minTemperature = new Integer(minTemperatureStr);

                //kapuljaca
                String needHoodStr = gearParams[3];
                boolean needHood = setSuitParam(needHoodStr);

                //pododijelo
                String needUndersuitStr = gearParams[4];
                boolean needUndersuit = setSuitParam(needUndersuitStr);

                //nocni uron
                String forNightStr = gearParams[5];
                boolean forNight = setToolParam(forNightStr);

                //snimanje
                String forFilmingStr = gearParams[6];
                boolean forFilming = setToolParam(forFilmingStr);

                //raspolozivost
                String countStr = gearParams[7];
                Integer count = new Integer(countStr);
                
                GearStorage.getInstance().addGear(GearFactory.createGear(
                        id, name, minTemperature, needHood, needUndersuit, forNight, forFilming, count));                
                
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
    private boolean setToolParam(String param) throws Exception{
        if(param.equalsIgnoreCase("*")) return true;
        if(param.equalsIgnoreCase("#")) return false;
        throw new Exception("Neispravan format parametara opreme");
    }
    private boolean setSuitParam(String param) throws Exception{
        if(param.equalsIgnoreCase("+")) return true;
        if(param.equalsIgnoreCase("#") || param.equalsIgnoreCase("-") || param.equalsIgnoreCase("*")) 
            return false;
        
        throw new Exception("Neispravan format parametara opreme");
    }
}
