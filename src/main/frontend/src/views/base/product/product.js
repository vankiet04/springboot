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
  CTableCaption,
  CTableDataCell,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
} from '@coreui/react';
import Pagination from '@mui/material/Pagination';
import './Product.css';

class ProductTable extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      products: [],
      currentPage: 1,
      pageLimit: 0,
      perPage: 5,
    };
  }

  componentDidMount() {
    // GET ALL PRODUCTS TO GET SIZE OF PRODUCTS
    ProductService.getProducts()
      .then((response) => {
        const { data } = response;
        const pageLimit = Math.ceil(data.length / this.state.perPage);
        this.setState({ pageLimit });
      })
      .catch((e) => {
        console.error('Lỗi fetch products:', e);
      });

    // GET PRODUCTS BY PAGE 1
    this.fetchProducts(1);
  }

  componentDidUpdate(prevProps, prevState) {
    alert(prevState.currentPage);
    if (prevState.currentPage !== this.state.currentPage) {
      this.fetchProducts(this.state.currentPage);
    }
  }

  fetchProducts(page) {
    ProductService.getProductAtPage(page)
      .then((response) => {
        const { data } = response;
        this.setState({ products: data });
      })
      .catch((e) => {
        console.error('Lỗi fetch products:', e);
      });
  }

  handlePageChange = (event, value) => {
    this.setState({ currentPage: value });
  }

  render() {
    const { products, currentPage, pageLimit } = this.state;
    // Safeguard to ensure products is always an array
    if (!Array.isArray(products)) {
      console.error('Products is not an array:', products);
      return null;
    }

    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>Product Information</CCardHeader>
            <CCardBody>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell>Hình ảnh</CTableHeaderCell>
                    <CTableHeaderCell>ID</CTableHeaderCell>
                    <CTableHeaderCell>Tên sản phẩm</CTableHeaderCell>
                    <CTableHeaderCell>Mô tả</CTableHeaderCell>
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
                        <button className="btn btn-warning">Sửa</button>
                        <button className="btn btn-danger">Xóa</button>
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
    );
  }
}

export default ProductTable;