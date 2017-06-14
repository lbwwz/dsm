/**
 * Created by Lbwwz on 2017/6/6.
 */

//图片预览小图移动效果,页面加载时触发
$(function(){
    var tempLength = 0; //临时变量,当前移动的长度
    var viewNum = 5; //设置每次显示图片的个数量
    var moveNum = 2; //每次移动的数量
    var moveTime = 300; //移动速度,毫秒
    var scrollDiv = $(".spec-scroll .items ul"); //进行移动动画的容器
    var scrollItems = $(".spec-scroll .items ul li"); //移动容器里的集合
    var moveLength = scrollItems.eq(0).width() * moveNum; //计算每次移动的长度
    var countLength = (scrollItems.length - viewNum) * scrollItems.eq(0).width(); //计算总长度,总个数*单个长度

    //下一张
    $(".spec-scroll .next").bind("click",function(){
        if(tempLength < countLength){
            if((countLength - tempLength) > moveLength){
                scrollDiv.animate({left:"-=" + moveLength + "px"}, moveTime);
                tempLength += moveLength;
            }else{
                scrollDiv.animate({left:"-=" + (countLength - tempLength) + "px"}, moveTime);
                tempLength += (countLength - tempLength);
            }
        }
    });
    //上一张
    $(".spec-scroll .prev").bind("click",function(){
        if(tempLength > 0){
            if(tempLength > moveLength){
                scrollDiv.animate({left: "+=" + moveLength + "px"}, moveTime);
                tempLength -= moveLength;
            }else{
                scrollDiv.animate({left: "+=" + tempLength + "px"}, moveTime);
                tempLength = 0;
            }
        }
    });

    $(".items ul").on("mouseover","li",function(){
        $(".items ul img").removeClass("on");
        $(this).children("img").addClass("on");
        $(".jqzoom img").attr("src",$(this).children("img").attr("src")).attr("jqimg",$(this).children("img").attr("bimg"));
    });

    $(".jqzoom").jqueryzoom({xzoom:500,yzoom:500});

});

/*sku生成算法*/

//skuList = [
//    {skuId:101,properties:{0:1,1:1,2:2,3:2},properties_name:{'0|颜色':'1|深灰色','1|内存':'1|16G ROM','2|尺寸':'2|4.7寸','3|类型':'2|国行'},num:1},
//    {skuId:102,properties:{0:1,1:2,2:3,3:2},properties_name:{'0|颜色':'1|深灰色','1|内存':'2|32G ROM','2|尺寸':'3|5.0寸','3|类型':'2|国行'},num:2},
//    {skuId:103,properties:{0:1,1:1,2:3,3:2},properties_name:{'0|颜色':'1|深灰色','1|内存':'1|16G ROM','2|尺寸':'3|5.0寸','3|类型':'2|国行'},num:8},
//    {skuId:104,properties:{0:4,1:1,2:2,3:2},properties_name:{'0|颜色':'1|深灰色','1|内存':'1|16G ROM','2|尺寸':'2|4.7寸','3|类型':'2|国行'},num:9},
//    {skuId:105,properties:{0:2,1:2,2:1,3:1},properties_name:{'0|颜色':'2|金色','1|内存':'2|32G ROM','2|尺寸':'1|4.3寸','3|类型':'1|港版（二网）'},num:3},
//    {skuId:106,properties:{0:2,1:2,2:2,3:2},properties_name:{'0|颜色':'2|金色','1|内存':'2|32G ROM','2|尺寸':'2|4.7寸','3|类型':'2|国行'},num:4},
//    {skuId:107,properties:{0:3,1:2,2:3,3:2},properties_name:{'0|颜色':'3|银色','1|内存':'2|32G ROM','2|尺寸':'3|5.0寸','3|类型':'2|国行'},num:51},
//    {skuId:108,properties:{0:3,1:2,2:2,3:2},properties_name:{'0|颜色':'3|银色','1|内存':'2|32G ROM','2|尺寸':'2|4.7寸','3|类型':'2|国行'},num:14},
//    {skuId:109,properties:{0:3,1:1,2:3,3:1},properties_name:{'0|颜色':'3|银色','1|内存':'1|16G ROM','2|尺寸':'3|5.0寸','3|类型':'1|港版（二网）'},num:16},
//    {skuId:110,properties:{0:4,1:1,2:2,3:2},properties_name:{'0|颜色':'4|黑色','1|内存':'1|16G ROM','2|尺寸':'2|4.7寸','3|类型':'2|国行'},num:1}
//];
$(function(){
    //初始化sku显示列表
    initSkuCheckPanel(skuList);
    $(".yListr ul li em").click(function(){

        if($(this).attr("class")=="yListrclickem"){
            $(this).removeClass("yListrclickem");

        }else if($(this).attr("class")=="yListrnoclickem"){
            return false;
        }else{
            $(this).addClass("yListrclickem").siblings().removeClass("yListrclickem");
        }
        //sku选项面板计算
        calculateSkuCheckPanelShow();
    })
});

