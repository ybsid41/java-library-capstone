public class Faculty extends Member{
    public Faculty(String id, String name){
        super(id, name);
    }
    @Override 
    public int getMaxItems(){
        return 5;
    }
    @Override 
    public int getLoanPeriod(){
        return 30;
    }
}
