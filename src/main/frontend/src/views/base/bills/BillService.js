import axios from 'axios'

const API_URL = 'http://localhost:8080/api/bills'

class BillService {
  getBills() {
    return axios.get(`${API_URL}/getall`)
  }

  getBillById(id) {
    return axios.get(`${API_URL}/get/${id}`)
  }
}

export default new BillService()