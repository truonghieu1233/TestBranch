/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entity.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class ManageStudent {
    public static List<Student> list = new ArrayList<>();
    public void addNew(Student student){
        list.add(student);
    }
    public List<Student> getList(){
        return list;
    }
}
