import{r as v,_ as j,R as e,b as m,P as r,c as l}from"./index-C-5cxYHG.js";import{C as y}from"./CFormControlValidation-xUoZ7MXY.js";import{C}from"./CFormLabel-9ydK42Z1.js";import{u as w}from"./DefaultLayout-CeRKVQXQ.js";var N=v.forwardRef(function(t,g){var k=t.className,n=t.button,R=t.feedback,L=t.feedbackInvalid,V=t.feedbackValid,x=t.floatingLabel,A=t.tooltipFeedback,p=t.hitArea,a=t.id,s=t.indeterminate,T=t.inline,d=t.invalid,i=t.label,I=t.reverse,h=t.type,O=h===void 0?"checkbox":h,f=t.valid,E=j(t,["className","button","feedback","feedbackInvalid","feedbackValid","floatingLabel","tooltipFeedback","hitArea","id","indeterminate","inline","invalid","label","reverse","type","valid"]),c=v.useRef(null),z=w(g,c);v.useEffect(function(){c.current&&s&&(c.current.indeterminate=s)},[s,c.current]);var o=function(){return e.createElement("input",m({type:O,className:l(n?"btn-check":"form-check-input",{"is-invalid":d,"is-valid":f,"me-2":p}),id:a},E,{ref:z}))},b=function(){return e.createElement(y,{describedby:E["aria-describedby"],feedback:R,feedbackInvalid:L,feedbackValid:V,floatingLabel:x,invalid:d,tooltipFeedback:A,valid:f})},F=function(){var u;return e.createElement(C,m({customClassName:l(n?l("btn",n.variant?"btn-".concat(n.variant,"-").concat(n.color):"btn-".concat(n.color),(u={},u["btn-".concat(n.size)]=n.size,u),"".concat(n.shape)):"form-check-label")},a&&{htmlFor:a}),i)},P=function(){return n?e.createElement(e.Fragment,null,e.createElement(o,null),i&&e.createElement(F,null),e.createElement(b,null)):i?p?e.createElement(e.Fragment,null,e.createElement(o,null),e.createElement(C,m({customClassName:l("form-check-label stretched-link",k)},a&&{htmlFor:a}),i),e.createElement(b,null)):e.createElement("div",{className:l("form-check",{"form-check-inline":T,"form-check-reverse":I,"is-invalid":d,"is-valid":f},k)},e.createElement(o,null),e.createElement(F,null),e.createElement(b,null)):e.createElement(o,null)};return e.createElement(P,null)});N.propTypes=m({button:r.object,className:r.string,hitArea:r.oneOf(["full"]),id:r.string,indeterminate:r.bool,inline:r.bool,label:r.oneOfType([r.string,r.node]),reverse:r.bool,type:r.oneOf(["checkbox","radio"])},y.propTypes);N.displayName="CFormCheck";export{N as C};