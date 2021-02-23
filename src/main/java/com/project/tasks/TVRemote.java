package com.project.tasks;

public class TVRemote {
	
	public static void main(String[] args) {
		
		System.out.print(getCount("for" ));

		
	}
	
	public static int getCount(String word) {
		
		 char[][] keyboard = {
			      {'a', 'b', 'c', 'd', 'e', '1', '2', '3'},
			      {'f', 'g', 'h', 'i', 'j', '4', '5', '6'},
			      {'k', 'l', 'm', 'n', 'o', '7', '8', '9'},
			      {'p', 'q', 'r', 's', 't', '.', '@', '0'},
			      {'u', 'v', 'w', 'x', 'y', 'z', '_', '/'}
			    };
		  int presses = 0;
		  int lastX = 0, lastY = 0;
		    
		    for (int i = 0; i < word.length(); i++) {
		      here: for (int x = 0; x < keyboard.length; x++)
		        for (int y = 0; y < keyboard[x].length; y++)
		          if (word.charAt(i) == keyboard[x][y]) {
		            presses += Math.abs(x - lastX) + Math.abs(y - lastY) + 1;
		            lastX = x;
		            lastY = y;
		            break here;
		          }
		    }
		    
		    return presses;
		  }
		
	}


