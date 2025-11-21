import java.util.Scanner;
class Banking_application{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String name = in.nextLine();
		System.out.print("Enter your id: ");
		int id = in.nextInt();
		BankAccount account = new BankAccount(name,id);
		account.ShowMenu(in);
		in.close();
	}
}
class BankAccount{
	String name;
	int id;
	int balance;
	int previousTransaction;
	BankAccount(String name, int id){
		this.name = name;
		this.id = id;
	}
	void deposit(int amount){
		if(amount>0){
			balance += amount;
			previousTransaction = amount;
		}
		else{
			System.out.println("Invalid input");
		}
	}
	void withdraw(int amount){
		if(amount>0){
			if(balance >= amount){
				balance -= amount;
				previousTransaction = -amount;
			}
			else{
				System.out.println("Insufficient balance");
			}
		}
		else{
			System.out.println("Invalid input");
		}
	}
	void get_previousTransaction(){
		if(previousTransaction>0){
			System.out.println("Deposited: "+previousTransaction);
		}
		else if(previousTransaction<0){
			System.out.println("Withdrawn: "+Math.abs(previousTransaction));
		}
		else{
			System.out.println("No transaction occured!");
		}
	}
	void ShowMenu(Scanner in){
		char option = '\0';
		System.out.println("Welcome "+name);
		System.out.println("Your id is "+id);
		do{
			System.out.println();
			System.out.println("A. Check balance");
			System.out.println("B. Deposit");
			System.out.println("C. Withdraw");
			System.out.println("D. Check Previous Transaction");
			System.out.println("E. Exit");
			System.out.println();
			System.out.println("======================");
			System.out.println("Enter Option");
			System.out.println("======================");
			option = in.next().toUpperCase().charAt(0);
			switch(option){
				case 'A':
					System.out.println();
					System.out.println("======================");
					System.out.println("Balance: "+balance);
					System.out.println("======================");
					break;
				case 'B':
					System.out.println();
					System.out.println("======================");
					System.out.println("Enter Amount");
					System.out.println("======================");
					int amount1 = in.nextInt();
					deposit(amount1);
					System.out.println();
					System.out.println("======================");
					System.out.println("Transaction completed successfully!");
					System.out.println("======================");
					break;
				case 'C':
					System.out.println();
					System.out.println("======================");
					System.out.println("Enter Amount");
					System.out.println("======================");
					int amount2 = in.nextInt();
					withdraw(amount2);
					System.out.println();
					System.out.println("======================");
					System.out.println("Transaction completed successfully!");
					System.out.println("======================");
					break;
				case 'D':
					System.out.println();
					System.out.println("======================");
					get_previousTransaction();
					System.out.println("======================");
					break;
				case 'E':
					System.out.println();
					System.out.println("======================");
					System.out.println("Thank you for using our service!");
					System.out.println("======================");
					break;
				default:
					System.out.println();
					System.out.println("======================");
					System.out.println("Invalid input, Try again!!");
					System.out.println("======================");
					break;
			}
			
		}while(option != 'E');
	}
}