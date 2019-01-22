package genAlg;

public class Main {

	/**
	 * @author MahlerRevsine
	 * @param args
	 * Genetic algorithm that matches the input string
	 * Input: target string of characters a-z and any spaces
	 */
	
	
	public static void main(String[] args) {
		
		runGens("Optoro");
		
	}

	public static void runGens(String target) {
		String TARGET = target;
		int POP_SIZE = target.length()*10;
		int MUTATION_RATE = 10;
		int GENERATIONS = POP_SIZE * 1000;
		
		
		Population p = new Population(TARGET, POP_SIZE, MUTATION_RATE);
		System.out.println("Generation 0:");
		for (Agent a: p.getAgents()) {
			System.out.println(a.getFitness() + ": " + a.getPheno());
		}
		System.out.println("");
		System.out.println("");
		
		
		outerloop:
		for (int i = 0; i < GENERATIONS; i++) {
			
			p.selectAgents();
			p.crossAgents();
			p.mutateAgents();
			System.out.println("Generation " + (i + 1) + ":");
			for (Agent a: p.getAgents()) {
				System.out.println(a.getFitness() + ": " + a.getPheno());
			}
			
			
			for (Agent a: p.getAgents()) {
				if (a.getFitness() < 0.0001) {
					System.out.println("");
					System.out.println("");
					System.out.println("DONE After " + i + " Generations:   " + a.getPheno() );
					break outerloop;
				}
			}
			System.out.println("");
			System.out.println("");
		}
	}

	public static void runGens(String target, int pop_size, int mut_rate, int gens) {
		String TARGET = target;
		int POP_SIZE = pop_size;
		int MUTATION_RATE = mut_rate;
		int GENERATIONS = gens;
		
		
		Population p = new Population(TARGET, POP_SIZE, MUTATION_RATE);
		System.out.println("Generation 0:");
		for (Agent a: p.getAgents()) {
			System.out.println(a.getFitness() + ": " + a.getPheno());
		}
		System.out.println("");
		System.out.println("");
		
		
		outerloop:
		for (int i = 0; i < GENERATIONS; i++) {
			for (Agent a: p.getAgents()) {
				if (a.getFitness() < 0.0001) {
					System.out.println("DONE After " + i + " Generations:   " + a.getPheno() );
					break outerloop;
				}
			}
			p.selectAgents();
			p.crossAgents();
			p.mutateAgents();
			System.out.println("Generation " + (i + 1) + ":");
			for (Agent a: p.getAgents()) {
				System.out.println(a.getFitness() + ": " + a.getPheno());
			}
			System.out.println("");
			System.out.println("");
		}
	}
}
