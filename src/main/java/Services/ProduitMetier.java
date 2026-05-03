package Services;

import java.util.List;
import DAO.Produit;

public interface ProduitMetier {
	public void addProduit(Produit p);
	public void deleteProduit(Long id);
	public void updateProduit(Produit p);
	public List<Produit> getAllProduits();
	public Produit getProduitById(Long id);
}
