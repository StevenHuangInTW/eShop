$("form").submit(function(event){
    event.preventDefault();

    var url = "../order";
    var name = $("#txtName").val();
    var price = $("#txtPrice").val();
    var qty = $("#txtQty").val();

    $.ajax({
        url : url,
        success : function(data) {
            if (data.message) {
                alert("Order creation fail, " + data.message);
            } else {
              alert("Order create successful, order no : " + data.data.id);
            }
        },

        error : function(data, textStatus, errorThrown) {
            alert("Order creation fail, " + data.responseJSON.message);
        },
        type : "PUT",
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
