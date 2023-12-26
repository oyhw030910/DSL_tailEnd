package parser;

public class Branch {
    public String key;
    public String desc;
    Branch(String key,String desc){
        this.key=key;
        this.desc=desc;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "key='" + key + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
