package sweeper;

//класс для размещения всех картинок
public enum Box {
//перечисляем картинки
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;


    public Object image; //здесь будем хранить картинку

    //берем последующий номер
    Box getNextNumberBox (){
        return Box.values() [this.ordinal()+1];
    }

    //получить номер
    int getNumber() {
        return this.ordinal();
    }
}

