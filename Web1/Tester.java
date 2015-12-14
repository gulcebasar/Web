package Homework;

public class Tester {
    public static void main(String[] args){
		Station.setMaxLines(3);		
        
		Station S1 = new Station("Kadikoy");	//Interchange station by default
		Station S2 = new Station("Besiktas", "Sea");
		Station S3 = new Station("Taksim");
		Station S4 = new Station("Gebze", "Rail");
		Station S5 = new Station("Sariyer", "Land");
		Station S6 = new Station("ITU Ayazaga", "Interchange");
		Station S7 = new Station("Kabatas");
		Station S8 = new Station("Mecidiyekoy");
		Station S9 = new Station("Sisli");
		
		FerryLine L1 = new FerryLine("Line1", S1, S2);
		SeabusLine L2 = new SeabusLine("Line2", S7, S1);
		BusLine L3 = new BusLine("Line3", S8, S3);
		BusLine L4 = new BusLine("Line4", S7, S5);
		MetrobusLine L5 = new MetrobusLine("Line5", S1, S8);
		MetroLine L6 = new MetroLine("Line6", S3, S8);
		TrainLine L7 = new TrainLine("Line7", S1, S4);	//Error: max lines reached for S1
		
		L1.PrintDetails();	//Prints all the information about the line in a user friendly way
		S3.PrintLines();	//Prints the information about all the lines stopping at the station 
							//sorted alphabetically by line type.
		
		L6.setStations(S3, S6);	//Updates the stations
		L6.setStations(S3, S5);	//Error: Sariyer is a land station. No changes are made.
		L6.PrintDetails();
		
		BusLine L8 = new BusLine("Line8", S6, S2);	//Error: Besiktas is a sea station.
		L8.setStations(S9, S6);
		L8.PrintDetails();
		
		L3.disable();			//Disables a line temporarily
		S1.connectionTo(S5);	//Prints the lines connecting the stations (L2, L4 for this case)
		L2.disable();
		S2.connectionTo(S5);	//No connection found
    }
}



