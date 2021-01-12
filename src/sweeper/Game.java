package sweeper;

public class Game {
    private Bomb bomb;
    private Flag flag;
    private GameState state;

    //геттер для состояния
    public GameState getState() {
        return state;
    }

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));//ukazivaem razmer polya
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    //запуск
    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    //метод, в котором возвращаем что находится в указанных координатах
    public Box getBox(Coord coord) {
        //если клетка открыта
        if (flag.get(coord) == Box.OPENED)
            return bomb.get(coord);// то получаем что под ней( нижний слой)
        else
            return flag.get(coord);// иначе получаем верхний слой
    }

    //метод для нажатия ЛКМ
    public void pressLeftButton(Coord coord) {
        if (gameOver()) return;
        openBox(coord);
        checkWinner();
    }

    //проверка на победу
    private void checkWinner() {
        //если еще играем
        if (state == GameState.PLAYED)
            //и количество закрытых клеток равно количеству бомб
            if (flag.getCountofCLosesBoxes() == bomb.getTotalBombs())
                //то состояние - победа
                state = GameState.WINNER;
    }

    //открытие клеток
    private void openBox(Coord coord) {
        switch (flag.get(coord)) {
            case OPENED:
                setOpenedToCloseBoxesAroundNumber(coord);
                return;
            case FLAGED:
                return;
            case CLOSED:
                switch (bomb.get(coord)) {
                    case ZERO:
                        openBoxesAround(coord);
                        return;
                    case BOMB:
                        openBombs(coord);
                        return;
                    default:
                        flag.setOpenedToBox(coord);
                        return;
                }
        }
    }

    // открыть закрытые ячейки вокруг числа
    private void setOpenedToCloseBoxesAroundNumber(Coord coord) {
        //если нет бомбы
        if (bomb.get(coord) != Box.BOMB)
            //если число равно количеству флагов вокруг
            if (flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord).getNumber())
                //перебираем клетки вокргу текущей
                for (Coord around : Ranges.getCoordsAround(coord))
                    //если закрыта, то открываем
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
    }

    //открытие бомб
    private void openBombs(Coord bombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coord coord : Ranges.getAllCoords())
            if (bomb.get(coord) == Box.BOMB)
                flag.setOpenedToClosedBombBox(coord);
            else
                flag.setNobombToFlagedSafeBox(coord);
    }

    //метод для открытия клеток вокруг
    private void openBoxesAround(Coord coord) {
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord)) {
            openBox(around);
        }
    }

    // метод для нажатия ПКМ
    public void pressRightButton(Coord coord) {
        if (gameOver()) return;
        flag.toggleFlagedToBox(coord); //ставим флаги для заданной координаты
    }

    //метод для окончания игры
    private boolean gameOver() {
        //если состояние- играем
        if (state == GameState.PLAYED)
            return false;
        //иначе перезапуск
        start();
        return true;
    }
}
