package DAO;
import java.util.List;

public interface ProduitDAO {
	public void addProduit(Produit p);
	public void deleteProduit(Long id);
	public void updateProduit(Produit p);
	public List<Produit> getAllProduits();
	public Produit getProduitById(Long id);
}
