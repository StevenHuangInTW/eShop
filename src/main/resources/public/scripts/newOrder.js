$("form").submit(function(event){
    event.preventDefault();

    var url = "../order";
    var name = $("#txtName").val();
    var price = $("#txtPrice").val();
    var qty = $("#txtQty").val();

    $.ajax({
        url : url,
        success : function(data) {
            if (data.requestHandleStatus == false) {
              alert("创建失败, " + data.message);
            } else {
              alert("成功创建, 订单编号 : " + data.data.id);
            }
        },

        error : function(data, textStatus, errorThrown) {
            alert("订单创建错误!");
        },
        type : "PUT",
//        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        contentType:"application/json;",
        dataType: "json",
        data : JSON.stringify({
            "name" : $.trim(name),
            "price" : price,
            "qty" : qty
        })

    });
    }
);
