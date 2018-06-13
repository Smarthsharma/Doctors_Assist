/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.s.assist;

/**
 *
 * @author Smarth Sharma
 */
public class med {
    private String brand;
    private String name;
    private String composition;
    private String type;
    private String id;

    public med(String id,String brand, String name, String composition, String type) {
        this.id=id;
        this.brand = brand;
        this.name = name;
        this.composition = composition;
        this.type = type;
    }

    /**
     * @return the brand
     */
       public String getid() {
        return id;
    }

 
     
    public void setid(String id) {
        this.id = id;
    }
    
    public String getBrand() {
        return brand;
    }

       public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /*
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the composition
     */
    public String getComposition() {
        return composition;
    }

    /**
     * @param composition the composition to set
     */
    public void setComposition(String composition) {
        this.composition = composition;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
   

}
