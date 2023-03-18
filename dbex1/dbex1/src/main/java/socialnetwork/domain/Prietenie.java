package socialnetwork.domain;

import java.util.Objects;

public class Prietenie extends Entity<Long>{

    private final Long ID1,ID2;
    
    public Prietenie(Long ID1, Long ID2){

        this.ID1 = ID1;
        this.ID2 = ID2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.getID(), ((Prietenie) o).getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID1, ID2);
    }

    public Long getID1() {
        return ID1;
    }

    public Long getID2() {

        return ID2;
    }

    public String toString() {
        return "Utilizator{" +
                "id='"+getId()+ '\''+
                ",ID1='" + getID1() + '\'' +
                ", ID2='" + getID2() + '\''+
                '}';
    }
}
