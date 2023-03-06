package esprit.tn.services;

import esprit.tn.Entites.Room;

import java.util.List;

public interface RoomService {

    Room addRoom(Room p);

    // read operation
    List<Room> retrieveAllRooms();

    Room retrieveRoom(Integer idRoom);

    Room updateRoom(Room p);


    // delete operation
    void removeRoomById(Integer idRoom);

    void assignUserToRoom(Integer idRoom, Long idUser);

    void signalAction();
}