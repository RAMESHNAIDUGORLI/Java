package com.crea.path;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ShortestPathRoute {
	
	private static final String DEFAULT_SEPARATOR = ",";
	
	public static Map<String,Integer> main(String[] args) throws Exception{
		String routesFile;
		if(args.length>0) {
			routesFile = args[0];
		}else {
			routesFile = "/data/routes.csv";
		}
		String workingDir = System.getProperty("user.dir");
		Scanner csvScanner = new Scanner(new File(workingDir+routesFile));
		Scanner sc = new Scanner(System.in);
		System.out.print("What station are you getting on the train?:");
		String startP = sc.nextLine();
		System.out.print("What station are you getting off the train?:");
		String endP = sc.nextLine();
		List<Route> routes = new ArrayList<>();
		while(csvScanner.hasNext()) {
			String line = csvScanner.nextLine();
			routes.addAll(parseLine(line));
		}
		Map<String,Integer> shortestPath = findtheShortestPath(routes,startP,endP);
				new HashMap<>();
		//}
		//Route r = q.peek();
		//Route test = routes.get(1);
		if(shortestPath.isEmpty()) {
			System.out.println("No routes from "+startP+" to "+endP);
		}else {
			System.out.println("Your trip from "+startP+" to "+endP+" includes "+ shortestPath.get("steps")+ 
					" and will take "+shortestPath.get("distance") +" minutes.");
		}
		csvScanner.close();
		sc.close();
		return shortestPath;
	}
	
	public static List<Route> parseLine(String csvLine){
		List<Route> result = new ArrayList<>();
		if(csvLine == null || csvLine.isEmpty()) {
			return result;
		}
		String [] arr = csvLine.split(DEFAULT_SEPARATOR);
		//System.out.println(arr[0]);
		Route route = new Route(arr[0],arr[1],Integer.parseInt(arr[2]),false);
		result.add(route);
		return result;
	}
	
	public static Map<String,Integer>findtheShortestPath(List<Route> routes,String startP,String endP){
		
		Queue<Route> q = new LinkedList<>();
		int destination = 0;
		Map<String,Integer> shortestPath = new HashMap<>();
		for (int i = 0; i< routes.size(); i++) {
			List<Route> path = new ArrayList<>();
			Route rt = routes.get(i);
			if(startP.equalsIgnoreCase(rt.startPosition)) {
				rt.flag = true;
				q.add(rt);
				while(!q.isEmpty()) {
					
					for(int j = 0; j < routes.size(); j++) {
						Route peek = q.peek();
						Route route = routes.get(j);
						if(peek.destination.equalsIgnoreCase(endP) && route.flag == false) {
							route.flag = true;
							//System.out.println("source "+route.startPosition+ "destination "+route.destination);
							break;
						} else if(route.startPosition.equalsIgnoreCase(peek.destination) && route.flag == false){
							route.flag = true;
							q.add(route);
						}
					}
					path.add(q.poll());
				}
				boolean visited1 = true;
				if(!path.isEmpty()) {
					Map<String,Integer> shortestPath1 = findthePath(path,visited1,endP);
					
					if(!shortestPath1.isEmpty() && destination == 0) {
						destination = shortestPath1.get("distance");
						shortestPath = shortestPath1;
					}else if(!shortestPath1.isEmpty() && shortestPath1.get("distance") < destination) {
						shortestPath = shortestPath1;
					}
				}
			}
			
		}
		return shortestPath;
	}
	
	public static Map<String,Integer> findthePath(List<Route> path, boolean visited,String endP){
		Map<String,Integer> finalOutput = new HashMap<String,Integer>();
		if(path.size() == 1 && path.get(0).destination.equalsIgnoreCase(endP)) {
			//System.out.println("distance"+path.get(0).distance);
			finalOutput.put("distance",path.get(0).distance);
			finalOutput.put("steps",0);
			return finalOutput;
		}
		Stack<Route> st = new Stack<>();
		st.add(path.get(0));
		int destination = path.get(0).distance;
		int steps = 0;
		while(!st.empty()) {
			
			for(int i = 1; i < path.size(); i++) {
				Route peek = st.peek();
				Route nextElement = path.get(i);
				if(peek.destination.equalsIgnoreCase(nextElement.startPosition) && nextElement.flag) {
					st.add(nextElement);
					destination = destination + nextElement.distance;
					steps++;
					nextElement.flag = false;
				}
			}
			
			Route distance1 = st.pop();
			if (distance1.destination.equalsIgnoreCase(endP) && !distance1.flag){
				//System.out.println("finalDestination"+ destination);
				finalOutput.put("distance",destination);
				finalOutput.put("steps",steps);
				destination = destination-distance1.distance;
				steps--;
			}else {
				destination = destination-distance1.distance;
				steps--;
			}
		}
		return finalOutput;
	}
}
