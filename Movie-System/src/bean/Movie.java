package bean;

public class Movie {
    private String id;
    private String title;
    private String date;
    private String time;
    private String price;
    private String hall;
    private String description;
    private byte[] image;


    public Movie(String id, String title, String date, String time, String price, String hall,
                 byte[] image) {
        // TODO Auto-generated constructor stub
        this.price = price;
        this.image = image;
        this.hall = hall;
        this.time = time;
        this.description = description;
        this.title = title;
        this.date = date;
        this.id = id;
    }

    public Movie() {
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }


    public byte[] getImage() {
        return image;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String descriprion) {
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}



