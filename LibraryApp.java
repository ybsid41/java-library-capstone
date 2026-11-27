import java.util.*;
public class LibraryApp {
    private static Library library;
    private void processCommand(String line){
        String[] lineSplit = line.split("\\|");
        for(int i = 0; i < lineSplit.length; i++){
            lineSplit[i] = lineSplit[i].trim();
        }
        try{
            switch (lineSplit[0].toUpperCase()) {
                case "ADD_BOOK":
                    if(lineSplit.length != 4) throw new IllegalArgumentException();
                    library.addBook(lineSplit[1], lineSplit[2], lineSplit[3]);
                    System.out.println("OK");
                    break;
                
                case "ADD_MAGAZINE":
                    if(lineSplit.length != 4) throw new IllegalArgumentException();
                    library.addMagazine(lineSplit[1], lineSplit[2], lineSplit[3]);
                    System.out.println("OK");
                    break;
                
                case "ADD_REFERENCE":
                    if(lineSplit.length != 4) throw new IllegalArgumentException();
                    library.addReferenceBook(lineSplit[1], lineSplit[2], lineSplit[3]);
                    System.out.println("OK");
                    break;
                
                case "ADD_MEMBER":
                    if(lineSplit.length != 4) throw new IllegalArgumentException();
                    library.addMember(lineSplit[1], lineSplit[2], lineSplit[3]);
                    System.out.println("OK");
                    break;

                case "BORROW":
                    if(lineSplit.length != 3) throw new IllegalArgumentException();
                    Loan loan = library.borrowItem(lineSplit[1], lineSplit[2]);
                    System.out.println("OK due on day " + loan.getDueDay());
                    break;

                case "RETURN":
                    if(lineSplit.length != 2) throw new IllegalArgumentException();
                    int amount = library.returnItem(lineSplit[1]);
                    System.out.println("OK fine " + amount);
                    break;

                case "ADVANCE":
                    if(lineSplit.length != 2) throw new IllegalArgumentException();
                    int day = library.advanceDay(Integer.parseInt(lineSplit[1]));
                    System.out.println("OK today is day " + day);
                    break;

                case "SEARCH":
                    if(lineSplit.length != 2) throw new IllegalArgumentException();
                    List<LibraryItem> items = library.searchItems(lineSplit[1]);
                    items.sort(Comparator.comparing(LibraryItem::getTitle));
                    if(items.size() == 0){
                        System.out.println("NONE");
                        break;
                    }
                    for(LibraryItem i : items){
                        System.out.println(i.getId() + "|" + i.getType() + "|" + i.getTitle());
                    }
                    break;

                case "LOANS":
                    if(lineSplit.length != 2) throw new IllegalArgumentException();
                    List<Loan> loans = library.getMemberLoans(lineSplit[1]);
                    loans.sort(Comparator.comparing(Loan::getDueDay).thenComparing(l -> l.getItem().getId()));
                    if(loans.size() == 0){
                        System.out.println("NONE");
                        break;
                    }
                    for(Loan i : loans){
                        System.out.println(i.getItem().getId() + "|" + i.getItem().getTitle() + "|" + i.getDueDay());
                    }
                    break;

                case "OVERDUE":
                    if(lineSplit.length != 1) throw new IllegalArgumentException();
                    List<Loan> overdues = library.getOverdueLoans();
                    overdues.sort(Comparator.comparingInt((Loan l) -> l.getDaysLate(library.advanceDay(0))).reversed().thenComparing(l -> l.getItem().getId()));

                    if(overdues.size() == 0){
                        System.out.println("NONE");
                        break;
                    }
                    for(Loan i : overdues){
                        System.out.println(i.getItem().getId() + "|" + i.getMember().getId() + "|" + i.getDaysLate(library.advanceDay(0)) + "|" + i.calculateFine(library.advanceDay(0)));
                    }
                    break;
                
                case "INVENTORY":
                    if(lineSplit.length != 1) throw new IllegalArgumentException();
                    List<LibraryItem> inventory = library.getInventory();
                    if(inventory.isEmpty()){
                        System.out.println("NONE");
                        break;
                    }
                    inventory.sort(Comparator.comparing(LibraryItem::getId));
                    for(LibraryItem i : inventory){
                        System.out.print(i.getId()+"|"+i.getType()+"|");
                        if(! i.isBorrowable()){
                            System.out.println("NOT_BORROWABLE");
                            continue;
                        }
                        Member m = library.isItemOnLoan(i.getId());
                        if(m == null){
                            System.out.println("AVAILABLE");
                        }
                        else{
                            System.out.println("ON_LOAN|" + m.getId());
                        }
                    }
                    break;

                default:
                    break;
            }
        }
        catch (LibraryException e) {
            System.out.println("ERROR " + e.getMessage());
        } catch (IllegalArgumentException e) {
            String msg = e.getMessage();
            System.out.println(msg == null || msg.isBlank() ? "ERROR Invalid command" : "ERROR " + msg);
        }
    }
    public void run(){
        Scanner sc = new Scanner(System.in);
        library = new Library();
        while(sc.hasNextLine()){
            String line = sc.nextLine().trim();
            if(line.isEmpty() || line.startsWith("#")) continue;
            processCommand(line);
        }
        sc.close();
    }
    public static void main(String[] args) {
        LibraryApp app = new LibraryApp();
        app.run();
    }
}
