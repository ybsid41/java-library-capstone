public abstract class LibraryItem {
    private final String id;
    private final String title;
    public LibraryItem(String id, String title){
        this.id = id;
        this.title = title;
    }
    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public abstract String getType();
    public abstract boolean isBorrowable();
}
