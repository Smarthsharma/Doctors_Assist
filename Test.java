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
public class Test {
    private String id;
    private String name;
    private String price;

    public Test(String name) {
       // this.id = id;
        this.name = name;
       // this.price = price;
    }
    public Test(String id,String name,String price) {
       this.id = id;
       this.name = name;
       this.price = price;
    }
  

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the prize
     */
    public String getPrice() {
        return price;
    }
  
}
