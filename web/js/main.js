/**
 * Created by cattenlinger on 16/9/19.
 */
var LNCSA = {
    mainPage: {
        getListTopBoard: function (targetNode) {
            $.ajax({
                type: "GET",
                url: "/bulletin/single/main_page_top",
                dataType: "json",
                success: function (data) {
                    if (data) {

                        var content = '<div class="media-body">' + data.context + '</div>';

                        if (data.imageLink) {
                            content = '<a class="media-left" href="' + data.imageLink + '">' +
                                '<img src="' + data.imageLink + '" onload="DrawImage(this,90,90)">' +
                                '</a>' + content;
                        }

                        content = '<div class="media">' + content + '</div>';
                        
                        targetNode.html(content);
                        targetNode.parent().removeClass("hidden");
                        targetNode.parent().addClass("animated");
                    }
                },
                error: function (jqXHR) {

                }
            });
        }
    }
};