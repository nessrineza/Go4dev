package esprit.tn.Controller;

import esprit.tn.Entites.Stock;
import esprit.tn.Service.IClaimService;
import esprit.tn.Entites.Claim;
import esprit.tn.Service.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/claim")
public class StockController {
    private IStockService iStockService;

    @GetMapping("/retrieve-all-stocks")
    public List<Stock> listStock() {
        return iStockService.retrieveAllStocks();
    }


    @PostMapping("/addd")
    public Stock addStock(@RequestBody Stock c) {

        Stock stock = iStockService.addStock(c);
        return stock;
    }
    @PutMapping("/modify-stock")
    public Stock modifyStock(@RequestBody Stock stock) {
        return iStockService.updateStock(stock);
    }

    @GetMapping("/retrieve-stock/{stock-id}")
    public Stock retrieveStock(@PathVariable("stock-id") Integer stockId) {
        return iStockService.retrieveStock(stockId);
    }
    @DeleteMapping("/remove-stock/{stock-id}")
    public void removeStock(@PathVariable("stock-id") int stocktId) {
        iStockService.deleteStock(stocktId);
    }

    @PutMapping("/calcul-stock/{stock-id}")
    public void calculStock(@PathVariable("stock-id") int stocktId) {
        iStockService.calculStock(stocktId);
    }

    @GetMapping("/stock-rupture")
    public List<Stock> retrieveStock() {
        return iStockService.getStockEnRupture();
    }

    @GetMapping("/search-stock/{str}")
    public List<Stock> searchStcokWithLibelle(@PathVariable("str") String str) {
        return iStockService.searchStcokWithLibelle(str);
    }


//




}