package esprit.tn.controllers;

import esprit.tn.Entites.Room;
import esprit.tn.repository.RoomRepository;
import esprit.tn.services.PublicationService;
import esprit.tn.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/Room")

public class RoomController {
    @Autowired
    RoomService roomService;
   @Autowired
   PublicationService publicationService;
    @Autowired
    private RoomRepository roomRepository;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Annotation

    // Save operation
    @Transactional
    @PostMapping("/add/{id}")
    public ResponseEntity<String> saveRoom(
            @RequestBody Room room,@PathVariable("id") Long id)
    {
        if(publicationService.isFormal(room.getTopic())){
            roomService.addRoom(room);
             roomService.assignUserToRoom(room, id);

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
    @Scheduled(fixedRate = 3600000)
    @PostMapping("/signalAction")public void signalAction(){roomService.signalAction();}

    @GetMapping("/sortedByFav")
    public List<Room> sortedByfav(){return roomRepository.SortedByFavoris();}
}
