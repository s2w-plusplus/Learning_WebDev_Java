package dependent;


import dependency.HttpTransport;
import dependency.TestTransport;
import dependency.Transport;

public class ATMImpl implements ATM {
	private Transport myTransport;
	
	public ATMImpl() {
		System.out.println("in cnstr of " +getClass().getName()+" "+myTransport);
	}
	
	//setter based dependency injection
	public void setMyTransport(Transport myTransport) {
		System.out.println("setting dependency to "+myTransport);
		this.myTransport = myTransport;
	}

	
	//init style method
	public void  myInit(){
		System.out.println("in myInit(), dependency is "+ myTransport);	
	}
	
	//Destroy style method
		public void  myDestroy(){
			System.out.println("in myDestroy(), dependency is "+ myTransport);	
		}
	@Override
	public void deposit(double amt) {
		System.out.println("depositing "+amt);
		byte[] data=("depositing "+amt).getBytes();
		myTransport.informBank(data);

	}

	@Override
	public void withdraw(double amt) {
		System.out.println("withdrawing "+amt);
		byte[] data=("withdrawing "+amt).getBytes();
		myTransport.informBank(data);
	}
	
}
