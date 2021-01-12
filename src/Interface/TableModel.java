package Interface;

import Database.Database;
import Interface.Player;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {

    private static final int NAME = 0;
    private static final int STATUS = 1;

    private String[] columnNames = {"Имя игрока", "Результат игры"};

    ArrayList<Player> data = new ArrayList<>();

    //создаем объект класса бд
    Database db = new Database();

    //конструктор
    public TableModel() {
        try {
            //устанавливаем соединение
            db.openConnection();
            //считываем данные
            data = db.read();
            //закрываем соединение
            db.closeConnection();
            System.out.println("Данные считаны.");
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Player tmpPlayers = data.get(row);

        switch (column) {
            case NAME:
                return tmpPlayers.getName();
            case STATUS:
                return tmpPlayers.getStatus();
            default:
                return "---";
        }
    }

    //метод добавления записи
    public void Add(Player player) {
        try {
            //устанавливаем соединение
            db.openConnection();
            db.AddPlayers(player);//добавляем в бд
            //добавляем в таблицу
            data.add(player);
            //закрываем соединение
            db.closeConnection();
            System.out.println("Запись добавлена.");
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
        //обновляем таблицу
        this.fireTableDataChanged();
    }

    //метод редактирования записи
    public void Set(Player player) {
        try {
            int index = 0;
            for (Player players : data) {
                String nameTmp = players.getName();
                //если имя имеющего объекта равно имени нового объекта
                if (nameTmp.equals(player.getName()))
                    //находим индекс имеющегося объекта с данным именем
                    index = data.indexOf(players);
            }
            //устанавливаем соединение
            db.openConnection();
            db.EditResult(player,index+1);//редактируем в бд
            //редактируем в таблице
            data.set(index, player);
            //закрываем соединение
            db.closeConnection();
            System.out.println("Запись отредактирована.");
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
        //обновляем таблицу
        this.fireTableDataChanged();

    }
}

