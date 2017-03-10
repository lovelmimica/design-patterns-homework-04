/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.app;

/**
 *
 * @author lovel_mimica
 */
public class Params {
    private static Params instance = null;
    
    private Integer seed;
    private String diversFile;
    private String divesFile;
    private String skillsFile;
    private String gearFile;
    private Integer maxLease;
    
    private static final String errMsg = "Parametri neispravni";

    private Params() {
    }

    public static Params getInstance() {
        if(instance == null) instance = new Params();
        return instance;
    }
    
    public void load(String[] paramsArray) throws Exception{
        if(paramsArray.length != 6) throw new Exception(errMsg);
        
        seed = new Integer(paramsArray[0]);
        if(seed < 100) throw new Exception(errMsg);
        
        diversFile = paramsArray[1];
        divesFile = paramsArray[2];
        skillsFile = paramsArray[3];
        gearFile = paramsArray[4];
        
        maxLease = new Integer(paramsArray[5]);
        if(maxLease <1 || maxLease>14) throw new Exception(errMsg);
    }
    
    public void print(){
        System.out.println("\n" + "Parametri: \n" + this.seed + "\n" + this.diversFile + "\n" + this.divesFile + "\n" + this.skillsFile
        + "\n" + this.gearFile + "\n" + this.maxLease);
    }

    public String getDiversFile() {
        return diversFile;
    }

    public String getDivesFile() {
        return divesFile;
    }

    public String getSkillsFile() {
        return skillsFile;
    }

    public String getGearFile() {
        return gearFile;
    }

    public Integer getSeed() {
        return seed;
    }
    
    
    
}
