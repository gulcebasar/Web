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
public abstract class Line {
    private String name; // name of line
    private String type; // type of line
    private Station startStation; // starting station of line
    private Station endStation; // ending station of line
    private boolean disabled; // state of line
    
    public Line(String nName, Station s1, Station s2, String nType) {
        name = nName;
        type = nType;
        disabled = false;
        setStations(s1,s2);
    }
    
    public String getName() {
        return name;
    }
    
    public void disable() {
        disabled = true;
    }
    
    public void able() {
        disabled = false;
    }
       
    public boolean isDisabled() {
        return disabled;
    }
    
    public abstract String getClassName();

    @Override
    public String toString() {
        return getClassName() + " " + name + ": From " + startStation.getName() + " to " + endStation.getName();
    }
    
    public void setStations(Station s1, Station s2) {
        
        if( s1.maxLine() )
            System.out.println("!!!Error: Max lines reached for " + s1.getName() + "\n");
        
        else if( s2.maxLine() )
            System.out.println("!!!Error: Max lines reached for " + s2.getName() + "\n");
        
        else if ( !(s1.getType().equals(type) || s1.getType().equals("Interchange")) )
            System.out.println("!!!Error: " + s1.getName() + " is a " + s1.getType() + "\n" );
        
        else if( !(s2.getType().equals(type) || s2.getType().equals("Interchange")) )
            System.out.println("!!!Error: " + s2.getName() + " is a " + s2.getType() + "\n" );
        
        else 
        {
            if(startStation != null && endStation != null)
            {
                startStation.removeLine(this);
                endStation.removeLine(this);
            }
            
            startStation=s1;
            startStation.addLine(this);
            endStation=s2;
            endStation.addLine(this);
        } 
    }
    
    public void PrintDetails() {
        if(disabled)
            System.out.println( name + " is temporarly unavailable!");
        else
            System.out.println(this + "\n");
        
    }
    
    public boolean hasStation(Station s) {
        if( ( startStation==s ) || ( endStation==s ) )
            return true;
        return false;
    }
    
    public Station getOtherStation(Station s) {
        if(endStation == s)
            return startStation;
        else if(startStation == s)
            return endStation;
        else
            return null;
    }
 
}
