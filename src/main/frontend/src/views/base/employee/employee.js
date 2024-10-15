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

class EmployeeTable extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      employees: [],
      currentPage: 1,
      pageLimit: 0,
      perPage: 5,
    }
  }

  componentDidMount() {
    this.fetchEmployees(1)
  }

  fetchEmployees(page) {
    const API_URL = 'http://localhost:8080/api/employees/getall'
    fetch(API_URL)
      .then((response) => response.json())
      .then((data) => {
        const pageLimit = Math.ceil(data.length / this.state.perPage)
          this.setState({ employees: data, pageLimit })
          alert("Danh sach nhan vien: " + data)
          console.log("Danh sach nhan vien: " + data)
      })
      .catch((error) => {
        console.error('Lỗi fetch employees:', error)
      })
  }

  handlePageChange = (event, value) => {
    this.setState({ currentPage: value })
    this.fetchEmployees(value)
  }

  render() {
    const { employees, currentPage, pageLimit } = this.state

    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              Employee Information
            </CCardHeader>
            <CCardBody>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell>Mã nhân viên</CTableHeaderCell>
                    <CTableHeaderCell>Họ tên</CTableHeaderCell>
                    <CTableHeaderCell>Giới tính</CTableHeaderCell>
                    <CTableHeaderCell>Ngày sinh</CTableHeaderCell>
                    <CTableHeaderCell>Số điện thoại</CTableHeaderCell>
                    <CTableHeaderCell>Email</CTableHeaderCell>
                    <CTableHeaderCell>Hành động</CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>
                  {employees.map((employee, index) => (
                    <CTableRow key={index}>
                      <CTableDataCell>{employee.id}</CTableDataCell>
                      <CTableDataCell>{employee.fullName}</CTableDataCell>
                      <CTableDataCell>{employee.gender}</CTableDataCell>
                      <CTableDataCell>{employee.birthDate}</CTableDataCell>
                      <CTableDataCell>{employee.phoneNumber}</CTableDataCell>
                      <CTableDataCell>{employee.email}</CTableDataCell>
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

export default EmployeeTable