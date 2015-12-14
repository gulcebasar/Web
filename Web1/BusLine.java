/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

/**
 *
 * @author Gulce
 */
public class BusLine extends Line {
    private String className; // name of the class

    public BusLine(String nName, Station s1, Station s2) {
        super(nName, s1, s2, "Land");
        className = "Bus Line";
    }

    @Override
    public String getClassName() {
        return className;
    }
    
}
