package sweeper;

//класс для работы с нижним слоем
class Bomb {

    private Matrix bombMap;
    private int totalBombs; //кол-во бомб

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombCount();
    }

    //запуск
    void start() {
        bombMap = new Matrix(Box.ZERO);
        //размещаем бомбы
        for (int j = 0; j < totalBombs; j++) {
            placeBomb();
        }
    }

    //получаем значение
    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    // метод для фиксированного кол-ва бомб на поле
    private void fixBombCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    //размещение одной бомбы
    private void placeBomb() {
        while (true) {
            //задаем случайное значение координате
            Coord coord = Ranges.getRandomCoord();
            //если уже есть бомба
            if (Box.BOMB == bombMap.get(coord))
                continue; //то продолжаем цикл
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break; //выходим из цикла
        }
    }

    // метод для увеличения чисел вокруг бомбы
    private void incNumbersAroundBomb(Coord coord) {
        //цикл, который переберет все клетки вокруг бомбы
        for (Coord around : Ranges.getCoordsAround(coord))
            //если не бомба
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());// то получаем значение
    }

    //получаем кол-во бомб
    int getTotalBombs() {
        return totalBombs;
    }
}
