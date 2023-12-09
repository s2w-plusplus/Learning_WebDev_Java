package POJO;

public class Candidate {
	private int cID;
	private String name;
	private String party;
	private int votes;
	public Candidate(int cID, String name, String party, int votes) {
		super();
		this.cID = cID;
		this.name = name;
		this.party = party;
		this.votes = votes;
	}

	public int getcID() {
		return cID;
	}
	public String getName() {
		return name;
	}
	public String getParty() {
		return party;
	}
	public int getVotes() {
		return votes;
	}

	@Override
	public String toString() {
		return "Candidate [Candidate ID=" + cID + ", Name=" + name + ", Party=" + party + ", Votes=" + votes + "]";
	}
	
	
	
}
