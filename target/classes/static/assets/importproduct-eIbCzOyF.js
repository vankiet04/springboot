var M=Object.defineProperty;var N=(h,l,s)=>l in h?M(h,l,{enumerable:!0,configurable:!0,writable:!0,value:s}):h[l]=s;var n=(h,l,s)=>N(h,typeof l!="symbol"?l+"":l,s);import{R as S,j as e}from"./index-DQkY96rc.js";import{C as v,a as w}from"./CRow-DMBw-idI.js";import{C as T,a as A}from"./CCardBody-Dka9-WXd.js";import{C as q}from"./CCardHeader-3ISR4CqK.js";import{a as c}from"./index.esm-CsVMth3X.js";import{C as f,a as g,b as m,c as a,d as C,e as i}from"./CTable-Hg-7IAkc.js";import{C as D,a as y,b as P,c as I,d as b}from"./CModalTitle-CeHpYnfN.js";import{C as F}from"./CForm-DinUw6Fq.js";import{C as x}from"./CFormSelect-PnEkIC5P.js";import{C as j}from"./CFormInput-2XdN1xrD.js";import"./DefaultLayout-DkBJ2y_r.js";import"./cil-user-Dlmw-Gem.js";import"./CFormControlWrapper-C9xCSD9I.js";import"./CFormControlValidation-D6H3tTV0.js";import"./CFormLabel-D-i2Ydtg.js";class Q extends S.Component{constructor(s){super(s);n(this,"fetchProducts",()=>{fetch("http://localhost:8080/api/products/getAllProductWithAllDetails").then(s=>s.json()).then(s=>{this.setState({products:s})}).catch(s=>{console.error("Error fetching products:",s)})});n(this,"fetchEmployees",()=>{fetch("http://localhost:8080/api/employees/current").then(s=>s.json()).then(s=>{this.setState({formData:{...this.state.formData,employeeId:s.id,employeeName:s.name}})}).catch(s=>{console.error("Error:",s)})});n(this,"toggleAddModal",()=>{this.setState(s=>({showAddModal:!s.showAddModal,formData:{...s.formData,supplierId:"",importPrice:0,exportPrice:0,quantity:1,selectedProducts:[]}}))});n(this,"toggleViewModal",(s=null)=>{this.setState(d=>({showViewModal:!d.showViewModal,selectedImport:s}))});n(this,"handleAddProduct",()=>{const s=document.querySelector('select[name="product"]').value;if(!s)return;const d=this.state.products.find(r=>r.product.id.toString()===s);d&&this.setState(r=>({formData:{...r.formData,selectedProducts:[...r.formData.selectedProducts,{productId:s,productName:d.product.name,importPrice:r.formData.importPrice,exportPrice:r.formData.exportPrice,quantity:r.formData.quantity}],importPrice:0,exportPrice:0,quantity:1}}))});n(this,"handleFormSubmit",s=>{s.preventDefault();const d={id:null,supplierId:parseInt(this.state.formData.supplierId)||null,employeeId:this.state.formData.employeeId,importDate:new Date().toISOString(),totalAmount:this.state.formData.selectedProducts.reduce((o,p)=>o+p.importPrice*(p.quantity||1),0),status:""},r=this.state.formData.selectedProducts.map(o=>({importId:null,productDetailId:parseInt(o.productId),quantity:o.quantity||1,importPrice:o.importPrice,exportPrice:o.exportPrice}));fetch("http://localhost:8080/api/importproduct/add",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(d)}).then(o=>o.json()).then(o=>{if(console.log("Import Response:",o),o){const p=r.map(t=>({...t,importId:o}));fetch("http://localhost:8080/api/importproduct/importdetailandupdateproductdetail",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(p)}).then(t=>t.json()).then(t=>{console.log("Update success:",t),this.toggleAddModal()}).catch(t=>console.error("Error updating detail:",t))}}).catch(o=>console.error("Error adding import:",o))});this.state={showAddModal:!1,showViewModal:!1,selectedImport:null,products:[],formData:{supplierId:"",employeeId:"",employeeName:"",importPrice:0,exportPrice:0,quantity:1,selectedProducts:[]}}}componentDidMount(){this.fetchEmployees(),this.fetchProducts()}render(){const{showAddModal:s,showViewModal:d,selectedImport:r,products:o}=this.state,p=[{id:1,importDate:"2024-01-01",supplierId:"SUP001",employeeId:"EMP001",totalAmount:1e6}];return e.jsxs(v,{children:[e.jsx(w,{children:e.jsxs(T,{children:[e.jsxs(q,{children:["Import Product Information",e.jsx(c,{color:"primary",className:"float-end",onClick:this.toggleAddModal,children:"Thêm phiếu nhập"})]}),e.jsx(A,{children:e.jsxs(f,{children:[e.jsx(g,{children:e.jsxs(m,{children:[e.jsx(a,{children:"Mã phiếu nhập"}),e.jsx(a,{children:"Thời gian"}),e.jsx(a,{children:"Mã nhà cung cấp"}),e.jsx(a,{children:"Nhân viên"}),e.jsx(a,{children:"Tổng tiền"}),e.jsx(a,{children:"Hành động"})]})}),e.jsx(C,{children:p.map((t,u)=>e.jsxs(m,{children:[e.jsx(i,{children:t.id}),e.jsx(i,{children:t.importDate}),e.jsx(i,{children:t.supplierId}),e.jsx(i,{children:t.employeeId}),e.jsx(i,{children:t.totalAmount}),e.jsxs(i,{children:[e.jsx(c,{color:"info",onClick:()=>this.toggleViewModal(t),children:"Xem phiếu"}),e.jsx(c,{color:"danger",className:"ms-2",children:"Hủy phiếu"})]})]},u))})]})})]})}),e.jsxs(D,{visible:s,onClose:this.toggleAddModal,size:"lg",children:[e.jsx(y,{children:e.jsx(P,{children:"Thêm phiếu nhập"})}),e.jsx(I,{children:e.jsxs(F,{onSubmit:this.handleFormSubmit,children:[e.jsxs(x,{className:"mb-3",label:"Nhà cung cấp",value:this.state.formData.supplierId,onChange:t=>this.setState({formData:{...this.state.formData,supplierId:t.target.value}}),children:[e.jsx("option",{value:"",children:"Chọn nhà cung cấp"}),e.jsx("option",{value:"1",children:"Nhà cung cấp 1"})]}),e.jsx(x,{className:"mb-3",label:"Nhân viên",value:this.state.formData.employeeId||"",disabled:!0,children:e.jsx("option",{value:this.state.formData.employeeId||"",children:this.state.formData.employeeName||"No Employee Found"})}),e.jsxs("div",{className:"row mb-3",children:[e.jsx("div",{className:"col-3",children:e.jsxs(x,{name:"product",children:[e.jsx("option",{value:"",children:"Chọn sản phẩm"}),o&&o.map(t=>e.jsx("option",{value:t.product.id,children:`${t.product.name} - Màu: ${t.details[0].color} - Size: ${t.details[0].size}`},t.product.id))]})}),e.jsx("div",{className:"col-3",children:e.jsx(j,{type:"number",label:"Giá nhập",value:this.state.formData.importPrice,onChange:t=>this.setState({formData:{...this.state.formData,importPrice:parseFloat(t.target.value)}})})}),e.jsx("div",{className:"col-3",children:e.jsx(j,{type:"number",label:"Giá xuất",value:this.state.formData.exportPrice,onChange:t=>this.setState({formData:{...this.state.formData,exportPrice:parseFloat(t.target.value)}})})}),e.jsx("div",{className:"col-3",children:e.jsx(j,{type:"number",label:"Số lượng",value:this.state.formData.quantity,onChange:t=>this.setState({formData:{...this.state.formData,quantity:parseInt(t.target.value)||1}})})})]}),e.jsx("div",{className:"mb-3 d-flex",children:e.jsx(c,{color:"primary",onClick:this.handleAddProduct,children:"Thêm"})}),e.jsx("h6",{children:"Danh sách sản phẩm đã chọn"}),e.jsxs(f,{children:[e.jsx(g,{children:e.jsxs(m,{children:[e.jsx(a,{children:"Mã SP"}),e.jsx(a,{children:"Tên SP"}),e.jsx(a,{children:"Giá nhập"}),e.jsx(a,{children:"Giá xuất"}),e.jsx(a,{children:"Số lượng"})]})}),e.jsx(C,{children:this.state.formData.selectedProducts.map((t,u)=>e.jsxs(m,{children:[e.jsx(i,{children:t.productId}),e.jsx(i,{children:t.productName}),e.jsx(i,{children:t.importPrice}),e.jsx(i,{children:t.exportPrice}),e.jsx(i,{children:t.quantity||1})]},u))})]}),e.jsxs(b,{children:[e.jsx(c,{color:"secondary",onClick:this.toggleAddModal,children:"Đóng"}),e.jsx(c,{color:"primary",type:"submit",children:"Lưu"})]})]})})]}),e.jsxs(D,{visible:d,onClose:()=>this.toggleViewModal(),children:[e.jsx(y,{children:e.jsx(P,{children:"Chi tiết phiếu nhập"})}),e.jsx(I,{children:r&&e.jsxs(e.Fragment,{children:[e.jsxs("p",{children:["Mã phiếu: ",r.id]}),e.jsxs("p",{children:["Thời gian: ",r.importDate]}),e.jsxs("p",{children:["Nhà cung cấp: ",r.supplierId]}),e.jsxs("p",{children:["Nhân viên: ",r.employeeId]}),e.jsxs("p",{children:["Tổng tiền: ",r.totalAmount]})]})}),e.jsx(b,{children:e.jsx(c,{color:"secondary",onClick:()=>this.toggleViewModal(),children:"Đóng"})})]})]})}}export{Q as default};