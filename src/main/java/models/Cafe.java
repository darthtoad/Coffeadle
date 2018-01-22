package models;

/**
 * Created by Guest on 1/22/18.
 */
public class Cafe {
    private String name;
    private String address;
    private String zip;
    private String phone;
    private String website;
    private String email;
    private int id;

    public Cafe(String name, String address, String zip, String phone) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.phone = phone;
        this.website = "no website listed";
        this.email = "no email available";
    }

    public Cafe(String name, String address, String zip, String phone, String website, String email) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.phone = phone;
        this.website = website;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cafe cafe = (Cafe) o;

        if (!name.equals(cafe.name)) return false;
        if (address != null ? !address.equals(cafe.address) : cafe.address != null) return false;
        if (zip != null ? !zip.equals(cafe.zip) : cafe.zip != null) return false;
        return phone != null ? phone.equals(cafe.phone) : cafe.phone == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
