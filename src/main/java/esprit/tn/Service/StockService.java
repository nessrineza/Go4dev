package esprit.tn.Service;

import esprit.tn.Entites.Stock;
import esprit.tn.Repository.ClaimRepository;
import esprit.tn.Repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StockService implements IStockService {
    private final StockRepository stockRepository;

    @Override
    public List<Stock> retrieveAllStocks() {

        return (List<Stock>) stockRepository.findAll();
    }

    @Override
    public Stock addStock(Stock s) {

        s.setCreatedAt(new Date());
        return stockRepository.save(s);
    }

    @Override
    public Stock updateStock(Stock s) {


        s.setUpdatedAt(new Date());
        return stockRepository.save(s);
    }

    @Override
    public Stock retrieveStock(int id) {

        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStock(int id) {
        stockRepository.deleteById(id);

    }

    @Override
    @Scheduled(cron = "*/60 * * * * *")
    public void StockStatut() {
        List<Stock> stockList;
        stockList = (List<Stock>) stockRepository.retrieveStock();
        for (Stock item : stockList) {
            log.info(item.getLibelleStock() + " en rupture la quantité min est " + item.getQuantityMin()
                    + " la quant actuelle est " + item.getQuantity());
        }
    }

    @Override
    public void calculStock(int idStock) {

    }

    /* @Transactional
     public void calculStock(int idStock) {
         Stock s = retrieveStock(idStock);
         s.setQuantity(SponsoringRepository.calculStock(idStock));
         updateStock(s);
     }*/
   @Override
   public List<Stock> getStockEnRupture() {


       return stockRepository.retrieveStockEnRp();

   }
    @Override
     public List<Stock> searchStcokWithLibelle(String str) {
        return null;
    }

















}
