package sweeper;

//класс для рабобты с верхним слоем
class Flag {

    private Matrix flagMap;
    private int countOfCloseBoxes; // кол-во закрытых клеток

    //запуск
    void start() {
        //инициализируем закрытые ячейки
        flagMap = new Matrix(Box.CLOSED);
        countOfCloseBoxes = Ranges.getSize().x * Ranges.getSize().y; //все клетки закрыты вначале
    }

    //получаем значение
    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    //установка открытия клетки
    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED); //делаем клетку открытой
        countOfCloseBoxes--;
    }

    // установка флага
    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED); //устанавливаем флаг
    }

    //переключение флага
    void toggleFlagedToBox(Coord coord) {
        //получаем координату
        switch (flagMap.get(coord)) {
            //если там флаг, то закрываем клетку
            case FLAGED:
                setClosedBox(coord);
                break;
            // если клетка закрыта, то ставим флаг
            case CLOSED:
                setFlagedToBox(coord);
                break;
        }
    }

    // делаем клетку закрытой
    void setClosedBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED); //устанавливаем закрытую клетку
    }

    //геттер для кол-ва закрытых клеток
    int getCountofCLosesBoxes() {
        return countOfCloseBoxes;
    }

    // установить бомбу в  клетку
    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    //открыть закрытую клетку с бомбой
    void setOpenedToClosedBombBox(Coord coord) {
    if (flagMap.get(coord)== Box.CLOSED)
        flagMap.set(coord,Box.OPENED);
    }

    //установить значок нет бомбы на обозначенный флагом безопасный бокс
    void setNobombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord)== Box.FLAGED)
            flagMap.set(coord,Box.NOBOMB);
    }

    //получить кол-во флагов вокруг
    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for(Coord around : Ranges.getCoordsAround(coord))
            if(flagMap.get(around)==Box.FLAGED)
                count++;
            return count;
    }
}
