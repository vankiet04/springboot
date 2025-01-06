import React from 'react'
import {
  CButton, CCard, CCardBody, CCardHeader, CCol, CRow,
  CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow,
  CModal, CModalHeader, CModalTitle, CModalBody, CModalFooter,
  CForm, CFormSelect, CFormInput
} from '@coreui/react'

class ImportProductTable extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      showAddModal: false,
      showViewModal: false,
      selectedImport: null,
      products: [],
      formData: {
        supplierId: '',
        employeeId: '',
        employeeName: '',
        importPrice: 0,
        exportPrice: 0,
        quantity: 1,
        selectedProducts: []
      }
    }
  }

  componentDidMount() {
    this.fetchEmployees()
    this.fetchProducts()
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

  toggleViewModal = (importProduct = null) => {
    this.setState(prev => ({
      showViewModal: !prev.showViewModal,
      selectedImport: importProduct
    }))
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
            console.log('Update success:', data)
            this.toggleAddModal()
          })
          .catch((error) => console.error('Error updating detail:', error))
      }
    })
    .catch((error) => console.error('Error adding import:', error))
  }

  render() {
    const { showAddModal, showViewModal, selectedImport, products } = this.state
    const importProducts = [
      {
        id: 1,
        importDate: '2024-01-01',
        supplierId: 'SUP001',
        employeeId: 'EMP001',
        totalAmount: 1000000
      }
    ]

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
                  {importProducts.map((importProduct, index) => (
                    <CTableRow key={index}>
                      <CTableDataCell>{importProduct.id}</CTableDataCell>
                      <CTableDataCell>{importProduct.importDate}</CTableDataCell>
                      <CTableDataCell>{importProduct.supplierId}</CTableDataCell>
                      <CTableDataCell>{importProduct.employeeId}</CTableDataCell>
                      <CTableDataCell>{importProduct.totalAmount}</CTableDataCell>
                      <CTableDataCell>
                        <CButton
                          color="info"
                          onClick={() => this.toggleViewModal(importProduct)}
                        >
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
                value={this.state.formData.supplierId}
                onChange={(e) =>
                  this.setState({
                    formData: { ...this.state.formData, supplierId: e.target.value },
                  })
                }
              >
                <option value="">Chọn nhà cung cấp</option>
                <option value="1">Nhà cung cấp 1</option>
              </CFormSelect>

              <CFormSelect
                className="mb-3"
                label="Nhân viên"
                value={this.state.formData.employeeId || ''}
                disabled
              >
                <option value={this.state.formData.employeeId || ''}>
                  {this.state.formData.employeeName || 'No Employee Found'}
                </option>
              </CFormSelect>

              <div className="row mb-3">
                <div className="col-3">
                  <CFormSelect name="product">
                    <option value="">Chọn sản phẩm</option>
                    {products &&
                      products.map((item) => (
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
                    value={this.state.formData.importPrice}
                    onChange={(e) =>
                      this.setState({
                        formData: {
                          ...this.state.formData,
                          importPrice: parseFloat(e.target.value),
                        },
                      })
                    }
                  />
                </div>
                <div className="col-3">
                  <CFormInput
                    type="number"
                    label="Giá xuất"
                    value={this.state.formData.exportPrice}
                    onChange={(e) =>
                      this.setState({
                        formData: {
                          ...this.state.formData,
                          exportPrice: parseFloat(e.target.value),
                        },
                      })
                    }
                  />
                </div>
                <div className="col-3">
                  <CFormInput
                    type="number"
                    label="Số lượng"
                    value={this.state.formData.quantity}
                    onChange={(e) =>
                      this.setState({
                        formData: {
                          ...this.state.formData,
                          quantity: parseInt(e.target.value) || 1,
                        },
                      })
                    }
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
                  {this.state.formData.selectedProducts.map((product, index) => (
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
                <p>Thời gian: {selectedImport.importDate}</p>
                <p>Nhà cung cấp: {selectedImport.supplierId}</p>
                <p>Nhân viên: {selectedImport.employeeId}</p>
                <p>Tổng tiền: {selectedImport.totalAmount}</p>
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
    )
  }
}

export default ImportProductTable