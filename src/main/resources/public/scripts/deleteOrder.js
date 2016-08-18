$("form").submit(function(event){
    event.preventDefault();

    var url = "../order";
    var orderId = $("#txtOrderId").val();

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
    }
);
