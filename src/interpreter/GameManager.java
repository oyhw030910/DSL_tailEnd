package interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    String path;
    ArrayList<Game> games=new ArrayList<>();
    public GameManager(String path){
        this.path=path;
        try {
            Scanner cin=new Scanner(new File(path));
            while (cin.hasNextLine()){
                String[] tokens=cin.nextLine().split(",");
                games.add(new Game(tokens[0],tokens[1]));
            }
            cin.close();
        } catch (FileNotFoundException e) {
            System.out.println("找不到相关文件");
        }
    }
}
