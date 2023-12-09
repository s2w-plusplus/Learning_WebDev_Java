package POJO;

public class Voter {
private int vID;	
private String name;
private String email;
private String passWd;
private boolean hasVoted;
private String role;

public Voter(int vID, String name, String email, String passWd, boolean hasVoted,String role) {
	super();
	this.vID = vID;
	this.name = name;
	this.email = email;
	this.passWd = passWd;
	this.hasVoted = hasVoted;
	this.role=role;
	}

public int getvID() {
	return vID;
}
public String getName() {
	return name;
}
public String getEmail() {
	return email;
}
public String getPassWd() {
	return passWd;
}
public boolean hasVoted() {
	return hasVoted;
}
public void doneVoting() {
	this.hasVoted = true;
}
public String getRole() {
	return role;
}

@Override
public String toString() {
	return "Voter [Voter ID= " + vID + ", Name= " + name + ", Email= " + email +
			", Password= " + passWd + ", Voting Status= " + hasVoted+ "]";
}

}
