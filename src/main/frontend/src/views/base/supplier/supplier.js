import React from 'react'
import {
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CRow,
  CTable,
  CTableBody,
  CTableDataCell,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
} from '@coreui/react'
import Pagination from '@mui/material/Pagination'

class SupplierTable extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      suppliers: [],
      currentPage: 1,
      pageLimit: 0,
      perPage: 5,
    }
  }

  componentDidMount() {
    this.fetchSuppliers(1)
  }

  fetchSuppliers(page) {
    const API_URL = 'http://localhost:8080/api/suppliers/getall'
    fetch(API_URL)
      .then((response) => response.json())
      .then((data) => {
        const pageLimit = Math.ceil(data.length / this.state.perPage)
        this.setState({ suppliers: data, pageLimit })
      })
      .catch((error) => {
        console.error('Lỗi fetch suppliers:', error)
      })
  }

  handlePageChange = (event, value) => {
    this.setState({ currentPage: value })
    this.fetchSuppliers(value)
  }

  render() {
    const { suppliers, currentPage, pageLimit } = this.state

    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              Supplier Information
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
                        <CButton color="warning">Sửa</CButton>
                        <CButton color="danger">Xóa</CButton>
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
      </CRow>
    )
  }
}

export default SupplierTable