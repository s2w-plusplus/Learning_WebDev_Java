package javabeans;

import java.util.List;

import dao.CourseDaoImpl;

public class CourseManagingBean {

	private CourseDaoImpl cdao;
	
	public CourseManagingBean() {
		System.out.println("course bean created");
		this.cdao = new CourseDaoImpl();
	}

	public List<String> fetchAllTitles(){
	
	 return cdao.getAllCourseTitles();
	}
	
	
	
}
