import{r as u,R as h,c as b,P as s,_ as y,b as l,d as g}from"./index-C-5cxYHG.js";import{C}from"./index.esm-1UYR0KCb.js";var v=u.forwardRef(function(a,c){var e,n=a.children,r=a.as,i=r===void 0?"ul":r,o=a.className,p=a.flush,t=a.layout;return h.createElement(i,{className:b("list-group",(e={"list-group-flush":p},e["list-group-".concat(t)]=t,e),o),ref:c},n)});v.propTypes={as:s.elementType,children:s.node,className:s.string,flush:s.bool,layout:s.oneOf(["horizontal","horizontal-sm","horizontal-md","horizontal-lg","horizontal-xl","horizontal-xxl"])};v.displayName="CListGroup";var f=u.forwardRef(function(a,c){var e,n=a.children,r=a.active,i=a.as,o=i===void 0?"li":i,p=a.className,t=a.disabled,m=a.color,d=y(a,["children","active","as","className","disabled","color"]),N=o==="a"||o==="button"?C:o;return d=l(l(l(l({},(o==="a"||o==="button")&&{active:r,disabled:t,as:o,ref:c}),r&&{"aria-current":!0}),t&&{"aria-disabled":!0}),d),h.createElement(N,l({className:b("list-group-item",(e={},e["list-group-item-".concat(m)]=m,e["list-group-item-action"]=o==="a"||o==="button",e.active=r,e.disabled=t,e),p)},d),n)});f.propTypes={active:s.bool,as:s.elementType,children:s.node,className:s.string,color:g,disabled:s.bool};f.displayName="CListGroupItem";export{v as C,f as a};