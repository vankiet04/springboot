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
import employeeService from './employeeService'

class EmployeeTable extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      employees: [],
      currentPage: 1,
      pageLimit: 0,
      perPage: 5,
      editEmployee: null,
      showModal: false,
      newEmployee: {
        id: '',
        fullName: '',
        birthDate: '',
        phoneNumber: '',
        email: '',
        gender: '',
      },
    }
  }

  componentDidMount() {
    employeeService.getEmployee()
    .then((response) => {
      this.setState({ employees: response.data })
    }
    )
    .catch((error) => {
      console.error('Lỗi fetch employees:', error)
    }
    )
    
    this.fetchEmployees(1)
  }


  fetchEmployees(page) {
    const API_URL = 'http://localhost:8080/api/employees/getall'
    fetch(API_URL)
      .then((response) => response.json())
      .then((data) => {
        const pageLimit = Math.ceil(data.length / this.state.perPage)
          this.setState({ employees: data, pageLimit })
          alert("Danh sach nhan vien: " + JSON.stringify(data))
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
  toggleModal = (employee = null) => {
    this.setState((prevState) => ({
      showModal: !prevState.showModal,
      editEmployee: employee,
      newEmployee: employee || {
        id: '',
        fullName: '',
        birthDate: '',
        phoneNumber: '',
        email: '',
        gender: '',
      },
    }))
  }
  handleInputChange = (e) => {
    const { name, value } = e.target
      this.setState((prevState) => ({
        newEmployee: {
          ...prevState.newEmployee,
          [name]: value,
        },
      }))
  }

  handleSave = () => {
    const { newEmployee, editEmployee } = this.state
    const employeeData = {
      id: 9999,
      fullName: newEmployee.fullName,
      birthDate: newEmployee.birthDate,
      phoneNumber: newEmployee.phoneNumber,
      email: newEmployee.email,
      gender: newEmployee.gender,
      status: 1,
    }
    if(newEmployee.fullName == ''){
      alert("Vui lòng nhập họ tên")
      return
    }
    if(newEmployee.birthDate == ''){
      alert("Vui lòng nhập ngày sinh")
      return
    }
    if(newEmployee.phoneNumber == ''){
      alert("Vui lòng nhập số điện thoại")
      return
    }
    if(newEmployee.email == ''){
      alert("Vui lòng nhập email")
      return
    }
    if(newEmployee.gender == ''){
      alert("Vui lòng nhập giới tính")
      return
    }
    
    if (editEmployee) {

      alert("Sửa thông tin nhân viên: " +  JSON.stringify(employeeData))
      console.log('Sửa thông tin nhân viên', employeeData)

     const API_URL_SUA = 'http://localhost:8080/api/employees';
      fetch(`${API_URL_SUA}/update/${editEmployee.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(employeeData),
      })
        .then((response) => {
          console.log('data trả về nè', response)
          if (response.ok) {
            alert('Sửa thông tin nhân viên thành công rồi nè')
            this.toggleModal()
            this.fetchEmployees(this.state.currentPage)
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
      alert('Thêm thông tin nhân viên' + JSON.stringify(employeeData))
      console.log('Thêm thông tin nhân viên', employeeData)

      const API_URL = 'http://localhost:8080/api/employees';
      fetch(`${API_URL}/add`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(employeeData),
      })
        .then((response) => {
          console.log('data trả về nè', response)
          if (response.ok) {
            alert('Thêm thông tin nhân viên thành công rồi nè')
            this.toggleModal()
            this.fetchEmployees(this.state.currentPage)
          } else {
            alert('Có lỗi xảy ra khi thêm thông tin nhân viên ở trong')
            console.error('Lỗi thêm thông tin nhân viên:', response)
          }
        })
        .catch((error) => {
          alert('Có lỗi xảy ra khi thêm thông tin nhân viên')
          console.error('Lỗi thêm thông tin nhân viên:', error)
        })
    }
  }
  handleDelete = (employeeId) => {
    const API_URL = 'http://localhost:8080/api/emloyees';
    fetch(`${API_URL}/updateStatus/${employeeId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ status: 0 }),
    })
      .then((response) => {
        if (response.ok) {
          alert('Xóa thông tin nhân viên thành công rồi nè');
          this.fetchEmployees(this.state.currentPage);
        } else {
          alert('Có lỗi xảy ra khi xóa thông tin nhân viên');
          console.error('Lỗi xóa thông tin nhân viên:', response);
        }
      })
      .catch((error) => {
        alert('Có lỗi xảy ra khi xóa thông tin nhân viên');
        console.error('Lỗi xóa thông tin nhân viên:', error);
      });
  };

  render() {
    const { employees, currentPage, pageLimit, showModal, newEmployee } = this.state
    // Safeguard to ensure products is always an array
    if (!Array.isArray(employees)) {
      console.error('Employees is not an array:', employees)
      return null
    }

    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              Employee Information
              <CButton color="primary" className="float-end" onClick={() => this.toggleModal()}>
                Thêm nhân viên
              </CButton>
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
                        <CButton color="warning" onClick={() => this.toggleModal(employee)}>Sửa</CButton>
                        <CButton color="danger" onClick={() => this.handleDelete(employee.id)}>Xóa</CButton>
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
            <CModalTitle>{this.state.editEmployee ? 'Chỉnh sửa thông tin nhân viên' : 'Thêm thông tin nhân viên'}</CModalTitle>
          </CModalHeader>
          <CModalBody>
            <CForm>
              {/* <div className="mb-3">
                <CFormLabel htmlFor="productId">ID</CFormLabel>
                <CFormInput type="text" id="productId" name="id" value={newProduct.id} onChange={this.handleInputChange} />
              </div> */}
              <div className="mb-3">
                <CFormLabel htmlFor="employeeName">Tên nhân viên</CFormLabel>
                <CFormInput type="text" id="employeeName" name="name" value={newEmployee.fullName} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="employeebirthDate">Ngày sinh</CFormLabel>
                <CFormInput type="text" id="employeebirthDate" name="birthDate" value={newEmployee.birthDate} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="employeePhoneNumber">Số điện thoại</CFormLabel>
                <CFormInput type="text" id="employeePhoneNumber" name="phoneNumber" value={newEmployee.phoneNumber} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="employeeEmail">Email</CFormLabel>
                <CFormInput type="text" id="employeeEmail" name="email" value={newEmployee.email} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="employeeGender">Giới tính</CFormLabel>
                <CFormSelect id="employeeGender" name="gender" value={this.state.newEmployee.gender} onChange={this.handleInputChange}>
                  <option value="Nam">Nam</option>
                  <option value="Nữ">Nữ</option>
                </CFormSelect>
              </div>

            </CForm>
          </CModalBody>
          <CModalFooter>
            {/* <CButton color="secondary" onClick={() => this.toggleModal()}>
              Đóng
            </CButton> */}
            <CButton color="primary" onClick={this.handleSave}>{this.state.editEmployee ? 'Cập nhật' : 'Lưu'}</CButton>
          </CModalFooter>
        </CModal>
      </CRow>
    )
  }
}

export default EmployeeTable