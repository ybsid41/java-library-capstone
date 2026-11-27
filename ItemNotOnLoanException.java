public class ItemNotOnLoanException extends LibraryException{
    public ItemNotOnLoanException(String id){
        super(id + " is not on loan");
    }
}
