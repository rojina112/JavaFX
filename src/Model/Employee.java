package Model;

public class Employee {
	 public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	private String employeeName;
	    public Employee(String employeeName, String position, String department, int salary) {
		super();
		this.employeeName = employeeName;
		this.position = position;
		this.department = department;
		this.Salary = salary;
	}
		private String position;
	    private String department;
	    private int Salary;
	    

public Employee() {
	
	this.employeeName = "";
	this.position = "";
	this.department = "";
	Salary = 0;
}
	
}

