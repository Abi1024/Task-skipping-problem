import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
	static double K = 1;
	static HashMap<ArrayList<Integer>,Values> explored_nodes = new HashMap<>();


    public static void main(String[] args) {
		ArrayList<Integer> obj = new ArrayList<Integer>(Arrays.asList(43,16,15,85,99,69,27,79,45,32));
        double cost = solve(obj,1).cost;
        System.out.println("Optimal cost: " + cost);
        String result = "";
        boolean flag = true;
        ArrayList<Integer> current_node = new ArrayList<Integer>(obj);;
        while(current_node.size() > 0) {
			System.out.println("Traversing: " + current_node.toString());
        	String next = explored_nodes.get(current_node).next;
        	result += next;
			ArrayList<Integer> problem2 = new ArrayList<Integer>(current_node);
			int task = problem2.remove(0);
        	if (next.equals("S")){
        		problem2.add(task);
			}
        	current_node = problem2;
		}
        System.out.println("Optimal solution: " + result);
		//ArrayList<Integer> obj2 = new ArrayList<Integer>(Arrays.asList(4,2,9,7));
		//System.out.println("Cost of " + obj2.toString() + " " + explored_nodes.get(obj2).cost);
    }

    public static Values solve(ArrayList<Integer> problem, int E)
	{
		//System.out.println("Solving problem: " + problem.toString() + " with E: " + E);
		if (explored_nodes.containsKey(problem))
		{
			return explored_nodes.get(problem);
		}
		explored_nodes.put(problem,new Values(Double.POSITIVE_INFINITY,""));
		if (problem.size() == 0)
		{
			explored_nodes.put(problem, new Values(0, ""));
			//System.out.println("Done with this problem: " + problem.toString() + " and its cost: " + 0);
			return explored_nodes.get(problem);
		}
		else if (problem.size() == 1)
		{
			double cost = (Double.valueOf(problem.get(0)))/ E;
			explored_nodes.put(problem, new Values(cost, "D"));
			//System.out.println("Done with this problem: " + problem.toString() + " and its cost: " + cost);
			return explored_nodes.get(problem);
		}
		else
		{
			ArrayList<Integer> problem2 = new ArrayList<Integer>(problem);
			int task = problem2.remove(0);
			ArrayList<Integer> problem3 = new ArrayList<Integer>(problem2);
			problem2.add(task);
			double skip_cost = K + solve(problem2, E).cost;
			double do_cost = Double.valueOf(task)/ Double.valueOf(E) + solve(problem3, E + task).cost;
			if (skip_cost < do_cost)
			{
				explored_nodes.put(problem, new Values(skip_cost, "S"));
				//System.out.println("Done with this problem: " + problem.toString() + " and its cost: " + skip_cost);
				return explored_nodes.get(problem);
			}
			else
			{
				explored_nodes.put(problem, new Values(do_cost, "D"));
				//System.out.println("Done with this problem: " + problem.toString() + " and its cost: " + do_cost);
				return explored_nodes.get(problem);
			}
		}
	}
}
