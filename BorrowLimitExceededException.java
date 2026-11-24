public class BorrowLimitExceededException extends LibraryException{
    BorrowLimitExceededException(String memberId, int max){
        super(memberId + " has reached the limit of " + max);
    }
}
