package interpreter;

public class Game {
    public String name;
    public String price;
    public Game(String name,String price){
        this.name=name;
        this.price=price;
    }

    @Override
    public String toString() {
        return "游戏名:"+name+",价格:"+price;
    }
}
