package com.vub.datadump;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vub.model.Assistant;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Professor;

public class LoadDump {

	public ArrayList<Course> loadCourses() {
		ArrayList<Course> listCourse = new ArrayList<Course>();
		
		
		DbTranslateDump dbTranslateDump = new DbTranslateDump();
		listCourse = dbTranslateDump.loadCourseId();
		//listCourseComponents = dbTranslateDump.loadCourseComponent(8187);
		
		for (Course course : listCourse) {
			ArrayList<CourseComponent> listCourseComponents = new ArrayList<CourseComponent>();
			ArrayList<Professor> listOfProfessors = new ArrayList<Professor>();
			ArrayList<Assistant> listOfAssistants = new ArrayList<Assistant>();
			
			listCourseComponents = dbTranslateDump.loadCourseComponent(course.getiD());
			listOfProfessors = dbTranslateDump.loadProfessor(course.getiD());
			listOfAssistants = dbTranslateDump.loadAssistant(course.getiD());
			
			course.setListOfAssistants(listOfAssistants);
			course.setListOfProfessors(listOfProfessors);
			course.setListOfComponents(listCourseComponents);
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(listCourse));

		return listCourse;
	}
}
