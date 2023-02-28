package esprit.tn.controllers;

import esprit.tn.Entites.Room;
import esprit.tn.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Room")

public class RoomController {
    @Autowired
    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Annotation

    // Save operation
    @PostMapping("/add")
    public Room saveRoom(
            @RequestBody Room room)
    {

        return roomService.addRoom(room);
    }

    // Read operation
    @GetMapping("/Rooms")
    public List<Room> retrieveAllRooms()
    {

        return roomService.retrieveAllRooms();
    }
    @GetMapping("/retrieve/{id}")
    public Room getRoom(@PathVariable("id") Integer roomId)
    {

        return roomService.retrieveRoom(roomId);
    }


    // Update operation
    @PutMapping("/update")
    public Room
    updateRoom(@RequestBody Room Room)
    {

        return roomService.updateRoom(Room);
    }

    // Delete operation
    @DeleteMapping("/delete/{id}")
    public String deleteRoomById(@PathVariable("id") Integer RoomId)
    {

        roomService.removeRoomById(RoomId);
        return "Deleted Successfully";
    }
}
