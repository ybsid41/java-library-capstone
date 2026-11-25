public class Loan {
    private LibraryItem item;
    private Member member;
    private int borrowedDay;
    public Loan(LibraryItem item, Member member, int borrowedDay){
        this.item = item;
        this.member = member;
        this.borrowedDay = borrowedDay;
    }
    public LibraryItem getItem(){
        return item;
    }
    public Member getMember(){
        return member;
    }
    public int getDueDay(){
        return borrowedDay + member.getLoanPeriod();
    }
    public boolean isOverdue(int today){
        return today > getDueDay();
    }
    public int getDaysLate(int today){
        return (today > getDueDay()) ? today - getDueDay() : 0;
    }
    public int calculateFine(int today){
        return getDaysLate(today)*(((Borrowable)item).getFineRate());
    }
}
