import java.util.*;
public class Library {
    private int currentDay = 0;
    private Map<String, LibraryItem> items = new HashMap<>();
    private Map<String, Member> members = new HashMap<>();
    private Map<String, Loan> loans = new HashMap<>();
    public void addBook(String id, String title, String author){
        items.put(id, new Book(id, title, author));
    }
    public void addMagazine(String id, String title, String issue){
        items.put(id, new Magazine(id, title, issue));
    }
    public void addReferenceBook(String id, String title, String author){
        items.put(id, new ReferenceBook(id, title, author));
    }
    public void addMember(String id, String name, String type){
        members.put(id, switch(type.toUpperCase()){
            case "STUDENT" -> new Student(id, name);
            case "STAFF" -> new Staff(id, name);
            case "FACULTY" -> new Faculty(id, name);
            default -> throw new IllegalArgumentException("Unknown member type: " + type);
        });
    }
    public Loan borrowItem(String memberId, String itemId) throws LibraryException{
        if(!members.containsKey(memberId)) throw new MemberNotFoundException(memberId);
        if(!items.containsKey(itemId)) throw new ItemNotFoundException(itemId);
        if(!items.get(itemId).isBorrowable()) throw new ItemNotAvailableException(itemId + " cannot be borrowed");
        if(loans.containsKey(itemId)) throw new ItemNotAvailableException(itemId + " is on loan");
        if(getMemberLoans(memberId).size() == members.get(memberId).getMaxItems()) throw new BorrowLimitExceededException(memberId, members.get(memberId).getMaxItems());
        Loan loan = new Loan(items.get(itemId), members.get(memberId), currentDay);
        loans.put(itemId, loan);
        return loan;
    }
    public int returnItem(String itemId) throws LibraryException{
        if(! items.containsKey(itemId)) throw new ItemNotFoundException(itemId);
        if(! loans.containsKey(itemId)) throw new ItemNotOnLoanException(itemId);
        Loan loan = loans.remove(itemId);
        return loan.calculateFine(currentDay);
    }
    public int advanceDay(int days){
        if(days < 0) throw new IllegalArgumentException("Invalid number of days");
        currentDay += days;
        return currentDay;
    }
    public List<LibraryItem> searchItems(String text){
        List<LibraryItem> result = new ArrayList<>();
        for(LibraryItem i : items.values()){
            if(i.getTitle().toLowerCase().contains(text.toLowerCase())){
                result.add(i);
            }
            else if(i.getType().equals("BOOK") && ((Book)i).getAuthor().toLowerCase().contains(text.toLowerCase())){
                result.add(i);
            }
            else if(i.getType().equals("MAGAZINE") && ((Magazine)i).getIssue().toLowerCase().contains(text.toLowerCase())){
                result.add(i);
            }
            else if(i.getType().equals("REFERENCE") && ((ReferenceBook)i).getAuthor().toLowerCase().contains(text.toLowerCase())){
                result.add(i);
            }
        }
        return result;
    }
    public List<Loan> getMemberLoans(String memberId) throws MemberNotFoundException{
        if(!members.containsKey(memberId)) throw new MemberNotFoundException(memberId);
        List<Loan> result = new ArrayList<>();
        for(Loan l : loans.values()){
            if(l.getMember().getId().equals(memberId)){
                result.add(l);
            }
        }
        return result;
    }
    public List<Loan> getOverdueLoans(){
        List<Loan> result = new ArrayList<>();
        for(Loan l : loans.values()){
            if(l.isOverdue(currentDay)){
                result.add(l);
            }
        }
        return result;
    }
    public List<LibraryItem> getInventory(){
        return new ArrayList<>(items.values());
    }

    public Member isItemOnLoan(String itemId) {
        Loan l = loans.getOrDefault(itemId, null);
        return (l == null) ? null : l.getMember();
    }

}
