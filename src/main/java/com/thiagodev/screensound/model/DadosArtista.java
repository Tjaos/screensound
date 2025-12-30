package com.thiagodev.screensound.model;

import java.util.List;

public record DadosArtista(
        Long id,
        String nome,
        TipoArtista tipoArtista
) {
}
