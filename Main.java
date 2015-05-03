//	Name: Willis, Alexandra
//	Project: PA-3 (Disk Scheduling Algorithms)
//	File: Main.java
//	Instructor: Feng Chen
//	Class: cs4103-au14
//	LogonID: cs4103

/*
 * Programming Task
 * Write a program (in C or Java) that implements the FCFS, SSTF, C-SCAN, and C-LOOK disk scheduling
	algorithms. Your program will service a disk with a specified number of cylinders
	numbered from 0. For example, assuming a disk with 5,000 cylinders, the cylinders are
	numbered from 0 to 4,999. Your program should accept three parameters on the command line –
	(1) the initial position of the disk head, (2) the maximum number of cylinders, and (3) a
	sequence of disk requests in cylinders. Your program should service them according to each of
	the algorithm, display the sequence of disk head positions, and report the total amount of head
	movement required by each algorithm. You should also provide help information to instruct how
	to run your program.
 */

package diskschedulingalg;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to the disk algorithm simulator. This program takes four inputs. Enter them one by one.");
		
		// get initial pos
		System.out.println("Initial position on head disk?");
		int initialPos = input.nextInt();
		
		// fill requests with -1 (no request)
		System.out.println("Maximum number of cylinders?");
		int maxNum = input.nextInt();
		int[] r = new int[maxNum];
		for(int i=0; i<r.length; i++)
			r[i] = -1;
		
		// fill requests with requested cylinders
		System.out.println("Enter sequence of disk requests, seperated by spaces. Type any letter and press enter to finish.");
		int i=0;
		while(input.hasNextInt()){
			r[i] =input.nextInt();
			i++;
		}
		
		// print out scheduling algorithms
		Scheduler sched = new Scheduler(initialPos, r, maxNum);
		System.out.print("FCFS:\n");
		sched.FCFS();
		System.out.print("SSTF:\n");
		sched.SSTF();
		System.out.print("C-SCAN:\n");
		sched.CSCAN();
		System.out.print("C-LOOK:\n");
		sched.CLOOK();
		
		
		
		input.close();
		

	}

}
