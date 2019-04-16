package travel.action;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import travel.db.travel;
import travel.db.travelDAO;
import travel.db.travel_image;
import travel.db.travel_imgDAO;
import travel.db.travel_type;

public class TravelAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		travelDAO tdao = new travelDAO();
		travel_imgDAO t_imgdao=new travel_imgDAO();
		travel t = new travel();
		travel_type t_type=new travel_type();
		travel_image t_img=new travel_image();
		ActionForward forward = new ActionForward();
		
		String realFolder = "";
		
		//WebContent�Ʒ��� �� ���� �����ϼ���
		String saveFolder = "travelUpload";
		int fileSize = 5*1024*1024;//���ε��� ������ �ִ� �������Դϴ�.
		
		//���� ���� ��θ� �����մϴ�.
		 ServletContext sc = request.getServletContext();
	     realFolder = sc.getRealPath(saveFolder);
	     System.out.println(realFolder);
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			
			int no=0;
			
			String[] user_type=multi.getParameterValues("user_type");
			String name = multi.getParameter("name");
			String content = multi.getParameter("textarea");
			String address =multi.getParameter("add");
			String location = multi.getParameter("location");
			String tema = multi.getParameter("tema");
			
			t.setTravel_address(address);
			t.setTravel_name(name);
			t.setTravel_location(location);
			t.setTravel_tema(tema);
			t.setTravel_content(content);
			
			no=tdao.insertTravel(t);
			
			
			
			//������ Ÿ��.
			for(int i=0;i<user_type.length;i++) {
				t_type.setTravel_type(Integer.parseInt(user_type[i]));
				tdao.insertTravelType(t_type);
			}
			
			System.out.println("��ȣ:"+no);
			
			//�̹��� ����.
			String type = multi.getParameter("imgType"); //��ǥ�̹��� ����
			System.out.println("üũ�ڽ� :"+type);
			
			for(int i=1;i<5;i++) {
				String temp="test"+i;
				String fileName= multi.getFilesystemName(temp);
				if(fileName!=null) {
					if(temp.equals(type)) {
						System.out.println(fileName);
					t_img.setTravel_img_name(fileName);
					t_img.setTravel_img_type(1);
					t_img.setTravel_no(no);
					t_imgdao.travel_img_insert(t_img);
					
					}else {
						
						t_img.setTravel_img_name(fileName);
						t_img.setTravel_img_type(0);
						t_img.setTravel_no(no);
						t_imgdao.travel_img_insert(t_img);	
					}
					
				}   
				
				
			}
			forward.setRedirect(true);
			forward.setPath("./travel_list.tr");
		  }catch(Exception e) {
			 e.printStackTrace();
	      }
	      return forward;
	}

}
