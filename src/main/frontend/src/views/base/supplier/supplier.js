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
import supplierService from './supplierService'

class SupplierTable extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      suppliers: [],
      currentPage: 1,
      pageLimit: 0,
      perPage: 5,
      editSupplier: null,
      showModal: false,
      newSupplier: {
        id: '',
        name: '',
        address: '',
        email: '',
        phoneNumber: '',
      },
    }
  }

  componentDidMount() {
    supplierService.getSupplier()
    .then((response) => {
      const { data } = response;
      const pageLimit = Math.ceil(data.length / this.state.perPage);
      this.setState({ pageLimit });
    })
    .catch((error) => {
      console.error('Lỗi fetch suppliers:', error);
    });
    this.fetchSuppliers(1)
  }

  componentDidUpdate(prevProps, prevState) {
    if (prevState.currentPage !== this.state.currentPage) {
      this.fetchSuppliers(this.state.currentPage);
    }
  }

  fetchSuppliers(page) {
    supplierService.getSupplierAtPage(page)
      .then((response) => {
        const { data } = response;
        this.setState({ suppliers: data });
      })
      .catch((error) => {
        console.error('Lỗi fetch suppliers:', error);
      });
  }

  handlePageChange = (event, value) => {
    this.setState({ currentPage: value });
  }

  toggleModal = (supplier = null) => {
    this.setState((prevState) => ({
      showModal: !prevState.showModal,
      editSupplier: supplier,
      newSupplier: supplier || {
        id: '',
        name: '',
        address: '',
        email: '',
        phoneNumber: '',
      },
    }))
  }
  handleInputChange = (e) => {
    const { name, value } = e.target
      this.setState((prevState) => ({
        newSupplier: {
          ...prevState.newSupplier,
          [name]: value,
        },
      }));
  }

  handleSave = () => {
    const { newSupplier, editSupplier } = this.state;
    const supplierData = {
      id: 9999,
      name: newSupplier.name,
      address: newSupplier.address,
      email: newSupplier.email,
      phoneNumber: newSupplier.phoneNumber,
      status: 1,
    };
    if(newSupplier.name == ''){
      alert("Vui lòng nhập họ tên")
      return
    }
    if(newSupplier.address == ''){
      alert("Vui lòng nhập địa chỉ")
      return
    }
    if(newSupplier.email == ''){
      alert("Vui lòng nhập email")
      return
    }
    if(newSupplier.phoneNumber == ''){
      alert("Vui lòng nhập số điện thoại")
      return
    }
    
    if (editSupplier) {

      alert("Sửa thông tin nhà cung cấp: " +  JSON.stringify(supplierData))
      console.log('Sửa thông tin nhà cung cấp', supplierData)

     const API_URL_SUA = 'http://localhost:8080/api/suppliers';
      fetch(`${API_URL_SUA}/update/${editSupplier.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(supplierData),
      })
        .then((response) => {
          console.log('data trả về nè', response)
          if (response.ok) {
            alert('Sửa thông tin nhà cung cấp thành công rồi nè')
            this.toggleModal()
            this.fetchSuppliers(this.state.currentPage)
          } else {
            alert('Có lỗi xảy ra khi sửa thông tin ở trong')
            console.error('Lỗi sửa thông tin:', response)
          }
        })
        .catch((error) => {
          alert('Có lỗi xảy ra khi sửa thông tin')
          console.error('Lỗi sửa thông tin:', error
          )
        }
      )
    }else{
      alert('Thêm thông tin nhà cung cấp' + JSON.stringify(supplierData))
      console.log('Thêm thông tin nhà cung cấp', supplierData)

      const API_URL = 'http://localhost:8080/api/suppliers';
      fetch(`${API_URL}/add`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(supplierData),
      })
        .then((response) => {
          console.log('data trả về nè', response)
          if (response.ok) {
            alert('Thêm thông tin nhà cung cấp thành công rồi nè')
            this.toggleModal()
            this.fetchSuppliers(this.state.currentPage)
          } else {
            alert('Có lỗi xảy ra khi thêm thông tin nhà cung cấp ở trong')
            console.error('Lỗi thêm thông tin nhà cung cấp:', response)
          }
        })
        .catch((error) => {
          alert('Có lỗi xảy ra khi thêm thông tin nhà cung cấp')
          console.error('Lỗi thêm thông tin nhà cung cấp:', error)
        })
    }
  }
  handleDelete = (supplierId) => {
    const API_URL = 'http://localhost:8080/api/suppliers';
    fetch(`${API_URL}/updateStatus/${supplierId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ status: 0 }), // Đảm bảo gửi đúng dữ liệu
    })
      .then((response) => {
        console.log('Response:', response); // Thêm log để kiểm tra phản hồi
        if (response.ok) {
          alert('Xóa thông tin nhà cung cấp thành công rồi nè');
          this.fetchSuppliers(this.state.currentPage);
        } else {
          alert('Có lỗi xảy ra khi xóa thông tin nhà cung cấp');
          console.error('Lỗi xóa thông tin nhà cung cấp:', response);
        }
      })
      .catch((error) => {
        alert('Có lỗi xảy ra khi xóa thông tin nhà cung cấp');
        console.error('Lỗi xóa thông tin nhà cung cấp:', error);
      });
  }

  render() {
    const { suppliers, currentPage, pageLimit, showModal, newSupplier } = this.state;
    if (!Array.isArray(suppliers)) {
      console.error('Suppliers is not an array:', suppliers)
      return null
    }

    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              Supplier Information
              <CButton color="primary" className="float-end" onClick={() => this.toggleModal()}>
                Thêm nhà cung cấp 
              </CButton>
            </CCardHeader>
            <CCardBody>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell>Mã nhà cung cấp</CTableHeaderCell>
                    <CTableHeaderCell>Tên nhà cung cấp</CTableHeaderCell>
                    <CTableHeaderCell>Địa chỉ</CTableHeaderCell>
                    <CTableHeaderCell>Email</CTableHeaderCell>
                    <CTableHeaderCell>Số điện thoại</CTableHeaderCell>
                    <CTableHeaderCell>Hành động</CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>
                  {suppliers.map((supplier, index) => (
                    <CTableRow key={index}>
                      <CTableDataCell>{supplier.id}</CTableDataCell>
                      <CTableDataCell>{supplier.name}</CTableDataCell>
                      <CTableDataCell>{supplier.address}</CTableDataCell>
                      <CTableDataCell>{supplier.email}</CTableDataCell>
                      <CTableDataCell>{supplier.phoneNumber}</CTableDataCell>
                      <CTableDataCell>
                      <CButton color="warning" onClick={() => this.toggleModal(supplier)}>Sửa</CButton>
                      <CButton color="danger" onClick={() => this.handleDelete(supplier.id)}>Xóa</CButton>
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
            <CModalTitle>{this.state.editSupplier ? 'Chỉnh sửa thông tin nhà cung cấp' : 'Thêm thông tin nhà cung cấp'}</CModalTitle>
          </CModalHeader>
          <CModalBody>
            <CForm>
              {/* <div className="mb-3">
                <CFormLabel htmlFor="productId">ID</CFormLabel>
                <CFormInput type="text" id="productId" name="id" value={newProduct.id} onChange={this.handleInputChange} />
              </div> */}
              <div className="mb-3">
                <CFormLabel htmlFor="supplierName">Tên nhà cung cấp</CFormLabel>
                <CFormInput type="text" id="supplierName" name="name" value={newSupplier.name} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="supplierAddress">Địa chỉ</CFormLabel>
                <CFormInput type="text" id="supplierAddress" name="address" value={newSupplier.address} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="supplierEmail">Email</CFormLabel>
                <CFormInput type="text" id="supplierEmail" name="email" value={newSupplier.email} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="supplierPhoneNumber">Số điện thoại</CFormLabel>
                <CFormInput type="text" id="supplierPhoneNumber" name="phoneNumber" value={newSupplier.phoneNumber} onChange={this.handleInputChange} />
              </div>
            </CForm>
          </CModalBody>
          <CModalFooter>
            {/* <CButton color="secondary" onClick={() => this.toggleModal()}>
              Đóng
            </CButton> */}
            <CButton color="primary" onClick={this.handleSave}>{this.state.editSupplier ? 'Cập nhật' : 'Lưu'}</CButton>
          </CModalFooter>
        </CModal>
      </CRow>
    )
  }
}

export default SupplierTable;