public class ItemNotFoundException extends LibraryException{
    public ItemNotFoundException(String id){
        super("item " + id + " not found");
    }
}
