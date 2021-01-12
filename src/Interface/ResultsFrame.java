package Interface;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class ResultsFrame extends JFrame {

    private static JScrollPane scrollpane;
    private static JPanel frameMain;
    private static JPanel paneTable;
    private static JTable table;
    public static TableModel model;

    public ResultsFrame(TableModel model1) {
        super("Результаты");
        this.model=model1;
        frameSetting();
        //запрет на изменение окна
        setResizable(false);
        //размер окна
        setSize(250, 300);
        //расположение по центру
        setLocationRelativeTo(null);
        //видимость
        setVisible(true);
    }

    //настройка фрейма
    public void frameSetting() {
        //Основная панель
        frameMain = new JPanel();
        //менеджер расположения
        frameMain.setLayout(new BoxLayout(frameMain, BoxLayout.Y_AXIS));
        //панель с таблицей
        paneTable = new JPanel();
        //размер устанавливаем
        paneTable.setSize(450, 150);
        //менеджер расположения
        paneTable.setLayout(new BoxLayout(paneTable, BoxLayout.Y_AXIS));
        //создаем таблицу
        table = new JTable();
        //передаем в таблицу модель
        table.setModel(model);
        //создаем скроллпэйн
        scrollpane = new JScrollPane(table);

        //добавляем на панель
        paneTable.add(scrollpane);
        frameMain.add(paneTable);

        getContentPane().add(frameMain);
    }
}

