package shop.bugred.utils.models;

public class IdRequest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IdRequest() {
    }

    public IdRequest(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdRequest{" +
                "id=" + id +
                '}';
    }
}
