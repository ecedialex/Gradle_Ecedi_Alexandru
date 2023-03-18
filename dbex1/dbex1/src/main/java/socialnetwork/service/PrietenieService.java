package socialnetwork.service;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PrietenieService {
    private Repository<Long, Prietenie> repo;

    public PrietenieService(Repository<Long,Prietenie> repo)
    {
        this.repo=repo;
    }
    public Optional<Prietenie> addPrietenie(Long id1,Long id2)
    {
        try {
        return repo.save(new Prietenie(id1, id2));
    }
        catch(ValidationException e){
        System.out.println(e.getMessage());
    }

        return Optional.empty();
    }
    public Optional<Prietenie> removePrietenie(String id_prietenie) {
        try {
            return repo.delete(Integer.parseInt(id_prietenie));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
    public Optional<Prietenie> updatePrietenie(int id,Prietenie pNou) {
        try {
            return repo.update(id, pNou);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
    public List<Prietenie> getAllFriendships(){
        Iterable<Prietenie> friendships = repo.findAll();
        return StreamSupport.stream(friendships.spliterator(),false).collect(Collectors.toList());
    }
    public Iterable<Prietenie> getAll()
    {
        return repo.findAll();
    }

}
