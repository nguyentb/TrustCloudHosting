package com.test.servlet;

import com.trust.process.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
        description = "Trust Process Servlet", 
        urlPatterns = { "/TrustProcess" }, 
        initParams = { 
                @WebInitParam(name = "user", value = "user"), 
                @WebInitParam(name = "password", value = "password"),
                @WebInitParam(name = "r_option", value = "trust")
        })
public class TrustProcess extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    public void init() throws ServletException {
        //we can create DB connection resource here and set it to Servlet context
        if(getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/mysql_db") &&
                getServletContext().getInitParameter("dbUser").equals("mysql_user") &&
                getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd"))
        getServletContext().setAttribute("DB_Success", "True");
        else throw new ServletException("DB Connection error");
    }
      
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        //get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String option = request.getParameter("r_option");

         
        //get servlet config init params
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        String r_option = getServletConfig().getInitParameter("r_option");

        System.out.println(r_option);
        System.out.println(option);
                        
        if(option.equals("trust")){
        	
            System.out.println("go to Trust option!");

        	ServletContext context = getServletContext();
    		String rulefile = context.getRealPath("/WEB-INF/rule/trust.rules");
    		String data_in = context.getRealPath("/WEB-INF/rdf/trust.ttl");
    		String data_out = context.getRealPath("/WEB-INF/output/");
    		data_out += "/" + option + ".ttl";
    		
    		response.getWriter().append("Rule file is obtained at: ").append(rulefile).println();
    		response.getWriter().append("Data input is obtained at: ").append(data_in).println();
    		response.getWriter().append("Data output is generated at: ").append(data_out).println();
    		
    		reasoning.reasoner(data_out, data_in, rulefile, "hybrid");
       	
            //response.sendRedirect("LoginSuccess.jsp");
    		//response.sendRedirect("jsps/UserPreference.jsp");
    				
        } 
        
        else if (option.equals("knowledge")){
            System.out.println("go to Knowledge option!");
            
        	ServletContext context = getServletContext();
    		String rulefile = context.getRealPath("/WEB-INF/rule/knowledge/knowledge.rules");
    		String data_in = context.getRealPath("/WEB-INF/rdf/knowledge/knowledge.ttl");
    		String data_out = context.getRealPath("/WEB-INF/output/knowledge/");
    		data_out += "/" + option + ".ttl";
    		    		
    		response.getWriter().append("Rule file is obtained at: ").append(rulefile).println();
    		response.getWriter().append("Data input is obtained at: ").append(data_in).println();
    		response.getWriter().append("Data output is generated at: ").append(data_out).println();
    		
    		reasoning.reasoner(data_out, data_in, rulefile, "hybrid");
            
        }
        
        else if (option.equals("reputation")){
            System.out.println("go to Reputation option!");
        	ServletContext context = getServletContext();
    		String rulefile = context.getRealPath("/WEB-INF/rule/reputation/reputation.rules");
    		String data_in = context.getRealPath("/WEB-INF/rdf/reputation/reputation.ttl");
    		String data_out = context.getRealPath("/WEB-INF/output/reputation/");
    		data_out += "/" + option + ".ttl";
    		
    		response.getWriter().append("Rule file is obtained at: ").append(rulefile).println();
    		response.getWriter().append("Data input is obtained at: ").append(data_in).println();
    		response.getWriter().append("Data output is generated at: ").append(data_out).println();
    		
    		reasoning.reasoner(data_out, data_in, rulefile, "hybrid");
        }
        
        else if (option.equals("recommendation")){
            System.out.println("go to Recommendation option!");
        	ServletContext context = getServletContext();
    		String rulefile = context.getRealPath("/WEB-INF/rule/recommendation/recommendation.rules");
    		String data_in = context.getRealPath("/WEB-INF/rdf/recommendation/recommendation.ttl");
    		String data_out = context.getRealPath("/WEB-INF/output/recommendation/");
    		data_out += "/" + option + ".ttl";
    		
    		response.getWriter().append("Rule file is obtained at: ").append(rulefile).println();
    		response.getWriter().append("Data input is obtained at: ").append(data_in).println();
    		response.getWriter().append("Data output is generated at: ").append(data_out).println();
    		
    		reasoning.reasoner(data_out, data_in, rulefile, "hybrid");
        }
        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            rd.include(request, response);
            out.println("<font color=red>Trust Selection is wrong.</font>");
        }
    }
 
}
