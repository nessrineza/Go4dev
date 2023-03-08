package esprit.tn.controllers;

import esprit.tn.Entites.Room;
import esprit.tn.services.PublicationService;
import esprit.tn.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Room")

public class RoomController {
    @Autowired
    RoomService roomService;
   @Autowired
   PublicationService publicationService;
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Annotation

    // Save operation
    @PostMapping("/add")
    public ResponseEntity<String> saveRoom(
            @RequestBody Room room)
    {
        if(publicationService.isFormal(room.getTopic())){
            roomService.addRoom(room);

            return ResponseEntity.ok("Input processed successfully.");}
        else{
            return ResponseEntity.ok("Your room wasn't added because its topic wasn't well formal") ;}}


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
