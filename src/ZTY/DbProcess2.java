package ZTY;
import java.sql.*;
import ZTY.QUESTIONONE;
public class DbProcess2{
	public Connection con = null;public Statement sta=null;
	  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/q1?useSSL=false&serverTimezone=UTC";
	   static final String USER = "root";
	    static final String PASS = "2000918131X";
	    public DbProcess2(){
			try {
				//mysql���ݿ�����������������
				Class.forName(JDBC_DRIVER); 
				System.out.println("�������ݿ�...");
				System.out.println("mysql���ݿ��������سɹ�");

			}
			catch(java.lang.ClassNotFoundException e) 
			{
				e.printStackTrace();
			}		
}
	    public void executeUpdate(String sql)
	    {
	    	connect();
	    	try{
	    		sta=con.createStatement();
	    		sta.execute(sql);
	    	}
	    	catch(SQLException ex) { 
				System.err.println(ex.getMessage());		
			}
	    	disconnect();
	    }
	    
	    public void queryall(String sql)
	    {
	    	connect();
	    	String s1="select change_code.DSRPIN,chang.REC_TIME,chang.DSRPIN as REASON from chang join change_code on change_code.CODE = chang.CHAN";
	    	s1=s1+" where STUDENTID = "+sql;
	    	System.out.println(s1);
	    	
	    	String s2="select reward_levels.DSRPIN,reward.REC_TIME,reward.DSRPIN as REASON from reward join reward_levels on reward_levels.CODE=reward.LEVELS";
	    	s2=s2+" where STUDENTID = "+sql;
	    	System.out.println(s2);
	    	
	    	String s3="select punish_levels.DSRPIN,punishment.REC_TIME,punishment.DSRPIN as REASON,punishment.ENABLE from punishment join punish_levels on punish_levels.CODE=punishment.LEVELS";
	    	s3=s3+" where STUDENTID = "+sql;
	    	System.out.println(s3);
	    	
	    	try{
	    		sta=con.createStatement();
	    		ResultSet RS1=sta.executeQuery(s1);
	    		while(RS1.next())
	    		{
	    		String P1=RS1.getString("DSRPIN");
	    		String P2=RS1.getString("REC_TIME");
	    		String P3=RS1.getString("REASON");
	    		System.out.println(P1);System.out.println(P2);System.out.println(P3);
	    		}
	    	}
	    	catch(SQLException ex) { 
				System.err.println(ex.getMessage());		
			}
	    	disconnect();
	    }
	    
	    
	    
public void connect()
{
			try{
				//mysql���ݿ�
				con = DriverManager.getConnection(DB_URL,USER,PASS);  
				

				if(con!=null){
		            System.out.println("���ݿ����ӳɹ�");
		        }
			}
			catch(Exception e){
				e.printStackTrace();
			}
}	 


public void disconnect(){
	try{
		if(con != null){
			System.out.println("���ݿ�Ͽ��ɹ�");
			con.close();
			con = null;
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
}
