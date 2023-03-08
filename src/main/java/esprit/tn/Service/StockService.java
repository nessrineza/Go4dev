package esprit.tn.Service;
import esprit.tn.repository.ClaimRepository;
import esprit.tn.repository.StockRepository;
import esprit.tn.Entites.Sponsoring;
import esprit.tn.Entites.Stock;
import esprit.tn.Entites.searchStock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StockService implements IStockService {
    @Autowired
      StockRepository stockRepository;

    //done
    @Override
    public List<Stock> retrieveAllStocks() {

        return  stockRepository.findAll();
    }
//done
    @Override
    public void addStock(Stock s) {

       stockRepository.save(s);
    }
//done
    @Override
    public Stock updateStock(Stock s) {


        s.setUpdatedAt(new Date());
        return stockRepository.save(s);
    }

//done
    @Override
    public Stock retrieveStock(Integer idStock) {
        return stockRepository.findById(idStock).orElse(null);


    }
//done
    @Override
    public void deleteStock(int id) {
        stockRepository.deleteById(id);

    }
//done
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
   //done
    @Override
     public List<Stock> searchStcokWithLibelle(String str) {
        return stockRepository.findByLibelleStock(str) ;
    }

/*ici*/
@Override
public List<Stock> rechercheStcokAvance(searchStock obj) {

    return stockRepository.rechercheStcokAvance(obj.getQuery(), obj.getDateDebut(), obj.getDateFin(),
            obj.getNbrProduct());
}

    @Override
    public List<Stock> getStockLsitOrderByCreatedAtDesc() {
        // TODO Auto-generated method stub
        return stockRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Stock> getStockLsitOrderByCreatedAtAsc() {
        // TODO Auto-generated method stub
        return stockRepository.findAllByOrderByCreatedAtAsc();
    }

    @Override
    public List<Stock> getStockLsitOrderByUpdatedAtDesc() {
        // TODO Auto-generated method stub
        return stockRepository.findAllByOrderByUpdatedAtDesc();
    }

    @Override
    public List<Stock> getStockLsitOrderByUpdatedAtAsc() {
        // TODO Auto-generated method stub
        return stockRepository.findAllByOrderByUpdatedAtAsc();
    }

    @Override
    public List<Stock> getStockLsitOrderByQuantityDesc() {
        // TODO Auto-generated method stub
        return stockRepository.findAllByOrderByQuantityDesc();
    }

    @Override
    public List<Stock> getStockLsitOrderByQuantityAsc() {
        // TODO Auto-generated method stub
        return stockRepository.findAllByOrderByQuantityAsc();
    }

    @Override
    public List<Stock> getStockLsitOrderByLibelleDesc() {
        // TODO Auto-generated method stub
        List<Stock> sortedList = retrieveAllStocks().stream()
                .sorted(((x1, x2) -> x2.getLibelleStock().compareTo(x1.getLibelleStock())))
                .collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Stock> getStockLsitOrderByLibelleAsc() {
        // TODO Auto-generated method stub

        List<Stock> sortedList = retrieveAllStocks().stream()
                .sorted(((x1, x2) -> x1.getLibelleStock().compareTo(x2.getLibelleStock())))
                .collect(Collectors.toList());
        return sortedList;
    }



    }








