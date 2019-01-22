package genAlg;

import java.util.Comparator;

public class FitnessComparator implements Comparator<Agent> {

	public int compare(Agent a, Agent b) {
		return a.getFitness() - b.getFitness();
	}
}
