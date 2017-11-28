jQuery(function() {
    $.getJSON("/api/lugares", null, function(lugares,b) {
        var pagina = window.obtenerNombrePagina();
        switch (pagina) {
            case "tramos_1.html": procesarListaTramos(lugares); break
            case "tramos_2.html": {
                var id_tramo = window.obtenerId()
                if (id_tramo == null) {
                    procesarAgregarTramo(lugares)
                }
                else {
                    procesarEditarTramo(id_tramo, lugares)
                }
            }; break
            default: procesarError("Error: Pagina desconocida '" + pagina + "'")
        }
    }).fail(function(err, b) {
        procesarError("Error: " + err.statusText)
    })

    function procesarListaTramos(lugares) {
        $.getJSON("/api/tramos", null, function(tramos,b) {
            var tramosFormatteados = tramos.map(function (tramo) {
                return {
                    "id": tramo.id,
                    "origen": lugares.porId(tramo.origen).nombre,
                    "destino": lugares.porId(tramo.destino).nombre
                }
            })

            for(tramo of tramosFormatteados) {
                $("#tramos > tbody").append($("#tramos > tbody > .template").moldear(tramo))
            }
        }).fail(function(err, b) {
            procesarError("Error: " + err.statusText)
        })
    }

    function procesarEditarTramo(id, lugares) {
        $("#titulo-pagina").text("Editar tramo")

        $.getJSON("/api/tramos/" + id, null, function(tramo,b) {
            for(lugar of lugares) {
                var origenOpt = $("<option>").val(lugar.id).text(lugar.nombre)
                if (tramo.origen == lugar.id) {
                    origenOpt.attr("selected", "selected")
                }
                $("#origen").append(origenOpt)

                var destinoOpt = $("<option>").val(lugar.id).text(lugar.nombre)
                if (tramo.destino == lugar.id) {
                    destinoOpt.attr("selected", "selected")
                }
                $("#destino").append(destinoOpt)
            }

            $("#id").val(tramo.id)
            $("#costo").val(tramo.costo)
            $("#ganancia").val(tramo.ganancia)
        }).fail(function(err, b) {
            procesarError("Error: " + err.statusText)
        })
    }

    function procesarAgregarTramo(lugares) {
        $("#titulo-pagina").text("Nuevo tramo")

        for(lugar of lugares) {
            $("#origen").append($("<option>").val(lugar.id).text(lugar.nombre))
            $("#destino").append($("<option>").val(lugar.id).text(lugar.nombre))
        }
    }

    // Maneja el boton de Enviar en tramos_2.html

    $("#enviar").click(function() {
        data = {
            "origen": $("#origen").val(),
            "destino": $("#destino").val(),
            "costo": $("#costo").val(),
            "ganancia": $("#ganancia").val()
        }

        var id = parseInt($("#id").val())
        if (isNaN(id)) {
            $.ajax({ url: "/api/tramos", method: "PUT", data: data }).done(function() {
                window.location.href = "tramos_1.html"
            }).fail(function(err, b) {
                procesarError("Error: " + err.statusText)
            })
        }
        else {
            $.ajax({ url: "/api/tramos/" + id, method: "PATCH", data: data }).done(function() {
                window.location.href = "tramos_1.html"
            }).fail(function(err, b) {
                procesarError("Error: " + err.statusText)
            })
        }

        return false;
    })
})
