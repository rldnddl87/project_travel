package schedule.db;



public class ScheduleBean {
		
		private int sch_no;			//������ȣ
		private int user_no;		//ȸ����ȣ
		private String start_date;  	//���� ������
		private String end_date;		//���� ������
		private String sch_title;	//���� ����
		private String sch_content;	//������ ���� ����
		private String sch_review; 	//������ ���� �ı�
		//shcedule ���̺�
		
		public int getSch_no() {
			return sch_no;
		}
		public void setSch_no(int sch_no) {
			this.sch_no = sch_no;
		}
		public int getUser_no() {
			return user_no;
		}
		public void setUser_no(int user_no) {
			this.user_no = user_no;
		}
		public String getStart_date() {
			return start_date;
		}
		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}
		public String getEnd_date() {
			return end_date;
		}
		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}
		public String getSch_title() {
			return sch_title;
		}
		public void setSch_title(String sch_title) {
			this.sch_title = sch_title;
		}
		public String getSch_content() {
			return sch_content;
		}
		public void setSch_content(String sch_content) {
			this.sch_content = sch_content;
		}
		public String getSch_review() {
			return sch_review;
		}
		public void setSch_review(String sch_review) {
			this.sch_review = sch_review;
		}
		
		
}
