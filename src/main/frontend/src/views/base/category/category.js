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
    CModalFooter,
    CModalBody
} from '@coreui/react'
import Pagination from '@mui/material/Pagination'

class CategoryTable extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      categories: [],
      currentPage: 1,
      pageLimit: 0,
      perPage: 5,
      showModal: false,
      editCategory: null,
      newCategory: {
        id: '',
        name: '',
      },
    }
  }

  componentDidMount() {
    this.fetchCategories(1)
  }

  fetchCategories(page) {
    const API_URL = 'http://localhost:8080/api/categories/getall';
    fetch(API_URL)
      .then((response) => response.json())
      .then((data) => {
        // in ra categories
        alert("Danh sách thể loại: " + JSON.stringify(data));
        const pageLimit = Math.ceil(data.length / this.state.perPage)
        this.setState({ categories: data, pageLimit })
        console.log("Danh sach the loai: " + categories)
      })
      .catch((error) => {
        console.error('Lỗi fetch categories:', error)
      })
  }

  handlePageChange = (event, value) => {
    this.setState({ currentPage: value })
    this.fetchCategories(value)
  }

  toggleModal = (category = null) => {
    this.setState((prevState) => ({
      showModal: !prevState.showModal,
      editCategory: category,
      newCategory: category || {
        id: '',
        name: '',
      },
    }))
  }

  handleInputChange = (e) => {
    const { name, value } = e.target
    this.setState((prevState) => ({
      newCategory: {
        ...prevState.newCategory,
        [name]: value,
      },
    }));
  }

  handleSave = () => {
    const { newCategory, editCategory } = this.state;
    const categoryData = {
      id: newCategory.id,
        name: newCategory.name,
      
    };

    if (newCategory.name === '') {
      alert('Vui lòng nhập tên thể loại')
      return
    }

    const API_URL = 'http://localhost:8080/api/categories';

    if (editCategory) {
      fetch(`${API_URL}/update/${editCategory.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(categoryData),
      })
        .then((response) => {
          if (response.ok) {
            alert('Sửa thể loại thành công')
            this.toggleModal()
            this.fetchCategories(this.state.currentPage)
          } else {
            alert('Có lỗi xảy ra khi sửa thể loại')
            console.error('Lỗi sửa thể loại:', response)
          }
        })
        .catch((error) => {
          alert('Có lỗi xảy ra khi sửa thể loại')
          console.error('Lỗi sửa thể loại:', error)
        })
    } else {
      fetch(`${API_URL}/add`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(categoryData),
      })
        .then((response) => {
          if (response.ok) {
            alert('Thêm thể loại thành công')
            this.toggleModal()
            this.fetchCategories(this.state.currentPage)
          } else {
            alert('Có lỗi xảy ra khi thêm thể loại')
            console.error('Lỗi thêm thể loại:', response)
          }
        })
        .catch((error) => {
          alert('Có lỗi xảy ra khi thêm thể loại')
          console.error('Lỗi thêm thể loại:', error)
        })
    }
  }

  handleDelete = (categoryId) => {
      // cap nhat trang thai bang 0
      const API_URL = 'http://localhost:8080/api/categories';
      fetch(`${API_URL}/updateStatus/${categoryId}`, {
          method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
          },
      })
        .then((response) => {
            if (response.ok) {
                alert('Xóa thể loại thành công')
                this.fetchCategories(this.state.currentPage)
            } else {
                alert('Có lỗi xảy ra khi xóa thể loại')
                console.error('Lỗi xóa thể loại:', response)
            }
        })
        .catch((error) => {
          alert('Có lỗi xảy ra khi xóa thể loại');
          console.error('Lỗi xóa thể loại:', error);
        });
  }

  render() {
    const { categories, currentPage, pageLimit, showModal, newCategory, editCategory } = this.state;

    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              Category Information
              <CButton color="primary" className="float-end" onClick={() => this.toggleModal()}>
                Thêm thể loại
              </CButton>
            </CCardHeader>
            <CCardBody>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell>ID</CTableHeaderCell>
                    <CTableHeaderCell>Tên thể loại</CTableHeaderCell>
                    <CTableHeaderCell>Hành động</CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>
                {categories.length === 0 ? (
        alert('Rỗng')
      ) : (
        categories.map((category, index) => (
          <CTableRow key={index}>
            <CTableDataCell>{category.id}</CTableDataCell>
            <CTableDataCell>{category.categoryName}</CTableDataCell>
            <CTableDataCell>
              <CButton color="warning" onClick={() => this.toggleModal(category)}>Sửa</CButton>
              <CButton color="danger" onClick={() => this.handleDelete(category.id)}>Xóa</CButton>
            </CTableDataCell>
          </CTableRow>
        ))
      )}
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
            <CModalTitle>{editCategory ? 'Chỉnh sửa thể loại' : 'Thêm thể loại'}</CModalTitle>
          </CModalHeader>
          <CModalBody>
            <CForm>
              <div className="mb-3">
                <CFormLabel htmlFor="categoryName">Tên thể loại</CFormLabel>
                <CFormInput
                  type="text"
                  id="categoryName"
                  name="name"
                  value={newCategory.name}
                  onChange={this.handleInputChange}
                />
              </div>
            </CForm>
          </CModalBody>
          <CModalFooter>
            <CButton color="secondary" onClick={() => this.toggleModal()}>
              Đóng
            </CButton>
            <CButton color="primary" onClick={this.handleSave}>
              {editCategory ? 'Cập nhật' : 'Lưu'}
            </CButton>
          </CModalFooter>
        </CModal>
      </CRow>
    )
  }
}

export default CategoryTable