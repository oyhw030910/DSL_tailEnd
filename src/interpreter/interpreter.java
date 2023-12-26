package interpreter;

import parser.Action;
import parser.Branch;
import parser.Parser;
import parser.Step;
import user.User;

import java.util.Scanner;

public class interpreter {
    static int stepId=0;
    static Scanner cin=new Scanner(System.in);
    public static String input;
    static GameManager store=new GameManager("src/resources/store.txt");
    static GameManager repository=new GameManager("src/resources/repository.txt");
    static Game thisGame=new Game("空","空");
    public static void main(String[] args){
//        new Parser();//读取文件构建语法树
//
//        while (true){
//            Step curStep=Parser.steps.get(stepId);
//            execStep(curStep);
//        }
    }
    public static void execStep(Step step){
        for (Action action:step.actions){
            switch (action.name) {
                case "Speak" -> Speak(action);
                case "Listen" -> Listen();
                case "List" -> List(action);
                case "Goto" -> Goto(action);
                case "Buy" -> Buy();
            }
        }
        if(!step.branches.isEmpty()){
            Branch thisBranch=null;
            for (Branch branch: step.branches){
                if(input.contains(branch.key)){
                    thisBranch=branch;
                    break;
                }
            }
            if(thisBranch!=null){
                setStepId(thisBranch.desc);
            }
            else {
                setStepId("default");
            }
        }
    }
    public static void Speak(Action action){
        action.contents=action.contents.replaceAll("\\$name", User.name);
        action.contents=action.contents.replaceAll("\\$price", thisGame.price);
        action.contents=action.contents.replaceAll("\\$input", input);
        System.out.println(action.contents);
    }
    public static void Listen(){
        input=cin.next();
    }
    public static void List(Action action){
        if(action.contents.equals("$store")){
            for (Game game:store.games){
                System.out.println(game);
            }
        }
        else{
            for (Game game:repository.games){
                System.out.println(game);
            }
        }
    }
    public static void Goto(Action action){
        for (int i=0;i<Parser.steps.size();i++){
            Step step=Parser.steps.get(i);
            if(step.name.equals(action.contents)){
                stepId=i;
                break;
            }
        }
    }
    public static void Buy(){
        for (Game game:store.games){
            if(game.name.equals(input)){
                thisGame=game;
            }
        }
        repository.games.add(thisGame);
        store.games.remove(thisGame);

    }
    public static void setStepId(String name){
        for (int i=0;i<Parser.steps.size();i++){
            Step step=Parser.steps.get(i);
            if(step.name.equals(name)){
                stepId=i;
                break;
            }
        }
    }
}
