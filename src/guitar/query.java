package guitar;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.SQLiteTest;

@WebServlet("/query.do")
public class query extends HttpServlet{
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		String price=new String (request.getParameter("price").getBytes("ISO-8859-1"),"UTF-8");
		String model=new String (request.getParameter("model").getBytes("ISO-8859-1"),"UTF-8");
		String type=new String (request.getParameter("type").getBytes("ISO-8859-1"),"UTF-8");
		String backwood=new String (request.getParameter("backwood").getBytes("ISO-8859-1"),"UTF-8");
		String topwood=new String (request.getParameter("topwood").getBytes("ISO-8859-1"),"UTF-8");
		
try{
	String serialnumber;
	String builder;
	int inventory;
	Connection conn= SQLiteTest.getConnection();
	Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
    String sql = "select * from guitar where model='"+model+"'and type='"+type+"'and backwood='"+backwood+"'and topwood='"+topwood+"'";
	ResultSet rs = stmt.executeQuery(sql);
	while(rs.next()){
		model=rs.getString("model");
		price=rs.getString("price");
		builder=rs.getString("builder");
		type=rs.getString("type");
		backwood=rs.getString("backwood");
		topwood=rs.getString("topwood");
		serialnumber=rs.getString("serialnumber");
		inventory=rs.getInt("inventory");
		conn.close();
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01" +" Transitional//EN'>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>查询结果</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>查询结果如下：</h1><br/>");
        out.println("编号:"+serialnumber+"<br/>");
        out.println("价格:"+ price+"<br/>");
        out.println("制造商"+builder+"<br/>");
        out.println("型号"+model+"<br/>");
        out.println("类型"+type+"<br/>");
        out.println("backwood"+backwood+"<br/>");
        out.println("topwood"+topwood+"<br/>");
        out.println("库存"+inventory+"<br/>");
        out.println("</body>");
        out.println("</html>");
        out.close();
		
	}
	rs.last();
	rs.close();
	stmt.close();
	conn.close();
		}catch(Exception e)
			{e.printStackTrace();	}
		}
	}