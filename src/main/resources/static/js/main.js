$(document).ready(function () {
    // when send the data from the form
    $("#unary_op_form").submit(function (event) {
        // prevent the default behaviour
        event.preventDefault();

        var requestUnary = {};
        requestUnary["firstMatrix"] = $("#unary_op_matrix").val();
        requestUnary["operation"] = $("#select_operation").val();

        ajax_submit(requestUnary);
    });

    $("#binary_op_form").submit(function (event) {
        // prevent the default behaviour
        event.preventDefault();

        var requestBinary = {};
        requestBinary["firstMatrix"] = $("#binary_op_matrix1").val();
        requestBinary["operation"] = $("#select_operation").val();
        requestBinary["secondMatrix"] = $("#binary_op_matrix2").val();

        ajax_submit(requestBinary);
    });
});

function ajax_submit(requestBody) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/operations",
        data: JSON.stringify(requestBody), // Jackson accept the string representation of the object!
        dataType: "json",
        cache: false,
        timeout: 600000,
        success: function (response) {
            var json_result = "<h4>Result<h4> <pre>" + response["result"] + "</pre>";
            $("#result").html(json_result); // add inside the document
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    });
}