package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.FestivalBean;
import schedule.db.PocketDAO;
import schedule.db.TravelBean;
import schedule.db.Travel_InfoDAO;

public class ShceduleMakeView2 implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		PocketDAO pock_DAO = new PocketDAO();
		Travel_InfoDAO travel_InfoDAO = new Travel_InfoDAO();
		List<TravelBean> travel = new ArrayList<TravelBean>();
		List<FestivalBean>festival = new ArrayList<FestivalBean>();
		String user_id = (String) session.getAttribute("user_id");
		////////////////////////////////////////////////
		//pt = pocket Travel
		int pagePT =1;
		int limitPT = 5;
		int listcountPT =travel_InfoDAO.getTravelListCount(user_id);//�ش� ȸ���� ������ ���� �� ���� 
		int maxpagePT=listcountPT/limitPT;
		if(listcountPT%limitPT>0)
			maxpagePT++;

		if(request.getParameter("pagePocketTravel")!=null) 
			pagePT=Integer.parseInt(request.getParameter("pagePocketTravel"));
		
		int startpagePT=((pagePT-1)/limitPT)*limitPT+1;
		int endpagePT=startpagePT+limitPT-1;
		if(endpagePT>maxpagePT)
			endpagePT=maxpagePT;
		
		travel = travel_InfoDAO.loadTravelInfo_main(user_id,pagePT,limitPT);// ��ٱ��Ͽ� ����� �ִ� ������  ���� 
	
		request.setAttribute("pagePocketTravel", pagePT); //���� ���� ������ ��
		request.setAttribute("maxpagePocketTravel", maxpagePT); //���� �ִ� ������ ��
		request.setAttribute("startpagePocketTravel", startpagePT);//���� ���� �������� ǥ���� ù ������ ��
		request.setAttribute("endpagePocketTravel", endpagePT);//���� ���� �������� ǥ���� �� ������ ��
		request.setAttribute("listcountPocketTravel", listcountPT); //�� ���� ��
		request.setAttribute("trav_list", travel);
		
		/////////////////////////////////////////////////��ٱ��� ���� ����¡ ó��
		
		///////////////////////////////////////////////////////////////
		int pagePF = 1;
		int limitPF = 5;
		int listcountPF = travel_InfoDAO.getFestivalListCount(user_id);
		int maxPagePF = listcountPF /limitPF;
		if(listcountPF %limitPF>0)
			maxPagePF ++;
		
		if(request.getParameter("pagePocketFestival")!=null)
			pagePF = Integer.parseInt(request.getParameter("pagePocketFestival"));
		
		int startPagePF=((pagePF-1)/limitPF)*limitPF+1;
		int endPagePF=startPagePF+limitPF-1;
		if(endPagePF>maxPagePF)
			endPagePF = maxPagePF;
		festival = travel_InfoDAO.loadFestivalInfo_main(user_id,pagePF,limitPF);// ��ٱ��Ͽ� ����� �ִ� ���� ���� 
		
		
		request.setAttribute("pagePocketFestival", pagePF); //���� ���� ������ ��
		request.setAttribute("maxpagePocketFestival", maxPagePF); //���� �ִ� ������ ��
		request.setAttribute("startpagePocketFestival", startPagePF);//���� ���� �������� ǥ���� ù ������ ��
		request.setAttribute("endpagePocketFestival", endPagePF);//���� ���� �������� ǥ���� �� ������ ��
		request.setAttribute("listcountPocketFestival", listcountPF); //�� ���� ��
		request.setAttribute("fest_list", festival);
		
		
		
		
		///////////////////////////////////////////////��ٱ��� ���� ����¡ ó��
		forward.setRedirect(false);
		if(request.getParameter("statePocketTravel")!=null) {
			forward.setPath("/Schedule/Ajax_Make_PocketTravel.jsp");
		}else if(request.getParameter("statePocketFestival")!=null){
			forward.setPath("/Schedule/Ajax_Make_PocketFestival.jsp");
		}else{

		forward.setPath("/Schedule/Schedule_make.jsp");
		}
		return forward;
	}

}
