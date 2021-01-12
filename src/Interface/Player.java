package Interface;

public class Player {

    public int id; // айди
    public String name;  //название корабля
    public String status; //статус игры

    //Метод возвращающий название
    public String getName() {
        return name;
    }

    //Метод возвращающий название
    public String getStatus() {
        return status;
    }


    public Player(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Player( String name, String status) {
        this.name = name;
        this.status = status;
    }
}
