package com.library.main;

import java.util.Scanner;

import com.library.service.LibraryService;

import java.util.List;

import com.library.model.*;;

public class App {

    private static Scanner in = new Scanner(System.in);
    private static LibraryService service = new LibraryService();
    public static void main(String[] args) {
        System.out.println("====== LIBRARY MANAGEMENT SYSTEM ======");

        while(true){
            showMenu();
            System.out.println();
            int choice = getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addBookUI();
                    break;
                case 2:
                    addMemberUI();
                    break;
                case 3:
                    issueBookUI();
                    break;
                case 4:
                    returnBookUI();
                    break;
                case 5:
                    updateBookUI();
                    break;
                case 6:
                    deleteBookUI();
                    break;
                case 7:
                    updateMemberUI();
                    break;
                case 8:
                    deleteMemberUI();
                    break;
                case 9:
                    viewIssuedBookUI();
                    break;
                case 10:
                    viewIssuedMemberUI();
                    break;
                case 11:
                    exitUI();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
            
        }
    }

    private static int getInt(String message){
        System.out.print(message);
        while(!in.hasNextInt()){
            System.out.println("Invalid input, enter a number: ");
            in.next();
        }
        int value = in.nextInt();
        in.nextLine();
        return value;
    }

    private static String getString(String message){
        System.out.print(message);
        return in.nextLine();
    }

    private static void showMenu(){
        System.out.println("\n ====== MENU ======");
        System.out.println("1. Add Book");
        System.out.println("2. Add Member");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        System.out.println("5. Update Book");
        System.out.println("6. Delete Book");
        System.out.println("7. Update Member");
        System.out.println("8. Delete Member");
        System.out.println("9. All Issued Books");
        System.out.println("10. Issued Member Record");
        System.out.println("11. Exit");
    }

    private static void addBookUI(){
        String title = getString("Enter book title: ");
        String author = getString("Enter author's name: ");
        int totalCopies = getInt("Enter total copies: ");
        String result = service.addBook(title, author, totalCopies);
        System.out.println(result);
    }

    private static void addMemberUI(){
        String name = getString("Enter member's name: ");
        String email = getString("Enter email: ");
        String phone = getString("Enter phone number: ");
        String result = service.addMember(name, email, phone);
        System.out.println(result);
    }

    private static void issueBookUI(){
        int bookId = getInt("Enter book ID: ");
        int memberId = getInt("Enter member ID: ");
        String result = service.issueBook(bookId, memberId);
        System.out.println(result);
    }

    private static void returnBookUI(){
        int issueId = getInt("Enter issue ID: ");
        String result = service.returnBook(issueId);
        System.out.println(result);
    }

    private static void updateBookUI(){
        int bookId = getInt("Enter book ID: ");
        String title = getString("Enter book title: ");
        String author = getString("Enter author's name: ");
        int totalCopies = getInt("Enter total copies: ");
        int availableCopies = getInt("Enter available copies: ");
        String result = service.updateBook(bookId,title, author, totalCopies, availableCopies);
        System.out.println(result);
    }

    private static void deleteBookUI(){
        int bookId = getInt("Enter book ID: ");
        String result = service.deleteBook(bookId);
        System.out.println(result);
    }

    private static void updateMemberUI(){
        int memberId = getInt("Enter member ID: ");
        String name = getString("Enter member's name: ");
        String email = getString("Enter email: ");
        String phone = getString("Enter phone number: ");
        String result = service.updateMember(memberId, name, email, phone);
        System.out.println(result);
    }

    private static void deleteMemberUI(){
        int memberId = getInt("Enter member ID: ");
        String result = service.deleteMember(memberId);
        System.out.println(result);
    }

    private static void viewIssuedBookUI(){
        List<IssuedBook> result = service.getAllIssuedBooks();
        System.out.println("\n===== All Issued Books =====");
        if(result.isEmpty()){
            System.out.println("No record found.");
            return;
        }
        result.forEach(System.out::println);
    }

    private static void viewIssuedMemberUI(){
        int memberId = getInt("Enter member ID: ");
        List<IssuedBook> result = service.getIssuedBooksByMemberId(memberId);
        System.out.println("\n===== Issue History for Member: " + memberId + " =====");
        if(result.isEmpty()){
            System.out.println("No record found.");
            return;
        }
        result.forEach(System.out::println);
    }

    private static void exitUI(){
        System.out.println("Exiting... Thank you!");
    }
}
