package Services;

import java.util.List;
import DAO.Produit;
import DAO.ProduitDAO;
import DAO.ProduitImpl;

public class ProduitMetierImpl implements ProduitMetier {
	
	private static ProduitMetierImpl instance;
	private ProduitDAO dao;
	
	private ProduitMetierImpl() {
		dao = new ProduitImpl();
	}
	
	public static ProduitMetierImpl getInstance() {
		if (instance == null) {
			instance = new ProduitMetierImpl();
		}
		return instance;
	}
	
	@Override
	public void addProduit(Produit p) {
		dao.addProduit(p);
	}
	
	@Override
	public void deleteProduit(Long id) {
		dao.deleteProduit(id);
	}
	
	@Override
	public List<Produit> getAllProduits() {
		return dao.getAllProduits();
	}
	
	@Override
	public Produit getProduitById(Long id) {
		return dao.getProduitById(id);
	}
	
	@Override
	public void updateProduit(Produit p) {
		dao.updateProduit(p);
	}
}
