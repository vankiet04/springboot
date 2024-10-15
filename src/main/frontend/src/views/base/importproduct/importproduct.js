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

class ImportProductTable extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      importProducts: [],
      currentPage: 1,
      pageLimit: 0,
      perPage: 5,
    }
  }

  componentDidMount() {
    this.fetchImportProducts(1)
  }

  fetchImportProducts(page) {
    const API_URL = 'http://localhost:8080/api/importproduct/getall'
    fetch(API_URL)
      .then((response) => response.json())
      .then((data) => {
        const pageLimit = Math.ceil(data.length / this.state.perPage)
        this.setState({ importProducts: data, pageLimit })
      })
      .catch((error) => {
        console.error('Lỗi fetch import products:', error)
      })
  }

  handlePageChange = (event, value) => {
    this.setState({ currentPage: value })
    this.fetchImportProducts(value)
  }

  render() {
    const { importProducts, currentPage, pageLimit } = this.state

    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              Import Product Information
              <CButton color="primary" className="float-end">
                Thêm  phiếu nhập
              </CButton>
            </CCardHeader>
            <CCardBody>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell>Mã phiếu nhập</CTableHeaderCell>
                    <CTableHeaderCell>Thời gian</CTableHeaderCell>
                    <CTableHeaderCell>Mã nhà cung cấp</CTableHeaderCell>
                    <CTableHeaderCell>Nhân viên</CTableHeaderCell>
                    <CTableHeaderCell>Tổng tiền</CTableHeaderCell>
                    <CTableHeaderCell>Hành động</CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>
                  {importProducts.map((importProduct, index) => (
                    <CTableRow key={index}>
                      <CTableDataCell>{importProduct.id}</CTableDataCell>
                      <CTableDataCell>{importProduct.importDate}</CTableDataCell>
                      <CTableDataCell>{importProduct.supplierId}</CTableDataCell>
                      <CTableDataCell>{importProduct.employeeId}</CTableDataCell>
                      <CTableDataCell>{importProduct.totalAmount}</CTableDataCell>
                      <CTableDataCell>
                        <CButton color="info">Xem phiếu</CButton>
                        <CButton color="danger">Hủy phiếu</CButton>
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

export default ImportProductTable
