package esprit.tn.Service;

import esprit.tn.Entites.Stock;

import java.util.List;

public interface IStockService {
    List<Stock> retrieveAllStocks();

    Stock addStock(Stock s);

    Stock updateStock(Stock u);

    Stock retrieveStock(int id);

    void deleteStock(int id);

    void StockStatut();
    void calculStock(int idStock);

    List<Stock> getStockEnRupture();

    List<Stock> searchStcokWithLibelle(String str);

}
