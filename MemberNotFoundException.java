public class MemberNotFoundException extends LibraryException{
    public MemberNotFoundException(String id){
        super("member " + id + " not found");
    }
}
