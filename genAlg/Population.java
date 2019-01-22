package genAlg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Population {

	private List<Agent> agents;
	private String target;
	private int population_size;
	private int mutation_rate;
	private String letters = "abcdefghijklmnopqrstuvwxyz ";
	
	public Population(String target, int population_size, int mutation_rate) {
		this.target = target;
		this.population_size = population_size;
		this.mutation_rate = mutation_rate;
		this.agents = initializeAgents();
	}
	
	public List<Agent> getAgents() { return agents; }
	
	public String getTarget() { return target; }
	
	public int getPopulationSize() { return population_size; }
	
	public int getMutationRate() { return mutation_rate; }
	
	public String getLetters() { return letters; }
	
	public void setAgents(List<Agent> as) {
		this.agents = as;
	}
	
	public List<Agent> initializeAgents() {
		List<Agent> tempAgents = new ArrayList<Agent>();
		for (int i  = 0; i < population_size; i++) {
			String str = "";
			for (int j = 0; j < target.length(); j++) {
				int n = randomInt(0,27);
				str += letters.substring(n++, n);
			}
			tempAgents.add(new Agent(target, str));
		}
		return tempAgents;
	}
	
	public void selectAgents() {
		int n = (int) (population_size / 3);
		Agent[] as = listToArray(agents);
		Arrays.sort(as, new FitnessComparator());
		Agent[] as2 = new Agent[n];
		for (int i = 0; i < n; i++) {
			as2[i] = as[i];
		}
		List<Agent> newAs = arrayToList(as2);
		setAgents(newAs);
	}
	
	public void crossAgents() {
		Agent[] as = listToArray(agents);
		for (int i = 0; i < as.length; i++) {
			int a = randomInt(0,as.length);
			agents.add(new Agent(target, combinePhenos(as[i], as[a])));
			agents.add(new Agent(target, combinePhenos(as[a], as[i])));
		}
	}
	
	public void mutateAgents() {
		for (Agent a: agents) {
			if (randomInt(0,1000) < mutation_rate) {
				//mutation rate within agent is 10x mutation rate among agents
				int n = (int)(a.getPheno().length() * ((double)mutation_rate/100.0)) + 1;
				String phenotemp = a.getPheno();
				if (Math.random() < 0.5) {
					//point mutation
					for (int i = 0; i < n; i++) {
						int rand = randomInt(0, phenotemp.length());
						int num = randomInt(0,27);
						phenotemp = phenotemp.substring(0, rand) + letters.substring(num++,  num) + phenotemp.substring(rand + 1, a.getPheno().length());
					}
				} else {
					//frameshift mutation
					for (int i = 0; i < n; i++) {
						int rand1 = randomInt(0, phenotemp.length());
						int rand2 = randomInt(0, phenotemp.length());
						String uno = phenotemp.substring(rand1, rand1 + 1);
						if (rand1 < rand2) {
							phenotemp = phenotemp.substring(0, rand1) + phenotemp.substring(rand1 + 1, rand2) + uno + phenotemp.substring(rand2, phenotemp.length());
						} else {
							phenotemp = phenotemp.substring(0, rand2) + uno + phenotemp.substring(rand2, rand1) + phenotemp.substring(rand1 + 1, phenotemp.length());
						} 
					}
				}
				
				a.setPheno(phenotemp);
			}
		}
	}
	
	
	public String combinePhenos(Agent a, Agent b) {
		int n = (int) a.getPheno().length()/2;
		String str = a.getPheno().substring(0, n);
		str += b.getPheno().substring(n, b.getPheno().length());
		return str;
	}
	
	
	public static int randomInt(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	public static Agent[] listToArray(List<Agent> as) {
		Agent[] newAs = new Agent[as.size()];
		int i = 0;
		for (Agent a : as) {
			newAs[i++] = a;
		}
		return newAs;
	}

	public static List<Agent> arrayToList(Agent[] as) {
		List<Agent> newAs = new ArrayList<Agent>();
		for (int i = 0; i < as.length; i++) {
			newAs.add(as[i]);
		}
		return newAs;
	}

}