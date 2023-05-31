package pl.wsb.programowaniejava.ProjektRestauracja.entity;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDto {
    public final String message;
}
