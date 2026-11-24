public class ReferenceBook extends LibraryItem{
    private final String author;
    public ReferenceBook(String id, String title, String author){
        super(id, title);
        this.author = author;
    }
    public String getAuthor(){
        return author;
    }
    @Override 
    public String getType(){
        return "REFERENCE";
    }
    @Override 
    public boolean isBorrowable(){
        return false;
    }
}
