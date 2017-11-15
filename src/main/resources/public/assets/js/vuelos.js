jQuery(function() {

    $.getJSON("/api/lugares", null, function(a,b) {
        console.log(a, b)
    })

    $.getJSON("/api/tramos", null, function(a,b) {
        console.log(a, b)
    })
})