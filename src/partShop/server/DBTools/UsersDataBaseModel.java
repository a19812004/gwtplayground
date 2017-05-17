package partShop.server.DBTools;

import partShop.shared.Entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 14.05.2017.
 */
public class UsersDataBaseModel implements DBModel <User> {

    DataBaseConnector connection=DataBaseConnector.getInstance();

//    int ID;
//    String login;
//    String password;
//    query for add

    String query = "INSERT INTO USERS (login,password)" +
            " VALUES(?,?)";

    @Override
    public void add(User data) {

        try(PreparedStatement stmt=connection.prepareQuery(query)){
            int i=1;
            stmt.setString(i++,data.getLogin());
            stmt.setString(i++,data.getPassword());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void upd(User base, User newdata) {
        String query = "update USERS set LOGIN=?,PASSWORD=? where ID=?";

        try(PreparedStatement stmt = connection.prepareQuery(query)){
            int i=1;
            stmt.setString(i++,newdata.getLogin());
            stmt.setString(i++,newdata.getPassword());
            stmt.setInt(i++,base.getID());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(User data) {
        //вопрос по целостности базы ?
        String query = "DELETE FROM USERS WHERE ID=?";
        try(PreparedStatement stmt = connection.prepareQuery(query)){
            stmt.setInt(1,data.getID());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> whereQuery(String whereString, Object... params) {
        ArrayList<User> users = new ArrayList<>();

        if(whereString!=null && !whereString.isEmpty()){
            whereString="AND ("+whereString+")";
        }
        try( PreparedStatement stmt = connection.prepareQuery(
                "SELECT * FROM USERS WHERE (1=1) "+whereString+";")) {
            //Statement - одна задача . запрашиваем шаблон для БД
            for(int i=0;i<params.length;i++){
                stmt.setObject(i+1,params[i]);
            }

            ResultSet ccc =stmt.executeQuery();

            while (ccc.next()) {

                users.add(userFromResultSet(ccc));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User userFromResultSet(ResultSet ccc) throws SQLException {
        User u=new User(ccc.getInt("ID"),ccc.getString("login"),
                ccc.getString("password"));
        return u;
    }
}
