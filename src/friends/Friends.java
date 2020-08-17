package friends;

import java.util.ArrayList;

import structures.Queue;
import structures.Stack;

public class Friends {

	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {

		boolean s1c=false,s2c=false;
		ArrayList<String> results = new ArrayList<String>();
		p1 = p1.toLowerCase();
		p2 = p2.toLowerCase();
		Person[] ppl=g.members;
		for(int x=0;x<ppl.length;x++)
		{
			if(!s1c||!s2c)
			{
				if(p1.equals(ppl[x].name))
					s1c=true;
				if(p2.equals(ppl[x].name))
					s2c=true;
			}
			if(x==ppl.length-1&&(!s1c||!s2c))
			{
				return null;
			}
		}
		Queue x = new Queue();
		int ptr;
		Person u;
		Friend hold;
		int endIndex;


		int [] d = new int[ppl.length];
		Person [] path = new Person[ppl.length];

		for(int i = 0; i<ppl.length; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		java.util.HashMap<String,Integer> hashy=g.map;
		endIndex = hashy.get(p2);
		d[endIndex] = 0;
		x.enqueue(ppl[endIndex]);
		java.util.HashMap<String,Integer> myMap = g.map;
		while(!x.isEmpty()) {
			u = (Person) x.dequeue();
			hold = u.first;


			while(hold != null) {

				int cnt =hold.fnum;
				if(d[cnt] == Integer.MAX_VALUE){

					d[cnt] = d[hashy.get(u.name)] + 1;
					path[cnt] = u;
					x.enqueue(ppl[cnt]);

				}
				hold = hold.next;
				//cnt++;
			}
		}

		if(d[hashy.get(p1)] == Integer.MAX_VALUE || p1.equals(p2)){
			//        throw new Exception("No path exists");
		}

		ptr = hashy.get(p1);
		java.util.ArrayList<String> names = new java.util.ArrayList<>();
		String namelist=null;
		boolean ispath=false;
		try{
			while(!p2.equals(ppl[ptr].name))
			{

				if(namelist==null) {
					namelist = ppl[ptr].name + "--";
					names.add(ppl[ptr].name);
				}
				else {
					namelist = namelist + ppl[ptr].name + "--";
					names.add(ppl[ptr].name);
				}
				//System.out.print(adjLists[ptr].name + "--");
				ptr = hashy.get(path[ptr].name);
			}
			names.add(ppl[ptr].name);
			ispath=true;
		}catch(Exception e){};
		if(ispath)
			return names;

		return null;

	}

	/**
	 * Finds all cliques of students in a given school.
	 *
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 *
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {

		Person[] ppl=g.members;
		school=school.toLowerCase();
		java.util.ArrayList<java.util.ArrayList<String>> allC = new java.util.ArrayList<>();

		if(school== null)
			return null ;
		for(int x=0;x<ppl.length;x++)
		{
			if(school.equals(ppl[x].school))
			{
				x=ppl.length;
			}
			if(x==ppl.length-1)
			{
				return null;
			}
		}
		Boolean[] visited=new Boolean[ppl.length];
		Person currvert,holdervert;
		Friend neighborhunt;
		int cliquenumber=0;
		for(int x=0;x<ppl.length;x++)
			visited[x]=false;

		for(int x=0;x<ppl.length;x++)
		{
			if(visited[x]!=true)
			{
				if(school.equals(ppl[x].school))
				{
					ArrayList<String> n = new ArrayList<>();
					Queue edgeq=new Queue();
					n.add(ppl[x].name);
					visited[x]=true;
					edgeq.enqueue(ppl[x]);
					while(edgeq.isEmpty()!=true)
					{
						currvert=(Person)edgeq.dequeue();
						neighborhunt=currvert.first;
						while(neighborhunt!=null)
						{
							if(visited[neighborhunt.fnum]!=true)
							{
								holdervert=ppl[neighborhunt.fnum];
								if(school.equals(holdervert.school)!=true)
								{
									visited[neighborhunt.fnum]=true;
									continue;
								}
								n.add(holdervert.name);
								visited[neighborhunt.fnum]=true;
								edgeq.enqueue(holdervert);


							}

							neighborhunt=neighborhunt.next;
						}
						boolean a = false;
						if(allC.size() == 0)
							allC.add(n);
						for(int i = 0; i < allC.size(); i++){
							a = hC(allC.get(i), n);
							if(!a){
								break;
							}
						}
						if(a == true)
							allC.add(n);
					}
				}
				else
					visited[x]=true;

			}



		}
		return allC;


	}

	private static boolean hC(ArrayList<String> y , ArrayList<String> z){
		if(y.size() != z.size())
			return true;
		int counter = 0;
		for(int i = 0; i <y.size(); i++)
			for (int a = 0; a<z.size(); a++){
				if(z.get(a).equals(y.get(i)))
					counter++;
			}
		if(counter == y.size())
			return false;

		return true;
	}

	/**
	 * Finds and returns all connectors in the graph.
	 *
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {

		Person[] ppl=g.members;
		boolean[] visited = new boolean[ppl.length];
		int[] backNum = new int[ppl.length];
		ArrayList<String> x = new ArrayList<>();

		/** COMPLETE THIS METHOD **/

		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION



		int[] dfsNum = new int[ppl.length];
		int dfsNumCounter = 0, backNumCounter = 0;


		java.util.TreeMap<Integer, Integer> connectors = new java.util.TreeMap<Integer, Integer>();


		for (int i = 0; i < ppl.length; i++) {

			if (!visited[i]) {
				connectors.put(i, 1);



				hC2(g,ppl[i], visited, dfsNum, backNum, dfsNumCounter, backNumCounter, connectors);
			}
		}

		if(connectors.containsValue(3) == false) {
			return null;
		}

		java.util.Set<java.util.Map.Entry<Integer, Integer>> connectorEntries = connectors.entrySet();
		java.util.Iterator<java.util.Map.Entry<Integer, Integer>> it = connectorEntries.iterator();

		while (it.hasNext()) {
			java.util.Map.Entry<Integer, Integer> entry = it.next();

			if (entry.getValue() == 3) {
				x.add(ppl[entry.getKey()].name);

			}
		}

		return x;


	}

