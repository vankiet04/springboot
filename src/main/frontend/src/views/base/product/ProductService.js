import axios from 'axios'

const API_URL = 'http://localhost:8080/api/products'

class ProductService {
  getProducts() {
    return axios.get(`${API_URL}/getall`)
  }

  getProductAtPage(page) {
    return axios.get(`${API_URL}/getProductPage?page=${page}`)
  }

}

export default new ProductService()