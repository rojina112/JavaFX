package Model;

public class EmployeePageModel {
	private int employeeId;
	private String employeeName;
	private String phone;
	private String address;
	private String email;
	private String password;
	private String role;
	private String status;
	private int UploadDate;
	

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		if (employeeName == null || employeeName.trim().isEmpty()) {
			throw new IllegalArgumentException("Employee name cannot be empty");
		}
		this.employeeName = employeeName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (phone == null || phone.trim().isEmpty()) {
			throw new IllegalArgumentException("Phone number cannot be empty");
		}
		if (!phone.matches("^[0-9]{10}$")) {
			throw new IllegalArgumentException("Phone number must be 10 digits");
		}
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty");
		}
		if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("Invalid email format");
		}
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public int getUploadDate() {
		 return UploadDate;
		}
	public void setUploadDate(int UploadDate) {
		this.UploadDate = UploadDate;
	}

	public EmployeePageModel() {
		this.employeeId = 0;
		this.employeeName = "";
		this.phone = "";
		this.address = "";
		this.email = "";
		this.role = "Employee"; // Default role
		this.status = "Pending";
		this.UploadDate=0;
	}

	public EmployeePageModel(int employeeId, String employeeName, String phone, String address, String email, int Uploaddate) {
		this.setEmployeeId(employeeId);
		this.setEmployeeName(employeeName);
		this.setPhone(phone);
		this.setAddress(address);
		this.setEmail(email);
		this.role = "Employee"; // Default role
		
	}

	public EmployeePageModel(int employeeId, String employeeName, String phone, String address, String email, String role) {
		this.setEmployeeId(employeeId);
		this.setEmployeeName(employeeName);
		this.setPhone(phone);
		this.setAddress(address);
		this.setEmail(email);
		this.setRole(role);
	}

	
}
