var x=Object.defineProperty;var y=(l,r,t)=>r in l?x(l,r,{enumerable:!0,configurable:!0,writable:!0,value:t}):l[r]=t;var h=(l,r,t)=>y(l,typeof r!="symbol"?r+"":r,t);import{R as j,j as e}from"./index-DQkY96rc.js";import{C as f,a as u}from"./CRow-DMBw-idI.js";import{C as P,a as T}from"./CCardBody-Dka9-WXd.js";import{C as M}from"./CCardHeader-3ISR4CqK.js";import{a as n}from"./index.esm-CsVMth3X.js";import{C as L,a as N,b as m,c as d,d as b,e as g}from"./CTable-Hg-7IAkc.js";import{P as k}from"./Pagination-Db4ijxZz.js";import{C as w,a as S,b as I,c as R,d as D}from"./CModalTitle-CeHpYnfN.js";import{C as v}from"./CForm-DinUw6Fq.js";import{C as U}from"./CFormLabel-D-i2Ydtg.js";import{C as A}from"./CFormInput-2XdN1xrD.js";import"./DefaultLayout-DkBJ2y_r.js";import"./cil-user-Dlmw-Gem.js";import"./CFormControlWrapper-C9xCSD9I.js";import"./CFormControlValidation-D6H3tTV0.js";class Y extends j.Component{constructor(t){super(t);h(this,"handlePageChange",(t,o)=>{this.setState({currentPage:o}),this.fetchCategories(o)});h(this,"toggleModal",(t=null)=>{this.setState(o=>({showModal:!o.showModal,editCategory:t,newCategory:t||{id:"",categoryName:""}}))});h(this,"handleInputChange",t=>{const{name:o,value:a}=t.target;this.setState(s=>({newCategory:{...s.newCategory,[o]:a}}))});h(this,"handleSave",()=>{const{newCategory:t,editCategory:o}=this.state,a={id:t.id,categoryName:t.categoryName};if(t.categoryName===""){alert("Vui lòng nhập tên thể loại");return}const s="http://localhost:8080/api/categories";o?fetch(`${s}/update/${o.id}`,{method:"PUT",headers:{"Content-Type":"application/json"},body:JSON.stringify(a)}).then(i=>{i.ok?(alert("Sửa thể loại thành công"),this.toggleModal(),this.fetchCategories(this.state.currentPage)):(alert("Có lỗi xảy ra khi sửa thể loại"),console.error("Lỗi sửa thể loại:",i))}).catch(i=>{alert("Có lỗi xảy ra khi sửa thể loại"),console.error("Lỗi sửa thể loại:",i)}):fetch(`${s}/add`,{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(a)}).then(i=>{i.ok?(alert("Thêm thể loại thành công"),this.toggleModal(),this.fetchCategories(this.state.currentPage)):(alert("Có lỗi xảy ra khi thêm thể loại"),console.error("Lỗi thêm thể loại:",i))}).catch(i=>{alert("Có lỗi xảy ra khi thêm thể loại"),console.error("Lỗi thêm thể loại:",i)})});h(this,"handleDelete",t=>{fetch(`http://localhost:8080/api/categories/updateStatus/${t}`,{method:"PUT",headers:{"Content-Type":"application/json"}}).then(a=>{a.ok?(alert("Xóa thể loại thành công"),this.fetchCategories(this.state.currentPage)):(alert("Có lỗi xảy ra khi xóa thể loại"),console.error("Lỗi xóa thể loại:",a))}).catch(a=>{alert("Có lỗi xảy ra khi xóa thể loại"),console.error("Lỗi xóa thể loại:",a)})});this.state={categories:[],currentPage:1,pageLimit:0,perPage:5,showModal:!1,editCategory:null,newCategory:{id:"",categoryName:""}}}componentDidMount(){this.fetchCategories(1)}fetchCategories(t){fetch("http://localhost:8080/api/categories/getall").then(a=>a.json()).then(a=>{alert("Danh sách thể loại: "+JSON.stringify(a));const s=Math.ceil(a.length/this.state.perPage);this.setState({categories:a,pageLimit:s}),console.log("Danh sach the loai: "+categories)}).catch(a=>{console.error("Lỗi fetch categories:",a)})}render(){const{categories:t,currentPage:o,pageLimit:a,showModal:s,newCategory:i,editCategory:C}=this.state;return e.jsxs(f,{children:[e.jsx(u,{children:e.jsxs(P,{children:[e.jsxs(M,{children:["Category Information",e.jsx(n,{color:"primary",className:"float-end",onClick:()=>this.toggleModal(),children:"Thêm thể loại"})]}),e.jsxs(T,{children:[e.jsxs(L,{children:[e.jsx(N,{children:e.jsxs(m,{children:[e.jsx(d,{children:"ID"}),e.jsx(d,{children:"Tên thể loại"}),e.jsx(d,{children:"Hành động"})]})}),e.jsx(b,{children:t.map((c,p)=>e.jsxs(m,{children:[e.jsx(g,{children:c.id}),e.jsx(g,{children:c.categoryName}),e.jsxs(g,{children:[e.jsx(n,{color:"warning",onClick:()=>this.toggleModal(c),children:"Sửa"}),e.jsx(n,{color:"danger",onClick:()=>this.handleDelete(c.id),children:"Xóa"})]})]},p))})]}),e.jsx(k,{count:a,page:o,onChange:this.handlePageChange,variant:"outlined",shape:"rounded"})]})]})}),e.jsxs(w,{visible:s,onClose:()=>this.toggleModal(),children:[e.jsx(S,{children:e.jsx(I,{children:C?"Chỉnh sửa thể loại":"Thêm thể loại"})}),e.jsx(R,{children:e.jsx(v,{children:e.jsxs("div",{className:"mb-3",children:[e.jsx(U,{htmlFor:"categoryName",children:"Tên thể loại"}),e.jsx(A,{type:"text",id:"categoryName",name:"categoryName",value:i.categoryName,onChange:this.handleInputChange})]})})}),e.jsxs(D,{children:[e.jsx(n,{color:"secondary",onClick:()=>this.toggleModal(),children:"Đóng"}),e.jsx(n,{color:"primary",onClick:this.handleSave,children:C?"Cập nhật":"Lưu"})]})]})]})}}export{Y as default};