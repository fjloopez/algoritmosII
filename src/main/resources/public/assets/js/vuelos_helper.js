Array.prototype.porId = function(id) {
    var els = this.filter(function (el) { return el.id == id })
    if (els.length > 0) {
        return els[0]
    }
    else {
        return null
    }
}

$.prototype.moldear = function(datos) {
    var htmlMolde = $(this).get(0).outerHTML

    for(var el in datos) {
        htmlMolde = htmlMolde.split("{" + el + "}").join(datos[el])
    }

    return $(htmlMolde).removeClass("template")
}

window.obtenerId = function() {
    var id = window.location.href.match(/\?id=(.?)$/)
    if (id != null && !isNaN(parseInt(id[1]))) {
        return parseInt(id[1])
    }
    else {
        return null
    }
}

window.obtenerNombrePagina = function() {
    var match = window.location.href.match(/\/([^\/\?]+)(\?(.*?))?$/)
    if (match != null) {
        return match[1]
    }
    else {
        return null
    }
}

function procesarError(mensaje) {
    $("body").append($("<div />").addClass("error_general").text(mensaje))
}