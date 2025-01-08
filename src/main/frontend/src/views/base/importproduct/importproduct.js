import React from 'react'
import moment from 'moment'
import {
  CButton, CCard, CCardBody, CCardHeader, CCol, CRow,
  CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow,
  CModal, CModalHeader, CModalTitle, CModalBody, CModalFooter,
  CForm, CFormSelect, CFormInput,
  CPagination, CPaginationItem
} from '@coreui/react'

class ImportProductTable extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      showAddModal: false,
      showViewModal: false,
      selectedImport: null,
      importDetails: [],
      products: [],
      formData: {
        supplierId: '',
        employeeId: '',
        employeeName: '',
        importPrice: 0,
        exportPrice: 0,
        quantity: 1,
        selectedProducts: [],
        importDetails: []
      },
      imports: [],
      currentPage: 1,
      totalPages: 1,
      pageSize: 10
    }
  }

  componentDidMount() {
    this.fetchEmployees()
    this.fetchProducts()
    this.fetchImports()
  }

  fetchImports = () => {
    const { currentPage, pageSize } = this.state
    fetch(`http://localhost:8080/api/importproduct/getAllImport?page=${currentPage-1}&size=${pageSize}`)
      .then(res => res.json())
      .then(data => {
        this.setState({
          imports: data.content,
          totalPages: data.totalPages
        })
      })
      .catch(error => {
        console.error('Error fetching imports:', error)
      })
  }

  fetchImportDetails = (importId) => {
    fetch(`http://localhost:8080/api/importproduct/detail/${importId}`)
      .then(res => res.json())
      .then(data => {
        this.setState({ 
          importDetails: Array.isArray(data) ? data : [] 
        });
      })
      .catch(error => {
        console.error('Error fetching details:', error); 
        this.setState({ importDetails: [] });
      });
  }

  handlePageChange = (page) => {
    this.setState({ currentPage: page }, () => {
      this.fetchImports()
    })
  }

  fetchProducts = () => {
    fetch('http://localhost:8080/api/products/getAllProductWithAllDetails')
      .then(res => res.json())
      .then(data => {
        this.setState({ products: data })
      })
      .catch(error => {
        console.error('Error fetching products:', error)
      })
  }

  fetchEmployees = () => {
    fetch('http://localhost:8080/api/employees/current')
      .then(res => res.json())
      .then(data => {
        this.setState({
          formData: {
            ...this.state.formData,
            employeeId: data.id,
            employeeName: data.name
          }
        })
      })
      .catch(error => {
        console.error('Error:', error)
      })
  }

  toggleAddModal = () => {
    this.setState(prev => ({
      showAddModal: !prev.showAddModal,
      formData: {
        ...prev.formData,
        supplierId: '',
        importPrice: 0,
        exportPrice: 0,
        quantity: 1,
        selectedProducts: []
      }
    }))
  }

  toggleViewModal = (importItem = null) => {
    this.setState({
      showViewModal: !this.state.showViewModal, 
      selectedImport: importItem,
      importDetails: []
    });
    
    if (importItem) {
      this.fetchImportDetails(importItem.id);
    }
  }

  handleAddProduct = () => {
    const productId = document.querySelector('select[name="product"]').value
    if (!productId) return

    const selectedProductDetails = this.state.products.find(
      p => p.product.id.toString() === productId
    )

    if (!selectedProductDetails) return

    this.setState(prevState => ({
      formData: {
        ...prevState.formData,
        selectedProducts: [
          ...prevState.formData.selectedProducts,
          {
            productId: productId,
            productName: selectedProductDetails.product.name,
            importPrice: prevState.formData.importPrice,
            exportPrice: prevState.formData.exportPrice,
            quantity: prevState.formData.quantity
          }
        ],
        importPrice: 0,
        exportPrice: 0,
        quantity: 1
      }
    }))
  }

  handleFormSubmit = (e) => {
    e.preventDefault()
    const importData = {
      id: null,
      supplierId: parseInt(this.state.formData.supplierId) || null,
      employeeId: this.state.formData.employeeId,
      importDate: new Date().toISOString(),
      totalAmount: this.state.formData.selectedProducts.reduce(
        (sum, product) => sum + product.importPrice * (product.quantity || 1),
        0
      ),
      status: ""
    }

    const importDetailDtos = this.state.formData.selectedProducts.map((item) => ({
      importId: null,
      productDetailId: parseInt(item.productId),
      quantity: item.quantity || 1,
      importPrice: item.importPrice,
      exportPrice: item.exportPrice
    }))
  
    fetch('http://localhost:8080/api/importproduct/add', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(importData),
    })
    .then(response => response.json())
    .then(importResponse => {
      console.log('Import Response:', importResponse);
      if (importResponse) {
        const updatedImportDetailDtos = importDetailDtos.map(detail => ({
          ...detail,
          importId: importResponse
        }));
  
        fetch('http://localhost:8080/api/importproduct/importdetailandupdateproductdetail', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(updatedImportDetailDtos),
        })
          .then((res) => res.json())
          .then((data) => {
            console.log('Update thanh cong:', data)
            alert('Thêm phiếu nhập thành công')
            this.toggleAddModal()
          })
          .catch((error) => console.error('Error updating detail:', error))
      }
    })
    .catch((error) => console.error('Loi add import:', error))
  }

  render() {
    const { 
      showAddModal, 
      showViewModal, 
      selectedImport, 
      products, 
      imports, 
      currentPage, 
      totalPages,
      importDetails,
      formData 
    } = this.state;

    return (
      <CRow>
        <CCol>
          <CCard>
            <CCardHeader>
              Import Product Information
              <CButton color="primary" className="float-end" onClick={this.toggleAddModal}>
                Thêm phiếu nhập
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
                  {imports.map((importItem, index) => (
                    <CTableRow key={index}>
                      <CTableDataCell>{importItem.id}</CTableDataCell>
                      <CTableDataCell>{moment(importItem.importDate).format('DD/MM/YYYY HH:mm')}</CTableDataCell>
                      <CTableDataCell>{importItem.supplierId}</CTableDataCell>
                      <CTableDataCell>{importItem.employeeId}</CTableDataCell>
                      <CTableDataCell>{importItem.totalAmount.toLocaleString('vi-VN')} VNĐ</CTableDataCell>
                      <CTableDataCell>
                        <CButton color="info" onClick={() => this.toggleViewModal(importItem)}>
                          Xem phiếu
                        </CButton>
                        <CButton color="danger" className="ms-2">
                          Hủy phiếu
                        </CButton>
                      </CTableDataCell>
                    </CTableRow>
                  ))}
                </CTableBody>
              </CTable>

              <CPagination className="mt-3 justify-content-center">
                {[...Array(totalPages)].map((_, index) => (
                  <CPaginationItem
                    key={index + 1}
                    active={currentPage === index + 1}
                    onClick={() => this.handlePageChange(index + 1)}
                  >
                    {index + 1}
                  </CPaginationItem>
                ))}
              </CPagination>
            </CCardBody>
          </CCard>
        </CCol>

        {/* Add Import Modal */}
        <CModal visible={showAddModal} onClose={this.toggleAddModal} size="lg">
          <CModalHeader>
            <CModalTitle>Thêm phiếu nhập</CModalTitle>
          </CModalHeader>
          <CModalBody>
            <CForm onSubmit={this.handleFormSubmit}>
              <CFormSelect
                className="mb-3"
                label="Nhà cung cấp"
                value={formData.supplierId}
                onChange={(e) => this.setState({
                  formData: { ...formData, supplierId: e.target.value }
                })}
              >
                <option value="">Chọn nhà cung cấp</option>
                <option value="1">Nhà cung cấp 1</option>
              </CFormSelect>

              <CFormSelect
                className="mb-3"
                label="Nhân viên"
                value={formData.employeeId || ''}
                disabled
              >
                <option value={formData.employeeId || ''}>
                  {formData.employeeName || 'No Employee Found'}
                </option>
              </CFormSelect>

              <div className="row mb-3">
                <div className="col-3">
                  <CFormSelect name="product">
                    <option value="">Chọn sản phẩm</option>
                    {products.map((item) => (
                      <option key={item.product.id} value={item.product.id}>
                        {`${item.product.name} - Màu: ${item.details[0].color} - Size: ${item.details[0].size}`}
                      </option>
                    ))}
                  </CFormSelect>
                </div>
                <div className="col-3">
                  <CFormInput
                    type="number"
                    label="Giá nhập"
                    value={formData.importPrice}
                    onChange={(e) => this.setState({
                      formData: { ...formData, importPrice: parseFloat(e.target.value) }
                    })}
                  />
                </div>
                <div className="col-3">
                  <CFormInput
                    type="number"
                    label="Giá xuất"
                    value={formData.exportPrice}
                    onChange={(e) => this.setState({
                      formData: { ...formData, exportPrice: parseFloat(e.target.value) }
                    })}
                  />
                </div>
                <div className="col-3">
                  <CFormInput
                    type="number"
                    label="Số lượng"
                    value={formData.quantity}
                    onChange={(e) => this.setState({
                      formData: { ...formData, quantity: parseInt(e.target.value) || 1 }
                    })}
                  />
                </div>
              </div>

              <div className="mb-3 d-flex">
                <CButton color="primary" onClick={this.handleAddProduct}>
                  Thêm
                </CButton>
              </div>

              <h6>Danh sách sản phẩm đã chọn</h6>
              <CTable>
                <CTableHead>
                  <CTableRow>
                    <CTableHeaderCell>Mã SP</CTableHeaderCell>
                    <CTableHeaderCell>Tên SP</CTableHeaderCell>
                    <CTableHeaderCell>Giá nhập</CTableHeaderCell>
                    <CTableHeaderCell>Giá xuất</CTableHeaderCell>
                    <CTableHeaderCell>Số lượng</CTableHeaderCell>
                  </CTableRow>
                </CTableHead>
                <CTableBody>
                  {formData.selectedProducts.map((product, index) => (
                    <CTableRow key={index}>
                      <CTableDataCell>{product.productId}</CTableDataCell>
                      <CTableDataCell>{product.productName}</CTableDataCell>
                      <CTableDataCell>{product.importPrice}</CTableDataCell>
                      <CTableDataCell>{product.exportPrice}</CTableDataCell>
                      <CTableDataCell>{product.quantity || 1}</CTableDataCell>
                    </CTableRow>
                  ))}
                </CTableBody>
              </CTable>

              <CModalFooter>
                <CButton color="secondary" onClick={this.toggleAddModal}>
                  Đóng
                </CButton>
                <CButton color="primary" type="submit">
                  Lưu
                </CButton>
              </CModalFooter>
            </CForm>
          </CModalBody>
        </CModal>

        {/* View Modal */}
        <CModal visible={showViewModal} onClose={() => this.toggleViewModal()}>
          <CModalHeader>
            <CModalTitle>Chi tiết phiếu nhập</CModalTitle>
          </CModalHeader>
          <CModalBody>
            {selectedImport && (
              <>
                <p>Mã phiếu: {selectedImport.id}</p>
                <p>Thời gian: {moment(selectedImport.importDate).format('DD/MM/YYYY HH:mm')}</p>
                <p>Tổng tiền: {selectedImport.totalAmount.toLocaleString('vi-VN')} VNĐ</p>
                
                <CTable>
                  <CTableHead>
                    <CTableRow>
                      <CTableHeaderCell>Sản phẩm</CTableHeaderCell>
                      <CTableHeaderCell>Giá nhập</CTableHeaderCell>
                      <CTableHeaderCell>Số lượng</CTableHeaderCell>
                      <CTableHeaderCell>Thành tiền</CTableHeaderCell>
                    </CTableRow>
                  </CTableHead>
                  <CTableBody>
                    {importDetails.map((detail, index) => (
                      <CTableRow key={index}>
                        <CTableDataCell>{detail.tensanpham}</CTableDataCell>
                        <CTableDataCell>{detail.importPrice.toLocaleString('vi-VN')} VNĐ</CTableDataCell>
                        <CTableDataCell>{detail.quantity}</CTableDataCell>
                        <CTableDataCell>
                          {(detail.importPrice * detail.quantity).toLocaleString('vi-VN')} VNĐ
                        </CTableDataCell>
                      </CTableRow>
                    ))}
                  </CTableBody>
                </CTable>
              </>
            )}
          </CModalBody>
          <CModalFooter>
            <CButton color="secondary" onClick={() => this.toggleViewModal()}>
              Đóng
            </CButton>
          </CModalFooter>
        </CModal>
      </CRow>
    );
  }
}

export default ImportProductTable