package partShop.server.DBTools;

import java.util.List;

/**
 * Created by Александр on 09.05.2017.
 */

public interface DBModel <T> {

        void add( T data) ;
        void upd (T base,T newdata);
        void delete (T data);
        List<T> whereQuery(String whereString, Object... params);

}
