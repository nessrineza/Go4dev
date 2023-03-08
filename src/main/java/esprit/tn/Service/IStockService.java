package esprit.tn.Service;

import esprit.tn.Entites.Stock;
import esprit.tn.Entites.searchStock;

import java.util.List;

public interface IStockService {
    List<Stock> retrieveAllStocks();

     void addStock(Stock s);

    Stock updateStock(Stock u);

    public Stock retrieveStock(Integer idStock);

    void deleteStock(int id);

    void StockStatut();


    void calculStock(int idStock);

    List<Stock> getStockEnRupture();

    List<Stock> searchStcokWithLibelle(String str);

/*ici*/
List<Stock> rechercheStcokAvance(searchStock obj);

    List<Stock> getStockLsitOrderByCreatedAtDesc();

    List<Stock> getStockLsitOrderByCreatedAtAsc();

    List<Stock> getStockLsitOrderByUpdatedAtDesc();

    List<Stock> getStockLsitOrderByUpdatedAtAsc();

    List<Stock> getStockLsitOrderByQuantityDesc();

    List<Stock> getStockLsitOrderByQuantityAsc();

    List<Stock> getStockLsitOrderByLibelleDesc();

    List<Stock> getStockLsitOrderByLibelleAsc();


}
