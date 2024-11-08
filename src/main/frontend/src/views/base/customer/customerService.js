import axios from 'axios'

const API_URL = 'http://localhost:8080/api/customers'

class customerService {
  getCustomer() {
    return axios.get(`${API_URL}/getall`)
  }

  getCustomerAtPage(page) {
    return axios.get(`${API_URL}/getCustomerPage?page=${page}`)
  }

}

export default new customerService()