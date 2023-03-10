package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.KhachHangDAO;
import model.KhachHang;

/**
 * Servlet implementation class Register
 */
@WebServlet("/do-register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String hoVaTen = request.getParameter("hoVaTen");
		String gioiTinh =  request.getParameter("gioiTinh");
		String ngaySinh =  request.getParameter("ngaySinh");
		String diaChi = request.getParameter("diaChi");
		String diaChiNhanHang = request.getParameter("diaChiNhanHang");
		String email = request.getParameter("email");
		String dienThoai = request.getParameter("dienThoai");
		String dieukhoan = request.getParameter("dieukhoan");
		String dongYNhapEmail =  request.getParameter("dongYNhapEmail");
		
		String url;
		String baoLoi = "";
		KhachHangDAO khachHangDAO =  new KhachHangDAO();
		if(khachHangDAO.checkExistUserName(username)) {
			baoLoi+= "Đã tồn tại";
		}
		if(!password.equals(password2)) {
			baoLoi+= "Mật khẩu không khớp";
		}
		if(dieukhoan==null) {
			baoLoi+= "Chưa chấp nhận điều khoản";
		}
		if(baoLoi.length()>0) {
			request.setAttribute("username",username);
			request.setAttribute("password",password);
			request.setAttribute("password2",password2);
			request.setAttribute("hoVaTen",hoVaTen);
			request.setAttribute("gioiTinh",gioiTinh);
			request.setAttribute("ngaySinh",ngaySinh);
			request.setAttribute("diaChi",diaChi);
			request.setAttribute("diaChiNhanHang",diaChiNhanHang);
			request.setAttribute("email",email);
			request.setAttribute("dienThoai",dienThoai);
			request.setAttribute("dieukhoan",dieukhoan);
			request.setAttribute("dongYNhapEmail",dongYNhapEmail);
			request.setAttribute("baoLoi",baoLoi);
			url = "/register.jsp";
		}else {
			Random random = new Random();
			String maKhachHang = System.currentTimeMillis()+ random.nextInt(1000)+"";
			khachHangDAO.insert(new KhachHang(maKhachHang, username, password, hoVaTen, gioiTinh, diaChi, diaChiNhanHang, Date.valueOf(ngaySinh), dienThoai, email, dongYNhapEmail!=null));
			url = "/success.jsp";
		}
		System.out.println(baoLoi);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
