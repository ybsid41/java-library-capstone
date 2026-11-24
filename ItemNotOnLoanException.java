public class ItemNotOnLoanException extends LibraryException{
    ItemNotOnLoanException(String id){
        super(id + " is not on loan");
    }
}
