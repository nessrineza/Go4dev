package esprit.tn.services;

import esprit.tn.Entites.Room;
import esprit.tn.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;


    @Override
    public Room addRoom(Room p) {
        return roomRepository.save(p);

    }
    @Override
    public Room updateRoom(Room p) {
        return roomRepository.save(p);


    }

    @Override
    public void removeRoomById(Integer idRoom) {
        roomRepository.deleteById(idRoom);
    }



    @Override
    public Room retrieveRoom(Integer idRoom) {
        return roomRepository.findById(idRoom).orElse(null);
    }



    @Override
    public List<Room> retrieveAllRooms() {
        List<Room> Rooms =new ArrayList<>();
        roomRepository.findAll().forEach(Rooms::add);

        return Rooms;
    }

}
