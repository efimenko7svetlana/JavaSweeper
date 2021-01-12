package Database;

import Interface.Player;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static final String PATH_TO_DB_FAIL = "resultsData.db";
    public static final String URL = "jdbc:sqlite:" + PATH_TO_DB_FAIL;

    //создаем объект для соединения
    public static Connection connection;
    //создаем объект для выполнения SQL-запросов
    public static Statement statement;
    //создаем объект для создания результата запроса
    public static ResultSet resultSet;

    //закрытие соединения
    public static void closeConnection() throws SQLException {
        connection.close();
        statement.close();
        resultSet.close();
    }

    //соединение
    public static void openConnection() throws SQLException {
        connection = DriverManager.getConnection(URL);
        if (connection != null) {
            System.out.println("База Подключена!");
            createDB();
        }
    }

    //метод для создания таблицы
    public static void createDB() throws SQLException {
        statement = connection.createStatement();
        //запрос на создание таблицы
        statement.execute("CREATE TABLE if not exists 'Players' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'status' text);");
        System.out.println("Таблица создана или уже существует.");
    }

    //метод для записи в таблицу БД
    public static void AddPlayers(Player player) throws SQLException {
        //выполняем запрос
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Players(`name`,`status`) " +
                "VALUES(?,?)");
        //определяем значение параметров
        statement.setObject(1, player.getName());
        statement.setObject(2, player.getStatus());
        statement.execute();
    }

    //метод для записи в таблицу БД
    public static void EditResult(Player player, int index) throws SQLException {
        //выполняем запрос
        PreparedStatement statement = connection.prepareStatement("UPDATE Players SET name=?,status=? WHERE id=?");
        //определяем значение параметров
        statement.setObject(1, player.getName());
        statement.setObject(2, player.getStatus());
        statement.setObject(3, index);
        statement.execute();

    }

    //метод для чтения таблицы БД
    public static ArrayList<Player> read() throws SQLException {
        ArrayList<Player> list = new ArrayList<>();
        //создаем объект statement
        statement = connection.createStatement();
        //выполняем запрос
        resultSet = statement.executeQuery("SELECT * FROM Players");
        // пока есть что выбирать, выполняем
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String status = resultSet.getString("status");
            Player pl = null;
            pl = new Player(id, name, status);
            list.add(pl);
        }
        //возвращаем список
        return list;
    }
}

