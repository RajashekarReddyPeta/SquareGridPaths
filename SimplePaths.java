import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class SimplePaths {
private static final String START = "1";
private static int c=0;
private static String END = "";
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.println("Enter number of Nodes : ");
int nodes = sc.nextInt();
END = Integer.toString(nodes);
int factor = (int) (nodes/Math.sqrt(nodes));
System.out.println("For the Given Nodes the Grid size is "+factor);
ArrayList<String> nodeNames = new ArrayList<String>();
Graph1 graph = new Graph1();
for(int i = 1; i <= nodes; i++) {
nodeNames.add(Integer.toString(i));
}
for(int i = 0; i < nodeNames.size()-1; i++){
if( Integer.parseInt(nodeNames.get(i)) % factor != 0) {
	graph.addEdge(nodeNames.get(i),nodeNames.get(i+1));
	graph.addEdge(nodeNames.get(i+1),nodeNames.get(i));
}
}
System.out.println("\n");
for(int i = factor+1; i <= nodeNames.size(); i++){
	graph.addEdge(nodeNames.get(i-1),nodeNames.get(i-factor-1));
	graph.addEdge(nodeNames.get(i-factor-1),nodeNames.get(i-1));
}
LinkedList<String> visited = new LinkedList();
visited.add(START);
long startTime = System.currentTimeMillis();
long actualtime;
new SimplePaths().deapthFirst(graph, visited);
System.out.println("Number of paths for "+factor +"X"+factor+" Grid is "+SimplePaths.c);
long endtime = System.currentTimeMillis();
actualtime=endtime-startTime;
System.out.print("The Running time for "+factor +"X"+factor+" Grid is "+actualtime+ " ms");
}
public void deapthFirst(Graph1 graph, LinkedList<String> visited) {
LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
for (String node : nodes) {
if (visited.contains(node)) {
continue;
}
if (node.equals(END)) {
visited.add(node);
c++;//counting visited path
visited.removeLast();
break;
}
}
for (String node : nodes) {
if (visited.contains(node) || node.equals(END)) {
continue;
}
visited.addLast(node);
deapthFirst(graph, visited);
visited.removeLast();
}
}
}
class Graph1 {
    private Map<String, LinkedHashSet<String>> map = new HashMap();
    public void addEdge(String node1, String node2) {
        LinkedHashSet<String> adjacent = map.get(node1);
        if(adjacent==null) {
            adjacent = new LinkedHashSet();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }
    
    public LinkedList<String> adjacentNodes(String last) {
        LinkedHashSet<String> adjacent = map.get(last);
        if(adjacent==null) {
            return new LinkedList();
        }
        return new LinkedList<String>(adjacent);
    }
 }