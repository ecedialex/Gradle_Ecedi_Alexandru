package socialnetwork.ui;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.PrietenieService;
import socialnetwork.service.UtilizatorService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UI {
    private final Scanner scanner;
    private final UtilizatorService utilizatorService;
    private final PrietenieService prietenieService;

    public UI(UtilizatorService utilizatorService,PrietenieService prietenieService){
        this.utilizatorService=utilizatorService;
        this.prietenieService=prietenieService;
        this.scanner=new Scanner(System.in);
    }

    public void start() {
        int alegere;
        while (true) {
            printeazaMeniu();
            System.out.print("Alegerea dvs. este: ");
            alegere=scanner.nextInt();

            //eliminare \n in plus
            scanner.nextLine();

            switch(alegere){
                case 1 -> adaugaPrietenie();
                case 2 -> stergePrietenie();

                case 3 -> printeazaPrietenii();

                case 4 -> adaugaUtilizator();
                case 5 -> stergeUtilizator();
                case 6 -> modificaUtilizator();
                case 7 -> printeazaUtilizatori();


                case 8 -> System.exit(0);
                default -> System.out.println("Alegere invalida!");
            }
        }
    }

    private void printeazaMeniu(){
        System.out.println("1. Adauga prietenie");
        System.out.println("2. Sterge prietenie");
        System.out.println("3. Afiseaza lista de prietenii\n");
        System.out.println("4. Adauga utilizator");
        System.out.println("5. Sterge utilizator");
        System.out.println("6. Modifica utilizator");
        System.out.println("7. Afiseaza lista de utilizatori\n");
        System.out.println("8. Exit");
    }

    private void printeazaPrietenii(){
        System.out.println("Lista de prietenii:");
        for (Prietenie fr : prietenieService.getAllFriendships()) {
            Long id1=fr.getID1();
            Optional<Utilizator> user1= utilizatorService.findUser(id1);
            String nume1 = user1.get().getFirstName();
            Long id2=fr.getID2();
            Optional<Utilizator> user2= utilizatorService.findUser(id2);
            String nume2 = user2.get().getFirstName();
            System.out.println("Prietenie cu ID: " + fr.getID() +" intre " + nume1 +" -id("+fr.getID1()+")"+ " si " + nume2+" -id("+fr.getID2()+")");
        }
    }

    private String citire(String text) {
        System.out.print(text);
        return scanner.nextLine();
    }
    private void adaugaPrietenie(){
        Long ID1, ID2;
        ID1=Long.parseLong(citire("ID prieten 1: "));
        ID2=Long.parseLong(citire("ID prieten 2: "));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String dateTimeString = now.format(formatter);
        LocalDateTime friendsFrom = LocalDateTime.parse(dateTimeString, formatter);
        Iterable<Utilizator> usersList= utilizatorService.getAll();
        int ok=0;
        int ok2=1;
        Iterable<Prietenie> friendshipsList = prietenieService.getAll();
        for(Prietenie prietenie : friendshipsList)
        {
            if(prietenie.getID1()==ID1 && prietenie.getID2()==ID2)
                ok2=0;
            if(prietenie.getID1()==ID2 && prietenie.getID2()==ID1)
                ok2=0;
        }
        for (Utilizator utilizator : usersList) {
                if (utilizator.getId()==ID1)
                    ok++;
                if(utilizator.getId()==ID2)
                    ok++;
            }
             if(ok==2 && ok2==1) {
                 prietenieService.addPrietenie(ID1, ID2);
                 System.out.println("Prietenie adaugata cu succes!\n");
             } else System.out.println("Eroare.");
    }

    private void stergePrietenie(){
        //int ID = Integer.parseInt(citire("ID prietenie de sters: "));
        String ID = citire("ID prietenie de sters: ");
        prietenieService.removePrietenie(ID);
        System.out.println("Prietenie stearsa cu succes!\n");

    }

    private void adaugaUtilizator(){
        String string2=citire("Nume: ");
        String string3=citire("Prenume: ");
        utilizatorService.addUtilizator(string2, string3);
        System.out.println("Utilizator adaugat cu succes!\n");

    }

    private void modificaUtilizator(){
        String string1=citire("ID-ul utilizatorului de modificat: ");
        String string2=citire("Nume nou: ");
        String string3=citire("Prenume nou: ");
        utilizatorService.updateUtilizator(Integer.parseInt(string1), string2, string3);
        System.out.println("Utilizator modificat cu succes!\n");
    }

    private void stergeUtilizator(){
        int ID = (int) Long.parseLong(citire("ID utilizator de sters: "));
        utilizatorService.removeUtilizator(ID);
        System.out.println("Utilizator sters cu succes!\n");
    }

    private void printeazaUtilizatori(){
        System.out.println("Lista de utilizatori:");
        //utilizatorService.getAll().forEach(System.out::println);
        List<Utilizator> list=new ArrayList<>();
        utilizatorService.getAll().forEach(x->list.add(x));
        list.sort((Utilizator x,Utilizator y)->x.getLastName().compareTo(y.getLastName()));
        list.forEach(System.out::println);
    }


}