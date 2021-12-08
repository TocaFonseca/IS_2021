package beans;

import data.*;

import javax.persistence.*;
import java.util.*;

public interface IManageStudents {

    public void addStudent(String name);

    public List<Student> listStudents();
}
