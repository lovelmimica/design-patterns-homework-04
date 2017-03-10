/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.gear;

/**
 *
 * @author lovel_mimica
 */
public class Gear {
    private String id;
    private String name;
    private Integer minTemperature;
    private boolean needHood;
    private boolean needUndersuit;
    private boolean forNight;
    private boolean forFilming;
    private Integer count;

    public Gear(String id, String name, Integer minTemperature, boolean needHood, boolean needUndersuit, boolean forNight, boolean forFilming, Integer count) {
        this.id = id;
        this.name = name;
        this.minTemperature = minTemperature;
        this.needHood = needHood;
        this.needUndersuit = needUndersuit;
        this.forNight = forNight;
        this.forFilming = forFilming;
        this.count = count;
    }

    public void incrementCount(){
        count++;
    }
    public void decrementCount() throws Exception{
        if(count == 0) throw new Exception("Nema raspolozive opreme");
        count--;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + "id=" + id + ", name=" + name + ", count=" +count + '}';
    }
    //geteri i seteri 

    public Integer getMinTemperature() {
        return minTemperature;
    }

    public Integer getCount() {
        return count;
    }

    public boolean isForNight() {
        return forNight;
    }

    public boolean isForFilming() {
        return forFilming;
    }

    public boolean isNeedHood() {
        return needHood;
    }
    
}
