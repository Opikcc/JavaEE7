package javaeems.chapter4.widgets;

public class Guest  {
    private String name;
    private int age;
    
    public Guest(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String toString() {
        return "Item: " + name;
    }
    
}
