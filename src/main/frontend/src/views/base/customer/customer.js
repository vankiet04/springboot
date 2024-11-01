import React from 'react';
import {
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CForm,
  CFormInput,
  CFormLabel,
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
} from '@coreui/react';
import Pagination from '@mui/material/Pagination';
import customerService from './customerService';

class CustomerTable extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      customers: [],
      currentPage: 1,
      pageLimit: 0,
      perPage: 5,
      editCustomer: null,
      showModal: false,
      newCustomer: {
        id: '',
        fullName: '',
        birthDate: '',
        phoneNumber: '',
        email: '',
        gender: '',
      },
    };
  }

  componentDidMount() {
    this.fetchCustomers(1)
  }

  fetchCustomers(page) {
    customerService.getCustomerAtPage(page)
      .then((response) => {
        const { data } = response;
        // Lọc các khách hàng có status = 1
        const activeCustomers = data.filter(customer => customer.status === 1);
        const pageLimit = Math.ceil(activeCustomers.length / this.state.perPage);
        this.setState({ customers: activeCustomers, pageLimit });
      })
      .catch((error) => {
        console.error('Lỗi fetch customers:', error);
      });
  }

  handlePageChange = (event, value) => {
    this.setState({ currentPage: value });
    this.fetchCustomers(value);
  }

  toggleModal = (customer = null) => {
    this.setState((prevState) => ({
      showModal: !prevState.showModal,
      editCustomer: customer,
      newCustomer: customer || {
        id: '',
        fullName: '',
        birthDate: '',
        phoneNumber: '',
        email: '',
        gender: '',
      },
    }));
  }

  handleInputChange = (e) => {
    const { name, value } = e.target;
    this.setState((prevState) => ({
      newCustomer: {
        ...prevState.newCustomer,
        [name]: value,
      },
    }));
  }

  handleSave = () => {
    const { newCustomer, editCustomer } = this.state;
    const customerData = {
      id: 9999,
      fullName: newCustomer.fullName,
      birthDate: newCustomer.birthDate,
      phoneNumber: newCustomer.phoneNumber,
      email: newCustomer.email,
      gender: newCustomer.gender,
      status: 1,
    };
    if (newCustomer.fullName === '') {
      alert('Vui lòng nhập họ tên');
      return;
    }
    if (newCustomer.birthDate === '') {
      alert('Vui lòng nhập ngày sinh');
      return;
    }
    if (newCustomer.phoneNumber === '') {
      alert('Vui lòng nhập số điện thoại');
      return;
    }
    if (newCustomer.email === '') {
      alert('Vui lòng nhập email');
      return;
    }
    if (newCustomer.gender === '') {
      alert('Vui lòng nhập giới tính');
      return;
    }

    if (editCustomer) {
      alert('Sửa thông tin khách hàng: ' + JSON.stringify(customerData));
      console.log('Sửa thông tin khách hàng', customerData);

      const API_URL_SUA = 'http://localhost:8080/api/customers';
      fetch(`${API_URL_SUA}/update/${editCustomer.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(customerData),
      })
        .then((response) => {
          console.log('data trả về nè', response);
          if (response.ok) {
            alert('Sửa thông tin khách hàng thành công rồi nè');
            this.toggleModal();
            this.fetchCustomers(this.state.currentPage);
          } else {
            alert('Có lỗi xảy ra khi sửa thông tin ở trong');
            console.error('Lỗi sửa thông tin:', response);
          }
        })
        .catch((error) => {
          alert('Có lỗi xảy ra khi sửa thông tin');
          console.error('Lỗi sửa thông tin:', error);
        });
    } else {
      alert('Thêm thông tin khách hàng' + JSON.stringify(customerData));
      console.log('Thêm thông tin khách hàng', customerData);

      const API_URL = 'http://localhost:8080/api/customers';
      fetch(`${API_URL}/add`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(customerData),
      })
        .then((response) => {
          console.log('data trả về nè', response);
          if (response.ok) {
            alert('Thêm thông tin khách hàng thành công rồi nè');
            this.toggleModal();
            this.fetchCustomers(this.state.currentPage);
          } else {
            alert('Có lỗi xảy ra khi thêm thông tin khách hàng ở trong');
            console.error('Lỗi thêm thông tin khách hàng:', response);
          }
        })
        .catch((error) => {
          alert('Có lỗi xảy ra khi thêm thông tin khách hàng');
          console.error('Lỗi thêm thông tin khách hàng:', error);
        });
    }
  }

  handleDelete = (customerId) => {
    const API_URL = 'http://localhost:8080/api/customers';
    fetch(`${API_URL}/updateStatus/${customerId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ status: 0 }), // Đảm bảo gửi đúng dữ liệu
    })
      .then((response) => {
        console.log('Response:', response); // Thêm log để kiểm tra phản hồi
        if (response.ok) {
          alert('Xóa thông tin khách hàng thành công rồi nè');
          this.fetchCustomers(this.state.currentPage);
        } else {
          alert('Có lỗi xảy ra khi xóa thông tin khách hàng');
          console.error('Lỗi xóa thông tin khách hàng:', response);
        }
      })
      .catch((error) => {
        alert('Có lỗi xảy ra khi xóa thông tin khách hàng');
        console.error('Lỗi xóa thông tin khách hàng:', error);
      });
  }

  render() {
    const { customers, currentPage, pageLimit, showModal, newCustomer } = this.state;
    if (!Array.isArray(customers)) {
      console.error('Customers is not an array:', customers);
      return null;
    }

    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              Customer Information
              <CButton color="primary" className="float-end" onClick={() => this.toggleModal()}>
                Thêm khách hàng
              </CButton>
            </CCardHeader>
            <CCardBody>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell>Mã khách hàng</CTableHeaderCell>
                    <CTableHeaderCell>Họ tên</CTableHeaderCell>
                    <CTableHeaderCell>Giới tính</CTableHeaderCell>
                    <CTableHeaderCell>Ngày sinh</CTableHeaderCell>
                    <CTableHeaderCell>Số điện thoại</CTableHeaderCell>
                    <CTableHeaderCell>Email</CTableHeaderCell>
                    <CTableHeaderCell>Hành động</CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>
                  {customers.map((customer, index) => (
                    <CTableRow key={index}>
                      <CTableDataCell>{customer.id}</CTableDataCell>
                      <CTableDataCell>{customer.fullName}</CTableDataCell>
                      <CTableDataCell>{customer.gender}</CTableDataCell>
                      <CTableDataCell>{customer.birthDate}</CTableDataCell>
                      <CTableDataCell>{customer.phoneNumber}</CTableDataCell>
                      <CTableDataCell>{customer.email}</CTableDataCell>
                      <CTableDataCell>
                        <CButton color="warning" onClick={() => this.toggleModal(customer)}>Sửa</CButton>
                        <CButton color="danger" onClick={() => this.handleDelete(customer.id)}>Xóa</CButton>
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
            <CModalTitle>{this.state.editCustomer ? 'Chỉnh sửa thông tin khách hàng' : 'Thêm thông tin khách hàng'}</CModalTitle>
          </CModalHeader>
          <CModalBody>
            <CForm>
              <div className="mb-3">
                <CFormLabel htmlFor="customerName">Tên khách hàng</CFormLabel>
                <CFormInput type="text" id="customerName" name="fullName" value={newCustomer.fullName} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="customerbirthDate">Ngày sinh</CFormLabel>
                <CFormInput type="text" id="customerbirthDate" name="birthDate" value={newCustomer.birthDate} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="customerPhoneNumber">Số điện thoại</CFormLabel>
                <CFormInput type="text" id="customerPhoneNumber" name="phoneNumber" value={newCustomer.phoneNumber} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="customerEmail">Email</CFormLabel>
                <CFormInput type="text" id="customerEmail" name="email" value={newCustomer.email} onChange={this.handleInputChange} />
              </div>
              <div className="mb-3">
                <CFormLabel htmlFor="customerGender">Giới tính</CFormLabel>
                <CFormSelect id="customerGender" name="gender" value={newCustomer.gender} onChange={this.handleInputChange}>
                  <option value="">Chọn giới tính</option>
                  <option value="0">Nam</option>
                  <option value="1">Nữ</option>
                </CFormSelect>
              </div>
            </CForm>
          </CModalBody>
          <CModalFooter>
            <CButton color="primary" onClick={this.handleSave}>{this.state.editCustomer ? 'Cập nhật' : 'Lưu'}</CButton>
          </CModalFooter>
        </CModal>
      </CRow>
    );
  }
}

export default CustomerTable;