package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Produit;
import DAO.UserDAO;
import Services.ProduitMetier;
import Services.ProduitMetierImpl;
import model.User;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProduitMetier metier;
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		metier  = ProduitMetierImpl.getInstance();
		userDAO = new UserDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null || action.isEmpty()) {
			action = "login";
		}

		String vue = "";

		switch (action) {
			case "login":
				vue = handleLogin(request, response);
				break;
			case "logout":
				vue = handleLogout(request, response);
				break;
			case "listProduits":
				vue = handleListProduits(request, response);
				break;
			case "addProduit":
				vue = handleAddProduit(request, response);
				break;
			case "deleteProduit":
				vue = handleDeleteProduit(request, response);
				break;
			case "editProduit":
				vue = handleEditProduit(request, response);
				break;
			case "updateProduit":
				vue = handleUpdateProduit(request, response);
				break;
			default:
				vue = "/WEB-INF/views/login.jsp";
		}

		if (vue != null && !vue.isEmpty()) {
			request.getRequestDispatcher(vue).forward(request, response);
		}
	}

	private String handleLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			String login    = request.getParameter("login");
			String password = request.getParameter("password");

			User u = userDAO.findByLoginAndPassword(login, password);
			if (u != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", u);
				response.sendRedirect(request.getContextPath() + "/Controller?action=listProduits");
				return null;
			} else {
				request.setAttribute("erreur", "Login ou mot de passe invalide");
				return "/WEB-INF/views/login.jsp";
			}
		}
		return "/WEB-INF/views/login.jsp";
	}

	private String handleLogout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath() + "/Controller?action=login");
		return null;
	}

	private String handleListProduits(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("idProduit");
		List<Produit> liste = new ArrayList<>();

		if (idParam != null && !idParam.isEmpty()) {
			try {
				Long id = Long.parseLong(idParam);
				Produit p = metier.getProduitById(id);
				if (p != null) {
					liste.add(p);
				}
			} catch (NumberFormatException e) {
				liste = metier.getAllProduits();
			}
		} else {
			liste = metier.getAllProduits();
		}

		request.setAttribute("listeProduits", liste);

		HttpSession session = request.getSession(false);
		User u = (session != null) ? (User) session.getAttribute("user") : null;

		if (u != null && "admin".equals(u.getRole())) {
			return "/WEB-INF/views/index.jsp";
		} else {
			return "/WEB-INF/views/list.jsp";
		}
	}

	private String handleAddProduit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			String nom         = request.getParameter("nom");
			String description = request.getParameter("description");
			Double prix        = Double.parseDouble(request.getParameter("prix"));

			metier.addProduit(new Produit(nom, description, prix));
			response.sendRedirect(request.getContextPath() + "/Controller?action=listProduits");
			return null;
		}
		return "/WEB-INF/views/index.jsp";
	}

	private String handleDeleteProduit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		metier.deleteProduit(id);
		response.sendRedirect(request.getContextPath() + "/Controller?action=listProduits");
		return null;
	}

	private String handleEditProduit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id   = Long.parseLong(request.getParameter("id"));
		Produit p = metier.getProduitById(id);

		request.setAttribute("produitEdit",   p);
		request.setAttribute("listeProduits", metier.getAllProduits());
		return "/WEB-INF/views/index.jsp";
	}

	private String handleUpdateProduit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Long   id          = Long.parseLong(request.getParameter("idProduit"));
		String nom         = request.getParameter("nom");
		String description = request.getParameter("description");
		Double prix        = Double.parseDouble(request.getParameter("prix"));

		Produit p = new Produit();
		p.setIdProduit(id);
		p.setNom(nom);
		p.setDescription(description);
		p.setPrix(prix);

		metier.updateProduit(p);
		response.sendRedirect(request.getContextPath() + "/Controller?action=listProduits");
		return null;
	}
}
