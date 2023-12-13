package queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/* 
 * arr is a 2d array of integers
 * my_stack is a stack of Strings
 * queens is an ArrayList of Strings
 * holder is an ArrayList of Strings
 */
public class F224365Source {
	static void showArr(int[][] arr, int n){ //outputs array
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				System.out.print(arr[i][j]+" ");				
			}
			System.out.println("");
		}
	    System.out.println("");
		}

	static boolean check(int[][] arr, int n){ //checks there is no row entirely full with no queen;
	    boolean solvable = true;
	    for (int i=0; i<n;i++){
	    	int count = 0;
	    	for(int j=0; j<n ; j++) {
	    		if(arr[i][j]==1) {
	    			count=count+1;
	    		}
	    	}
	    	if(count == n) {
	    		solvable = false;
	    		break;
	    	}
	    }
	    return solvable;} 
	
	static void clear(int[][] arr, int n) { //sets arr to all 0s
		for(int i=0; i<n ; i++) {
			Arrays.fill(arr[i],0);
		}
		//showArr(arr, n);
	}
	    

	static void addQueen(int[][] arr,int n,int row,int col){ //adds queen, fills where the queen could attack
	    arr[row][col]=2;
	    for (int k=0;k <n; k++){
	        if (arr[row][k]==0){
	            arr[row][k]=1;}
	        if (arr[k][col]==0){
	            arr[k][col]=1;}
	        if (row+k < n && col+k < n){
	            if (arr[row+k][col+k]==0){
	                arr[row+k][col+k]=1;}}
	        if (row-k >=0 && col-k >=0){
	            if( arr[row-k][col-k]==0){
	                arr[row-k][col-k]=1;}}
	        if (row+k < n && col-k >=0){
	            if (arr[row+k][col-k]==0){
	                arr[row+k][col-k]=1;}}
	        if (row-k >=0 && col+k < n){
	            if (arr[row-k][col+k]==0){
	                arr[row-k][col+k]=1;}}  
	    }
	}
	public static void main(String[] args) {
		//long startTime = System.nanoTime();
		Scanner myObj = new Scanner(System.in);
		System.out.println("How many Queens? "); 
	    int n = myObj.nextInt();
		myObj.close();
	    int[][] arr = new int[n][n]; //array of integers
	    Stack<String> my_stack = new Stack<String>() ;
	    List<String> queens = new ArrayList<>();
	   
	    for (int i=0;i<Math.ceil(n/2.0);i++){ //only has to check n/2 
	        my_stack.push("0-"+String.valueOf(i));} //stack has the available top row options
	    boolean again=true;
	    int row=0; //last row a queen was placed
	    
	    while (again==true){ //n rows, place a queen in every row
	        if(my_stack.empty() == false){
	            String place = my_stack.pop();
	            String[] parts = place.split("-");
	            addQueen(arr,n,Integer.valueOf(parts[0]),Integer.valueOf(parts[1]));
	            //showArr(arr, n);
	            queens.add(place);
	        }
	        boolean solvable = check(arr, n);
	        if (solvable == false) { //if there is a row with no space
	            if (my_stack.size()==0){ //no backtrack
	                System.out.println("not possible");
	                again=false;
	                break;
	        	}else if (my_stack.size()!=0){ //backtrack
	                String x = my_stack.peek();
	                String[] bits = x.split("-");
	                int index = queens.size()-1;
	                queens.remove(index);//remove last queen placed
	                clear(arr,n);
	                List<String> holder = new ArrayList<>();
	                for (int i=0; i <queens.size();i++) { //if row of placed queens <= to row of top of stack add to stack
	                	String[] parts = queens.get(i).split("-");
	                	if (Integer.valueOf(bits[0]) > Integer.valueOf(parts[0])) {
	                    	addQueen(arr,n,Integer.valueOf(parts[0]),Integer.valueOf(parts[1]));}
	                    else {
	                    	holder.add(queens.get(i));
	                    }}
	                for(int i=0; i<holder.size();i++) {
	                		queens.remove(holder.get(i));	
	                		}
	                	}
	                //showArr(arr, n);
	       }else if (solvable == true){//add free spaces on next row to stack
	            if(queens.size()!=n) {
	            	String[] parts = queens.get(queens.size()-1).split("-");
	                row = Integer.valueOf(parts[0]);
	                row=row+1; //go next row
	                for(int i=0; i<n;i++) {
	                    if (arr[row][i]==0) {
	                        my_stack.push(Integer.toString(row)+"-"+Integer.toString(i));}}
	           }else {
	                showArr(arr, n);
	                System.out.println("is possible");
	                again=false;
	                break;
	            }
	        }
	    }
	    //long endTime = System.nanoTime();
	    //long duration = endTime - startTime;
	    //System.out.println(duration/1000000000);//answer in seconds
	}
}