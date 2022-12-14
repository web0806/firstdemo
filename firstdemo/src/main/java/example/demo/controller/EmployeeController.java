package example.demo.controller;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import example.demo.entity.Employee;

@RestController
public class EmployeeController {
	private ArrayList<Employee> employeeList = new ArrayList<>();

	public EmployeeController() {
		employeeList.add(new Employee("Arpit", "IT"));
		employeeList.add(new Employee("Sanjeev", "IT"));
		employeeList.add(new Employee("Ben", "IT"));
	}

	@GetMapping("/employee")
	public ArrayList<Employee> get() {
		return employeeList;
	}

	@GetMapping("/employee/{id}")
	public Employee retrieveOneEmployee(@PathVariable("id") int id) {
		Employee emp = null;
		try {
			emp = employeeList.get(id);
		} catch (Exception ex) {
			System.out.println("employee error " + ex.getMessage());
		}
		return emp;
	}

	@PostMapping(value = "/employee", produces = MediaType.TEXT_HTML_VALUE)
	public String addOneEmployee(@RequestBody Employee employee) {
		employeeList.add(employee);
		return "employee add to List";
	}
	@PutMapping(value="/employee/{id}",produces=MediaType.TEXT_HTML_VALUE)
	  public String editOneEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		  if(id >=employeeList.size() || id<0) {
			  employeeList.add(employee);
			  return "index not in list so add "+employee.toString();
		  }
		  employeeList.set(id,employee);
		  return "update index "+id+ " to"+ employee.toString();
	  }
	@DeleteMapping(value="/employee/{id}",produces=MediaType.TEXT_HTML_VALUE)
	  public String removeOneEmployee(@PathVariable("id") int id) {
		  if(id>=0 && id <employeeList.size()) {
			     String temp=employeeList.get(id).toString();
	             employeeList.remove(id);
	             return temp+"   deleted";
		  }
		  else
			  return id+" index invalid";
	  }

}


//http://localhost:8080/demo/employee----->0/1/2

