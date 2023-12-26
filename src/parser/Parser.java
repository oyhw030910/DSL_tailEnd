package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    static String filePath="src/parser/boot.txt";
    public static ArrayList<Step> steps=new ArrayList<>();
    static ArrayList<String> lines=new ArrayList<>();
    static int lineCount=0;
    public Parser(){
        setLines();
        setSteps();
    }
    public static void setLines(){
        try {
            Scanner cin=new Scanner(new File(filePath));
            while (cin.hasNextLine()){
                String thisLine= cin.nextLine().strip();
                thisLine=thisLine.strip();
                if(thisLine.charAt(0)!='#'){
                    lines.add(thisLine);
                }
            }
            cin.close();
        } catch (FileNotFoundException e) {
            System.out.println("未找到脚本文件");
        }
    }

    public static void setSteps(){
        while (lineCount<lines.size()){
            String thisLine=lines.get(lineCount);
            String[] tokens=thisLine.split(" ");
            lineCount++;
            setOneStep(tokens[1]);
        }

    }
    public static void setOneStep(String name){
        Step step=new Step(name);
        boolean notEnd=true;
        while (lineCount<lines.size()&&notEnd){
            String thisLine= lines.get(lineCount);
            lineCount++;
            String[] tokens= thisLine.split(" ");
            switch (tokens[0]) {
                case "Step" -> {
                    notEnd = false;
                    lineCount--;
                }
                case "branch" -> step.branches.add(new Branch(tokens[1].replaceAll("\"",""), tokens[2]));
                case "Speak" -> step.actions.add(new Action("Speak", tokens[1].replaceAll("\"","")));
                case "Listen" -> step.actions.add(new Action("Listen"));
                case "Goto" -> step.actions.add(new Action("Goto", tokens[1].replaceAll("\"","")));
                case "List" -> step.actions.add(new Action("List", tokens[1].replaceAll("\"","")));
                case "Buy" -> step.actions.add(new Action("Buy", tokens[1].replaceAll("\"","")));
                default -> System.out.println("第" + lineCount + "行，不符合语法规则，无法识别");
            }
        }
        steps.add(step);
    }

}