function calculateSkuCheckPanelShow(){
    var skuObj = getCheckedSkuData();
    var tempList =[];
    var checkedList = [];
    var flag = false;
    var count = 0;
    for(var i = 0; i < skuList.length; i++){
        if(skuList[i].quantity == 0)continue;
        for(var key in skuObj){

            if(skuObj[key] != (skuList[i].jsonProperties)[key]){
                flag = true;
                count++;
            }
        }
        if(flag){
            flag = false;
        }else{
            tempList.push(deepCopy(skuList[i]));
        }
        if(count == 1){
            checkedList.push(deepCopy(skuList[i].jsonProperties));
            //console.log(skuList[i].jsonProperties);
        }
        count = 0;
    }
    //非选中选项样式
    initSkuCheckPanel(tempList);
    //选中选项样式
    for(var key in skuObj){
        for(var i = 0; i < checkedList.length; i++)
            $(".yListr li[data_attrId='"+key+"']").find("em[data='"+checkedList[i][key]+"']").removeClass("yListrnoclickem");
    }
    if(tempList.length == 1){
        setDataToInput(tempList[0]);
    }
}

function deepCopy(source) {
    var result={};
    for (var key in source) {
        result[key] = typeof source[key]==='object'? deepCopy(source[key]): source[key];
    }
    return result;
}
function setDataToInput(skuItem){
    var a, b,temp;
    for(var i = 0; i<skuList.length;i++){

        if(skuList[i].skuId == skuItem.skuId){
            temp =skuList[i].skuPrice+"";
            a = temp.indexOf(".")
            if(a != -1){
                if(b = temp.length-a<3){
                    for(var j = 0;j<3-b;j++){
                        temp+="0";
                    }
                }
                $("#sku_price").text(temp)
            }else{
                $("#sku_price").text(temp+".00")
            }

        }
    }
}

function formatPrice(price){
    if(price.indexOf(".") != -1){

    }
}

function initSkuCheckPanel(skuList){

    var startTime = new Date().getTime();

    //获取能购买的选项单元
    var showList = {};
    //for(var i = 0; i<$(".yListr li").length; i++){
    //    showList[i] = new Set();
    //}
    for(var key in skuList[0].jsonProperties){
        showList[key] = new Set();
    }
    var temp;
    for(var i = 0; i<skuList.length; i++){
        if(skuList[i].quantity == 0)continue;

        temp = skuList[i].jsonProperties;
        for(var key in temp){
            showList[key].add(temp[key]);
        }
    }

    $(".yListr ul li em:not(.yListrclickem)").addClass("yListrnoclickem");
    for(var i in showList){
        showList[i].forEach(function (element, index, set) {
            $(".yListr li[data_attrId='"+i+"']").find("em[data='"+element+"']").removeClass("yListrnoclickem");
        });
    }
    console.log(new Date().getTime()-startTime);


}


//http://git.shepherdwind.com/sku-search-algorithm.html#toc_0.0.4
/**
 * 获取选中sku信息数据
 */
function getCheckedSkuData(){
    skuObj = {};
    var attrId,valueId;
    for(var i = 0; i<$(".yListr li").length; i++){
        valueId = $($(".yListr li")[i]).children(".yListrclickem").attr("data");
        attrId = $($(".yListr li")[i]).attr("data_attrId");
        if(valueId != undefined){
            skuObj[attrId] = valueId||0;
        }
    }
    return skuObj;

}


/**
 * 业务js
 */

$(function(){

    /**
     * 加入购物车
     */
    $("#addToCart").click(function(){

    })

});