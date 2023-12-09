package tester;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.CandidateDAOImpl;
import DAO.VoterDAOImpl;
import POJO.Candidate;
import POJO.Voter;
import static utils.DButils.closeConnection;;

public class TestElection {

	public static void main(String[] args) {
		
	
		try (Scanner scan = new Scanner(System.in)) {
			
			ArrayList<Voter> voterList=new ArrayList<>();
			ArrayList<Candidate> candidateList=new ArrayList<>();
			VoterDAOImpl Vdao = new VoterDAOImpl();
			CandidateDAOImpl Cdao = new CandidateDAOImpl();
			
			boolean Exit = false;
			while (!Exit) {
				System.out.println("1. Voter Login."); 
				System.out.println("2. Candidate Registration.");
				System.out.println("3. List All Candidates");
				System.out.println("4. Cast A Vote");
				System.out.println("5. Exit");
				
				try {
						switch (scan.nextInt()) {
							
						case 1: 
									System.out.println("Enter Email & Password");
									String tempnm=scan.next();
									String tempPwd=scan.next();
									for(Voter vt:voterList)
										if(vt.getName().equals(tempnm) && vt.getPassWd().equals(tempPwd)) {
											System.out.println("You have already Logged In");
											break;
										}
									Voter v=Vdao.authenticateVoter(tempnm,tempPwd);
									if(v!=null) {
										System.out.println("Voter List Entry Authenticated! Details as follows....");
										System.out.println("Name:"+v.getName()+" ,Email:"+v.getEmail()+
												" ,Status:"+(v.hasVoted()?"Voted":"Not Voted"));
										voterList.add(v);
									}	
									else
									    System.out.println("Login Failed");	
									break;
						case 2: 
									System.out.println("Enter Name & Party-Name");
									String status=Cdao.registerCandidate(scan.next(),scan.next());
									System.out.println(status);
									break;
						case 3: 
									candidateList=Cdao.dispCandidateList();
									candidateList.forEach(System.out :: println);
									break;
						case 4: 
									System.out.println("Enter your Voter ID");
									int vidtemp=scan.nextInt();
									boolean found=false;
									for(Voter vt:voterList)
										if(vt.getvID() == vidtemp) {
											if(!vt.hasVoted()){
												System.out.println("Cast a vote for one of the candidates(Enter Candidate ID)...... ");
												candidateList=Cdao.dispCandidateList();
												candidateList.forEach(c -> System.out.println(c));
												Cdao.updateVotes(scan.nextInt());
												Vdao.updateVotingStatus(vidtemp);
												vt.doneVoting();
												System.out.println("The Vote has been casted successfully");
												found=true;
												break;
											}
											else {
												System.out.println("The Voter belonging to this Voter-ID has already casted the vote");
												found=true;
												break;
											}
										}
									if(!found)
										System.out.println("The Voter belonging to this Voter-ID has not been authenticated");
									break;
						case 5: 
									Exit=true;
									Vdao.cleanUp();
									Cdao.cleanUp();
									closeConnection();
									break;
						default:
								System.out.println("Invalid Choice");
						}
					}catch (SQLException s) {
						s.printStackTrace();
					}	
			 scan.nextLine();
			}					
		}catch (Exception s) {
			s.printStackTrace();
		}						
	}
}