import React from 'react';
import ProductService from './ProductService';
import {
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
  CButton,
  CFormInput,
  CFormSelect,
} from '@coreui/react';
import Pagination from '@mui/material/Pagination';
import './ImportProduct.css';

class ImportProduct extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchId: '',
      sortDate: '',
      sortPrice: '',
      importProducts: [],
      currentPage: 1,
      pageLimit: 10,
    };
  }

  componentDidMount() {
    // this.fetchImportProducts();
  }

  fetchImportProducts = () => {
    // // Fetch import products logic here
    // ProductService.getImportProducts(this.state.currentPage)
    //   .then((response) => {
    //     this.setState({ importProducts: response.data });
    //   })
    //   .catch((error) => {
    //     console.error('Error fetching import products:', error);
    //   });
  };

  handleSearchChange = (event) => {
    this.setState({ searchId: event.target.value });
  };

  handleSortDateChange = (event) => {
    this.setState({ sortDate: event.target.value });
  };

  handleSortPriceChange = (event) => {
    this.setState({ sortPrice: event.target.value });
  };

  handlePageChange = (event, value) => {
    this.setState({ currentPage: value }, this.fetchImportProducts);
  };

  handleSearch = () => {
    // Implement search logic here
    this.fetchImportProducts();
  };

  render() {
    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>Import Product Management</CCardHeader>
            <CCardBody>
              <div className="d-flex justify-content-between mb-3">
                <CButton color="primary">Thêm Phiếu Nhập</CButton>
                <CButton color="danger">Hủy Phiếu Nhập</CButton>
              </div>
              <div className="d-flex justify-content-between mb-3">
                <CFormInput
                  type="text"
                  placeholder="Tìm kiếm theo mã phiếu nhập"
                />
                <CFormSelect>
                  <option value="">Sắp xếp theo ngày nhập</option>
                  <option value="asc">Tăng dần</option>
                  <option value="desc">Giảm dần</option>
                </CFormSelect>
                <CFormSelect>
                  <option value="">Sắp xếp theo giá tiền nhập</option>
                  <option value="asc">Tăng dần</option>
                  <option value="desc">Giảm dần</option>
                </CFormSelect>
                <CButton color="primary">Tìm kiếm</CButton>
              </div>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell>ID Phiếu Nhập</CTableHeaderCell>
                    <CTableHeaderCell>Nhân Viên Nhập</CTableHeaderCell>
                    <CTableHeaderCell>Giá Tiền Nhập</CTableHeaderCell>
                    <CTableHeaderCell>Hành Động</CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>
                  <CTableRow>
                    <CTableDataCell>ID</CTableDataCell>
                    <CTableDataCell>Nhân Viên</CTableDataCell>
                    <CTableDataCell>Giá Tiền</CTableDataCell>
                    <CTableDataCell>
                      <CButton color="info">Xem Phiếu Nhập</CButton>
                    </CTableDataCell>
                  </CTableRow>
                  {/* Thêm các hàng khác ở đây */}
                </CTableBody>
              </CTable>
              <Pagination
                count={10} // Số trang
                page={1} // Trang hiện tại
                onChange={() => {}} // Hàm xử lý khi thay đổi trang
                variant="outlined"
                shape="rounded"
              />
            </CCardBody>
          </CCard>
        </CCol>
      </CRow>
    );
  }
}

export default ImportProduct;