package client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.db.ClientDAO;

public class Client_deleteAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, 
         HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("utf-8");
      String user_id = request.getParameter("user_id");
      if (user_id.equals("admin")) {
         response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('�����ڴ� �������� ���մϴ�.');");
            out.println("history.back()");
            out.println("</script>");
            out.close();
            return null;
      } else {
    	  response.setContentType("text/html;charset=utf-8");
          PrintWriter out=response.getWriter();
          out.println("<script>");
          out.println("alert('�ش� ȸ���� �����Ͽ����ϴ�. ');");
          out.println("location.href='client_list.net'");
          out.println("</script>");
          out.close();
         ClientDAO cd = new ClientDAO();
         cd.delete(user_id);

      }
      return null;
   }
}






