$("form").submit(function(event){
    event.preventDefault();

    var url = "../order";
    var orderId = $("#txtOrderId").val();

    $.ajax({
        url : url + "?orderId=" + orderId,
        success : function(data) {
            if (data.message) {
              alert("删除失败, " + data.message);
            } else {
              alert("成功删除。");
            }
        },

        error : function(data, textStatus, errorThrown) {
            alert("订单删除错误!");
        },
        type : "DELETE",
    });
    }
);
