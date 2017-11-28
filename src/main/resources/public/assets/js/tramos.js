jQuery(function() {
    $.getJSON("/api/lugares", null, function(lugares,b) {
        var pagina = window.obtenerNombrePagina();
        switch (pagina) {
            case "tramos_1.html": procesarTramos1(lugares); break
            case "tramos_2.html": {
                var id_tramo = window.obtenerId()
                if (id_tramo == null) {
                    procesarError("Error: falta ID de tramo")
                }
                else {
                    procesarTramos2(lugares)
                }
            }; break
            default: procesarError("Error: Pagina desconocida '" + pagina + "'")
        }
    })

    function procesarTramos1(lugares) {
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
        })
    }

    function procesarTramos2(lugares) {
        $.getJSON("/api/tramos", null, function(tramos,b) {
            var tramosFormatteados = tramos.map(function (tramo) {
                return {
                    "id": tramo.id,
                    "origen": lugares.porId(tramo.origen).nombre,
                    "destino": lugares.porId(tramo.destino).nombre
                }
            })

            console.log(window.obtenerId())
        })
    }
})
