package partShop.server.DBTools;

import partShop.shared.Entities.Part;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 09.05.2017.
 */
public class PartsDataBaseModel implements DBModel <Part> {

    DataBaseConnector connection=DataBaseConnector.getInstance();

//    int ID;
//    String partNumber;
//    String producer;
//    String partName12345;
//    String description;


    @Override
    public void add(Part data) {
        // query for add
        String query = "INSERT INTO Parts (partNumber,producer,PARTNAME,description)" +
                " VALUES(?,?,?,?)";
        try(PreparedStatement stmt=connection.prepareQuery(query)){
            int i=1;
            stmt.setString(i++,data.getPartNumber());
            stmt.setString(i++,data.getProducer());
            stmt.setString(i++,data.getPartName12345());
            stmt.setString(i++,data.getDescription());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upd(Part base, Part newdata) {
        String query = "update PARTS set partNumber=?,producer=?,partName=?," +
                "description=? where ID=?";

        try(PreparedStatement stmt = connection.prepareQuery(query)){
            int i=1;
            stmt.setString(i++,newdata.getPartNumber());
            stmt.setString(i++,newdata.getProducer());
            stmt.setString(i++,newdata.getPartName12345());
            stmt.setString(i++,newdata.getDescription());
            stmt.setInt(i++,base.getID());
            stmt.execute();

        } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void delete(Part data) {
        //вопрос по целостности базы ?
        String query = "DELETE FROM PARTS WHERE ID=?";
        try(PreparedStatement stmt = connection.prepareQuery(query)){
            stmt.setInt(1,data.getID());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Part> whereQuery(String whereString, Object... params) {
        ArrayList<Part> parts = new ArrayList<>();

        if(whereString!=null && !whereString.isEmpty()){
            whereString="AND ("+whereString+")";
        }
        try( PreparedStatement stmt = connection.prepareQuery(
                "SELECT * FROM Parts WHERE (1=1) "+whereString+";")) {
            //Statement - одна задача . запрашиваем шаблон для БД
            for(int i=0;i<params.length;i++){
                stmt.setObject(i+1,params[i]);
            }

            ResultSet ccc =stmt.executeQuery();

            while (ccc.next()) {

                parts.add(partFromResultSet(ccc));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parts;
    }

    private Part partFromResultSet(ResultSet ccc) throws SQLException {
        Part p=new Part(ccc.getInt("ID"),ccc.getString("partNumber"),
                        ccc.getString("producer"),ccc.getString("partName"),
                        ccc.getString("description"));
        return p;
}
}
