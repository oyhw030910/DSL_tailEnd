package parser;

import java.util.ArrayList;

public class Step {
    public String name;
    public ArrayList<Action> actions=new ArrayList<>();
    public ArrayList<Branch> branches=new ArrayList<>();
    Step(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Step{" +
                "name='" + name + '\'' +
                ", actions=" + actions +
                ", branches=" + branches +
                '}';
    }
}
