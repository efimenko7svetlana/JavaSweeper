package sweeper;

//для хранения всех боксов(нижний и верхний слой)
class Matrix {
    private Box[][] matrix;

    //конструктор принимает элемент
    Matrix(Box defaultBox) {
        //будет создана матрица, заполненная указанными элементами
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        //заполняем элементами
        for (Coord coord : Ranges.getAllCoords()) {
            //записываем значение которое получили ранее
            matrix[coord.x][coord.y] = defaultBox;
        }
    }

    // получить значение бокса
    Box get(Coord coord) {
        //проверяем находится ли координата в пределах поля
        if (Ranges.inRange(coord))
            return matrix[coord.x][coord.y];
        return null;
    }

    //присваиваем значение указанной клетке
    void set(Coord coord, Box box) {
        if (Ranges.inRange(coord))
            matrix[coord.x][coord.y] = box;
    }
}
