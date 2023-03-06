package esprit.tn.services;

import esprit.tn.Entites.EmailDetails;
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
    @Autowired
    EmailService emailService;

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
    @Override
    public void signalAction() {


        List<Room> pubs = retrieveAllRooms();
        for (Room pub : pubs) {
            if (pub.getReport() >= 3 && !pub.isVerif()) {/*send mail to admin and user
                pub.getUsers().get(1).getUsername();*/

                EmailDetails emailDetails = new EmailDetails(/*admin email*/"adminMail@esprit.tn",
                        pub.getUsers().get(0).getUsername() + "'s Room has been reported "
                                + pub.getReport() + " times  ",
                        "Room reported", "");
                emailService.sendSimpleMail(emailDetails);
                EmailDetails emailDetails2 = new EmailDetails
                        (pub.getUsers().get(0).getEmail(),

                                "Your Room has been reported " + pub.getReport() + "times,it might be deleted.Contact admins for more info.  ",
                                "Room reported", "");
                emailService.sendSimpleMail(emailDetails2);
                pub.setVerif(true);
                updateRoom(pub);
                System.out.println("Room reported multiple times");
            } else if
            (pub.getReport() > 5
                            && ((pub.getReport() * 0.15) > (pub.getUsers().size())) ||
                            (pub.getReport() > 5 &&
                                    (pub.getReport() * 0.25) > (pub.getFavoris())))
                /*les Reportes supérieur à 15% de nombre des users => supprimer automatiquement le pub
                 * et envoyer mail à user et admin  */ {
                EmailDetails emailDetails = new EmailDetails(/*admin email*/"adminMail@esprit.tn",
                        pub.getUsers().get(0).getUsername()
                                + "'s Room has been deleted due to multiple reports ",
                        "Room deleted", "");
                emailService.sendSimpleMail(emailDetails);
                EmailDetails emailDetails2 = new EmailDetails
                        (pub.getUsers().get(0).getEmail(),

                                "Your Room has been deleted  ",
                                "Room deleted", "");
                emailService.sendSimpleMail(emailDetails2);
                removeRoomById(pub.getId());
                System.out.println("Room removed");
                System.out.println(pub.getUsers().get(1).getUsername());
            }
        }
    }

}