	private static void hC2(Graph g,Person user, boolean[] visited, int[] dfsNum, int[] backNum, int dfsNumCounter, int backNumCounter, java.util.TreeMap<Integer, Integer> connectors) {

		Person[] ppl=g.members;
		java.util.HashMap<String,Integer>  h=g.map;
		int userIndex = h.get(user.name);
		visited[userIndex] = true;
		dfsNum[userIndex] = dfsNumCounter;  dfsNumCounter++;
		backNum[userIndex] = backNumCounter;  backNumCounter++;


		Friend neighbor = user.first;
		while (neighbor != null) {


			if (!visited[neighbor.fnum]) {
				Person next = ppl[neighbor.fnum];
				hC2(g,next, visited, dfsNum, backNum, dfsNumCounter, backNumCounter, connectors);


				if (dfsNum[userIndex] > backNum[neighbor.fnum]) {
					backNum[userIndex] = Math.min(backNum[userIndex], backNum[neighbor.fnum]);
				}
				else {

					if (connectors.get(userIndex) == null) {

						connectors.put(userIndex, 3);
					}
					else if (connectors.get(userIndex) == 1) {

						connectors.remove(userIndex);
						connectors.put(userIndex, 2);
					}
					else if (connectors.get(userIndex) == 2) {

						connectors.remove(userIndex);
						connectors.put(userIndex, 3);
					}
				}
			}

			else {
				backNum[userIndex] = Math.min(backNum[userIndex], dfsNum[neighbor.fnum]);
			}
			neighbor = neighbor.next;
		}
	}
}