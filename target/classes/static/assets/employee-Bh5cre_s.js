var j=Object.defineProperty;var f=(r,l,n)=>l in r?j(r,l,{enumerable:!0,configurable:!0,writable:!0,value:n}):r[l]=n;var d=(r,l,n)=>f(r,typeof l!="symbol"?l+"":l,n);import{R as v,j as e}from"./index-CVA_rVvw.js";import{a as u}from"./axios-Bf4ed8-h.js";import{C as b,a as N}from"./CRow-BUr9gXbK.js";import{C as E,a as P}from"./CCardBody-Ckp6apWz.js";import{C as S}from"./CCardHeader-BLFhMyXV.js";import{a as p}from"./index.esm-CVHgl3fu.js";import{C as T,a as L,b as x,c as s,d as M,e as m}from"./CTable-p68S9zF9.js";import{P as w}from"./Pagination-3ipfoQ3h.js";import{C as D,a as I,b as k,c as A,d as R}from"./CModalTitle-BgAUIy7S.js";import{C as U}from"./CForm-q-oIZxIa.js";import{C as c}from"./CFormLabel-B4WlIzwb.js";import{C as g}from"./CFormInput-oF2nYMsK.js";import{C as F}from"./CFormSelect-BjqzzKw9.js";import"./DefaultLayout-BRSfxTJa.js";import"./cil-user-Dlmw-Gem.js";import"./CFormControlWrapper-DmNTHh_f.js";import"./CFormControlValidation-Cda0gd5m.js";const y="http://localhost:8080/api/employees";class _{getEmployee(){return u.get(`${y}/getall`)}getEmployeeAtPage(l){return u.get(`${y}/getEmployeePage?page=${l}`)}}const $=new _;class ae extends v.Component{constructor(n){super(n);d(this,"handlePageChange",(n,a)=>{this.setState({currentPage:a}),this.fetchEmployees(a)});d(this,"toggleModal",(n=null)=>{this.setState(a=>({showModal:!a.showModal,editEmployee:n,newEmployee:n||{id:"",fullName:"",birthDate:"",phoneNumber:"",email:"",gender:""}}))});d(this,"handleInputChange",n=>{const{name:a,value:t}=n.target;this.setState(o=>({newEmployee:{...o.newEmployee,[a]:t}}))});d(this,"handleSave",()=>{const{newEmployee:n,editEmployee:a}=this.state,t={id:9999,fullName:n.fullName,birthDate:n.birthDate,phoneNumber:n.phoneNumber,email:n.email,gender:n.gender,status:1};if(n.fullName==""){alert("Vui lòng nhập họ tên");return}if(n.birthDate==""){alert("Vui lòng nhập ngày sinh");return}if(n.phoneNumber==""){alert("Vui lòng nhập số điện thoại");return}if(n.email==""){alert("Vui lòng nhập email");return}if(n.gender==""){alert("Vui lòng nhập giới tính");return}a?(alert("Sửa thông tin nhân viên: "+JSON.stringify(t)),console.log("Sửa thông tin nhân viên",t),fetch(`http://localhost:8080/api/employees/update/${a.id}`,{method:"PUT",headers:{"Content-Type":"application/json"},body:JSON.stringify(t)}).then(i=>{console.log("data trả về nè",i),i.ok?(alert("Sửa thông tin nhân viên thành công rồi nè"),this.toggleModal(),this.fetchEmployees(this.state.currentPage)):(alert("Có lỗi xảy ra khi sửa thông tin ở trong"),console.error("Lỗi sửa thông tin:",i))}).catch(i=>{alert("Có lỗi xảy ra khi sửa thông tin"),console.error("Lỗi sửa thông tin:",i)})):(alert("Thêm thông tin nhân viên"+JSON.stringify(t)),console.log("Thêm thông tin nhân viên",t),fetch("http://localhost:8080/api/employees/add",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(t)}).then(i=>{console.log("data trả về nè",i),i.ok?(alert("Thêm thông tin nhân viên thành công rồi nè"),this.toggleModal(),this.fetchEmployees(this.state.currentPage)):(alert("Có lỗi xảy ra khi thêm thông tin nhân viên ở trong"),console.error("Lỗi thêm thông tin nhân viên:",i))}).catch(i=>{alert("Có lỗi xảy ra khi thêm thông tin nhân viên"),console.error("Lỗi thêm thông tin nhân viên:",i)}))});d(this,"handleDelete",n=>{fetch(`http://localhost:8080/api/employees/updateStatus/${n}`,{method:"PUT",headers:{"Content-Type":"application/json"},body:JSON.stringify({status:0})}).then(t=>{console.log("Response:",t),t.ok?(alert("Xóa thông tin nhân viên thành công rồi nè"),this.fetchEmployees(this.state.currentPage)):(alert("Có lỗi xảy ra khi xóa thông tin nhân viên"),console.error("Lỗi xóa thông tin nhân viên:",t))}).catch(t=>{alert("Có lỗi xảy ra khi xóa thông tin nhân viên"),console.error("Lỗi xóa thông tin nhân viên:",t)})});this.state={employees:[],currentPage:1,pageLimit:0,perPage:5,editEmployee:null,showModal:!1,newEmployee:{id:"",fullName:"",birthDate:"",phoneNumber:"",email:"",gender:""}}}componentDidMount(){this.fetchEmployees(1)}fetchEmployees(n){$.getEmployeeAtPage(n).then(a=>{const{data:t}=a,o=t.filter(h=>h.status===1),i=Math.ceil(o.length/this.state.perPage);this.setState({employees:o,pageLimit:i})}).catch(a=>{console.error("Lỗi fetch customers:",a)})}render(){const{employees:n,currentPage:a,pageLimit:t,showModal:o,newEmployee:i}=this.state;return Array.isArray(n)?e.jsxs(b,{children:[e.jsx(N,{children:e.jsxs(E,{children:[e.jsxs(S,{children:["Employee Information",e.jsx(p,{color:"primary",className:"float-end",onClick:()=>this.toggleModal(),children:"Thêm nhân viên"})]}),e.jsxs(P,{children:[e.jsxs(T,{children:[e.jsx(L,{children:e.jsxs(x,{children:[e.jsx(s,{children:"Mã nhân viên"}),e.jsx(s,{children:"Họ tên"}),e.jsx(s,{children:"Giới tính"}),e.jsx(s,{children:"Ngày sinh"}),e.jsx(s,{children:"Số điện thoại"}),e.jsx(s,{children:"Email"}),e.jsx(s,{children:"Hành động"})]})}),e.jsx(M,{children:n.map((h,C)=>e.jsxs(x,{children:[e.jsx(m,{children:h.id}),e.jsx(m,{children:h.fullName}),e.jsx(m,{children:h.gender}),e.jsx(m,{children:h.birthDate}),e.jsx(m,{children:h.phoneNumber}),e.jsx(m,{children:h.email}),e.jsxs(m,{children:[e.jsx(p,{color:"warning",onClick:()=>this.toggleModal(h),children:"Sửa"}),e.jsx(p,{color:"danger",onClick:()=>this.handleDelete(h.id),children:"Xóa"})]})]},C))})]}),e.jsx(w,{count:t,page:a,onChange:this.handlePageChange,variant:"outlined",shape:"rounded"})]})]})}),e.jsxs(D,{visible:o,onClose:()=>this.toggleModal(),children:[e.jsx(I,{children:e.jsx(k,{children:this.state.editEmployee?"Chỉnh sửa thông tin nhân viên":"Thêm thông tin nhân viên"})}),e.jsx(A,{children:e.jsxs(U,{children:[e.jsxs("div",{className:"mb-3",children:[e.jsx(c,{htmlFor:"employeeName",children:"Tên nhân viên"}),e.jsx(g,{type:"text",id:"employeeName",name:"fullName",value:i.fullName,onChange:this.handleInputChange})]}),e.jsxs("div",{className:"mb-3",children:[e.jsx(c,{htmlFor:"employeebirthDate",children:"Ngày sinh"}),e.jsx(g,{type:"text",id:"employeebirthDate",name:"birthDate",value:i.birthDate,onChange:this.handleInputChange})]}),e.jsxs("div",{className:"mb-3",children:[e.jsx(c,{htmlFor:"employeePhoneNumber",children:"Số điện thoại"}),e.jsx(g,{type:"text",id:"employeePhoneNumber",name:"phoneNumber",value:i.phoneNumber,onChange:this.handleInputChange})]}),e.jsxs("div",{className:"mb-3",children:[e.jsx(c,{htmlFor:"employeeEmail",children:"Email"}),e.jsx(g,{type:"text",id:"employeeEmail",name:"email",value:i.email,onChange:this.handleInputChange})]}),e.jsxs("div",{className:"mb-3",children:[e.jsx(c,{htmlFor:"employeeGender",children:"Giới tính"}),e.jsxs(F,{id:"employeeGender",name:"gender",value:this.state.newEmployee.gender,onChange:this.handleInputChange,children:[e.jsx("option",{value:"",children:"Chọn giới tính"}),e.jsx("option",{value:"0",children:"Nam"}),e.jsx("option",{value:"1",children:"Nữ"})]})]})]})}),e.jsx(R,{children:e.jsx(p,{color:"primary",onClick:this.handleSave,children:this.state.editEmployee?"Cập nhật":"Lưu"})})]})]}):(console.error("Employees is not an array:",n),null)}}export{ae as default};