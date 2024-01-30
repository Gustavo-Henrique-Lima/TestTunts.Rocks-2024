package com.gustavonascimento.TesteEstagioDevTrainingProgram.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gustavonascimento.TesteEstagioDevTrainingProgram.entities.Student;

@Component
public class ConvertSheetDataToClass {

	/**
	 * Converts the provided spreadsheet data into a list of Student entities. Each
	 * row in the data corresponds to a Student, starting from the 4th row.
	 *
	 * @param data                 The spreadsheet data to be converted.
	 * @param totalClassesSemester The total number of classes in the semester.
	 * @return A list of Student entities representing the data.
	 */
	public static List<Student> convertToStudents(List<List<Object>> data, int totalClassesSemester) {
		List<Student> students = new ArrayList<>();

		// Iterate through each row in the data starting from the 4th row.
		for (int i = 3; i < data.size(); i++) {
			List<Object> row = data.get(i);
			Student student = createStudentFromRow(row, totalClassesSemester);
			students.add(student);
		}

		return students;
	}

	/**
	 * Creates a Student entity from a given row of spreadsheet data. Extracts and
	 * parses relevant information from the row and calculates the student's
	 * situation.
	 *
	 * @param row                  The row of data representing a student in the
	 *                             spreadsheet.
	 * @param totalClassesSemester The total number of classes in the semester.
	 * @return A Student entity populated with data from the row.
	 */
	private static Student createStudentFromRow(List<Object> row, int totalClassesSemester) {
		Student student = new Student();
		// Set student properties based on spreadsheet columns.
		student.setId(Long.parseLong(row.get(0).toString()));
		student.setName(row.get(1).toString());
		student.setAbsences(Integer.parseInt(row.get(2).toString()));
		student.setPartialOne(Double.parseDouble(row.get(3).toString()) / 10.0);
		student.setPartialTwo(Double.parseDouble(row.get(4).toString()) / 10.0);
		student.setPartialThree(Double.parseDouble(row.get(5).toString()) / 10.0);
		 // Calculate the student's situation based on total classes in the semester.
		student.calculateSituation(totalClassesSemester);
		return student;
	}
}