import java.util.ArrayList;

public class SetStuffTester {
	
	public static void main(String[] args) {
		
SetStuff stuff = new SetStuff();
		
		System.out.println("================================ Return 17 =================================================");
		System.out.println(stuff.ret17());
		
		System.out.println("================================ Zeroth element =================================================");
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(17);
		System.out.println(stuff.zerothElement(list));
		
		
		list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(stuff.zerothElement(list));
		
		list = new ArrayList<Integer>();
		list.add(3);
		list.add(1);
		list.add(4);
		list.add(1);
		list.add(5);
		System.out.println(stuff.zerothElement(list));
			
		list = new ArrayList<Integer>();
		list.add(2);
		list.add(2);
		list.add(2);
		list.add(2);
		list.add(2);
		
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		System.out.println(stuff.zerothElement(list));
		
		list = new ArrayList<Integer>();
		list.add(222);
		list.add(22);
		list.add(222);
		list.add(22);
		list.add(2222);
		System.out.println(stuff.zerothElement(list));
		
		list = new ArrayList<Integer>();
		list.add(314);
		list.add(159);
		list.add(265);
		list.add(3);		
		System.out.println(stuff.zerothElement(list));
		
		
		System.out.println("================================ Contains =================================================");
		System.out.println(stuff.contains(1, new int[]{1}));
		System.out.println(stuff.contains(77, new int[]{1, 2, 3, 4, 5, 6, 77, 7}));
		System.out.println(stuff.contains(1, new int[]{2, 3, 4, 5, 6, 7}));
		System.out.println(stuff.contains(8, new int[]{1, 2, 3, 4, 5, 6, 7}));
		System.out.println(stuff.contains(3, new int[]{1, 2, 3, 4, 5, 6, 7}));

		
		System.out.println("================================ Any Contains =================================================");
		System.out.println(stuff.anyContains(new int[] {}, new int[]{}));
		System.out.println(stuff.anyContains(new int[] {1}, new int[]{}));
		System.out.println(stuff.anyContains(new int[] {}, new int[]{1}));
		System.out.println(stuff.anyContains(new int[] {1}, new int[]{1}));
		System.out.println(stuff.anyContains(new int[] {1}, new int[]{1,2}));
		System.out.println(stuff.anyContains(new int[] {2}, new int[]{1,2}));
		System.out.println(stuff.anyContains(new int[] {3}, new int[]{1,2}));
		System.out.println(stuff.anyContains(new int[] {}, new int[]{1,2}));
		System.out.println(stuff.anyContains(new int[] {1,2}, new int[]{1,2}));




		
		System.out.println("================================ Union =================================================");
		SetStuff.printArr(stuff.union(new int[] {}, new int[]{}));
		SetStuff.printArr(stuff.union(new int[] {1}, new int[]{}));
		SetStuff.printArr(stuff.union(new int[] {1, 2}, new int[]{3, 4}));
		SetStuff.printArr(stuff.union(new int[] {1, 2}, new int[]{}));
		SetStuff.printArr(stuff.union(new int[] {}, new int[]{3, 4}));
		SetStuff.printArr(stuff.union(new int[] {1,2}, new int[]{3, 4,5,6,7,8}));
		SetStuff.printArr(stuff.union(new int[] {1,2,7}, new int[]{3, 4}));
		SetStuff.printArr(stuff.union(new int[] {1,2,2222}, new int[]{3, 4}));

		
		System.out.println("================================ Intersect =================================================");
		SetStuff.printArr(stuff.intersect(new int[] {1, 2}, new int[]{3, 4}));
		SetStuff.printArr(stuff.intersect(new int[] {1, 2}, new int[]{1, 3, 4}));
		SetStuff.printArr(stuff.intersect(new int[] {1, 2}, new int[]{2, 3, 4}));
		SetStuff.printArr(stuff.intersect(new int[] {1, 2}, new int[]{1, 2, 3, 4}));
		SetStuff.printArr(stuff.intersect(new int[] {1, 2, 4, 5}, new int[]{3, 2, 4}));
		SetStuff.printArr(stuff.intersect(new int[] {1, 2, 4, 5}, new int[]{3, 4, 5, 7}));



		
		System.out.println("================================ NearestUnvisitedNode =================================================");
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, false, false}, new int[] {1, 2, 3}, new String[] {"A", "B", "C"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, true, true}, new int[] {1, 2, 3}, new String[] {"A", "B", "C"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {false, false, true}, new int[] {1, 2, 3}, new String[] {"AB", "BB", "C"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, false, false, true}, new int[] {1, 2, 3, 4}, new String[] {"A", "B", "C", "D"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, false, true, false}, new int[] {1, 2, 3, 4}, new String[] {"A", "B", "C", "D"})) ;	
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, false, true, false}, new int[] {4, 3, 2, 1}, new String[] {"A", "B", "C", "D"}));	
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {false, true, false, true, false}, new int[] {-3, 4, 3, 2, 1}, new String[] {"hey", "A", "B", "C", "D"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {false, true, true, true, false}, new int[] {33, 4, 3, 2, 11}, new String[] {"hey", "A", "B", "C", "DD"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {false, false, false}, new int[] {1,2,3}, new String[] {"A", "B", "C"}));
		System.out.println(stuff.nearesUnvisitedNode(new boolean[] {true, true, false}, new int[] {1,2,3}, new String[] {"A", "B", "CC"}));
		
		System.out.println("================================ Listify =================================================");
		System.out.println(stuff.listify(1, 2, 4));
		
		System.out.println("================================ Add Edge =================================================");
		ArrayList<ArrayList<Object>> graph = new ArrayList<ArrayList<Object>>();
		graph.add(stuff.listify(1, 2, 3));
		System.out.println(stuff.addEdge(graph, 4, 5, 6));
		
		graph = new ArrayList<ArrayList<Object>>();
		graph.add(stuff.listify(1, 2, 3));
		graph.add(stuff.listify(3, 1, 4));
		System.out.println(stuff.addEdge(graph, 4, 5, 6));
		
		graph = new ArrayList<ArrayList<Object>>();
		graph.add(stuff.listify(1, 2, 3));
		System.out.println(stuff.addEdge(graph, 4, 5, 7));
		
		graph = new ArrayList<ArrayList<Object>>();
		graph.add(stuff.listify(1, 3, 3));
		System.out.println(stuff.addEdge(graph, 4, 5, 6));
		
		graph = new ArrayList<ArrayList<Object>>();
		graph.add(stuff.listify(1, 2, 33));
		graph.add(stuff.listify(3, 1, 4));
		graph.add(stuff.listify(3, 5, 7));
		System.out.println(stuff.addEdge(graph, 4, 5, 6));
				
		System.out.println("================================ collectNodes =================================================");
		ArrayList<ArrayList<Integer>> edgeList = new ArrayList<ArrayList<Integer>>();
		edgeList.add(stuff.buildList(1, 2, 3));
		edgeList.add(stuff.buildList(2, 3, 4));
		System.out.println(stuff.collectNodes(edgeList));
		
		edgeList = new ArrayList<ArrayList<Integer>>();
		edgeList.add(stuff.buildList(1, 2, 3));
		edgeList.add(stuff.buildList(2, 3, 4));
		edgeList.add(stuff.buildList(3, 2, 4));
		System.out.println(stuff.collectNodes(edgeList));
		
		edgeList = new ArrayList<ArrayList<Integer>>();
		edgeList.add(stuff.buildList(1, 2, 3));
		edgeList.add(stuff.buildList(2, 3, 4));
		edgeList.add(stuff.buildList(3, 7, 4));
		System.out.println(stuff.collectNodes(edgeList));
		
		edgeList = new ArrayList<ArrayList<Integer>>();
		edgeList.add(stuff.buildList(7, 2, 3));
		edgeList.add(stuff.buildList(2, 3, 4));
		edgeList.add(stuff.buildList(3, 1, 4));
		System.out.println(stuff.collectNodes(edgeList));
		
		edgeList = new ArrayList<ArrayList<Integer>>();
		edgeList.add(stuff.buildList(1, 2, 3));
		edgeList.add(stuff.buildList(2, 3, 444));
		edgeList.add(stuff.buildList(3, 2, 44));
		System.out.println(stuff.collectNodes(edgeList));
		
		
		System.out.println("================================ setInfinity =================================================");
		System.out.println(stuff.setInfinity(1, 0));
		System.out.println(stuff.setInfinity(2, 0));
		System.out.println(stuff.setInfinity(3, 1));
		System.out.println(stuff.setInfinity(4, 2));
		System.out.println(stuff.setInfinity(5, 3));
		System.out.println(stuff.setInfinity(6, 3));
	}

}
