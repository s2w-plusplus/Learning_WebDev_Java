package pojo;

public class User {
 private String name;
 private String passwd;
 private int age;
 
 public User(String name, String passwd, int age) {
	super();
	this.name = name;
	this.passwd = passwd;
	this.age = age;
}

public String getName() {
	return name;
}
public String getPasswd() {
	return passwd;
}
public int getAge() {
	return age;
}

@Override
public String toString() {
	return " Name=" + name + ", Password=" + passwd + ", Age=" + age;
}
 
 
 
 
 
 
 
}
