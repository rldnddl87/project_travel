package schedule.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.*;

public class ScheduleDelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ScheduleDAO sch_DAO =  new ScheduleDAO();
	
		DetailDAO det_DAO = new DetailDAO();
		
		List<ScheduleBean> list = new ArrayList<ScheduleBean>();
		List<DetailBean> detail = new ArrayList<DetailBean>();
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		//���� ���� , �������� ���� , ���� ��� 
		
		
		String str =request.getParameter("ch1");
		String str_array[] = str.split(" ");
		System.out.println(str);
	
		//���õ� ���� ���� ����
		boolean result1 =det_DAO.delDetail(str_array);
		/*if(result1)
			System.out.println("delDetail �Ϸ�");*/
		//���� ����
		boolean result = sch_DAO.delSchedule(str_array);
		/*if(result)
			System.out.println("delSchedule �Ϸ�");
*/
		
		
		int page = 1; //�⺻ ��
		int limit = 5; //���������� ������ ���� ����
		int listcount = sch_DAO.getScheduleListCount(user_id); //�ش� ȸ���� ������ ���� �� ���� 
		int maxpage=listcount/limit;
		if(listcount%limit>0)
			maxpage++;

		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}else {}
		int startpage=((page-1)/limit)*limit+1;
		int endpage=startpage+limit-1;
		if(endpage>maxpage)
			endpage=maxpage;
		
		list = sch_DAO.viewSchedule_main(user_id,page,limit); //���� ��� �������� 
		System.out.println("page : "+page);
		request.setAttribute("page", page); //���� ���� ������ ��
		request.setAttribute("maxpage", maxpage); //���� �ִ� ������ ��
		request.setAttribute("startpage", startpage);//���� ���� �������� ǥ���� ù ������ ��
		request.setAttribute("endpage", endpage);//���� ���� �������� ǥ���� �� ������ ��
		request.setAttribute("listcount", listcount); //�� ���� ��
		request.setAttribute("list", list); //�� bean
		
		
				
		forward.setRedirect(false);
		forward.setPath("/Schedule/Ajax_main_Schedule.jsp");
		
		return forward;
	}

}
