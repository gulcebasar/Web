/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Gulce
 */
public class Station {
    private static int maxLines; // keeps max line limit
    private static int totalStationNum; // total number of created stations
    private String name; // name of station
    private String type; // type of station
    private int index; // index of station (i'th station created
    private List<Line> lines; // list to keep lines passing through station
    
    public Station(String nName) {
        lines = new ArrayList<Line>();
        name = nName;
        
        type = "Interchange";
        
        index = totalStationNum;
        totalStationNum++;
    }
    
    public Station(String nName, String nType) {
        lines = new ArrayList<Line>();
        name = nName;
        
        if(nType.equals("Interchange") || nType.equals("Sea") || nType.equals("Land") || nType.equals("Rail"))
            type = nType;
        else
            type = "Interchange";
        
        index = totalStationNum;
        totalStationNum++;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
    public List<Line> getLines() {
        return lines;
    }

    public int getIndex() {
        return index;
    }
    
    public static void setMaxLines(int mLine) {
        maxLines = mLine;
    }
    
    public boolean maxLine() {
        if(lines.size() == maxLines)
            return true;
        return false;
    }
    
    public void addLine(Line newLine) {
        if( lines.size() < maxLines )
            lines.add(newLine);
    }
    
    public void removeLine(Line newLine) {
        lines.remove(newLine);
    }

    public void PrintLines() {
        System.out.println("Lines stopping at " + name + ":");
        Collections.sort(lines, 
            new Comparator<Line>(){
                @Override
                public int compare(Line l1, Line l2) {
                    return (l1.getClassName()).compareTo(l2.getClassName());
                }
            } );
    
        int i=1;
        for(Line l : lines)
        {
            System.out.print(i++ + "- ");
            
            if(l.isDisabled())
                System.out.println( l.getName() + " is temporarly unavailable!");
            else
                System.out.println(l);
        }
        System.out.println("");
    }
  
    public void connectionTo(Station s) {
        
        boolean[] visited = new boolean[totalStationNum];
        Arrays.fill(visited, false);
        
        List<Station> path = new ArrayList<Station>();
        
        search(path, visited, this, s);
        
        if(path.size()==0)
        {
            System.out.println("No connection found between " + name + " and " + s.getName() +"\n");
        }
        else if(path.size() == 2)
        {
            System.out.print("Direct connection between " + name + " and " + s.getName() + " through ");
            for(Line l : lines)
            {
                if(l.hasStation(s) && !l.isDisabled())
                    System.out.println(l.getName() + "\n");
            }
        }
        else
        {
            System.out.println("Connection from " + name + " to " + s.getName() + ": ");
            
            for(int i=0; i<path.size()-1; i++)
            {
                System.out.print("-From " + path.get(i).getName() + " to " + path.get(i+1).getName() + " through ");
                for(Line l : path.get(i).getLines())
                {
                    if( l.hasStation(path.get(i+1)) && !l.isDisabled() )
                        System.out.println(l.getName());
                }
            }
            System.out.println("");
        }
    }
    
    public boolean search(List<Station> path, boolean[] visited, Station s1, Station s2){
        visited[ s1.getIndex() ]=true;
        path.add(s1);
        boolean found = false;
        
        for(Line l1 : s1.getLines())
        {
            if(!found && !l1.isDisabled())
            {
                if(l1.hasStation(s2))
                {
                    path.add(s2);
                    return true;
                }
                if( visited[ l1.getOtherStation(s1).getIndex() ] == false )
                {
                    found = search(path, visited, l1.getOtherStation(s1), s2);
                }
            }
        }
        if (!found)
            path.remove(s1);
        
        return found;
    }
  
}
