package socialnetwork.service;

import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.repository.Repository;



import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UtilizatorService {
    private Repository<Long, Utilizator> repo;

    public UtilizatorService(Repository<Long, Utilizator> repo) {
        this.repo = repo;
    }

    public Optional<Utilizator> addUtilizator(String nume,String prenume) {
        try {
            return repo.save(new Utilizator(nume, prenume));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
    public Optional<Utilizator> removeUtilizator(int id_user) {
        try {
            return repo.delete(id_user);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
    public Optional<Utilizator> updateUtilizator(int id,String numeN,String prenumeN){
        try {
            return repo.update(id, new Utilizator(numeN, prenumeN));
        }
        catch(ValidationException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public List<Utilizator> getAllUsers() {
        Iterable<Utilizator> students = repo.findAll();
        return StreamSupport.stream(students.spliterator(), false).collect(Collectors.toList());
    }


    public List<Utilizator> filterUsersName(String s) {
        Iterable<Utilizator> students = repo.findAll();

        List<Utilizator> filteredUsers = StreamSupport.stream(students.spliterator(), false)
                .filter(user -> user.getFirstName().contains(s)).collect(Collectors.toList());


//        Set<Utilizator> filteredUsers1= new HashSet<>();
//        students.forEach(filteredUsers1::add);
//        filteredUsers1.removeIf(student -> !student.getFirstName().contains(s));

        return filteredUsers;
    }

    public Iterable<Utilizator> getAll(){
        return repo.findAll();
    }

    public Optional<Utilizator> findUser(Long id1) {
        return repo.findOne(id1);
    }


    ///TO DO: add other methods
}
