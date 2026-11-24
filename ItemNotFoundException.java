public class ItemNotFoundException extends LibraryException{
    ItemNotFoundException(String id){
        super("item " + id + " not found");
    }
}
