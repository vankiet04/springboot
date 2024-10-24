import axios from 'axios'

const API_URL = 'http://localhost:8080/api/employees'

class ProductService {
  getEmployee() {
    return axios.get(`${API_URL}/getall`)
  }

  getEmployeeAtPage(page) {
    return axios.get(`${API_URL}/getemployeePage?page=${page}`)
  }

}

export default new employeeService()