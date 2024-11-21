import axios from 'axios'

const API_URL = 'http://localhost:8080/api/suppliers'

class supplierService {
  getSupplier() {
    return axios.get(`${API_URL}/getall`)
  }

  getSupplierAtPage(page) {
    return axios.get(`${API_URL}/getSupplierPage?page=${page}`)
  }

}

export default new supplierService()