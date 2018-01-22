package models;

/**
 * Created by Guest on 1/22/18.
 */
public class Review {
    private String writtenBy;
    private int rating;
    private int id;
    private int cafeId;
    private String kantTent;

    public Review(String writtenBy, int rating, int cafeId, String kantTent) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.cafeId = cafeId;
        this.kantTent = kantTent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (rating != review.rating) return false;
        if (cafeId != review.cafeId) return false;
        if (!writtenBy.equals(review.writtenBy)) return false;
        return kantTent != null ? kantTent.equals(review.kantTent) : review.kantTent == null;
    }

    @Override
    public int hashCode() {
        int result = writtenBy.hashCode();
        result = 31 * result + rating;
        result = 31 * result + cafeId;
        result = 31 * result + (kantTent != null ? kantTent.hashCode() : 0);
        return result;
    }

    public String getWrittenBy() {

        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCafeId() {
        return cafeId;
    }

    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;
    }

    public String getKantTent() {
        return kantTent;
    }

    public void setKantTent(String kantTent) {
        this.kantTent = kantTent;
    }
}
