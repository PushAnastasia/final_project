package shop.bugred.utils.models;

public class ItemShortVersion {

    private int last_id;
    private String title;

    public int getLast_id() {
        return last_id;
    }

    public void setLast_id(int last_id) {
        this.last_id = last_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ItemShortVersion{" +
                "last_id=" + last_id +
                ", title='" + title + '\'' +
                '}';
    }
}
