package esprit.tn.services;

import esprit.tn.Entites.Room;
import esprit.tn.Entites.User;
import esprit.tn.repository.RoomRepository;
import esprit.tn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserRepository userRepository;

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
    @Override
    public void assignUserToRoom(Integer idRoom, Long idUser) {
        Room r= roomRepository.findById(idRoom).orElse(null);
        User u=userRepository.findById(idUser).orElse(null);
        if(r.getUsers()==null){
            Set<User> users=new HashSet<>();
            users.add(u);
            r.setUsers((List<User>) users);}
        else{r.getUsers().add(u);}
        roomRepository.save(r);
        userRepository.save(u);

    }

}
