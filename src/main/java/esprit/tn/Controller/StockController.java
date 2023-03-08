package esprit.tn.Controller;

import esprit.tn.Entites.Stock;
import esprit.tn.Entites.searchStock;
import esprit.tn.Service.IStockService;
import esprit.tn.Service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(tags = "stock management")
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private  IStockService iStockService;



    @GetMapping("/retrieve-all-stocks")
    public List<Stock> listStock() {
        return iStockService.retrieveAllStocks();
    }




   @PostMapping(value= {"/search-stock"})
    @ApiOperation(value = "search multi")
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
    @ApiOperation(value = "trie par date creation desc")
    @ResponseBody
    public List<Stock> getStockOrderByCreatedAtDesc() {
        return iStockService.getStockLsitOrderByCreatedAtDesc();
    }

    @GetMapping("/stock-createdAt-Asc")
    @ApiOperation(value = "trie par date creation asc")
    @ResponseBody
    public List<Stock> getStockOrderByCreatedAtAsc() {
        return iStockService.getStockLsitOrderByCreatedAtAsc();
    }
    @GetMapping("/stock-uapdatedAt-Desc")
    @ApiOperation(value = "trie par date modification desc")
    @ResponseBody
    public List<Stock> getStockOrderByUpdatedAtDesc() {
        return iStockService.getStockLsitOrderByUpdatedAtDesc();
    }

    @GetMapping("/stock-uapdatedAt-Asc")
    @ApiOperation(value = "trie par date modification asc")
    @ResponseBody
    public List<Stock> getStockOrderByUpdatedAtAsc() {
        return iStockService.getStockLsitOrderByUpdatedAtAsc();
    }
    @GetMapping("/stock-qte-Desc")
    @ApiOperation(value = "trie par qte desc")
    @ResponseBody
    public List<Stock> getStockOrderBy() {
        return iStockService.getStockLsitOrderByQuantityDesc();
    }

    @GetMapping("/stock-qte-Asc")
    @ApiOperation(value = "trie par qte asc")
    @ResponseBody
    public List<Stock> getStockOrderByQteAsc() {
        return iStockService.getStockLsitOrderByQuantityAsc();
    }

    @GetMapping("/stock-libelle-desc")
    @ApiOperation(value = "trie par liebelle desc")
    @ResponseBody
    public List<Stock> getStockOrderByLibelleDisc() {
        return iStockService.getStockLsitOrderByLibelleDesc();
    }

    @GetMapping("/stock-libelle-asc")
    @ApiOperation(value = "trie par liebelle asc")
    @ResponseBody
    public List<Stock> getStockOrderByLibelleAsc() {
        return iStockService.getStockLsitOrderByLibelleAsc();
    }






}