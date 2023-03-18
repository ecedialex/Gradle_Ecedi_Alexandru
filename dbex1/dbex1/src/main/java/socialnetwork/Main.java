package socialnetwork;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.UtilizatorValidator;
import socialnetwork.repository.Repository;
import socialnetwork.repository.database.PrietenieDbRepository;
import socialnetwork.repository.database.UtilizatorDbRepository;
import socialnetwork.service.PrietenieService;
import socialnetwork.service.UtilizatorService;
import socialnetwork.ui.UI;

public class Main {
    public static void main(String[] args) {

        String username="postgres";
        String pasword="postgres";
        String url="jdbc:postgresql://localhost:5432/academic";

       Repository<Long, Utilizator> userDbRepository = new UtilizatorDbRepository(url,username, pasword,  new UtilizatorValidator());
       Repository<Long,Prietenie> friendsDbRepository = new PrietenieDbRepository(url,username,pasword);
       PrietenieService prietenieService = new PrietenieService(friendsDbRepository);
       UtilizatorService utilizatorService = new UtilizatorService(userDbRepository);
//       System.out.println(utilizatorService.findUser(1L));
//       System.out.println(utilizatorService.findUser(2L));
       UI ui = new UI(utilizatorService,prietenieService);
       ui.start();


//        //userDbRepository.delete(3);
//        //userDbRepository.save(new Utilizator("cojocarescu","dan"));
//        //userDbRepository.update(5,new Utilizator("numeschimbat","muie"));
//        userDbRepository.findAll().forEach(x-> System.out.println(x));
//
//        //friendsDbRepository.save(new Prietenie(3l,4l));
//        //friendsDbRepository.update(4,new Prietenie(5l,6l));
//        //friendsDbRepository.delete(3);
//        friendsDbRepository.findAll().forEach(System.out::println);
//
//        UtilizatorService service = new UtilizatorService(userDbRepository);
//        PrietenieService servicePrietenii = new PrietenieService(friendsDbRepository);
//        //service.addUtilizator(new Utilizator("popesc","ana"));
//        //userDbRepository.findAll().forEach(x-> System.out.println(x));
//        service.removeUtilizator(6);
//        service.getAll().forEach(System.out::println);
//        service.getAll().forEach(System.out::println);
//        servicePrietenii.getAll().forEach(System.out::println);

    }
}