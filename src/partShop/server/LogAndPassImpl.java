package partShop.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import partShop.client.LogAndPassService;
import partShop.server.DBTools.PartsDataBaseModel;
import partShop.server.DBTools.UsersDataBaseModel;
import partShop.shared.Entities.Part;
import partShop.shared.Entities.User;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public class LogAndPassImpl extends RemoteServiceServlet

implements LogAndPassService {

	UsersDataBaseModel usersDataBaseModel=new UsersDataBaseModel();


	public String logAndPassServer(String login,String password) throws IllegalArgumentException {

		String res="";
		Map <String,String> logsAndPasses = takeUsersWithPassFromDB();
//		logsAndPasses.put("Alex", "123");
//		logsAndPasses.put("Ivan", "345");
//		logsAndPasses.put("Petr","678");

		if(logsAndPasses.size()==0 || logsAndPasses.equals(null)){res="Нет зарегистрирорванных пользователей";}
		
		if (logsAndPasses.containsKey(login)){

//			usersDataBaseModel.add(new User("Alex", "123"));
//			usersDataBaseModel.add(new User("Ivan", "345"));
			usersDataBaseModel.add(new User("Petr","678"));

//  Тест методов обновления и удаления объекта в БД




			String key=logsAndPasses.get(login);
			if (key.equals(password)){res="Авторизация выполнена";
				HttpServletRequest req = getThreadLocalRequest();
				req.getSession().setAttribute("user",login);
			}
			else{
			res="Пароль не верен";}
		}
		else{
			res="Пользователь в системе не зарегистрирован";}
		return res;
	}

	@Override
	public void logOut() throws IllegalArgumentException {
		HttpServletRequest req = getThreadLocalRequest();
		req.getSession().removeAttribute("user");
	}

	private Map <String,String>  takeUsersWithPassFromDB (){
		//помещает в MAP значения, полученный из базы

		Map <String,String> logsAndPasses=new HashMap<>();
        List <User> users=usersDataBaseModel.whereQuery("");
        // Тест методов добавления и удаления объекта
		List<User> users2 = usersDataBaseModel.whereQuery("LOGIN='Ivan'");
       //LOGIN = Petr выбрасывал Exception
		if(users2.size()>0) {
			User userToTest = users2.get(0);
			userToTest.setLogin("Andrey");
			usersDataBaseModel.upd(userToTest, userToTest);

			usersDataBaseModel.delete(userToTest);
		}
			if (users.size() == 0) {
				return null;
			}
			for (User user : users) {
				logsAndPasses.put(user.getLogin(), user.getPassword());
			}

		return logsAndPasses;
	}
}
