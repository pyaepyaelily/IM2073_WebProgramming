public class SQLData {

    private String name;
    private String desc;
    private Double price;

    public SQLData (String name, String desc, Double price){
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public Double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price = price;
    }

    @Override
    public String toString(){
        return "<p>" + name + "<br>"  + desc + "</p>";
    }

}