$("form").submit(function(event){
    event.preventDefault();

    var orderId = $("#txtOrderId").val();
    if(confirm("Do want to delete the order of " + orderId)){
        var url = "http://localhost:8008/order-api  /order";

        $.ajax({
            url : url + "?orderId=" + orderId,
            success : function(data) {
                if (data.message) {
                  alert("Delete Fail, " + data.message);
                } else {
                  alert("Delete Successfully。");
                }
            },

            error : function(data, textStatus, errorThrown) {
                alert("Delete Fail, " + data.responseJSON.message);
            },
            type : "DELETE",
        });
        };
    }
);
