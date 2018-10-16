package org.store.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.store.domain.StoreVO;
import org.store.service.StoreService;

import com.google.gson.Gson;

/**
 * Servlet implementation class StoreServlet
 */
@WebServlet("/store")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StoreService service;

    /**
     * Default constructor. 
     */
    public StoreServlet() {
    	try {
    	service = new StoreService();
    	} catch(Exception e) {
    		e.getMessage();
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String latStr = request.getParameter("lat");
		String lngStr = request.getParameter("lng");
		String[] arr = request.getParameterValues("kind");
		
		String callback = request.getParameter("callback");
		
		double lat = Double.parseDouble(latStr);
		double lng = Double.parseDouble(lngStr);
		
		List<StoreVO> result = service.getNearKind(arr, lat, lng);
		
		Gson gson = new Gson();
		String data = gson.toJson(result);
		
		response.setContentType("application/javascript; charset=UTF8");
		response.getWriter().print(callback + "(" + data + ")");
	}

}
