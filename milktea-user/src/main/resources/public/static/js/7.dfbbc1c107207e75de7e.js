webpackJsonp([7],{MT5U:function(t,s,e){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var o=e("gHqA"),i=e("hpcr"),a=e("Y52p"),r=(new o.a,i.a,a.a,new o.a),n={name:"mineOrderDetail",data:function(){return{headTitle:this.$t("mine.orderDetails"),mineOrderType:1,orderDetail:[],goodsDetail:[],storeAddress:null,promotionName:null,orderDetailTitle:this.$t("mine.orderInformation")}},mounted:function(){this.initData(),this.queryPromotionByStoreNoNation()},methods:{initData:function(){console.log("DB.getItem(orderDetail).toJson()"),console.log(r.getItem("orderDetail").toJson());var t=r.getItem("orderDetail").toJson();if(t){t.orderDetails.map(function(t,s){t.attrItems="",t.attrs.forEach(function(s,e){t.attrItems+=s.attrName+" "})}),console.log("orderDetail"),console.log(t);var s=[];t.orderDetails.forEach(function(t,e){if(!s.length)return t.tGoodsLeng=1,void s.push(t);var o=0;s.map(function(e,i){if(t.attrItems!=e.attrItems||t.goodsId!=e.goodsId)return++o==s.length?(t.tGoodsLeng=1,void s.push(t)):void 0;e.tGoodsLeng+=1})})}console.log("getDataLeng"),console.log(s),this.orderDetail=t,this.goodsDetail=s},queryPromotionByStoreNoNation:function(){var t=this;r.getItem("storeList").toJson().forEach(function(s,e){r.getItem("storeNo").toString()==s.storeNo&&(t.storeAddress="素匠·泰茶("+s.storeAddress+")")}),this.$http.get("/userLogin/queryPromotionByStoreNoNation",{params:{storeNo:r.getItem("storeNo").toString(),lang:r.getItem("localLang").toString()}}).then(function(s){200==s.status&&"00000"==s.data.rspCode&&(t.promotionName=s.data.data.data[0].promotionName)}).catch(function(t){console.log(t)})}},components:{headerBack:i.a,XImg:a.a}},d={render:function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",{staticClass:"app-init"},[e("header-back",{attrs:{title:t.headTitle}}),t._v(" "),e("div",{staticClass:"yyTcCont"},[e("div",{staticClass:"orderStatusCont"},[0==t.orderDetail.orderStatus?e("div",[t._v(t._s(t.$t("mine.placedAnOrder")))]):1==t.orderDetail.orderStatus?e("div",[t._v(t._s(t.$t("mine.finishedOrder")))]):2==t.orderDetail.orderStatus?e("div",[t._v(t._s(t.$t("mine.PickupToComplete")))]):3==t.orderDetail.orderStatus?e("div",[t._v(t._s(t.$t("mine.delivery")))]):4==t.orderDetail.orderStatus?e("div",[t._v(t._s(t.$t("mine.revocation")))]):t._e(),t._v(" "),e("svg",{staticClass:"vux-x-icon vux-x-icon-ios-arrow-right",attrs:{type:"ios-arrow-right",size:"30",xmlns:"http://www.w3.org/2000/svg",width:"30",height:"30",viewBox:"0 0 512 512"}},[e("path",{attrs:{d:"M160 115.4L180.7 96 352 256 180.7 416 160 396.7 310.5 256z"}})])]),t._v(" "),e("div",{staticClass:"goodsDetail"},[e("div",{staticClass:"gd-cont"},[e("div",{staticClass:"gdc-title"},[t._v("\n\t\t\t\t\t\t"+t._s(t.storeAddress)+"\n\t\t\t\t\t")]),t._v(" "),t._l(t.goodsDetail,function(s,o){return e("div",{staticClass:"gdc-detail"},[e("x-img",{directives:[{name:"lazy",rawName:"v-lazy",value:s.goodsPictureBig,expression:"item.goodsPictureBig"}],staticClass:"gdcd-img"}),t._v(" "),e("div",{staticClass:"gdcd-price"},[e("div",{staticClass:"gdcdp-right"},[e("div",{staticClass:"gdcdpr-title"},[t._v(t._s(s.goodsName))]),t._v(" "),e("div",t._l(s.attrs,function(s){return e("span",[t._v(t._s(s.attrName)+" ")])})),t._v(" "),e("div",[t._v("x"+t._s(s.tGoodsLeng))])]),t._v(" "),e("div",{staticClass:"gdcdp-plus"},[e("span",[t._v("C$"+t._s(s.origPrice))]),t._v(" "),e("span",[t._v("C$"+t._s(s.attrPrice))])])])],1)}),t._v(" "),e("div",{staticClass:"gdc-footer"},[e("div",{staticClass:"gdcf-allPrice"},[e("span",{staticClass:"gdcfa-One"},[t._v(t._s(t.$t("closeAccount.originalPrice"))+"：")]),t._v(" "),e("span",{staticClass:"gdcfa-Two"},[t._v("C$"+t._s(t.orderDetail.origPrice))])]),t._v(" "),e("div",{staticClass:"gdcf-allPrice"},[e("span",{staticClass:"gdcfa-One"},[t._v(t._s(t.$t("closeAccount.discounts"))+"("+t._s(t.promotionName)+")：")]),t._v(" "),e("span",{staticClass:"gdcfa-Two"},[t._v("C$"+t._s(t.orderDetail.discount))])]),t._v(" "),e("div",{staticClass:"gdcf-allPrice"},[e("span",{staticClass:"gdcfa-One"},[t._v(t._s(t.$t("closeAccount.subtotal"))+"：")]),t._v(" "),e("span",{staticClass:"gdcfa-Two"},[t._v("C$"+t._s(t.orderDetail.orderPrice))])])])],2),t._v(" "),e("div",{staticClass:"orderDetailCont"},[e("div",{staticClass:"odc-title"},[t._v("\n\t\t\t\t\t"+t._s(t.orderDetailTitle)+"\n\t\t\t\t")]),t._v(" "),e("div",{staticClass:"odc-cont"},[e("div",{staticClass:"odcc-title"},[t._v("\n\t\t\t  \t\t"+t._s(t.$t("mine.orderNumber"))+"\n\t\t\t  \t")]),t._v(" "),e("div",{staticClass:"odcc-cont"},[t._v("\n\t\t\t  \t\t"+t._s(t.orderDetail.orderNo)+"\n\t\t\t  \t")])]),t._v(" "),e("div",{staticClass:"odc-cont"},[e("div",{staticClass:"odcc-title"},[t._v("\n\t\t\t  \t\t"+t._s(t.$t("mine.orderTime"))+"\n\t\t\t  \t")]),t._v(" "),e("div",{staticClass:"odcc-cont"},[t._v("\n\t\t\t  \t\t"+t._s(t.orderDetail.orderTime)+"\n\t\t\t  \t")])]),t._v(" "),t.orderDetail.bookTime?e("div",{staticClass:"odc-cont"},[e("div",{staticClass:"odcc-title"},[t._v("\n\t\t\t  \t\t"+t._s(t.$t("mine.bookTime"))+"\n\t\t\t  \t")]),t._v(" "),e("div",{staticClass:"odcc-cont"},[t._v("\n\t\t\t  \t\t"+t._s(t.orderDetail.bookTime)+"\n\t\t\t  \t")])]):t._e(),t._v(" "),e("div",{staticClass:"odc-cont"},[e("div",{staticClass:"odcc-title"},[t._v("\n\t\t\t  \t\t"+t._s(t.$t("mine.orderType"))+"\n\t\t\t  \t")]),t._v(" "),e("div",{staticClass:"odcc-cont"},[0==t.orderDetail.orderType?e("div",[t._v(t._s(t.$t("closeAccount.reserve")))]):e("div",[t._v(t._s(t.$t("closeAccount.eatIn")))])])]),t._v(" "),e("div",{staticClass:"odc-cont"},[e("div",{staticClass:"odcc-title"},[t._v("\n\t\t\t  \t\t"+t._s(t.$t("mine.modeOfPayment"))+"\n\t\t\t  \t")]),t._v(" "),e("div",{staticClass:"odcc-cont"},[t._v("\n\t\t\t  \t\t"+t._s(t.$t("mine.onlinePayment"))+"\n\t\t\t  \t")])]),t._v(" "),t.orderDetail.remark?e("div",{staticClass:"odc-cont"},[e("div",{staticClass:"odcc-title"},[t._v("\n\t\t\t  \t\t"+t._s(t.$t("mine.BuyerMessage"))+"\n\t\t\t  \t")]),t._v(" "),e("div",{staticClass:"odcc-cont odcc-contRemark"},[t._v("\n\t\t\t  \t\t"+t._s(t.orderDetail.remark)+"\n\t\t\t  \t")])]):t._e()])])])],1)},staticRenderFns:[]};var c=e("VU/8")(n,d,!1,function(t){e("OqGM")},"data-v-a58314b2",null);s.default=c.exports},OqGM:function(t,s){}});
//# sourceMappingURL=7.dfbbc1c107207e75de7e.js.map