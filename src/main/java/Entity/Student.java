/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class Student {
    private String id, name;
    private int age;
    private boolean gender;
    List<String> hobbies = new ArrayList<>();

    public Student(String id, String name, int age, boolean gender, List<String> hobbies) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.hobbies = hobbies;
    }


    public Student() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isGender() {
        return gender;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", hobbies=" + hobbies + '}';
    }
    
}
