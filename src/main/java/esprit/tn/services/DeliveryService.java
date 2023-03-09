package esprit.tn.services;


import esprit.tn.Entites.Delivery;
import esprit.tn.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public void addDelivery (Delivery delivery)
    {
        deliveryRepository.save(delivery);
    }

    public void updateDelivery (int id)
    {

    }

    public List<Delivery> readDeliveries()
    {
        return (List<Delivery>) deliveryRepository.findAll();
    }

    public Delivery findDeliveryById(int id)
    {
        return deliveryRepository.findById(id).orElse(null);
    }

    public void deleteDelivery (int id)
    {
        Delivery delivery=deliveryRepository.findById(id).orElse(null);
        deliveryRepository.delete(delivery);
    }

}
