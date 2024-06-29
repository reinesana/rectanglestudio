package assignment2.com.assignment2.models;
import jakarta.persistence.*;

@Entity 
@Table(name="rectangle")
public class Rectangles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int pid;
    private String name;
    private int width;
    private int height;
    private String color;

    public Rectangles() {
    }

    public Rectangles(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for color
    public String getColor() {
        return color;
    }

    // Setter for color
    public void setColor(String color) {
        this.color = color;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    
}
