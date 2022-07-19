package com.album.musica.expcetion;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@JsonIgnoreType
@Getter
public class EntityNotFoundException extends RuntimeException {
    @JsonProperty
    private final String message;
}
