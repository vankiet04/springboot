import axios from 'axios'

const API_URL = 'http://localhost:8080/api/products'

class ProductService {
  getProducts() {
    return axios.get(`${API_URL}/getall`)
  }

  getProductAtPage(page) {
    return axios.get(`${API_URL}/getProductPage?page=${page}`)
  }

  addProduct(product) {
    return axios
      .post(`${API_URL}/add`, product, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .then((response) => {
        // Xử lý khi request thành công
        console.log('Thêm sản phẩm thành công:', response.data)
      })
      .catch((error) => {
        // Xử lý khi request thất bại
        if (error.response) {
          // Máy chủ đã trả về phản hồi với mã trạng thái không nằm trong khoảng 2xx
          console.error('Lỗi thêm sản phẩm:', error.response.data)
          alert(`Có lỗi xảy ra khi thêm sản phẩm: ${error.response.data.message || error.response.data}`)
        } else if (error.request) {
          // Yêu cầu đã được gửi đi nhưng không nhận được phản hồi
          console.error('Không nhận được phản hồi từ máy chủ:', error.request)
          alert('Không nhận được phản hồi từ máy chủ. Vui lòng thử lại sau.')
        } else {
          // Đã xảy ra lỗi khi thiết lập yêu cầu
          console.error('Lỗi khi thiết lập yêu cầu:', error.message)
          alert(`Lỗi khi thiết lập yêu cầu: ${error.message}`)
        }
      })
  }
}

export default new ProductService()