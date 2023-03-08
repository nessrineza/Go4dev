package esprit.tn.Controller;

import esprit.tn.Entites.Stock;
import esprit.tn.Entites.searchStock;
import esprit.tn.Service.IStockService;
import esprit.tn.Service.StockService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private  IStockService iStockService;



    @GetMapping("/retrieve-all-stocks")
    public List<Stock> listStock() {
        return iStockService.retrieveAllStocks();
    }




   @PostMapping(value= {"/search-stock"})

    @ResponseBody
    public List<Stock> search(@RequestBody searchStock obj) {

        return iStockService.rechercheStcokAvance(obj);

    }

    @PostMapping("/addd")
    public ResponseEntity<Stock> addStock(@RequestBody Stock c) {

        iStockService.addStock(c);
return new ResponseEntity<>(c, HttpStatus.CREATED);


    }
    //done
    @PutMapping("/modify-stock")
    public Stock modifyStock(@RequestBody Stock stock) {
        return iStockService.updateStock(stock);
    }
    @GetMapping("/retrieveStock/{idStock}")
    public Stock retrieveStock(@PathVariable("idStock") int idStock) {

           return iStockService.retrieveStock(idStock);



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
    /*ici*/



    @GetMapping("/stock-createdAt-Desc")
    @ResponseBody
    public List<Stock> getStockOrderByCreatedAtDesc() {
        return iStockService.getStockLsitOrderByCreatedAtDesc();
    }

    @GetMapping("/stock-createdAt-Asc")

    @ResponseBody
    public List<Stock> getStockOrderByCreatedAtAsc() {
        return iStockService.getStockLsitOrderByCreatedAtAsc();
    }
    @GetMapping("/stock-uapdatedAt-Desc")
    @ResponseBody
    public List<Stock> getStockOrderByUpdatedAtDesc() {
        return iStockService.getStockLsitOrderByUpdatedAtDesc();
    }

    @GetMapping("/stock-uapdatedAt-Asc")

    @ResponseBody
    public List<Stock> getStockOrderByUpdatedAtAsc() {
        return iStockService.getStockLsitOrderByUpdatedAtAsc();
    }
    @GetMapping("/stock-qte-Desc")
    @ResponseBody
    public List<Stock> getStockOrderBy() {
        return iStockService.getStockLsitOrderByQuantityDesc();
    }

    @GetMapping("/stock-qte-Asc")
    @ResponseBody
    public List<Stock> getStockOrderByQteAsc() {
        return iStockService.getStockLsitOrderByQuantityAsc();
    }

    @GetMapping("/stock-libelle-desc")
    @ResponseBody
    public List<Stock> getStockOrderByLibelleDisc() {
        return iStockService.getStockLsitOrderByLibelleDesc();
    }

    @GetMapping("/stock-libelle-asc")
    @ResponseBody
    public List<Stock> getStockOrderByLibelleAsc() {
        return iStockService.getStockLsitOrderByLibelleAsc();
    }






}