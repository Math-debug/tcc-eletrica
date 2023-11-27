package br.unip.tcc.entity.dto;

import lombok.Getter;

import java.util.EventObject;

@Getter
public class KeepAliveEvent extends EventObject {
    private final KeepAliveDTO keepAliveDTO;
    public KeepAliveEvent(KeepAliveDTO dto) {
        super(dto);
        this.keepAliveDTO = dto;
    }
}
