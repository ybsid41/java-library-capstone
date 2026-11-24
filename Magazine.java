public class Magazine extends LibraryItem implements Borrowable{
    private final String issue;
    public Magazine(String id, String title, String issue){
        super(id, title);
        this.issue = issue;
    }
    public String getIssue(){
        return issue;
    }
    @Override
    public String getType(){
        return "MAGAZINE";
    }
    @Override 
    public boolean isBorrowable(){
        return true;
    }
    public int getFineRate(){
        return 1;
    }
}
