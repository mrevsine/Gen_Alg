package genAlg;

public class Agent {

	private String target;
	private String pheno;
	
	public Agent(String target, String pheno) {
		this.target = target;
		this.pheno = pheno;
	}
	
	public String getTarget() {
		return target;
	}
	
	public String getPheno() {
		return pheno;
	}
	
	public void setPheno(String s) {
		this.pheno = s;
	}
	
	public int getFitness() {
		return getLevenshteinDistance(target, pheno);
	}
	
	/**
	 * Main function in this class
	 * Returns the Levenshtein distance between two strings
	 * This represents the number of single-step changes necessary to transform one string into another
	 * These steps can be an insertion, deletion, or single-letter change
	 * Slow for large strings- takes time O(a.length * b.length) 
	 * @param a String A
	 * @param b String B
	 * @return Levenshtein Distance
	 */
	public static int getLevenshteinDistance(String a, String b) {
		
		int[][] table = new int[a.length() + 1][b.length() + 1];
		
		for (int i = 1; i < table.length; i++) {
			table[i][0] = i;
		}
		for (int i = 1; i < table[0].length; i++) {
			table[0][i] = i;
		}
		
		for (int i = 1; i < table.length; i++) {
			for (int j = 1; j < table[i].length; j++) {
				int cost = a.substring(i - 1, i).equalsIgnoreCase(b.substring(j - 1, j)) ? 0 : 1;
				table[i][j] = min(table[i - 1][j] + 1, table[i][j - 1] + 1, table[i-1][j-1] + cost);
			}
		}
		
		return table[table.length - 1][table[0].length - 1];
		
	}
	
	public static int min(int a, int b, int c) {
		
		return a < b ? a < c ? a : c : b < c ? b : c;
		
	}
}
