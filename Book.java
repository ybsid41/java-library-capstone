public class Book extends LibraryItem implements Borrowable{
    private final String author;
    public Book(String id, String title, String author){
        super(id, title);
        this.author = author;
    }
    public String getAuthor(){
        return author;
    }
    @Override 
    public String getType(){
        return "BOOK";
    }
    @Override 
    public boolean isBorrowable(){
        return true;
    }
    public int getFineRate(){
        return 2;
    }
}
