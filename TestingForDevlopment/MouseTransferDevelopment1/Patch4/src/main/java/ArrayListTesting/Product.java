package ArrayListTesting;

public class Product {
    private String name;
    private boolean Status;

    public Product(String name, boolean Status){
        this.name = name;
        this.Status = Status;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
