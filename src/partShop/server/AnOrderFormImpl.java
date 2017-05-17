package partShop.server;

import java.util.ArrayList;
import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import partShop.client.AnOrderFormService;
import partShop.server.DBTools.PartsDataBaseModel;
import partShop.shared.Entities.Part;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class AnOrderFormImpl extends RemoteServiceServlet

implements AnOrderFormService {

	//ArrayList <String> aviableProductQuery  () throws IllegalArgumentException;
	//private static final Random random = new Random();
	@Override
	public ArrayList<Part> aviableProductQuery() throws IllegalArgumentException {



		//получаем имя авторизованного пользователя
		HttpServletRequest req = getThreadLocalRequest();
		String login = (String) req.getSession().getAttribute("user");

		//получаем из базы список всех доступных продуктов для suggestBox
		PartsDataBaseModel partsDataBaseModel = new PartsDataBaseModel();
		ArrayList<Part> aviableProductsList=partsDataBaseModel.whereQuery("");

		if(login!=null){

//          Тест добавления объекта . Проверено. Работает.
//			Part part = new Part("222","USSR","Гайка","");
//			partsDataBaseModel.add(part);
//			Тест обновления объекта. Проверено. Работает.
//			Part partToUpdate=partsDataBaseModel.whereQuery("partNumber=111").get(0);
//			partToUpdate.setProducer("BELARUS");
//			partsDataBaseModel.upd(partToUpdate,partToUpdate);
//           Тест ограничения прав доступа для опред. пользователя.Проверено. Работает.
//			if(login.equalsIgnoreCase("Alex")){
//				aviableProductsList.add("Шайба");
//			}
//          Тест удаления записи. Проверено. Работает.
//			Part partToDelete=partsDataBaseModel.whereQuery("partNumber=222").get(0);
//			partsDataBaseModel.delete(partToDelete);
		}
		return aviableProductsList;
	}

	@Override
	public Integer stockReserveQuery(String symbol) throws IllegalArgumentException {
		//заглушка, имитирующая запрос к БД. Фактической связи товара и количества пока нет
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		Long randSeed =(Long)session.getAttribute("random");
		if(randSeed==null){
			randSeed =System.currentTimeMillis();
		}
		Random random = new Random(randSeed);
		Integer res=random.nextInt(10);
		session.setAttribute("random",random.nextLong());
		return res;
	}



}
