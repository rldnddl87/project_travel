/*
 DAO(Data Access Object)Ŭ����
 - ������ ���̽��� �����Ͽ� ���ڵ��� �߰�,����, ���� �۾��� �̷������ Ŭ�����Դϴ�.
 - � ActionŬ������ ȣ��Ǵ��� �׿� �ش��ϴ� ������ ���̽� ����ó���� DAO Ŭ�������� �̷������ �˴ϴ�.
 */

package travel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class userTravel_commentDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public userTravel_commentDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex);
			return;
		}
	}//������ end

	public List<travelComment> getCommentList(int page,int limit , int travel_no){
			List<travelComment> list = new ArrayList<travelComment>();
			
			int startrow=(page-1)* limit +1;	//���۹�ȣ	  1   11  21  31
			int endrow=startrow + limit -1;		//��������ȣ 10  20  30  40
			try {
				con=ds.getConnection();
				
				String sql="select * from (select rownum rnum, user_type,travel_comt_no,travel_comt_cont,user_no,travel_no,user_id " + 
						" from(select * from travel_comment natural join client where travel_no =? order by travel_comt_no)) "
						+" where rnum>=? and rnum<=? " ;
				
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, travel_no);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					travelComment t = new travelComment();
					t.setTravel_comt_cont(rs.getString("travel_comt_cont"));
					t.setTravel_comt_no(rs.getInt("travel_comt_no"));
					t.setTravel_no(rs.getInt("travel_no"));
					t.setUser_no(rs.getInt("user_no"));
					t.setUser_id(rs.getString("user_id"));
					t.setUser_type(rs.getString("user_type"));
					list.add(t);
				}
				return list;
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
				}catch(Exception ee) {
					ee.printStackTrace();
				}
			}
			return list;
	} // getCommentList end
	
	
	public int getComent_listcount(int no) {
		int count=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(*) from travel_comment where travel_no =? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return count;
	}
	
}//class end


