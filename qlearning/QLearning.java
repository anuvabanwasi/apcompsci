import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of Q-Learning algorithm
 * 
 * An unsupervised learning algorithm to find the shortest path from initial state to goal state 
 * 
 * Reference - http://mnemstudio.org/path-finding-q-learning-tutorial.htm
 * 
 * @author Anuva
 *
 */
public class QLearning {
	
	int n;					// number of states
	
	int[][] R;				// reward matrix
	
	int[][] Q ;				// q-matrix - rows represent the current state and columns represent the possible actions leading to the next state
	
	double gamma = 0.8;		// gamma value
	
	int initialState;		// initial state
	
	int goalState;			// goal state
	
	int trials = 100;		// max trials for convergence
	
	QLearning(int[][]R, int goalState){
		this.R = R;
		Q = new int[R.length][R[0].length];
		n = R.length;
		initialState = getRandomState(n);								// set initial state	
		this.goalState = goalState;
	}
	
	public static void main(String[] args){
		
		int[][] R = { 							// reward matrix similar to adjacency matrix representation of graph
				  {-1, -1, -1, -1,  0, -1},		// rows represent state and columns represent action
			      {-1, -1, -1,  0, -1, 100},	// state not directly connected to the target state have zero reward
			      {-1, -1, -1,  0, -1, -1},		// state directly connected to the target state have instant reward of 100
			      {-1,  0,  0, -1,  0, -1},		// -1 when there isn't a link between nodes
			      { 0, -1, -1,  0, -1, 100},
			      {-1,  0, -1, -1,  0, 100}
		};
	
		QLearning ql = new QLearning(R, 5);		
		
		ql.qLearning();							// initiate learning for goal state
	}

	/**
	 * Implement qLearning algorithm
	 * @param goalState integer representing final state
	 */
	private void qLearning() {

		int cnt = 1;
		
		boolean hasConverged = false;
		
		int[][] prevQ = makeArrCopy(Q);									// make a copy of Q, used to determine convergence
		
		while(!hasConverged){											// convergence is defined as when the currentQ and prevQ are identical (have not changed) for 10 episodes
			
			runEpisode(goalState);										// run episode
			
			boolean identical = compareMatrix(prevQ, Q);				// check if current Q matrix and previous Q matrix are identical
			
			if(identical)												// if identical, increment count
				cnt++;			
			else
				cnt = 0;
			
			prevQ = makeArrCopy(Q);										// reset previousQ
			
			if(cnt == trials)
				hasConverged = true;									// check for convergence
		}
		
		System.out.println("Initial state " + initialState);
		System.out.println("Goal state " + goalState);
		System.out.println("\nQ matrix ");
		printMatrix(Q);
		System.out.println("\nPath from " + initialState + " to " + goalState);
		printPath();
	}

	/**
	 * Run an episode to reach goal state
	 * @param goalState integer representing goal state
	 */
	private void runEpisode(int goalState) {
		
		int currentState = getRandomState(n);								// set current state
				
		while(currentState != goalState) {									// loop while current state is not goal state
			
			int[] possibleActions = getPossibleActions(currentState);	    // list of possible actions for current state
			
			int action = getRandomAction(possibleActions);					// select one of the possible actions randomly
			
			int nextState = action;											// this becomes the next state
	
			int[] nextActions = getPossibleActions(nextState);			    // list of possible actions for next state
					
			Q[currentState][action] = R[currentState][action] + (int)(gamma * getMaxValue(Q, nextState, nextActions));	// find the action with the highest Q value
			
			currentState = nextState;										// set current state to next state
			
		}
	}
	
	/**
	 * Find the action with the highest Q value
	 * @param Q	Q matrix
	 * @param nextState	integer representing next state
	 * @param nextActions integer array representing possible actions from next state
	 * @return
	 */
	private int getMaxValue(int[][] Q, int nextState, int[] nextActions) {
		
		int maxQ = Integer.MIN_VALUE;
		
		for(int i = 0; i < nextActions.length; i++){
			if(Q[nextState][nextActions[i]] > maxQ){
				maxQ = Q[nextState][nextActions[i]];
			}
		}
		
		return maxQ;
	}
	
	/**
	 * Get index of max value in Q matrix for specified state
	 * @param state 
	 * @return index of max value in Q matrix for specified state
	 */
	private int maxQIndex(int state) {

		int maxQ = Integer.MIN_VALUE;
		int maxIndex = -1;
		
		for(int j = 0; j < Q[0].length; j++){
			if(Q[state][j] > maxQ){
				maxQ = Q[state][j];
				maxIndex = j;
			}
		}
		
		return maxIndex;
	}
	
	/**
	 * Pick a random action from integer array of actions
	 * @param actions list of actions for a given state
	 * @return randomly selected action
	 * 
	 */
	private int getRandomAction(int[] actions) {
		
		int i = (int) (actions.length * Math.random());
		return actions[i];
		
	}

	/**
	 * Get possible actions for a given state
	 * @param R Reward matrix
	 * @param state 
	 * @return integer array representing list of possible actions at a given state
	 */
	private int[] getPossibleActions(int state) {
		List<Integer> actions = new ArrayList<Integer>();
		
		for(int j = 0; j < R[0].length; j++){
			if(R[state][j] != -1)
				actions.add(j);
		}
		
		return toArray(actions);
	}
	
	/**
	 * Get a random state from 0 to n
	 * @param n
	 * @return
	 */
	private int getRandomState(int n){
		return (int) (n * Math.random());
	}
	
	/**
	 * Compare 2 matrices
	 * @param initialQ first matrix
	 * @param revisedQ second matrix
	 * @return
	 */
	private boolean compareMatrix(int[][] initialQ, int[][] revisedQ){
		
		for(int i = 0; i < initialQ.length; i++){
			for(int j = 0; j < initialQ[0].length; j++){
				if(initialQ[i][j] != revisedQ[i][j])
					return false;
			}
		}
		return true;
	}

	/**
	 * Convert array list to integer array
	 * @param list 
	 * @return int[]
	 */
	private int[] toArray(List<Integer> list){
		int[] arr = new int[list.size()];
		
		for(int i = 0; i < arr.length; i++)
			arr[i] = list.get(i);
		
		return arr;
	}
	
	/**
	 * Make a copy of a 2d array
	 * @param matrix	
	 * @return 2d integer array representing copy of matrix
	 */
	private int[][] makeArrCopy(int[][] matrix){
		int [][] result = new int[matrix.length][matrix[0].length];
		
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				result[i][j] = matrix[i][j];
			}
		}
		
		return result;
	}
	
	/**
	 * Print path from initial state to goal state
	 */
	private void printPath(){
		
		int state = initialState;
		
		while(state != goalState){
			System.out.print(state + " -> ");
			state = maxQIndex(state);		
		}
		System.out.print(goalState);

	}

	private void printMatrix(int[][] q) {
		for(int i = 0; i < q.length; i++){
			for(int j = 0; j < q[0].length; j++){
				System.out.print(q[i][j] + " ");
			}
			System.out.println();
		}		
	}

	private void printArr(int[] arr) {
		for(int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		
		System.out.println();
	}
}
