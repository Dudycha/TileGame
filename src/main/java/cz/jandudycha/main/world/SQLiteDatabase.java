package cz.jandudycha.main.world;


import java.sql.*;

public class SQLiteDatabase {


    private static Connection con;
    private static boolean hasData = false;


    public ResultSet displayWorld() throws SQLException, ClassNotFoundException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        return state.executeQuery("SELECT worldMap FROM worldMap");
    }

    private void getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:SQLiteScoreData.db");
        initialise();
    }


    private void initialise() throws SQLException {
        if (!hasData) {
            hasData = true;
            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master master WHERE type='table' AND name='worldMap';");
            if (!res.next()) {
                System.out.println("Building the records table with prepopulated values");
                //need to build table
                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE worldMap(id integer,"
                        + "worldMap varchar(2200),"
                        + "primary key(id));");
            }

            //inserting some data
//            PreparedStatement prep = con.prepareStatement("INSERT INTO worldMap values(?,?);");
//            prep.setString(2,
//                         "005,005,005,005,005,005,005,005,005,005,005,005,005,005,005,005,005,005,005,005,005,005,005\n" +
//                            "006,006,006,006,006,006,006,006,006,006,006,006,006,006,006,006,006,006,006,006,006,006,006\n" +
//                            "007,007,007,007,007,007,007,007,007,007,007,007,007,007,007,007,007,007,007,007,007,007,007\n" +
//                            "008,008,008,008,008,008,008,008,008,008,008,008,008,008,008,008,008,008,008,008,008,008,008\n" +
//                            "009,009,009,009,009,009,009,009,009,009,009,009,009,009,009,009,009,009,009,009,009,009,009\n" +
//                            "010,010,010,010,010,010,010,010,010,010,010,010,010,010,010,010,010,010,010,010,010,010,010\n" +
//                            "011,011,011,011,011,011,011,011,011,011,011,011,011,011,011,011,011,011,011,011,011,011,011\n" +
//                            "003,003,003,004,003,004,003,004,003,004,004,003,004,003,004,003,004,003,004,003,004,003,004\n" +
//                            "012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012\n" +
//                            "012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012,012\n" +
//                            "002,002,001,002,001,002,001,002,001,002,002,002,001,002,002,001,002,002,002,001,002,002,001\n" +
//                            "002,001,002,000,001,000,002,001,000,002,001,000,002,002,001,002,001,000,002,002,002,002,000\n" +
//                            "000,000,001,000,001,000,001,000,001,000,000,001,000,001,000,000,000,001,000,001,000,000,001\n" +
//                            "001,000,000,000,001,000,001,001,000,001,000,001,000,001,000,001,000,001,000,001,000,000,001\n");
//            System.out.println(prep);
//
//            prep.execute();
        }
    }

    public void saveMap(String prepMap)
            throws SQLException, ClassNotFoundException {
        if (con == null) {
            getConnection();
        }

        PreparedStatement prep = con.prepareStatement("INSERT INTO worldMap values(?,?);");
        prep.setString(2, prepMap);

        prep.execute();

    }

    public void clearDatabase() throws SQLException, ClassNotFoundException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        state.execute("DELETE FROM records;");
        state.close();
    }

//    public ResultSet getCountOfRecords() throws SQLException, ClassNotFoundException {
//        if (con == null) {
//            getConnection();
//        }
//        Statement state = con.createStatement();
//        ResultSet res = state.executeQuery("SELECT COUNT(*) FROM records;");
//        return res;
//    }
}
