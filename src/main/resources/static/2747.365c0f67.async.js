(self.webpackChunkant_design_pro=self.webpackChunkant_design_pro||[]).push([[2747],{6700:function(ae,W,o){"use strict";o.d(W,{Z:function(){return y}});var B=o(28991),p=o(67294),C={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M872 474H286.9l350.2-304c5.6-4.9 2.2-14-5.2-14h-88.5c-3.9 0-7.6 1.4-10.5 3.9L155 487.8a31.96 31.96 0 000 48.3L535.1 866c1.5 1.3 3.3 2 5.2 2h91.5c7.4 0 10.8-9.2 5.2-14L286.9 550H872c4.4 0 8-3.6 8-8v-60c0-4.4-3.6-8-8-8z"}}]},name:"arrow-left",theme:"outlined"},O=C,L=o(27029),R=function(I,a){return p.createElement(L.Z,(0,B.Z)((0,B.Z)({},I),{},{ref:a,icon:O}))};R.displayName="ArrowLeftOutlined";var y=p.forwardRef(R)},64335:function(ae,W,o){"use strict";var B=o(67294),p=(0,B.createContext)({});W.Z=p},85224:function(ae,W,o){"use strict";var B=o(22122),p=o(28991),C=o(84305),O=o(75901),L=o(81253),R=o(67294),y=o(94184),z=o.n(y),I=o(97435),a=o(56264),N=o.n(a),F=o(64335),_=["children","className","extra","style","renderContent"],X=function(A){var j=A.children,Y=A.className,G=A.extra,oe=A.style,V=A.renderContent,de=(0,L.Z)(A,_),fe=(0,R.useContext)(O.ZP.ConfigContext),ue=fe.getPrefixCls,J=A.prefixCls||ue("pro"),ve="".concat(J,"-footer-bar"),b=(0,R.useContext)(F.Z),ie=(0,R.useMemo)(function(){var q=b.hasSiderMenu,Q=b.isMobile,ee=b.siderWidth;if(!!q)return ee?Q?"100%":"calc(100% - ".concat(ee,"px)"):"100%"},[b.collapsed,b.hasSiderMenu,b.isMobile,b.siderWidth]),le=R.createElement(R.Fragment,null,R.createElement("div",{className:"".concat(ve,"-left")},G),R.createElement("div",{className:"".concat(ve,"-right")},j));return(0,R.useEffect)(function(){return!b||!(b==null?void 0:b.setHasFooterToolbar)?function(){}:(b==null||b.setHasFooterToolbar(!0),function(){var q;b==null||(q=b.setHasFooterToolbar)===null||q===void 0||q.call(b,!1)})},[]),R.createElement("div",(0,B.Z)({className:z()(Y,"".concat(ve)),style:(0,p.Z)({width:ie},oe)},(0,I.Z)(de,["prefixCls"])),V?V((0,p.Z)((0,p.Z)((0,p.Z)({},A),b),{},{leftWidth:ie}),le):le)};W.Z=X},21349:function(ae,W,o){"use strict";var B=o(84305),p=o(75901),C=o(53645),O=o.n(C),L=o(67294),R=o(94184),y=o.n(R),z=o(64335),I=function(N){var F=(0,L.useContext)(z.Z),_=N.children,X=N.contentWidth,D=N.className,A=N.style,j=(0,L.useContext)(p.ZP.ConfigContext),Y=j.getPrefixCls,G=N.prefixCls||Y("pro"),oe=X||F.contentWidth,V="".concat(G,"-grid-content");return L.createElement("div",{className:y()(V,D,{wide:oe==="Fixed"}),style:A},L.createElement("div",{className:"".concat(G,"-grid-content-children")},_))};W.Z=I},62747:function(ae,W,o){"use strict";o.d(W,{ZP:function(){return Zt}});var B=o(38663),p=o(70883),C=o(22122),O=o(96156),L=o(6610),R=o(5991),y=o(10379),z=o(60446),I=o(90484),a=o(67294),N=o(94184),F=o.n(N),_=o(98423),X=o(48717),D=o(53124),A=o(85061),j=o(75164);function Y(i){var e,r=function(l){return function(){e=null,i.apply(void 0,(0,A.Z)(l))}},t=function(){if(e==null){for(var l=arguments.length,s=new Array(l),c=0;c<l;c++)s[c]=arguments[c];e=(0,j.Z)(r(s))}};return t.cancel=function(){j.Z.cancel(e),e=null},t}function G(){return function(e,r,t){var n=t.value,l=!1;return{configurable:!0,get:function(){if(l||this===e.prototype||this.hasOwnProperty(r))return n;var c=Y(n.bind(this));return l=!0,Object.defineProperty(this,r,{value:c,configurable:!0,writable:!0}),l=!1,c}}}}var oe=o(64019);function V(i){return i!==window?i.getBoundingClientRect():{top:0,bottom:window.innerHeight}}function de(i,e,r){if(r!==void 0&&e.top>i.top-r)return r+e.top}function fe(i,e,r){if(r!==void 0&&e.bottom<i.bottom+r){var t=window.innerHeight-e.bottom;return r+t}}var ue=["resize","scroll","touchstart","touchmove","touchend","pageshow","load"],J=[];function ve(){return J}function b(i,e){if(!!i){var r=J.find(function(t){return t.target===i});r?r.affixList.push(e):(r={target:i,affixList:[e],eventHandlers:{}},J.push(r),ue.forEach(function(t){r.eventHandlers[t]=(0,oe.Z)(i,t,function(){r.affixList.forEach(function(n){n.lazyUpdatePosition()})})}))}}function ie(i){var e=J.find(function(r){var t=r.affixList.some(function(n){return n===i});return t&&(r.affixList=r.affixList.filter(function(n){return n!==i})),t});e&&e.affixList.length===0&&(J=J.filter(function(r){return r!==e}),ue.forEach(function(r){var t=e.eventHandlers[r];t&&t.remove&&t.remove()}))}var le=function(i,e,r,t){var n=arguments.length,l=n<3?e:t===null?t=Object.getOwnPropertyDescriptor(e,r):t,s;if((typeof Reflect=="undefined"?"undefined":(0,I.Z)(Reflect))==="object"&&typeof Reflect.decorate=="function")l=Reflect.decorate(i,e,r,t);else for(var c=i.length-1;c>=0;c--)(s=i[c])&&(l=(n<3?s(l):n>3?s(e,r,l):s(e,r))||l);return n>3&&l&&Object.defineProperty(e,r,l),l};function q(){return typeof window!="undefined"?window:null}var Q;(function(i){i[i.None=0]="None",i[i.Prepare=1]="Prepare"})(Q||(Q={}));var ee=function(i){(0,y.Z)(r,i);var e=(0,z.Z)(r);function r(){var t;return(0,L.Z)(this,r),t=e.apply(this,arguments),t.state={status:Q.None,lastAffix:!1,prevTarget:null},t.getOffsetTop=function(){var n=t.props,l=n.offsetBottom,s=n.offsetTop;return l===void 0&&s===void 0?0:s},t.getOffsetBottom=function(){return t.props.offsetBottom},t.savePlaceholderNode=function(n){t.placeholderNode=n},t.saveFixedNode=function(n){t.fixedNode=n},t.measure=function(){var n=t.state,l=n.status,s=n.lastAffix,c=t.props.onChange,d=t.getTargetFunc();if(!(l!==Q.Prepare||!t.fixedNode||!t.placeholderNode||!d)){var v=t.getOffsetTop(),m=t.getOffsetBottom(),h=d();if(!!h){var f={status:Q.None},g=V(h),u=V(t.placeholderNode),x=de(u,g,v),Z=fe(u,g,m);x!==void 0?(f.affixStyle={position:"fixed",top:x,width:u.width,height:u.height},f.placeholderStyle={width:u.width,height:u.height}):Z!==void 0&&(f.affixStyle={position:"fixed",bottom:Z,width:u.width,height:u.height},f.placeholderStyle={width:u.width,height:u.height}),f.lastAffix=!!f.affixStyle,c&&s!==f.lastAffix&&c(f.lastAffix),t.setState(f)}}},t.prepareMeasure=function(){if(t.setState({status:Q.Prepare,affixStyle:void 0,placeholderStyle:void 0}),!1)var n},t}return(0,R.Z)(r,[{key:"getTargetFunc",value:function(){var n=this.context.getTargetContainer,l=this.props.target;return l!==void 0?l:n||q}},{key:"componentDidMount",value:function(){var n=this,l=this.getTargetFunc();l&&(this.timeout=setTimeout(function(){b(l(),n),n.updatePosition()}))}},{key:"componentDidUpdate",value:function(n){var l=this.state.prevTarget,s=this.getTargetFunc(),c=(s==null?void 0:s())||null;l!==c&&(ie(this),c&&(b(c,this),this.updatePosition()),this.setState({prevTarget:c})),(n.offsetTop!==this.props.offsetTop||n.offsetBottom!==this.props.offsetBottom)&&this.updatePosition(),this.measure()}},{key:"componentWillUnmount",value:function(){clearTimeout(this.timeout),ie(this),this.updatePosition.cancel(),this.lazyUpdatePosition.cancel()}},{key:"updatePosition",value:function(){this.prepareMeasure()}},{key:"lazyUpdatePosition",value:function(){var n=this.getTargetFunc(),l=this.state.affixStyle;if(n&&l){var s=this.getOffsetTop(),c=this.getOffsetBottom(),d=n();if(d&&this.placeholderNode){var v=V(d),m=V(this.placeholderNode),h=de(m,v,s),f=fe(m,v,c);if(h!==void 0&&l.top===h||f!==void 0&&l.bottom===f)return}}this.prepareMeasure()}},{key:"render",value:function(){var n=this,l=this.state,s=l.affixStyle,c=l.placeholderStyle,d=this.props,v=d.affixPrefixCls,m=d.children,h=F()((0,O.Z)({},v,!!s)),f=(0,_.Z)(this.props,["prefixCls","offsetTop","offsetBottom","target","onChange","affixPrefixCls"]);return a.createElement(X.Z,{onResize:function(){n.updatePosition()}},a.createElement("div",(0,C.Z)({},f,{ref:this.savePlaceholderNode}),s&&a.createElement("div",{style:c,"aria-hidden":"true"}),a.createElement("div",{className:h,ref:this.saveFixedNode,style:s},a.createElement(X.Z,{onResize:function(){n.updatePosition()}},m))))}}]),r}(a.Component);ee.contextType=D.E_,le([G()],ee.prototype,"updatePosition",null),le([G()],ee.prototype,"lazyUpdatePosition",null);var Le=a.forwardRef(function(i,e){var r=i.prefixCls,t=a.useContext(D.E_),n=t.getPrefixCls,l=n("affix",r),s=(0,C.Z)((0,C.Z)({},i),{affixPrefixCls:l});return a.createElement(ee,(0,C.Z)({},s,{ref:e}))}),Ie=Le,Kt=o(84305),ge=o(75901),wt=o(59903),zt=o(81262),jt=o(30887),$t=o(59250),Gt=o(94233),Xt=o(49111),Ce=o(28481),Ue=o(30470),We=o(6700),S=o(28991),Fe={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M869 487.8L491.2 159.9c-2.9-2.5-6.6-3.9-10.5-3.9h-88.5c-7.4 0-10.8 9.2-5.2 14l350.2 304H152c-4.4 0-8 3.6-8 8v60c0 4.4 3.6 8 8 8h585.1L386.9 854c-5.6 4.9-2.2 14 5.2 14h91.5c1.9 0 3.8-.7 5.2-2L869 536.2a32.07 32.07 0 000-48.4z"}}]},name:"arrow-right",theme:"outlined"},He=Fe,Ke=o(27029),xe=function(e,r){return a.createElement(Ke.Z,(0,S.Z)((0,S.Z)({},e),{},{ref:r,icon:He}))};xe.displayName="ArrowRightOutlined";var we=a.forwardRef(xe),ze=o(50344),je=o(57254),$e=o(92144),Ge=function(i,e){var r={};for(var t in i)Object.prototype.hasOwnProperty.call(i,t)&&e.indexOf(t)<0&&(r[t]=i[t]);if(i!=null&&typeof Object.getOwnPropertySymbols=="function")for(var n=0,t=Object.getOwnPropertySymbols(i);n<t.length;n++)e.indexOf(t[n])<0&&Object.prototype.propertyIsEnumerable.call(i,t[n])&&(r[t[n]]=i[t[n]]);return r},Pe=function(e){var r=e.prefixCls,t=e.separator,n=t===void 0?"/":t,l=e.children,s=e.overlay,c=e.dropdownProps,d=Ge(e,["prefixCls","separator","children","overlay","dropdownProps"]),v=a.useContext(D.E_),m=v.getPrefixCls,h=m("breadcrumb",r),f=function(x){return s?a.createElement($e.Z,(0,C.Z)({overlay:s,placement:"bottom"},c),a.createElement("span",{className:"".concat(h,"-overlay-link")},x,a.createElement(je.Z,null))):x},g;return"href"in d?g=a.createElement("a",(0,C.Z)({className:"".concat(h,"-link")},d),l):g=a.createElement("span",(0,C.Z)({className:"".concat(h,"-link")},d),l),g=f(g),l?a.createElement("li",null,g,n&&a.createElement("span",{className:"".concat(h,"-separator")},n)):null};Pe.__ANT_BREADCRUMB_ITEM=!0;var Ee=Pe,be=function(e){var r=e.children,t=a.useContext(D.E_),n=t.getPrefixCls,l=n("breadcrumb");return a.createElement("span",{className:"".concat(l,"-separator")},r||"/")};be.__ANT_BREADCRUMB_SEPARATOR=!0;var Xe=be,Ye=o(28682),Ve=o(96159),Je=function(i,e){var r={};for(var t in i)Object.prototype.hasOwnProperty.call(i,t)&&e.indexOf(t)<0&&(r[t]=i[t]);if(i!=null&&typeof Object.getOwnPropertySymbols=="function")for(var n=0,t=Object.getOwnPropertySymbols(i);n<t.length;n++)e.indexOf(t[n])<0&&Object.prototype.propertyIsEnumerable.call(i,t[n])&&(r[t[n]]=i[t[n]]);return r};function Qe(i,e){if(!i.breadcrumbName)return null;var r=Object.keys(e).join("|"),t=i.breadcrumbName.replace(new RegExp(":(".concat(r,")"),"g"),function(n,l){return e[l]||n});return t}function ke(i,e,r,t){var n=r.indexOf(i)===r.length-1,l=Qe(i,e);return n?a.createElement("span",null,l):a.createElement("a",{href:"#/".concat(t.join("/"))},l)}var ye=function(e,r){return e=(e||"").replace(/^\//,""),Object.keys(r).forEach(function(t){e=e.replace(":".concat(t),r[t])}),e},_e=function(e,r,t){var n=(0,A.Z)(e),l=ye(r||"",t);return l&&n.push(l),n},me=function(e){var r=e.prefixCls,t=e.separator,n=t===void 0?"/":t,l=e.style,s=e.className,c=e.routes,d=e.children,v=e.itemRender,m=v===void 0?ke:v,h=e.params,f=h===void 0?{}:h,g=Je(e,["prefixCls","separator","style","className","routes","children","itemRender","params"]),u=a.useContext(D.E_),x=u.getPrefixCls,Z=u.direction,P,T=x("breadcrumb",r);if(c&&c.length>0){var M=[];P=c.map(function(E){var H=ye(E.path,f);H&&M.push(H);var K;return E.children&&E.children.length&&(K=a.createElement(Ye.Z,{items:E.children.map(function(w){return{key:w.path||w.breadcrumbName,label:m(w,f,c,_e(M,w.path,f))}})})),a.createElement(Ee,{overlay:K,separator:n,key:H||E.breadcrumbName},m(E,f,c,M))})}else d&&(P=(0,ze.Z)(d).map(function(E,H){return E&&(0,Ve.Tm)(E,{separator:n,key:H})}));var $=F()(T,(0,O.Z)({},"".concat(T,"-rtl"),Z==="rtl"),s);return a.createElement("nav",(0,C.Z)({className:$,style:l},g),a.createElement("ol",null,P))};me.Item=Ee,me.Separator=Xe;var qe=me,et=qe,tt=o(51890),rt=o(19650),at=o(34952),nt=o(42051),ot=function(e,r,t){return!r||!t?null:a.createElement(nt.Z,{componentName:"PageHeader"},function(n){var l=n.back;return a.createElement("div",{className:"".concat(e,"-back")},a.createElement(at.Z,{onClick:function(c){t==null||t(c)},className:"".concat(e,"-back-button"),"aria-label":l},r))})},it=function(e){return a.createElement(et,(0,C.Z)({},e))},lt=function(e){var r=arguments.length>1&&arguments[1]!==void 0?arguments[1]:"ltr";return e.backIcon!==void 0?e.backIcon:r==="rtl"?a.createElement(we,null):a.createElement(We.Z,null)},st=function(e,r){var t=arguments.length>2&&arguments[2]!==void 0?arguments[2]:"ltr",n=r.title,l=r.avatar,s=r.subTitle,c=r.tags,d=r.extra,v=r.onBack,m="".concat(e,"-heading"),h=n||s||c||d;if(!h)return null;var f=lt(r,t),g=ot(e,f,v),u=g||l||h;return a.createElement("div",{className:m},u&&a.createElement("div",{className:"".concat(m,"-left")},g,l&&a.createElement(tt.C,(0,C.Z)({},l)),n&&a.createElement("span",{className:"".concat(m,"-title"),title:typeof n=="string"?n:void 0},n),s&&a.createElement("span",{className:"".concat(m,"-sub-title"),title:typeof s=="string"?s:void 0},s),c&&a.createElement("span",{className:"".concat(m,"-tags")},c)),d&&a.createElement("span",{className:"".concat(m,"-extra")},a.createElement(rt.Z,null,d)))},ct=function(e,r){return r?a.createElement("div",{className:"".concat(e,"-footer")},r):null},dt=function(e,r){return a.createElement("div",{className:"".concat(e,"-content")},r)},ft=function(e){var r=(0,Ue.Z)(!1),t=(0,Ce.Z)(r,2),n=t[0],l=t[1],s=function(d){var v=d.width;l(v<768,!0)};return a.createElement(D.C,null,function(c){var d,v=c.getPrefixCls,m=c.pageHeader,h=c.direction,f,g=e.prefixCls,u=e.style,x=e.footer,Z=e.children,P=e.breadcrumb,T=e.breadcrumbRender,M=e.className,$=!0;"ghost"in e?$=e.ghost:m&&"ghost"in m&&($=m.ghost);var E=v("page-header",g),H=function(){return(P==null?void 0:P.routes)?it(P):null},K=H(),w=P&&"props"in P,te=(f=T==null?void 0:T(e,K))!==null&&f!==void 0?f:K,U=w?P:te,ne=F()(E,M,(d={"has-breadcrumb":!!U,"has-footer":!!x},(0,O.Z)(d,"".concat(E,"-ghost"),$),(0,O.Z)(d,"".concat(E,"-rtl"),h==="rtl"),(0,O.Z)(d,"".concat(E,"-compact"),n),d));return a.createElement(X.Z,{onResize:s},a.createElement("div",{className:ne,style:u},U,st(E,e,h),Z&&dt(E,Z),ct(E,x)))})},ut=ft,pe=o(81253),Yt=o(18106),Oe=o(10759),Re=o(64335),vt=o(21349),mt=o(85224),Vt=o(12395),ht=o(83832),gt=function(e){if(!e)return 1;var r=e.backingStorePixelRatio||e.webkitBackingStorePixelRatio||e.mozBackingStorePixelRatio||e.msBackingStorePixelRatio||e.oBackingStorePixelRatio||e.backingStorePixelRatio||1;return(window.devicePixelRatio||1)/r},Ct=function(e){var r=e.children,t=e.style,n=e.className,l=e.markStyle,s=e.markClassName,c=e.zIndex,d=c===void 0?9:c,v=e.gapX,m=v===void 0?212:v,h=e.gapY,f=h===void 0?222:h,g=e.width,u=g===void 0?120:g,x=e.height,Z=x===void 0?64:x,P=e.rotate,T=P===void 0?-22:P,M=e.image,$=e.content,E=e.offsetLeft,H=e.offsetTop,K=e.fontStyle,w=K===void 0?"normal":K,te=e.fontWeight,U=te===void 0?"normal":te,ne=e.fontColor,he=ne===void 0?"rgba(0,0,0,.15)":ne,Ze=e.fontSize,Ne=Ze===void 0?16:Ze,Te=e.fontFamily,Me=Te===void 0?"sans-serif":Te,Nt=e.prefixCls,Tt=(0,a.useContext)(ge.ZP.ConfigContext),Mt=Tt.getPrefixCls,Be=Mt("pro-layout-watermark",Nt),Bt=F()("".concat(Be,"-wrapper"),n),Dt=F()(Be,s),At=(0,a.useState)(""),De=(0,Ce.Z)(At,2),St=De[0],Ae=De[1];return(0,a.useEffect)(function(){var se=document.createElement("canvas"),k=se.getContext("2d"),re=gt(k),Lt="".concat((m+u)*re,"px"),It="".concat((f+Z)*re,"px"),Ut=E||m/2,Wt=H||f/2;if(se.setAttribute("width",Lt),se.setAttribute("height",It),k){k.translate(Ut*re,Wt*re),k.rotate(Math.PI/180*Number(T));var Ft=u*re,Se=Z*re;if(M){var ce=new Image;ce.crossOrigin="anonymous",ce.referrerPolicy="no-referrer",ce.src=M,ce.onload=function(){k.drawImage(ce,0,0,Ft,Se),Ae(se.toDataURL())}}else if($){var Ht=Number(Ne)*re;k.font="".concat(w," normal ").concat(U," ").concat(Ht,"px/").concat(Se,"px ").concat(Me),k.fillStyle=he,k.fillText($,0,0),Ae(se.toDataURL())}}else console.error("\u5F53\u524D\u73AF\u5883\u4E0D\u652F\u6301Canvas")},[m,f,E,H,T,w,U,u,Z,Me,he,M,$,Ne]),a.createElement("div",{style:(0,S.Z)({position:"relative"},t),className:Bt},r,a.createElement("div",{className:Dt,style:(0,S.Z)({zIndex:d,position:"absolute",left:0,top:0,width:"100%",height:"100%",backgroundSize:"".concat(m+u,"px"),pointerEvents:"none",backgroundRepeat:"repeat",backgroundImage:"url('".concat(St,"')")},l)}))},xt=Ct,Pt=["title","content","pageHeaderRender","header","prefixedClassName","extraContent","style","prefixCls","breadcrumbRender"],Et=["children","loading","className","style","footer","affixProps","ghost","fixedHeader","breadcrumbRender"];function bt(i){return(0,I.Z)(i)==="object"?i:{spinning:i}}var yt=function(e){var r=e.tabList,t=e.tabActiveKey,n=e.onTabChange,l=e.tabBarExtraContent,s=e.tabProps,c=e.prefixedClassName;return Array.isArray(r)||l?a.createElement(Oe.Z,(0,C.Z)({className:"".concat(c,"-tabs"),activeKey:t,onChange:function(v){n&&n(v)},tabBarExtraContent:l},s),r==null?void 0:r.map(function(d,v){return a.createElement(Oe.Z.TabPane,(0,C.Z)({},d,{tab:d.tab,key:d.key||v}))})):null},pt=function(e,r,t){return!e&&!r?null:a.createElement("div",{className:"".concat(t,"-detail")},a.createElement("div",{className:"".concat(t,"-main")},a.createElement("div",{className:"".concat(t,"-row")},e&&a.createElement("div",{className:"".concat(t,"-content")},e),r&&a.createElement("div",{className:"".concat(t,"-extraContent")},r))))},Jt=function(e){var r=useContext(RouteContext);return React.createElement("div",{style:{height:"100%",display:"flex",alignItems:"center"}},React.createElement(_Breadcrumb,_extends({},r==null?void 0:r.breadcrumb,r==null?void 0:r.breadcrumbProps,e)))},Ot=function(e){var r,t=(0,a.useContext)(Re.Z),n=e.title,l=e.content,s=e.pageHeaderRender,c=e.header,d=e.prefixedClassName,v=e.extraContent,m=e.style,h=e.prefixCls,f=e.breadcrumbRender,g=(0,pe.Z)(e,Pt),u=(0,a.useMemo)(function(){if(!!f)return f},[f]);if(s===!1)return null;if(s)return a.createElement(a.Fragment,null," ",s((0,S.Z)((0,S.Z)({},e),t)));var x=n;!n&&n!==!1&&(x=t.title);var Z=(0,S.Z)((0,S.Z)((0,S.Z)({},t),{},{title:x},g),{},{footer:yt((0,S.Z)((0,S.Z)({},g),{},{breadcrumbRender:f,prefixedClassName:d}))},c),P=Z.breadcrumb,T=(!P||!(P==null?void 0:P.itemRender)&&!(P==null||(r=P.routes)===null||r===void 0?void 0:r.length))&&!f;return["title","subTitle","extra","tags","footer","avatar","backIcon"].every(function(M){return!Z[M]})&&T&&!l&&!v?null:a.createElement("div",{className:"".concat(d,"-warp")},a.createElement(ut,(0,C.Z)({},Z,{breadcrumb:f===!1?void 0:(0,S.Z)((0,S.Z)({},Z.breadcrumb),t.breadcrumbProps),breadcrumbRender:u,prefixCls:h}),(c==null?void 0:c.children)||pt(l,v,d)))},Rt=function(e){var r,t,n=e.children,l=e.loading,s=l===void 0?!1:l,c=e.className,d=e.style,v=e.footer,m=e.affixProps,h=e.ghost,f=e.fixedHeader,g=e.breadcrumbRender,u=(0,pe.Z)(e,Et),x=(0,a.useContext)(Re.Z),Z=(0,a.useContext)(ge.ZP.ConfigContext),P=Z.getPrefixCls,T=e.prefixCls||P("pro"),M="".concat(T,"-page-container"),$=F()(M,c,(r={},(0,O.Z)(r,"".concat(T,"-page-container-ghost"),h),(0,O.Z)(r,"".concat(T,"-page-container-with-footer"),v),r)),E=(0,a.useMemo)(function(){return n?a.createElement(a.Fragment,null,a.createElement("div",{className:"".concat(M,"-children-content")},n),x.hasFooterToolbar&&a.createElement("div",{style:{height:48,marginTop:24}})):null},[n,M,x.hasFooterToolbar]),H=(0,a.useMemo)(function(){var U;return g==!1?!1:g||(u==null||(U=u.header)===null||U===void 0?void 0:U.breadcrumbRender)},[g,u==null||(t=u.header)===null||t===void 0?void 0:t.breadcrumbRender]),K=a.createElement(Ot,(0,C.Z)({},u,{breadcrumbRender:H,ghost:h,prefixCls:void 0,prefixedClassName:M})),w=(0,a.useMemo)(function(){if(a.isValidElement(s))return s;if(typeof s=="boolean"&&!s)return null;var U=bt(s);return a.createElement(ht.Z,U)},[s]),te=(0,a.useMemo)(function(){var U=w||E;if(e.waterMarkProps||x.waterMarkProps){var ne=(0,S.Z)((0,S.Z)({},x.waterMarkProps),e.waterMarkProps);return a.createElement(xt,ne,U)}return U},[e.waterMarkProps,x.waterMarkProps,w,E]);return a.createElement("div",{style:d,className:$},f&&K?a.createElement(Ie,(0,C.Z)({offsetTop:x.hasHeader&&x.fixedHeader?x.headerHeight:0},m),K):K,te&&a.createElement(vt.Z,null,te),v&&a.createElement(mt.Z,{prefixCls:T},v))},Zt=Rt},56264:function(){},53645:function(){},12395:function(){},70883:function(){},81262:function(){},59903:function(){},34952:function(ae,W,o){"use strict";var B=o(22122),p=o(67294),C=o(15105),O=function(y,z){var I={};for(var a in y)Object.prototype.hasOwnProperty.call(y,a)&&z.indexOf(a)<0&&(I[a]=y[a]);if(y!=null&&typeof Object.getOwnPropertySymbols=="function")for(var N=0,a=Object.getOwnPropertySymbols(y);N<a.length;N++)z.indexOf(a[N])<0&&Object.prototype.propertyIsEnumerable.call(y,a[N])&&(I[a[N]]=y[a[N]]);return I},L={border:0,background:"transparent",padding:0,lineHeight:"inherit",display:"inline-block"},R=p.forwardRef(function(y,z){var I=function(j){var Y=j.keyCode;Y===C.Z.ENTER&&j.preventDefault()},a=function(j){var Y=j.keyCode,G=y.onClick;Y===C.Z.ENTER&&G&&G()},N=y.style,F=y.noStyle,_=y.disabled,X=O(y,["style","noStyle","disabled"]),D={};return F||(D=(0,B.Z)({},L)),_&&(D.pointerEvents="none"),D=(0,B.Z)((0,B.Z)({},D),N),p.createElement("div",(0,B.Z)({role:"button",tabIndex:0,ref:z},X,{onKeyDown:I,onKeyUp:a,style:D}))});W.Z=R},97435:function(ae,W){"use strict";function o(B,p){for(var C=Object.assign({},B),O=0;O<p.length;O+=1){var L=p[O];delete C[L]}return C}W.Z=o}}]);
