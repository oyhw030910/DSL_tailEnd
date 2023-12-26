package parser;

public class Action {
    public String name;
    public String contents;
    Action(String name){
        this.name=name;
    }
    Action(String name,String contents){
        this.name=name;
        this.contents=contents;
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
