$(function() {
    $('#side-menu').metisMenu();

    //目录样式设置选中样式
    //$.extend({
    //    /**
    //     *
    //     * @param firstIndex 一级目录位置
    //     * @param secondIndex 二级目录位置（可以不设置）
    //     */
    //    'setSideMenuChecked':function(firstIndex,secondIndex){
    //        firstIndex--;
    //        $("#side-menu>li:eq("+firstIndex+")").addClass("active");
    //        if(secondIndex && secondIndex>0){
    //            secondIndex--;
    //            $("#side-menu>li:eq("+firstIndex+") ul").addClass("in");
    //            $("#side-menu>li:eq("+firstIndex+") ul").attr("aria-expanded","true");
    //
    //            $("#side-menu>li:eq("+firstIndex+") li:eq("+secondIndex+") a").addClass("active");
    //        }else{
    //            $("#side-menu>li:eq("+firstIndex+") a:first" ).addClass("active");
    //        }
    //    }
    //});
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    //设置当前页面的目录选中效果
    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0 && url.href.length == this.href.length;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});
