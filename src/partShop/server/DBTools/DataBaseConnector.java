package partShop.server.DBTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Александр on 08.05.2017.
 */
public class DataBaseConnector {
    //singleTone - класс, который может использоваться другими классами одновременно без создания новых экземпляров
    //существует в единсвенном числе и не может быть создан повторно
    Connection connection;

    private static final DataBaseConnector instance=new DataBaseConnector();
    public static DataBaseConnector getInstance (){return instance; }

    public PreparedStatement prepareQuery(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    private DataBaseConnector(){
        try {
          //  org.mariadb.jdbc.Driver
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/partsshop?user=partshopuser&password=12345qwe");
            String where =connection.getCatalog();
            String xxx="";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void finalize() throws Throwable {
        connection.close();
        super.finalize();
    }
}
