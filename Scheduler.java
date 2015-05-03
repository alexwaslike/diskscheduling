//	Name: Willis, Alexandra
//	Project: PA-3 (Disk Scheduling Algorithms)
//	File: Main.java
//	Instructor: Feng Chen
//	Class: cs4103-au14
//	LogonID: cs4103

package diskschedulingalg;

public class Scheduler {
	
	private int initialPos;
	private int[] requests;
	private int maxNum;
	
	public Scheduler(int i, int[] r, int m){
		initialPos = i;
		requests = r;
		maxNum = m;
	}
	
	// Prints out the sequence of head disk positions visited
	// and, when finished,
	// the total amount of head movement.
	
	// Services requests in the order they appear.
	public void FCFS(){
		
		// Print out descriptive text + first position
		System.out.print("Order visited: " + initialPos + " ");
		
		int headMovement = 0;
		int previousLoc = initialPos;
		
		for(int i : requests){
			if(i == -1)
				break;
			
			headMovement += Math.abs(i - previousLoc);
			System.out.print(i + " ");
			previousLoc = i;
		}
		
		System.out.println("\nTotal head movement: " + headMovement);
		
	}
	
	
	// Selects request with shortest distance from previous request.
	public void SSTF(){
		
		// Print out descriptive text + first position
		System.out.print("Order visited: " + initialPos + " ");
		
		int headMovement = 0;
		// create new request array so that we can modify it
		int[] sstfrequests = new int[requests.length];
		for(int i=0; i<sstfrequests.length; i++)
			sstfrequests[i] = requests[i];
		
		int currentVal = initialPos;
		int previousLoc = -1;
		for(int i=0; i<sstfrequests.length; i++){
			
			if(sstfrequests[i] == -1)
				break;
			
			// search through requests for request closest to current
			int closest = 0;
			int shortestDistance = sstfrequests.length + 1;
			for(int j=0; j<sstfrequests.length; j++){
				if(sstfrequests[j] == -1)
					break;
				// check to see if distance between current request & request we're looking at is smaller than previous distance
				// also make sure we're not comparing the request to itself
				if( previousLoc != j && Math.abs(currentVal - sstfrequests[j]) < shortestDistance ){
					shortestDistance = Math.abs(currentVal - sstfrequests[j]);
					closest = j;
				}
			}
			
			// calculate output
			headMovement += shortestDistance;
			System.out.print(sstfrequests[closest] + " ");
			
			// set used request to be a value larger than array size (so that it doesn't get used again)
			if(previousLoc >= 0)	
				sstfrequests[previousLoc] = sstfrequests.length + 1;
			
			// set previous loc to be current
			previousLoc = closest;
			
			// set new current to be shortest distance spot
			currentVal = sstfrequests[closest];
			
		}
		
		System.out.println("\nTotal head movement: " + headMovement);
		
		
	}
	
	
	// Moves along disk in one direction and services reqeusts along the way.
	// Turns around once reached end of disk.
	public void CSCAN(){
		
		// Print out descriptive text
				System.out.print("Order visited: ");
			
				// create new array of cylinders to simulate disk
				// (did not do this for other algorithms for the sake of simplicity)
				int[] cylinders = new int[maxNum];
				// fill spots that are listed in requests with 1 (request)
				// all other spot filled with 0 (no request) by default by Java.
				cylinders[initialPos] = 1;
				for(int i=0; i<requests.length; i++){
					if(requests[i]==-1) 
						break;
					cylinders[requests[i]] = 1;
				}
						
				// go through cylinders linearly and service requests as they are found.
				int headMovement = 0;
				int previousLoc = 0;
				
				// goes in increasing order first...
				for(int i=initialPos; i<requests.length; i++){
					// if request exists at spot (spot = 1)
					if(cylinders[i] == 1){
						// calculate head movement from previous request
						headMovement += Math.abs(previousLoc - i);
						// output
						System.out.print(i + " ");
						// set previous -> current
						previousLoc = i;
						// indicate request serviced, so that we don't repeat it when we loop back around
						cylinders[i] = 0;
					}
				}
				headMovement += requests.length;
				// ... then starts back at beginning
				for(int i=0; i<cylinders.length; i++){
					// if request exists at spot (spot = 1)
					if(cylinders[i] == 1){
						// calculate head movement from previous request
						headMovement += Math.abs(previousLoc - i);
						// output
						System.out.print(i + " ");
						// set previous -> current
						previousLoc = i;
						// indicate request serviced
						cylinders[i] = 0;
					}
				}
						
						
				System.out.println("\nTotal head movement: " + headMovement);
		
		
		
		
	}
	
	// Moves along disk in one direction and services reqeusts along the way.
	// Turns around once reaches last request.
	public void CLOOK(){
		
		// find first request loc
		int firstRequest = requests.length+1;
		for(int i=0; i<requests.length; i++){
			if(requests[i]==-1 ) break;
			if(requests[i] < firstRequest)
				firstRequest = requests[i];
		}
		
		// find last reqeust loc
		int lastRequest = -1;
		for(int i=0; i<requests.length; i++){
			if(requests[i]==-1 ) break;
			if(requests[i] > lastRequest)
				lastRequest = requests[i];
		}
		
		
		// Print out descriptive text
				System.out.print("Order visited: ");
			
				// create new array of cylinders to simulate disk
				// (did not do this for other algorithms for the sake of simplicity)
				int[] cylinders = new int[maxNum];
				// fill spots that are listed in requests with 1 (request)
				// all other spot filled with 0 (no request) by default by Java.
				cylinders[initialPos] = 1;
				for(int i=0; i<requests.length; i++){
					if(requests[i]==-1) 
						break;
					cylinders[requests[i]] = 1;
				}
						
				// go through cylinders linearly and service requests as they are found.
				int headMovement = 0;
				int previousLoc = 0;
				
				// goes in increasing order first...
				for(int i=initialPos; i<=lastRequest; i++){
					// if request exists at spot (spot = 1)
					if(cylinders[i] == 1){
						// calculate head movement from previous request
						headMovement += Math.abs(previousLoc - i);
						// output
						System.out.print(i + " ");
						// set previous -> current
						previousLoc = i;
						// indicate request serviced, so that we don't repeat it when we loop back around
						cylinders[i] = 0;
					}
				}
				// ... then starts back at beginning
				for(int i=firstRequest; i<cylinders.length; i++){
					// if request exists at spot (spot = 1)
					if(cylinders[i] == 1){
						// calculate head movement from previous request
						headMovement += Math.abs(previousLoc - i);
						// output
						System.out.print(i + " ");
						// set previous -> current
						previousLoc = i;
						// indicate request serviced
						cylinders[i] = 0;
					}
				}
						
						
				System.out.println("\nTotal head movement: " + headMovement);
		
	}
	

}
