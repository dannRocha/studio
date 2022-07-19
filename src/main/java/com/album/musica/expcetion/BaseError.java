package com.album.musica.expcetion;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseError {
    private final String message;
}
