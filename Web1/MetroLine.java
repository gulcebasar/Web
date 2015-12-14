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
public class MetroLine extends Line {
    private String className;
    
    public MetroLine(String nName, Station s1, Station s2) {
        super(nName, s1, s2, "Rail");
        className = "Metro Line";
    }

    @Override
    public String getClassName() {
        return className;
    }
    
}
