package svl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class svlResultados1
 */
public class svlResultados1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public svlResultados1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		dao.OnpeDAO daoOnpe = new dao.OnpeDAO();
		
		String id = request.getParameter("id");
		
		String idDepartamento = request.getParameter("cboDepartamento");
		String idProvincia = request.getParameter("cboProvincia");
		String idDistrito = request.getParameter("cboDistrito");
		Object data = null;

		if ( idDepartamento == null ) idDepartamento = "-1";
		if ( idProvincia == null ) idProvincia = "-1";
		if ( idDistrito == null ) idDistrito = "-1";

		if ( idDepartamento.equals("-1") )  {
			idProvincia = "-1";
			session.setAttribute("provincias", null);
		}
		if ( idProvincia.equals("-1") ) {
			idDistrito = "-1";
			session.setAttribute("distritos", null);
		}
	
		
		
		if (  session.getAttribute("departamentos") == null )
			session.setAttribute("departamentos", daoOnpe.getDepartamentos(1,25) );
		
		if ( !idDepartamento.equals( session.getAttribute("idDepartamento") ) ) {
			idProvincia = "-1";
			session.setAttribute("idDepartamento", idDepartamento);
			session.setAttribute("provincias", daoOnpe.getProvincias(idDepartamento) );
		}
		
		if ( !idProvincia.equals( session.getAttribute("idProvincia") ) ) {
			idDistrito = "-1";
			session.setAttribute("idProvincia", idProvincia);
			session.setAttribute("distritos", daoOnpe.getDistritos(idProvincia) );
		}
			
		
		
		
		
		
		String sDPD = idDepartamento + "," + idProvincia + "," + idDistrito + "," ;
		session.setAttribute("id", id);
		session.setAttribute("data", data);
		session.setAttribute("dpd", sDPD);
		
		response.sendRedirect("resultados1.jsp");
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
