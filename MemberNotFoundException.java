public class MemberNotFoundException extends LibraryException{
    MemberNotFoundException(String id){
        super("member " + id + " not found");
    }
}
