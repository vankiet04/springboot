import React from 'react'
import {
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CForm,
  CFormInput,
  CFormLabel,
  CFormTextarea,
  CFormSelect,
  CRow,
  CTable,
  CTableBody,
  CTableDataCell,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
  CModal,
  CModalHeader,
  CModalTitle,
  CModalBody,
  CModalFooter,
} from '@coreui/react'
import Pagination from '@mui/material/Pagination'
import ProductService from './ProductService'
import './ProductTable.css' // Import file CSS

class ProductTable extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      products: [],
      categories: [],
      currentPage: 1,
      pageLimit: 0,
      perPage: 5,
      showModal: false,
      showDetailModal: false,
      editProduct: null,
      selectedProduct: null,
      showAddDetailModal: false,
      newProduct: {
        img: null,
        id: '',
        name: '',
        description: '',
        categoryId: '',
      },
      newDetail: {
        color: '',
        size: '',
        price: 0,
        quantity: 0,
      },
      productDetails: [],
    }
  }
  
  componentDidMount() {
    // GET ALL PRODUCTS TO GET SIZE OF PRODUCTS
    ProductService.getProducts()
      .then((response) => {
        const { data } = response
        const pageLimit = Math.ceil(data.length / this.state.perPage)
        this.setState({ pageLimit })
      })
      .catch((e) => {
        console.error('Lỗi fetch products:', e)
      })

    // GET PRODUCTS BY PAGE 1
    this.fetchProducts(1)
      // GET ALL CATEGORIES
      this.fetchCategories()
  }

  componentDidUpdate(prevProps, prevState) {
    if (prevState.currentPage !== this.state.currentPage) {
      this.fetchProducts(this.state.currentPage)
    }
  }

  fetchProducts(page) {
    ProductService.getProductAtPage(page)
      .then((response) => {
        const { data } = response
        this.setState({ products: data })
      })
      .catch((e) => {
        console.error('Lỗi fetch products:', e)
      })
  }

  fetchProductDetails(productId) {
    const API_URL = `http://localhost:8080/api/products/${productId}/details`
    fetch(API_URL)
      .then((response) => response.json())
      .then((data) => {
        if (Array.isArray(data)) {
          this.setState({ productDetails: data })
        } else {
          console.error('Dữ liệu trả về không phải là mảng:', data)
          this.setState({ productDetails: [] })
        }
      })
      .catch((error) => {
        console.error('Lỗi fetch product details:', error)
        this.setState({ productDetails: [] })
      })
  }
  fetchCategories() {
    const API_URL = 'http://localhost:8080/api/categories/getall';
    fetch(API_URL)
      .then((response) => response.json())
      .then((data) => {
        this.setState({ categories: data });
        alert('data categories' + JSON.stringify(data))
        console.log('data categories'+ JSON.stringify(data))
      })
      .catch((error) => {
        console.error('Lỗi fetch categories:', error);
        alert('Lỗi fetch categories:', error)
      });
  }

  handlePageChange = (event, value) => {
    this.setState({ currentPage: value })
  }

  toggleModal = (product = null) => {
    this.setState((prevState) => ({
      showModal: !prevState.showModal,
      editProduct: product,
      newProduct: product || {
        img: null,
        id: '',
        name: '',
        description: '',
        categoryId: '',
      },
    }))
  }
  toggleDetailModal = (product = null) => {
    if (product) {
      this.fetchProductDetails(product.id)
    }
    this.setState((prevState) => ({
      showDetailModal: !prevState.showDetailModal,
      selectedProduct: product,
    }))
  }

  handleInputChange = (e) => {
    const { name, value, files } = e.target
    if (name === 'img' && files.length > 0) {
      const reader = new FileReader()
      reader.onloadend = () => {
    
        this.setState((prevState) => ({
          newProduct: {
            ...prevState.newProduct,
            img: reader.result, // Lưu trữ URL hoặc base64 của tệp hình ảnh
          },
        }))
      }
      reader.readAsDataURL(files[0])
    } else {
      this.setState((prevState) => ({
        newProduct: {
          ...prevState.newProduct,
          [name]: value,
        },
      }))
    }
  }

  handleSave = () => {
    const { newProduct, editProduct } = this.state
    const productData = {
      id: 9999,
      name: newProduct.name,
      description: newProduct.description,
      img: newProduct.img,
      status: 1, // Bạn có thể thay đổi giá trị này tùy theo yêu cầu của bạn
      categoryId:  newProduct.categoryId,
    }

    //neu ten san pham = '' thi bao loi
    if (newProduct.name === '') {
      alert('Vui lòng nhập tên sản phẩm')
      return
    }
    //neu mo ta = '' thi bao loi
    if (newProduct.description === '') {
      alert('Vui lòng nhập mô tả sản phẩm')
      return
    }
    //neu categoryid = '' thi bao loi
    if (newProduct.categoryId === '') {
      alert('Vui lòng chọn thể loại')
      return
    }
  
    if (editProduct) {
      // Sửa sản phẩm theo ID
      alert('Sửa sản phẩm' + JSON.stringify(productData))
      console.log('Sửa sản phẩm', productData)

      const API_URL_SUA = 'http://localhost:8080/api/products';
      fetch(`${API_URL_SUA}/update/${editProduct.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(productData),
      })
        .then((response) => {
          console.log('data trả về nè', response)
          if (response.ok) {
            alert('Sửa sản phẩm thành công rồi nè')
            this.toggleModal()
            this.fetchProducts(this.state.currentPage)
          } else {
            alert('Có lỗi xảy ra khi sửa sản phẩm ở trong')
            console.error('Lỗi sửa sản phẩm:', response)
          }
        })
        .catch((error) => {
          alert('Có lỗi xảy ra khi sửa sản phẩm')
          console.error('Lỗi sửa sản phẩm:', error
          )
        }
      )

      
    } else {
      //neu san pham khong co hinh anh thi bao loi
      if (!newProduct.img) {
        alert('Vui lòng chọn hình ảnh')
        return
      }
      alert('Thêm sản phẩm' + JSON.stringify(productData))
      console.log('Thêm sản phẩm', productData)

      const API_URL = 'http://localhost:8080/api/products';
      fetch(`${API_URL}/add`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(productData),
      })
        .then((response) => {
          console.log('data trả về nè', response)
          if (response.ok) {
            alert('Thêm sản phẩm thành công rồi nè')
            this.toggleModal()
            this.fetchProducts(this.state.currentPage)
          } else {
            alert('Có lỗi xảy ra khi thêm sản phẩm ở trong')
            console.error('Lỗi thêm sản phẩm:', response)
          }
        })
        .catch((error) => {
          alert('Có lỗi xảy ra khi thêm sản phẩm')
          console.error('Lỗi thêm sản phẩm:', error)
        })
    }
  }
  handleDelete = (productId) => {
    const API_URL = 'http://localhost:8080/api/products';
    fetch(`${API_URL}/updateStatus/${productId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ status: 0 }),
    })
      .then((response) => {
        if (response.ok) {
          alert('Xóa sản phẩm thành công rồi nè');
          this.fetchProducts(this.state.currentPage);
        } else {
          alert('Có lỗi xảy ra khi xóa sản phẩm');
          console.error('Lỗi xóa sản phẩm:', response);
        }
      })
      .catch((error) => {
        alert('Có lỗi xảy ra khi xóa sản phẩm');
        console.error('Lỗi xóa sản phẩm:', error);
      });
  };

  toggleAddDetailModal = () => {
    this.setState((prevState) => ({
      showAddDetailModal: !prevState.showAddDetailModal,
      newDetail: {
        color: '',
        size: '',
        price: 0,
        quantity: 0,
      },
    }))
  }
  render() {
    const { newDetail, showAddDetailModal ,products, currentPage, pageLimit, showModal, showDetailModal, newProduct, selectedProduct, productDetails } = this.state
  
    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              Product Information
              <CButton color="primary" className="float-end" onClick={() => this.toggleModal()}>
                Thêm sản phẩm
              </CButton>
            </CCardHeader>
            <CCardBody>
              <CTable>
                <CTableHead>
                  <CTableRow>

                    <CTableHeaderCell>Hình ảnh</CTableHeaderCell>
                    <CTableHeaderCell>ID</CTableHeaderCell>
                    <CTableHeaderCell>Tên sản phẩm</CTableHeaderCell>
                    <CTableHeaderCell>Mô tả</CTableHeaderCell>
                    <CTableHeaderCell>Thể loại</CTableHeaderCell>
                    <CTableHeaderCell>Chỉnh sửa</CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>
                  {products.map((product, index) => (
                    <CTableRow key={index}>
                      <CTableDataCell>
                        <img src={product.img} alt="product" className="product-image" />
                      </CTableDataCell>
                      <CTableDataCell>{product.id || 'N/A'}</CTableDataCell>
                      <CTableDataCell>{product.name || 'N/A'}</CTableDataCell>
                      <CTableDataCell>{product.description || 'N/A'}</CTableDataCell>
                      <CTableDataCell>
                        {this.state.categories.map((category) => {
                          if (category.id === product.categoryId) {
                            return category.name
                          }
                          return null
                        })}
                      </CTableDataCell>
                      <CTableDataCell>
                        <CButton color="warning" onClick={() => this.toggleModal(product)}>Sửa</CButton>
                        <CButton color="danger" onClick={() => this.handleDelete(product.id)}>Xóa</CButton>
                        <CButton color="info" onClick={() => this.toggleDetailModal(product)}>Chi tiết</CButton>
                      </CTableDataCell>
                    </CTableRow>
                  ))}
                </CTableBody>
              </CTable>
              <Pagination
                count={pageLimit}
                page={currentPage}
                onChange={this.handlePageChange}
                variant="outlined"
                shape="rounded"
              />
            </CCardBody>
          </CCard>
        </CCol>
  
        <CModal visible={showModal} onClose={() => this.toggleModal()}>
          <CModalHeader>
            <CModalTitle>{this.state.editProduct ? 'Chỉnh sửa sản phẩm' : 'Thêm sản phẩm'}</CModalTitle>
          </CModalHeader>
          <CModalBody>
            <CForm>
              <div className="mb-3">
                <CFormLabel htmlFor="productImage">Hình ảnh</CFormLabel>
                <CFormInput type="file" id="productImage" name="img" onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="productName">Tên sản phẩm</CFormLabel>
                <CFormInput type="text" id="productName" name="name" value={newProduct.name} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="productDescription">Mô tả</CFormLabel>
                <CFormTextarea id="productDescription" name="description" rows={3} value={newProduct.description} onChange={this.handleInputChange}></CFormTextarea>
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="categoryId">Thể loại</CFormLabel>
                {this.state.categories.length > 0 && (
                  <CFormSelect id="categoryId" name="categoryId" value={newProduct.categoryId} onChange={this.handleInputChange}>
                    <option value="">Chọn thể loại</option>
                    {this.state.categories.map((category) => (
                      <option key={category.id} value={category.id}>
                        {category.name}
                      </option>
                    ))}
                  </CFormSelect>
                )}
              </div>
            </CForm>
          </CModalBody>
          <CModalFooter>
            <CButton color="secondary" onClick={() => this.toggleModal()}>
              Đóng
            </CButton>
            <CButton color="primary" onClick={this.handleSave}>
              {this.state.editProduct ? 'Cập nhật' : 'Lưu'}
            </CButton>
          </CModalFooter>
        </CModal>
  
        <CModal visible={showDetailModal} onClose={() => this.toggleDetailModal()}>
          <CModalHeader>
            <CModalTitle>Chi tiết sản phẩm</CModalTitle>
          </CModalHeader>
          <CModalBody>
            {selectedProduct && (
              <>
                <div className="mb-3">
                  <img src={selectedProduct.img} alt={selectedProduct.name} className="product-image" />
                </div>
                <div className="mb-3">
                  <CFormLabel>Tên sản phẩm</CFormLabel>
                  <p>{selectedProduct.name}</p>
                </div>
                <CButton color="primary" className="mb-3" onClick={this.toggleAddDetailModal}>Thêm chi tiết sản phẩm</CButton>

                <CTable>
                  <CTableHead>
                    <CTableRow>
                      <CTableHeaderCell>ID</CTableHeaderCell>
                      <CTableHeaderCell>Color</CTableHeaderCell>
                      <CTableHeaderCell>Size</CTableHeaderCell>
                      <CTableHeaderCell>Số lượng</CTableHeaderCell>
                      <CTableHeaderCell>Giá bán</CTableHeaderCell>
                    </CTableRow>
                  </CTableHead>
                  <CTableBody>
                    {Array.isArray(productDetails) && productDetails.map((detail, index) => (
                      <CTableRow key={index}>
                        <CTableDataCell>{detail.id}</CTableDataCell>
                        <CTableDataCell>{detail.color}</CTableDataCell>
                        <CTableDataCell>{detail.size}</CTableDataCell>
                        <CTableDataCell>{detail.quantity}</CTableDataCell>
                        <CTableDataCell>{detail.price}</CTableDataCell>
                      </CTableRow>
                      
                    ))}
                  </CTableBody>
                </CTable>
              </>
            )}
          </CModalBody>
          <CModalFooter>
            <CButton color="secondary" onClick={() => this.toggleDetailModal()}>
              Đóng
            </CButton>
          </CModalFooter>
        </CModal>
        <CModal visible={showAddDetailModal} onClose={this.toggleAddDetailModal}>
          <CModalHeader>
            <CModalTitle>Thêm chi tiết sản phẩm</CModalTitle>
          </CModalHeader>
          <CModalBody>
            <CForm>
              <div className="mb-3">
                <CFormLabel htmlFor="detailColor">Màu</CFormLabel>
                <CFormInput type="text" id="detailColor" name="color" value={newDetail.color} onChange={this.handleDetailInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="detailSize">Kích cỡ</CFormLabel>
                <CFormInput type="text" id="detailSize" name="size" value={newDetail.size} onChange={this.handleDetailInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="detailPrice">Giá bán</CFormLabel>
                <CFormInput type="number" id="detailPrice" name="price" value={newDetail.price} onChange={this.handleDetailInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="detailQuantity">Số lượng</CFormLabel>
                <CFormInput type="number" id="detailQuantity" name="quantity" value={newDetail.quantity} onChange={this.handleDetailInputChange} />
              </div>
            </CForm>
          </CModalBody>
          <CModalFooter>
            <CButton color="secondary" onClick={this.toggleAddDetailModal}>
              Đóng
            </CButton>
            <CButton color="primary" onClick={this.handleSaveDetail}>
              Lưu
            </CButton>
          </CModalFooter>
        </CModal>
      </CRow>
    )
  }
}

export default ProductTable
