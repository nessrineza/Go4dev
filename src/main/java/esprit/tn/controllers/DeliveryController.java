package esprit.tn.controllers;

import esprit.tn.Entites.Delivery;
import esprit.tn.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/delivery")
    public void addDelivery(@RequestBody Delivery delivery)
    {
        deliveryService.addDelivery(delivery);
    }

    @GetMapping("/deliveries")
    public List<Delivery> readDeliveries()
    {
        return deliveryService.readDeliveries();
    }

    @GetMapping("/delivery/{id}")
    public Delivery findDeliveryById(@PathVariable("id") String id)
    {
        return deliveryService.findDeliveryById(Integer.parseInt(id));
    }

    @PutMapping("/delivery/{id}")
    public void updateDelivery(@PathVariable("id") String id)
    {
        deliveryService.updateDelivery(Integer.parseInt(id));
    }

    @DeleteMapping("/delivery/{id}")
    public void deleteCommand (@PathVariable("id") String id)
    {
        deliveryService.deleteDelivery(Integer.parseInt(id));
    }
}
