package Interface;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;


public class JavaSweeper extends JFrame {

    private static Game game;
    private JPanel panel; // панель
    private JLabel label; // сообщение о статусе игры

    //модель таблицы
    TableModel model = new TableModel();

    private final int COLS = 9; // кол-во столбцов
    private final int ROWS = 9;  //кол-во строк
    private final int BOMBS = 10; // кол-во бомб всего
    private final int IMAGE_SIZE = 50; //размер одной картинки

    //конструктор
    public JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS); //создаем экземпляр
        game.start();
        setImages();
        initMenu();
        initLabel();
        initPanel();
        initFrame();

    }

    // метод для инициализации сообщения
    private void initLabel() {
        label = new JLabel("Добро пожаловать в 'Сапер'!");
        add(label, BorderLayout.SOUTH);
    }

    // метод для инициализации меню
    private void initMenu() {
        //создаем MenuBar и добавляем компоненты
        JMenuBar menubar = new JMenuBar();
        JMenu newGame = new JMenu("Новая игра");
        menubar.add(newGame);
        JMenu results = new JMenu("Результаты");
        menubar.add(results);
        add(menubar, BorderLayout.NORTH);

        //добавление игрока в таблицу
        model.Add(new Player(PlayersFrame.getFieldName(), getMessage()));

        //слушатель для элемента "Новвая игра"
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //если была нажата ЛКМ
                if (e.getButton() == MouseEvent.BUTTON1)
                    JavaSweeper.this.dispose();
                PlayersFrame.frameSetting();
                label.setText(getMessage()); //присвоить значение
                panel.repaint();//перерисовываем форму
            }
        });

        //слушатель для элемента "результаты"
        results.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //если была нажата ЛКМ
                if (e.getButton() == MouseEvent.BUTTON1) {
                    ResultsFrame f = new ResultsFrame(model);
                    model.Set(new Player(PlayersFrame.getFieldName(), getMessage()));
                }
                label.setText(getMessage()); //присвоить значение
                panel.repaint();//перерисовываем форму
            }
        });

    }

    //метод для инициализации панели
    private void initPanel() {
        panel = new JPanel() {
            //вызывается, когда нужно нарисовать нашу форму
            @Override
            protected void paintComponent(Graphics g) {
                //перебор всех коорд
                for (Coord coord : Ranges.getAllCoords()) {
                    //вывод картинки; имя, координаты, указание на текущий экземпляр
//                    Coord coord = new Coord(box.ordinal() * IMAGE_SIZE, 0);
                    //обращаемся к гейм и оттуда берем нужный бокс с нужными координатами
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };

        //слушатель мыши
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //получаем координаты, куда клткнули мышкой
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y); //создаем координату с этими х и у
                //если была нажата ЛКМ
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord); //то вызываем метод, который открывает клетку
                //если была нажата ПКМ
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord); //то вызываем метод, который переключает флаг
                label.setText(getMessage()); //присвоить значение
                panel.repaint();//перерисовываем форму
            }
        });

        //указываем размеры поля
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel); //добавляем панель
    }

    //метод для получения сообщения
    private String getMessage() {
        switch (game.getState()) {
            case PLAYED:
                return "Игра не окончена";
            case BOMBED:
                return "Поражение";
            case WINNER:
                return "Победа";
            default:
                return "-";
        }
    }

    //метод для инициализации фрейма
    private void initFrame() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//закрытие программы при нажатии на крестик
        setTitle("JavaSweeper");//заголовок
        setResizable(false); //запрещаем менять размер окна
        setVisible(true); //делаем видимым
        pack();//установка размера формы, чтобы все поместилось
        setLocationRelativeTo(null);//устанавливаем окно по центру
        setIconImage(getImage("icon")); //добавляем иконку
    }

    //получить все картинки
    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    //метод для установки и получение картинок
    private Image getImage(String name) {
        //задаем имя файла для каждой картинки
        String filename = "I:\\JavaSweeper\\res\\img\\" + name + ".png";
        //создаем объект из файла
        ImageIcon icon = new ImageIcon(filename);
        return icon.getImage();
    }
}

