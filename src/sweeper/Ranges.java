package sweeper;

import java.util.ArrayList;
import java.util.Random;

//класс для работы с полем
public class Ranges {

    private static Coord size; //размер
    private static ArrayList<Coord> allCoords; //создание списка всех координат
    private static Random random = new Random();

    //установка размера
    public static void setSize(Coord _size) {
        size = _size;
        allCoords = new ArrayList<Coord>(); //оздаем новый список координат
        //пройдемся по всем координатам и заполняем
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                //добавляем новую координату
                allCoords.add(new Coord(x, y));
            }
        }
    }

    //получение размера
    public static Coord getSize() {
        return size;
    }

    // получаем все координаты
    public static ArrayList<Coord> getAllCoords() {
        return allCoords;
    }

    //проверяем находится ли координата в границах
    static boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < size.x && coord.y >= 0 && coord.y < size.y;
    }

    //возврат случайной координаты
    static Coord getRandomCoord() {
        return new Coord(random.nextInt(size.x),
                random.nextInt(size.y));
    }

    // метод который переберет все клетки вокруг заданной
    static ArrayList<Coord> getCoordsAround(Coord coord) {
        Coord around;
        ArrayList<Coord> list = new ArrayList<Coord>();
        //перебирем все kletki vokrug
        for (int x = coord.x - 1; x <= coord.x + 1; x++)
            for (int y = coord.y - 1; y <= coord.y + 1; y++)
                if (inRange(around = new Coord(x, y)))
                    if (!around.equals(coord))
                        list.add(around);
        return list;
    }
}
